package classes;
// Door Martijn

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.io.IOException;

import java.util.concurrent.CountDownLatch;

import panels.PanelStatus;

public class Communication {
    private static SerialPort sp; // Globale Serial verbinding
    private static boolean hasComms; // Boolean die bijhoudt of er Serial verbinding is
    private static String receivedValue = "0"; // Waarde die wordt bijgewerkt op basis van Serial
    private static CountDownLatch latch; // Countdown die wacht tot Serial uitlezen is voltooid

    public Communication() {
//        Probeert verbinding te maken met de Arduino
        openComms();
    }

    // Methode die verbinding maakt met de Arduino
    public static void openComms() {
        if(GetEnv.getArduinoCommsPort() != null){
            // Zet de juiste parameters voor de Serial verbinding
            sp = SerialPort.getCommPort(GetEnv.getArduinoCommsPort());
            sp.setComPortParameters(9600, 8, 1, 0);
            sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

            // Probeert de Serial verbinding te beginnen
            if (sp.openPort()) {
                // Wacht twee seconden om de verbinding tot stand te brengen
                // Is nodig omdat anders te snel door gaat en al data probeert te verzenden
                // Terwijl de poort nog niet open is
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException ie){
                    System.out.println(Communication.class + " openComms: sleep error :" + ie);
                }

                // Probeert
                try{
                    // De commando 50 te versturen naar de Arduino
                    // 50 betekent: maak verbinding
                    String message = String.valueOf(50);
                    System.out.println(Communication.class + " openComms: sending: " +  message);
                    // Zet de String om naar een byte array
                    byte[] buffer = message.getBytes();

                    // Schrijft de byte buffer naar de Arduino
                    sp.getOutputStream().write(buffer);
                    sp.getOutputStream().flush();

                    // Leest de Serial comms uit
                    if(readComms() == 200){
                        // Zet hasFirstComms op true
                        hasComms = true;
                        Database.updateDatabase("INSERT INTO logbook (type, text) VALUES (?, ?)", new String[]{ "1", "Robot verbinding gemaakt."}); // in het logboek wordt opgeslagen dat er op Go gedrukt is (Joëlle)
                    }

                    // Update de status op het home scherm
                    PanelStatus.updateStatus();

                    // Print dat de comms open is
                    System.out.println(Communication.class + " openComms: Comms port is open");
                }catch (IOException ioe){
                    System.out.println(Communication.class + " openComms: Failed to open comms port");
                }
            } else {
                // Zet hasFirstComms op false omdat het niet is gelukt om verbinding te maken
                hasComms = false;
                // Print dat het niet is gelukt
                System.out.println(Communication.class + " openComms: Failed to open comms port");
            }
        }else{
            System.out.println(Communication.class + " openComms: env.json error");
        }
    }

    // Sluit Serial comms
    public static boolean closeComms(){
        try{
            // Als het is gelukt verbinding te sluiten
            if (sp.closePort()) {
                // Print dit en return true
                System.out.println(Communication.class + " closeComms: Port is closed :)");
                return true;
            } else {
                // Anders print een error en return false
                System.out.println(Communication.class + " closeComms: Failed to close port :(");
                return false;
            }
        }catch (NullPointerException npe){
            System.out.println(Communication.class + " closeComms: Port is closed :)");
            return true;
        }
    }

    // Methode die de waarde van hasFirstComms returnt
    public static boolean hasComms() {
        return hasComms;
    }

    // Verstuurd een status naar de Arduino
    public static void sendComms(int value) {
        if(hasComms()){
            // Probeert
            try{
                // Een int naar String om te zetten
                // Moet zodat de String omgezet kan worden naar bytes
                String message = String.valueOf(value);
                // Print de waarde die naar de Arduino verstuurd gaat worden
                System.out.println(Communication.class + " sendComms: sending " +  message);
                // Zet de Serial om naar een byte buffer
                byte[] buffer = message.getBytes();

                // Schrijft de byte buffer naar de Arduino
                sp.getOutputStream().write(buffer);
                sp.getOutputStream().flush();

            }catch (NumberFormatException | IOException exc){
                // Als er iets fout is gegaan in de int naar String of verzenden van de bytes
                // Print een error
                System.out.println(Communication.class + " sendComms comms error: " + exc);
            }
        }else{
            System.out.println(Communication.class + " sendComms: comms port not open, can't send message");
        }
    }

    // Leest de Serial communicatie uit
    public static void readSerialComms() {
        // Voegt een nieuwe dataListener toe aan de verbinding
        // Deze leest de Serial uit
        sp.addDataListener(new SerialPortDataListener() {
            @Override
            public void serialEvent(SerialPortEvent event) {
                // Wacht 250ms zodat de Arduino genoeg tijd heeft om alle bytes te verzenden
                // Zonder leest hij het niet altijd even goed uit
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Aantal bytes in de Serial buffer wordt gelezen
                int size = event.getSerialPort().bytesAvailable();
                // Maakt een nieuwe byte buffer aan van het formaat dat in de buffer zit
                byte[] buffer = new byte[size];

                // Leest de Serial data uit en stop dit in de buffer array
                int numBytesRead = 0;
                while (numBytesRead < size) {
                    numBytesRead += event.getSerialPort().readBytes(buffer, size - numBytesRead);
                }

                // Maakt een lege String aan die later wordt gevuld met de Serial data
                String value = "";

                // Voor elke byte in de buffer zet deze om naar een char en voeg hem toe aan de String value
                for(byte b:buffer) {
                    value += (char) b;
                }

                // Zet alle waardes in de static receivedValue
                // Doet een .trim() om eventuele enters en spaties weg te halen
                receivedValue = value.trim();
                // Hier wordt de CountDownLatch met één verlaagd. Dit betekent dat de await()-functie in de functie readComms() kan worden ontgrendeld.
                latch.countDown();
                // Zet de juiste robot status
                Robot.setRobotStatus(Integer.parseInt(receivedValue));
                // Update de panel
                PanelStatus.updateStatus();
                // Print de teruggestuurde waarde
                System.out.println(Communication.class + " readSerialComms: recived: " + value);
            }

            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }
        });
    }

    // Getter voor de static receivedValue
    public static int getReceivedValue() {
        int returnValue = 0;
        try{
            // Probeert de waarde om te zetten naar in int
            returnValue = Integer.parseInt(receivedValue);
        }catch (NumberFormatException nfe){
            System.out.println(Communication.class + ": getReceivedValue error: " + nfe);
        }

        // Returnt de waarde als een int
        return returnValue;
    }

    // Leest de Serial communicatie uit en zet de ontvangen waarde in receivedValue
    // Latch wordt gebruikt om de methode te blokkeren totdat er een waarde is ontvangen
    public static int readComms() {
        int returnValue = 0;
        try{
            // Roep de methode aan om de Serial communicatie uit te lezen
            readSerialComms();
            // Initialiseer de CountDownLatch met 1
            latch = new CountDownLatch(1);
            // Blokkeer de methode totdat de latch klaar is
            latch.await();
            // Haal de ontvangen waarde op uit receivedValue
            returnValue = getReceivedValue();
        } catch (InterruptedException e) {
            System.out.println(Communication.class + ": error " + e);
        }

        return returnValue;
    }
}

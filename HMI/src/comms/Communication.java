package comms;
// Door Martijn

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortIOException;
import javax.swing.*;
import java.io.IOException;

public class Communication extends JPanel {
    private static SerialPort sp; // Globale Serial verbinding
    private static boolean hasComms; // Boolean die bijhoudt of er Serial verbinding is

    public Communication() throws InterruptedException {
        //Probeert verbinding te maken met de Arduino
        openComms();
    }

    // Methode die verbinding maakt met de Arduino
    public static boolean openComms() throws InterruptedException {
        // Zet de juiste parameters voor de Serial verbinding
        sp = SerialPort.getCommPort("COM7");
        sp.setComPortParameters(9600, 8, 1, 0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        // Probeert de Serial verbinding te beginnen
        if (sp.openPort()) {
            // Wacht twee seconden om de verbinding tot stand te brengen
            // Is nodig omdat anders te snel door gaat en al data probeert te verzenden
            // Terwijl de poort nog niet open is
            Thread.sleep(2000);
            // Zet hasComms op true
            hasComms = true;

            // Print dat de comms open is
            System.out.println(Communication.class + ": Comms port is open");
            // Returnt true omdat het is gelukt
            // Wordt nu nog nergens gebruikt, maar kan wel
            return true;
        } else {
            // Zet hasComms op false omdat het niet is gelukt om verbinding te maken
            hasComms = false;
            // Print dat het niet is gelukt
            System.out.println(Communication.class + ": Failed to open comms port");
            // Returnt false omdat het niet is gelukt verbinding te maken
            return false;
        }
    }

    // Sluit Serial comms
    public static boolean closeComms(){
        // Als het is gelukt verbinding te sluiten
        if (sp.closePort()) {
            // Print dit en return true
            System.out.println(Communication.class + ": Port is closed :)");
            return true;
        } else {
            // Anders print een error en return false
            System.out.println(Communication.class + ": Failed to close port :(");
            return false;
        }
    }

    // Methode die de waarde van hasComms returnt
    public static boolean hasComms() {
        return hasComms;
    }

    // Verstuurd een status naar de Arduino
    public static boolean sendComms(int value) throws IOException {
        if(hasComms()){
            // Probeert
            try{
                // Een int naar String om te zetten
                // Moet zodat de String omgezet kan worden naar bytes
                String message = String.valueOf(value);
                // Print de waarde die naar de Arduino verstuurd gaat worden
                System.out.println(Communication.class + ": sendComms: " +  message);
                // Zet de Serial om naar een byte buffer
                byte[] buffer = message.getBytes();

                // Schrijft de byte buffer naar de Arduino
                sp.getOutputStream().write(buffer);
                sp.getOutputStream().flush();

                // Returnt true omdat het is gelukt
                // Doen we nu nog niks mee, maar zou wel kunnen
                return true;
            }catch (NumberFormatException | SerialPortIOException nfe){
                // Als er iets fout is gegaan in de int naar String of verzenden van de bytes
                // Print een error en returnt false
                System.out.println(Communication.class + ": comms error sendComms: " + nfe);
                return false;
            }
        }else{
            System.out.println(Communication.class + ": comms port not open, can't send message");
            return false;
        }
    }

    // Leest de Serial communicatie uit
    public int getComms(){
        // Voegt een nieuwe dataListener toe aan de verbinding
        // Deze leest de Serial uit
        sp.addDataListener(new SerialPortDataListener() {
            @Override
            public void serialEvent(SerialPortEvent event) {
                int size = event.getSerialPort().bytesAvailable();
                byte[] buffer = new byte[size];
                event.getSerialPort().readBytes(buffer, size);

                for(byte b:buffer) {
                    System.out.print((char) b);
                }
            }

            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }
        });

        return 0;
    }
}

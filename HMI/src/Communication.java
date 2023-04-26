import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.io.IOException;
import java.util.Arrays;

public class Communication {
    private SerialPort sp;

    // Maakt verbinding met de Arduino dmv Serial.
    public Communication(){
        SerialPort [] AvailablePorts = SerialPort.getCommPorts();
        sp = AvailablePorts[0];

        // Zoekt naar de commPort van de Arduino
//        sp = SerialPort.getCommPort("COM5"); //COM5 kan veranderen - verschillen per pc!
        sp.setComPortParameters(9600, 8, 1, 0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        // Opent de verbinding met de Arduino
        if (sp.openPort()) {
            System.out.println("Comms port is open :)");
        } else {
            System.out.println("Failed to open port :(");
        }
    }

    public int getComms() {
        final String[] status = {""};



        sp.addDataListener(
            new SerialPortDataListener() {
                @Override
                public void serialEvent(SerialPortEvent event) {
                    int size = event.getSerialPort().bytesAvailable();
                    byte[] buffer = new byte[size];
                    event.getSerialPort().readBytes(buffer, size);

                    for(byte b:buffer) {
                        System.out.print((char) b);
                        status[0] += b;
                    }
                }

                @Override
                public int getListeningEvents() {
                    return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                }
            }
        );

        return Integer.parseInt(status[0]);
    }

    public boolean sendComms(int status) throws IOException {
        try{
            String statusString = Integer.toString(status);
            byte[] buffer = statusString.getBytes();

            System.out.println(Arrays.toString(buffer));

            sp.getOutputStream().write(buffer);
            sp.getOutputStream().flush();

            return true;
        }catch (IOException ae){
            System.out.println("Comms failure");
            System.out.println("Recived " + status);
            System.out.println("Expected an int");
            return false;
        }
    }
}

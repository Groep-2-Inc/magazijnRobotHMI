package comms;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.io.IOException;

public class Commuinication {
    private SerialPort sp;
    private boolean hasComms;

    public Commuinication() throws InterruptedException {
        sp = SerialPort.getCommPort("COM7");
        sp.setComPortParameters(9600, 8, 1, 0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        if (sp.openPort()) {
            Thread.sleep(2000);
            hasComms = true;
            System.out.println(getClass() + ": Comms port is open");
        } else {
            System.out.println(getClass() + ": Failed to open comms port");
        }
    }

    public boolean closeComms(){
        if (sp.closePort()) {
            System.out.println(getClass() + ": Port is closed :)");
            return true;
        } else {
            System.out.println(getClass() + ": Failed to close port :(");
            return false;
        }
    }

    public boolean hasComms() {
        return hasComms;
    }

    public boolean sendComms(int value) throws IOException {
        try{
            String message = String.valueOf(value);
            System.out.println(message);
            byte[] buffer = message.getBytes();

            sp.getOutputStream().write(buffer);
            sp.getOutputStream().flush();

            return true;
        }catch (NumberFormatException nfe){
            System.out.println(getClass() + "Comms error: ");
            return false;
        }
    }

    public int getComms(){
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

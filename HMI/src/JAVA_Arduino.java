import java.io.IOException;
import java.util.Arrays;

import com.fazecast.jSerialComm.SerialPort;

public class JAVA_Arduino {
    public static void main(String[] args) throws IOException {
        SerialPort sp = SerialPort.getCommPort("COM5");
        sp.setComPortParameters(9600, 8, 1, 0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        if (sp.openPort()) {
            System.out.println("Port is open :)");
        } else {
            System.out.println("Failed to open port :(");
            return;
        }

        String message = "200";
        System.out.println(message);
        byte[] buffer = message.getBytes();

        System.out.println(Arrays.toString(buffer));

        sp.getOutputStream().write(buffer);
        sp.getOutputStream().flush();

        if (sp.closePort()) {
            System.out.println("Port is closed :)");
        } else {
            System.out.println("Failed to close port :(");
        }
    }
}

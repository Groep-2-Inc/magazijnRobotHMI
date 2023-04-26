import java.io.IOException;
import comms.Communication;
import frames.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        new FrameController();

        // Start de Serial communication
        Communication comms = new Communication();
        Communication.sendComms(200);

        comms.getComms();
    }
}
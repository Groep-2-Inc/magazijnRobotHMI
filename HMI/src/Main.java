import java.io.IOException;
import comms.Communication;
import env.GetEnv;
import frames.FrameController;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        new FrameController();
        new GetEnv();
        
        // Start de Serial communication
        Communication comms = new Communication();
        Communication.sendComms(200);

        comms.getComms();
    }
}

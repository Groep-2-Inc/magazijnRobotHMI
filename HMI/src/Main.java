import java.io.IOException;
import comms.Communication;
import frames.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Communication comms = new Communication();

        new FrameController(comms);

        comms.sendComms(200);

//        comms.getComms();
    }
}
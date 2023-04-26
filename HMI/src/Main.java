import java.io.IOException;
import comms.Commuinication;
import frames.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        new FrameController();

        Commuinication comms = new Commuinication();
//        comms.sendComms(200);

        comms.getComms();
    }
}
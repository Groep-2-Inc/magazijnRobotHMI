import comms.Communication;
import env.GetEnv;
import frames.FrameController;

public class Main {
    public static void main(String[] args) {
        new GetEnv();
        new FrameController();
        new Communication();

    }
}

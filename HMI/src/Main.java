import frames.FrameHome;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new FrameHome();

        Communication coms = new Communication();
        coms.sendComms(200);

        coms.getComms();
    }
}
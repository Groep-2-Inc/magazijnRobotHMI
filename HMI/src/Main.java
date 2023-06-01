import frames.FrameController;
import classes.Communication;
import classes.Database;
import classes.GetEnv;

public class Main {
    public static void main(String[] args) {
        new GetEnv();
        new Database();
        new FrameController();
        new Communication();
    }
}

package frames;

import javax.swing.*;
import java.awt.*;

public class FrameController {

    private static FrameHome jf_home = new FrameHome();
    private static FrameVerwerken jf_FrameVerwerken = new FrameVerwerken();

    public FrameController(){
        jf_home.setVisible(true);
    }

    public static void setActiveHome(JFrame f){
        jf_home.setVisible(true);
        f.setVisible(false);
    }

    public static void setActiveFrameVerwerken(JFrame f){
        if(jf_FrameVerwerken != f){
            jf_FrameVerwerken.setVisible(true);
            f.setVisible(false);
        }
    }

    public static FrameVerwerken getJf_FrameVerwerken() {
        return jf_FrameVerwerken;
    }
}

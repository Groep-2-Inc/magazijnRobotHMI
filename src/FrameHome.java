import javax.swing.*;
import java.awt.*;

public class FrameHome extends FrameHeader{

    public FrameHome(){
        JPanel p = new JPanel();
        setPreferredSize(new Dimension(1920, 1080));

        p.setLayout(new GridLayout(2,2));

        p.add(new PanelLogboek());
        p.add(new PanelStatus());
        p.add(new PanelOrderStatus());
        p.add(new PanelPositie());

        add(p);
        setVisible(true);
    }
}

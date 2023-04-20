import javax.swing.*;
import java.awt.*;

public class FrameHome extends FrameHeader{

    public FrameHome(){
        JPanel f = new JPanel();
        setPreferredSize(new Dimension(1920, 1080));
        f.setLayout(new GridLayout(2,2));

        f.add(new PanelPositie());
        f.add(new PanelStatus());
        f.add(new PanelOrderStatus());
        f.add(new PanelLogboek());

        add(f);
        setVisible(true);
    }
}

package panels;
// Door Jason Joshua van der Kolk
// Bijgewerkt met DB-data door Joëlle en Martijn

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import classes.Activity;
import frames.*;

public class PanelLogbook extends JPanel{
    JPanel table = tabel();
    JScrollPane scrollPane;
    public PanelLogbook(){
        //initializeer het hoofd paneel
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(FrameHeader.getScreenWidth(50f),FrameHeader.getScreenHeight(50f)));
        setBackground(new Color(236, 236, 236));
        setBorder(new LineBorder(Color.black, 1));

        //voeg nieuw paneel toe voor de titel
        JPanel titelPannel = new JPanel();
        titelPannel.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1920, 896)),FrameHeader.getScreenHeight(FrameHeader.getPercentage(1080, 40))));

        titelPannel.setBorder(new LineBorder(Color.BLACK));
        titelPannel.add(new Label("Logboek"));
        titelPannel.setBackground(Color.lightGray);
        titelPannel.setFont(new Font("Arial", Font.BOLD, 14));
        add(titelPannel);

        // Voegt de scrollpane toe
        scrollPane();
    }

    // Maakt de tabel en returnt hem
    public JPanel tabel(){
        Activity.getLogbookData(10);

        //nieuw paneel voor alle informatie
        JPanel table = new JPanel();
        table.setLayout(new FlowLayout());
        table.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1920, 896)), 46 * Activity.getActivities().size())); //hoogte geschaald met hoeveelheid(Joëlle)

        //voegt panels toen, met de gegevens van de database daarin (Joëlle)
        for(int i = 0; i < Math.min(Activity.getActivities().size(), 10); i++){
            JPanel tableRow = new JPanel();
            tableRow.setLayout(new GridLayout(1, 1));
            tableRow.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1920, 896)), 40));
            tableRow.setBorder(new LineBorder(Color.BLACK));

            tableRow.add(new JLabel(Activity.getActivities().get(i).getDate() + " #" + Activity.getActivities().get(i).getId() + " type: " + Activity.getActivities().get(i).getTypeText() + " - " + Activity.getActivities().get(i).getActivityDescription()));
            table.add(tableRow);
        }

        return table;
    }

    // Maakt het paneel
    public void scrollPane(){
        //maar het scrollpane en voeg uiteindelijk het scrollpane toe.
        scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        scrollPane.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1920, 896)),FrameHeader.getScreenHeight(FrameHeader.getPercentage(1080, 358))));
        add(scrollPane);
    }

    // Updates the panel
    public void updatePanel(){
        super.remove(table);
        super.remove(scrollPane);
        table = tabel();
        scrollPane();
    }
}

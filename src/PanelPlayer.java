import javax.swing.*;
import java.awt.*;
public class PanelPlayer extends JPanel{
    public PanelPlayer(){
        JScrollPane panel = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.setMinimumSize(new Dimension(1920,1080));
        panel.setBackground(Color.blue);
        this.add(panel);

    }
}

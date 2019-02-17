import javax.swing.*;
import java.awt.*;
public class PanelPlayer extends JPanel{
    public PanelPlayer(){
        this.setMinimumSize(new Dimension(1920,1080));
        JScrollPane panel = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(panel);

    }
}

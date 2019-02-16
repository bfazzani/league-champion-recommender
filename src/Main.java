import javax.swing.*;
import java.awt.*;

public class Main {
    public Main()
    {
        JFrame frame = new JFrame();
        frame.setMinimumSize(new Dimension(640,480));
        frame.setMaximumSize(new Dimension(640,480));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public static void main(String[] args)
    {
        new Main();
    }
}

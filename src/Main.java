import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class Main {
    public Main()
    {
        JFrame frame = new JFrame();
        frame.setMinimumSize(new Dimension(640,480));
        frame.setMaximumSize(new Dimension(640,480));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        frame.add(panel);
        panel.add(new JScrollPane(new PanelPlayer()));
        panel.add(new JScrollPane(new PanelRec()));
    }
    public static void main(String[] args)
    {
        ChampionList cList = new ChampionList("champData.txt");
        cList.readData();
        System.out.println(Arrays.toString(cList.standardDeviation()));
        System.out.println(cList);
        //new Main();

        cList.test();
    }
}
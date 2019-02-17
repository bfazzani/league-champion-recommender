import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

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
        ChampionList cList = new ChampionList("champData.txt");
        cList.readData();
        System.out.println(Arrays.toString(cList.standardDeviation()));
        System.out.println(cList);
        //new Main();

    }
}

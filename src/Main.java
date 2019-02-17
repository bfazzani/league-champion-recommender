import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteries;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

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
        Orianna.setRiotAPIKey("RGAPI-d8744ef4-37fa-4d60-8284-3c17649618fc");
        Orianna.setDefaultRegion(Region.NORTH_AMERICA);

        Summoner summoner = Orianna.summonerNamed("DuyvuKnight11").get();
        System.out.println(summoner.getName() + " is level " + summoner.getLevel() + " on the " + summoner.getRegion() + " server.");

        final ChampionMasteries cms = summoner.getChampionMasteries();
        //System.out.println(cms);
        String[] champions = new String[5];
        for(int i = 0; i < 5 ; i++){
            champions[i] = cms.get(i).getChampion().getName();
        }
        for(String c : champions){
            System.out.println(c);
        }

        ChampionList cList = new ChampionList("champData.txt");
        cList.readData();
        System.out.println(Arrays.toString(cList.standardDeviation()));
        //System.out.println(cList);
        //new Main();

        cList.test();
        query(cList);
    }

    public static void query(ChampionList list) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter champion name: ");
        String champion = in.nextLine();

        System.out.println(Arrays.toString(list.getMatches(champion, 5)));
    }
}
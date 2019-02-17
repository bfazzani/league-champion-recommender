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

        ChampionList cList = new ChampionList("champData.txt");
        cList.readData();
        //System.out.println(Arrays.toString(cList.standardDeviation()));
        //System.out.println(cList);
        //new Main();

        //cList.test();
        //querySummoner(cList);

        System.out.println("League of Legends Champion Recommender");

        Scanner in = new Scanner(System.in);
        String input;
        do {
            System.out.print("Look for similar [C]hampions, search a [S]ummoner, or [E]xit\n> ");
            input = in.nextLine().toLowerCase();

            if (input.equals("c")) {
                queryChampion(cList);
            } else if (input.equals("s")) {
                querySummoner(cList);
            } else if (input.equals("all")) {
                cList.test();
            }
        } while (!input.equals("e"));
    }

    public static void querySummoner(ChampionList list) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter summoner name: ");
        String summonerName = in.nextLine();

        Summoner summoner = Orianna.summonerNamed(summonerName).get();
        final ChampionMasteries cms;
        try {
            cms = summoner.getChampionMasteries();
            if (cms.get(0).getChampion().getName() == null) {
                System.err.println("API Error: Null Champion Mastery");
                return;
            }
        } catch (Exception e) {
            System.err.println(e);
            return;
        }

        //System.out.println(cms);
        String[] champions = new String[5];
        int[] masteryScores = new int[5];
        int masterySum = 0;
        for(int i = 0; i < 5 ; i++){
            champions[i] = cms.get(i).getChampion().getName();
            masteryScores[i] = cms.get(i).getPoints();
            masterySum += masteryScores[i];
        }
        System.out.println("\nMost Played Champions:");
        for (int i=0;i<5;i++) {
            System.out.println(champions[i] + ": " + masteryScores[i] + " points");
        }

        //double[]

        double[] stats = new double[7];
        for (int i=0;i<5;i++) {
            ChampionData c = list.get(champions[i]);
            for (int j=0;j<stats.length;j++) {
                stats[j] += c.stats[j]*masteryScores[i]/masterySum;
            }
        }

        /**for (int i=0;i<stats.length;i++) {
            stats[i] /= 5.0;
        }*/

        ChampionData user = new ChampionData(summonerName, stats);
        ChampionData[] suggestions = list.getMatches(user, 10);
        ChampionData[] real = new ChampionData[5];
        int index = 0;
        for (int i=0;i<suggestions.length;i++) {
            boolean found = false;
            for (int j=0;j<champions.length;j++) {
                if (champions[j].equals(suggestions[i].name)) {
                    found = true;
                    break;
                }
            }

            if (found) continue;

            real[index++] = suggestions[i];
            if (index == 5) break;
        }
        System.out.println("\nRecommendations:");
        System.out.println(Arrays.toString(real));
        System.out.println();
    }

    public static void queryChampion(ChampionList list) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter champion name: ");
        String champion = in.nextLine();

        System.out.println("\nRecommendations:");
        System.out.println(Arrays.toString(list.getMatches(champion, 5)));
        System.out.println();
    }
}
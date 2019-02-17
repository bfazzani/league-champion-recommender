import java.io.*;
import java.util.*;

public class ChampionList {

    private String fileName;
    private List<ChampionData> champions;

    public ChampionList(String fileName) {
        this.fileName = fileName;
        champions = new ArrayList<>();
    }

    public ChampionData get(String champion) {
        ChampionData c = new ChampionData(champion);
        return champions.get(champions.indexOf(c));
    }

    public ChampionData[] getMatches(String champion, int matchNum) {
        TreeMap<Double, ChampionData> close = new TreeMap<>();
        double[] stdev = standardDeviation();

        ChampionData c = new ChampionData(champion);
        int index = champions.indexOf(c);
        if (index == -1)  {
            System.err.println("ChampionData Not Found");
            return null;
        }

        ChampionData c1 = champions.get(index);
        return getMatches(c1, matchNum);
    }

    public ChampionData[] getMatches(ChampionData c1, int matchNum) {
        TreeMap<Double, ChampionData> close = new TreeMap<>();
        double[] stdev = standardDeviation();

        for (int i=0;i<champions.size();i++) {
            ChampionData c2 = champions.get(i);
            close.put(c1.compareTo(c2, stdev), c2);
        }

        ChampionData[] ret = new ChampionData[matchNum];
        for (int i=0;i<ret.length;i++) {
            ret[i] = close.pollFirstEntry().getValue();
        }

        return ret;
    }

    public void test() {
        //take reciprocal, divide 2.5, minus 0.04
        TreeMap<Double, Pair> close = new TreeMap<>();
        double[] stdev = standardDeviation();

        for (int i=0;i<champions.size();i++) {
            for (int j=i+1;j<champions.size();j++) {
                ChampionData c1 = champions.get(i);
                ChampionData c2 = champions.get(j);
                close.put(c1.compareTo(c2, stdev), new Pair(c1, c2));
            }
        }

        System.out.println(close);
    }

    public double[] standardDeviation() {
        double[] stdev = new double[7];
        double[] means = new double[7];

        for (int i=0;i<7;i++) {
            double sum = 0;
            for (ChampionData c : champions) {
                sum += c.stats[i];
            }
            means[i] = sum / champions.size();
        }
        for (int i=0;i<7;i++) {
            double sum = 0;
            for (ChampionData c : champions) {
                sum += Math.pow(means[i]-c.stats[i], 2);
            }
            sum /= champions.size()-1;
            stdev[i] = Math.sqrt(sum);
        }

        return stdev;
    }

    public void readData() {
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = in.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line);
                ChampionData c = new ChampionData(st.nextToken().replaceAll("_", " "), Integer.parseInt(st.nextToken()),
                        Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()),
                        Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()));
                champions.add(c);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println(champions.size());
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i=0;i<champions.size();i++) {
            ret.append(champions.get(i));
            if (i != champions.size()-1) {
                ret.append("\n");
            }
        }
        return ret.toString();
    }
}

class Pair {
    ChampionData first;
    ChampionData second;

    public Pair(ChampionData first, ChampionData second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return first + ", " + second;
    }
}
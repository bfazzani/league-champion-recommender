import java.io.*;
import java.util.*;

public class ChampionList {

    private String fileName;
    private List<Champion> champions;

    public ChampionList(String fileName) {
        this.fileName = fileName;
        champions = new ArrayList<>();
    }

    public Champion[] getMatches(String champion, int matchNum) {
        TreeMap<Double, Champion> close = new TreeMap<>();
        double[] stdev = standardDeviation();

        Champion c = new Champion(champion);
        int index = champions.indexOf(c);
        if (index == -1)  {
            System.err.println("Champion Not Found");
            return null;
        }

        Champion c1 = champions.get(index);
        return getMatches(c1, matchNum);
    }

    public Champion[] getMatches(Champion c1, int matchNum) {
        TreeMap<Double, Champion> close = new TreeMap<>();
        double[] stdev = standardDeviation();

        for (int i=0;i<champions.size();i++) {
            Champion c2 = champions.get(i);
            close.put(c1.compareTo(c2, stdev), c2);
        }

        Champion[] ret = new Champion[matchNum];
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
                Champion c1 = champions.get(i);
                Champion c2 = champions.get(j);
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
            for (Champion c : champions) {
                sum += c.stats[i];
            }
            means[i] = sum / champions.size();
        }
        for (int i=0;i<7;i++) {
            double sum = 0;
            for (Champion c : champions) {
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
                Champion c = new Champion(st.nextToken().replaceAll("_", " "), Integer.parseInt(st.nextToken()),
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
    Champion first;
    Champion second;

    public Pair(Champion first, Champion second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return first + ", " + second;
    }
}
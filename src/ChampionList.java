import java.io.*;
import java.util.*;

public class ChampionList {

    private String fileName;
    private List<Champion> champions;

    public ChampionList(String fileName) {
        this.fileName = fileName;
        champions = new ArrayList<>();
    }

    public void test() {
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
            while (true) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                if (st.hasMoreTokens()) {
                    Champion c = new Champion(st.nextToken().replaceAll("_", " "), Integer.parseInt(st.nextToken()),
                            Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()),
                            Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()));
                    champions.add(c);
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
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
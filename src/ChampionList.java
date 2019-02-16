import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChampionList {

    private String fileName;
    private List<Champion> champions;

    public ChampionList(String fileName) {
        this.fileName = fileName;
        champions = new ArrayList<>();
    }

    public void readData() {
        try (Scanner in = new Scanner(new File(fileName))) {
            while (in.hasNext()) {
                Champion c = new Champion(in.next().replaceAll("_", " "), in.nextInt(), in.nextDouble(), in.nextDouble(), in.nextDouble(),
                        in.nextInt(), in.nextInt(), in.nextDouble());
                System.out.println(c);
                champions.add(c);
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

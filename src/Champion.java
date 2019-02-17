public class Champion {

    private String name;
    double[] stats; // gold, kills, deaths, assists, damageDealt, damageTaken, creepScore

    public Champion(String name) {
        this.name = name;
    }

    public Champion(String name, int gold, double kills, double deaths, double assists, int damageDealt, int damageTaken, double creepScore) {
        this.name = name;
        stats = new double[7];
        stats[0] = gold;
        stats[1] = kills;
        stats[2] = deaths;
        stats[3] = assists;
        stats[4] = damageDealt;
        stats[5] = damageTaken;
        stats[6] = creepScore;
    }

    public double compareTo(Champion c, double[] stdev) {
        double sum = 0;
        for (int i=0;i<stats.length;i++) {
            sum += Math.pow((stats[i]-c.stats[i]) / stdev[i], 2);
        }
        return Math.sqrt(sum);
    }

    @Override
    public boolean equals(Object o) {
        Champion c = (Champion) o;
        return this.name.equals(c.name);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(name);
        /*ret.append(" ");
        for (int i=0;i<stats.length;i++) {
            ret.append(stats[i]);
            if (i != stats.length-1) {
                ret.append(" ");
            }
        }*/
        return ret.toString();
    }
}
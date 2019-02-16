public class Champion {

    private String name;
    private int gold;
    private double kills;
    private double deaths;
    private double assists;
    private int damageDealt;
    private int damageTaken;
    private int creepScore;

    public Champion(String name, int gold, double kills, double deaths, double assists, int damageDealt, int damageTaken, int creepScore) {
        this.name = name;
        this.gold = gold;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.damageDealt = damageDealt;
        this.damageTaken = damageTaken;
        this.creepScore = creepScore;
    }
}

public class TennisPlayer {

    // Data
    private String name;
    private double energy;

    // Shot Info
    private int placement;
    private char type;
    private int power;

    // Match scoreboard
    private int gamesWon;

    // Constructor
    public TennisPlayer(String pName, double pEnergy, int pPlacementShot, char pTypeShot, int pPowerShot) {
        name = pName;
        energy = 100.0;
        gamesWon = 0;
        placement = pPlacementShot;
        type = pTypeShot;
        power = pPowerShot;
    }

    // Getter function for other classes
    public double getEnergy() {
        return energy;
    }

    public String getName() {
        return name;
    }

    public char getType() {
        return type;
    }
    public int getPlacement() {
        return placement;
    }
    public int getPower() {
        return power;
    }

    public int getGamesWon() {
        return gamesWon; }
    
    // Setter function for other classes
    public void drainEnergy () {
        double deduction = 5.0 + (power * 0.1);
        energy -= deduction;
    }

    public void setEnergy(double addition) {
        energy += addition;
    }
    public void addGamesWon () {
        gamesWon ++;
    }

    public void resetGamesWon () {
        gamesWon = 0;
    }
    public static void main(String[] args) {
        TennisPlayer player = new TennisPlayer("Federer", 100.0, 2, 't', 80);
        
        System.out.println("Player Name: " + player.getName());
        System.out.println("Starting Energy: " + player.getEnergy());
        
        player.drainEnergy();
        System.out.println("Energy after 80-Power Shot: " + player.getEnergy());
        
        player.addGamesWon();
        System.out.println("Games Won: " + player.getGamesWon() + " (Expected: 1)");
    }
}


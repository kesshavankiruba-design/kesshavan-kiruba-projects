import java.util.Random;

public class TennisPhysics {
    private double chance;
    private Random random;

    public TennisPhysics() {
        random = new Random();
    }

    // Returns true or false if the ball went in    
    public boolean ballIsIn(double chance) { 
        if (random.nextDouble() * 100.0 < chance) {
            return true;
        }
        return false;
    }

    // Returns the chance
    public double getChance() { 
        return chance;
    }

    // Helper function for random ranges
    public double randomRange(double min, double max) { 
        return random.nextDouble() * (max - min) + min;
    }

    // Calculates chance based on previous shot
    public double prevShot(TennisPlayer curPlayer, TennisPlayer prevPlayer) {
        double prevChance = 0;

        if (prevPlayer.getType() == 'd') { // Dropshot is (-10, 5)
            prevChance += randomRange(-10.0,5.0);
        } else { // Topspin is (-5, 10)
            prevChance += randomRange(-5.0, 10.0);
        }

        if (prevPlayer.getPlacement() == 1 || prevPlayer.getPlacement() == 4) { // Edges is (-10, 5)
            prevChance += randomRange(-10.0, 5.0);
        } else { // Middle is (-5, 10)
            prevChance += randomRange(-5.0, 10.0);
        }

        // Formula for power
        prevChance += (50 - prevPlayer.getPower()) * 0.3;
        return prevChance;
    }

    // Calculates chance based on current shot
    public double curShot(TennisPlayer curPlayer, TennisPlayer prevPlayer) {
        double curChance = 0;

        if (curPlayer.getType() == 'd') { // Dropshot is (-10, 5)
            curChance += randomRange(-10.0,5.0);
        } else { // Topspin is (-5, 10)
            curChance += randomRange(-5.0, 10.0);
        }

        if (curPlayer.getPlacement() == 1 || curPlayer.getPlacement() == 4) { // Edges is (-10, 5)
            curChance += randomRange(-10.0, 5.0);
        } else { // Middle is (-5, 10)
            curChance += randomRange(-5.0, 10.0);
        }

        if (prevPlayer.getPlacement() == 1 || prevPlayer.getPlacement() == 2) { // Forehand is (-5, 10)
            curChance += randomRange(-5.0, 10.0);
        } else {
            curChance += randomRange(-10.0, 5.0);
        }

        // Formula for power
        curChance += (50 - curPlayer.getPower()) * 0.4;
        return curChance;
    }

    // Calculates total chance by combining both curShot and prevShot functions
    public double finalChance(TennisPlayer curPlayer, TennisPlayer prevPlayer) {
        chance = 100.0;

        // First calculate prevShot
        chance += prevShot(curPlayer, prevPlayer);

        // Then calculate curShot
        chance += curShot(curPlayer, prevPlayer);

        // After calculate chance based on how tired you are (energy)
        chance = chance * (0.5 + (0.3 * (curPlayer.getEnergy() / 100.0)));

        // Finally make boundary so chance cant go below 5%
        if (chance < 5.0) {
            chance = 5.0;
        }

        return chance;
    }

    public static void main(String[] args) {
        TennisPhysics physics = new TennisPhysics();
        TennisPlayer player1 = new TennisPlayer("Richard", 100, 3, 't', 60);
        TennisPlayer player2 = new TennisPlayer("Johanson", 100, 4, 'd', 100);

        double chanceBall = physics.finalChance(player2, player1);
        System.out.println("Chance of Johanson making shot: " + chanceBall);
        System.out.println("Does he make it in?");

        if (physics.ballIsIn(chanceBall)) {
            System.out.println("Yes! Yay :)");
        } else {
            System.out.println("No :(");
        }
    }
}

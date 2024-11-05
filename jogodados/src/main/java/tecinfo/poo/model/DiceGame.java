package tecinfo.poo.model;
import java.util.Random;

public class DiceGame {
    private Random random;
    
    public DiceGame() {
        this.random = new Random();
    }
    
    public int[] rollDice() {
        int[] results = new int[2];
        results[0] = random.nextInt(6) + 1;
        results[1] = random.nextInt(6) + 1;
        return results;
    }
}
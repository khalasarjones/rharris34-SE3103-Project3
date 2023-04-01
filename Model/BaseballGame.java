package Model;

import java.util.Random; 

public class BaseballGame {
    private int[] key = new int[3]; // GameKey
    private int[] guess = new int[3];
    private int ballCount = 0;
    private int strikeCount = 0;

    public BaseballGame() {
        generateKey();
    }

    public BaseballGame(int k0, int k1, int k2) {
        key[0] = k0;
        key[1] = k1;
        key[2] = k2; 
    }

    public void setGuess(int pos, int value) {
        assert 0 <= pos && pos < 3 : "setGuess out of range in position";
        guess[pos] = value;
    }

    public void computeBallsStrikes() {
        strikeCount = 0;
        for (int i = 0; i < 3; i++) {
            if (key[i] == guess[i]) {
                ++strikeCount;
            }
        }

        ballCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int n = 0; n < 3; n++) {
                if (i == n) continue;
                if (key[i] == guess[n]) {
                    ballCount++;
                }
            }
        }
    }

    public int getBallCount() {
        return ballCount;
    }

    public int getStrikeCount() {
        return strikeCount; 
    }

    private void generateKey() {         // repeats 0-9 for 3 digits with no repeating digits
        Random r = new Random(); 
        key[0] = r.nextInt(10);

        do {
            key[1] = r.nextInt(10);

        } while (key[1] == key[0]);

        do {
            key[2] = r.nextInt(10);

        } while (key[2] == key[1] || key[2] == key[0]);

    }

    public int[] getKey() {
        return key; 
    }
}

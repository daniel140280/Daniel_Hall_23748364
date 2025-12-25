package uk.ac.mmu.game.dice;

import java.util.Random;

/**
 * RandomSingleDiceShaker implements the DiceShaker interface.
 * The shake method is overridden with a random dice throw for one die.
 */
public class RandomSingleDiceShaker implements DiceShaker {
    private final Random random = new Random();

    @Override
    public int shake() {
        //generate random number between 1 and 6
        return random.nextInt(6)+1;
    }
}

package uk.ac.mmu.game.dice;

/**
 * RandomDoubleDiceShaker implements the DiceShaker interface.
 * It instantiates an instance (shaker) as type DiceShaker, but using the RandomSingleDiceShaker to access the random die throw.
 */
public class RandomDoubleDiceShaker implements DiceShaker {
    private final DiceShaker shaker = new RandomSingleDiceShaker();

    @Override
    public int shake() {
        //generate number between 2 and 12
        return shaker.shake() + shaker.shake();
    }
}

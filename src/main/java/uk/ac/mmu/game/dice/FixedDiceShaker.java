package uk.ac.mmu.game.dice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Fixed dice shaker for fixed scenario testing and avoidance of random dice.
 * Returns predetermined sequence of rolls.
 */
public class FixedDiceShaker implements DiceShaker {
    private final Queue<Integer> rolls;
    private boolean repeatLast = false;
    private Integer lastRoll = 1;

    public FixedDiceShaker(Integer... rolls) {
        this.rolls = new LinkedList<>(Arrays.asList(rolls));
    }

    /**
     * When enabled, repeats the last roll indefinitely after queue is empty.
     * Useful for long-running games where you don't want to pre-calculate all rolls.
     */
    public FixedDiceShaker withRepeatLast() {
        this.repeatLast = true;
        return this;
    }

    @Override
    public int shake() {
        if (rolls.isEmpty()) {
            if (repeatLast) {
                return lastRoll;
            }
            throw new IllegalStateException("No more predetermined rolls available");
        }
        lastRoll = rolls.poll();
        return lastRoll;
    }
}
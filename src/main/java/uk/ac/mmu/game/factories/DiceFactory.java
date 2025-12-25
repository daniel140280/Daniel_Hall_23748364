package uk.ac.mmu.game.factories;

import uk.ac.mmu.game.dice.DiceShaker;
import uk.ac.mmu.game.gameconfig.DiceOption;

/*
Dice factory abstraction utilises the configuration enums to determine the concrete instantiation.
 */
public interface DiceFactory {
    DiceShaker createDice(DiceOption option);           // ONE or TWO dice
}

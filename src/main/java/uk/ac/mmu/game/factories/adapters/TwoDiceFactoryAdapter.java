package uk.ac.mmu.game.factories.adapters;

import uk.ac.mmu.game.dice.DiceShaker;
import uk.ac.mmu.game.dice.RandomDoubleDiceShaker;
import uk.ac.mmu.game.factories.DiceFactory;
import uk.ac.mmu.game.gameconfig.DiceOption;

public class TwoDiceFactoryAdapter implements DiceFactory {
    @Override
    public DiceShaker createDice(DiceOption option) {
        return new RandomDoubleDiceShaker();
    }

}

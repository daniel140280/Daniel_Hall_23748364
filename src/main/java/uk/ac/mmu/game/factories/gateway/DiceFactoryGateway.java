package uk.ac.mmu.game.factories.gateway;

import uk.ac.mmu.game.dice.DiceShaker;
import uk.ac.mmu.game.factories.DiceFactory;
import uk.ac.mmu.game.factories.adapters.OneDiceFactoryAdapter;
import uk.ac.mmu.game.factories.adapters.TwoDiceFactoryAdapter;
import uk.ac.mmu.game.gameconfig.DiceOption;

import java.util.HashMap;
import java.util.Map;


/*
Gateway + Adapter Implementations
Instead of if statements, each factory option has its own adapter.
The gateway dispatches to the right adapter.
 */
public class DiceFactoryGateway {
    private static final Map<DiceOption, DiceFactory> registry = new HashMap<>();

    static {
        registry.put(DiceOption.ONE, new OneDiceFactoryAdapter());
        registry.put(DiceOption.TWO, new TwoDiceFactoryAdapter());
    }

    public static DiceShaker createDice(DiceOption option) {
        DiceFactory factory = registry.get(option);
        if (factory == null) throw new IllegalArgumentException("Unsupported DiceOption: " + option);
        return factory.createDice(option);
    }

}

package uk.ac.mmu.game.factories.gateway;

import uk.ac.mmu.game.board.GameBoard;
import uk.ac.mmu.game.factories.EndFactory;
import uk.ac.mmu.game.factories.adapters.ExactEndFactoryAdapter;
import uk.ac.mmu.game.factories.adapters.OvershootEndFactoryAdapter;
import uk.ac.mmu.game.gameconfig.EndOption;
import uk.ac.mmu.game.gamestrategies.EndStrategy;

import java.util.HashMap;
import java.util.Map;

public class EndFactoryGateway {
    private static final Map<EndOption, EndFactory> endRegistry = new HashMap<>();

    static {
        endRegistry.put(EndOption.EXACT, new ExactEndFactoryAdapter());
        endRegistry.put(EndOption.OVERSHOOT_ALLOWED, new OvershootEndFactoryAdapter());
    }

    public static EndStrategy createEndStrategy(GameBoard board, EndOption option) {
        EndFactory factory = endRegistry.get(option);
        if (factory == null){
            throw new IllegalArgumentException("Unsupported EndOption: " + option);
        }
        return factory.createEndStrategy(board, option);
    }
}

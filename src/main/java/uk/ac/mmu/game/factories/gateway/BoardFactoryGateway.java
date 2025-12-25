package uk.ac.mmu.game.factories.gateway;

import uk.ac.mmu.game.board.GameBoard;
import uk.ac.mmu.game.factories.BoardFactory;
import uk.ac.mmu.game.factories.adapters.LargeGameBoardFactoryAdapter;
import uk.ac.mmu.game.factories.adapters.SmallBoardFactoryAdapter;
import uk.ac.mmu.game.gameconfig.BoardOption;

import java.util.HashMap;
import java.util.Map;

/*
Gateway + Adapter Implementations
Instead of if statements, each factory option has its own adapter.
The gateway dispatches to the right adapter.
 */
public class BoardFactoryGateway {
    private static final Map<BoardOption, BoardFactory> registry = new HashMap<>();

    static {
        registry.put(BoardOption.SMALL, new SmallBoardFactoryAdapter());
        registry.put(BoardOption.LARGE, new LargeGameBoardFactoryAdapter());
    }

    public static GameBoard createBoard(BoardOption option) {
        BoardFactory factory = registry.get(option);
        if (factory == null){
            throw new IllegalArgumentException("Unsupported BoardOption: " + option);
        }
        return factory.createBoard(option);
    }

}

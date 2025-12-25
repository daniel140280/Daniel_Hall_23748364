package uk.ac.mmu.game.factories.adapters;

import uk.ac.mmu.game.board.GameBoard;
import uk.ac.mmu.game.factories.EndFactory;
import uk.ac.mmu.game.gameconfig.EndOption;
import uk.ac.mmu.game.gamestrategies.EndStrategy;
import uk.ac.mmu.game.gamestrategies.endimplementations.OvershootAllowedStrategy;

public class OvershootEndFactoryAdapter implements EndFactory {
    @Override
    public EndStrategy createEndStrategy(GameBoard board, EndOption option) {
        return new OvershootAllowedStrategy(board);
    }

}

package uk.ac.mmu.game.factories.adapters;

import uk.ac.mmu.game.board.GameBoard;
import uk.ac.mmu.game.board.LargeGameBoard;
import uk.ac.mmu.game.factories.BoardFactory;
import uk.ac.mmu.game.gameconfig.BoardOption;

public class LargeGameBoardFactoryAdapter implements BoardFactory {
    @Override
    public GameBoard createBoard(BoardOption option) {
        return new LargeGameBoard();
    }
}

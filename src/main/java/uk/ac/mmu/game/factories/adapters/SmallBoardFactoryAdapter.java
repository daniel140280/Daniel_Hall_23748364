package uk.ac.mmu.game.factories.adapters;

import uk.ac.mmu.game.board.GameBoard;
import uk.ac.mmu.game.board.SmallGameBoard;
import uk.ac.mmu.game.factories.BoardFactory;
import uk.ac.mmu.game.gameconfig.BoardOption;

public class SmallBoardFactoryAdapter implements BoardFactory {
    @Override
    public GameBoard createBoard(BoardOption option) {
        return new SmallGameBoard();
    }

}

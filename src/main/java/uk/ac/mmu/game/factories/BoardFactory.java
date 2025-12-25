package uk.ac.mmu.game.factories;

import uk.ac.mmu.game.board.GameBoard;
import uk.ac.mmu.game.gameconfig.BoardOption;

/*
Board factory abstraction utilises the configuration enums to determine the concrete instantiation.
 */
public interface BoardFactory {
    GameBoard createBoard(BoardOption option);          // SMALL or LARGE board

}

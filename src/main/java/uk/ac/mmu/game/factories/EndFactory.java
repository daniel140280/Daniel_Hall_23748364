package uk.ac.mmu.game.factories;

import uk.ac.mmu.game.board.GameBoard;
import uk.ac.mmu.game.gameconfig.EndOption;
import uk.ac.mmu.game.gamestrategies.EndStrategy;

/*
Strategy factory abstraction utilises the configuration enums to determine the concrete instantiation.
 */
public interface EndFactory {
    EndStrategy createEndStrategy(GameBoard board, EndOption option);   // EXACT or OVERSHOOT_ALLOWED

}
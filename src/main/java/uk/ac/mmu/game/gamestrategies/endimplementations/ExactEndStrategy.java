package uk.ac.mmu.game.gamestrategies.endimplementations;

import uk.ac.mmu.game.board.GameBoard;
import uk.ac.mmu.game.gamestrategies.EndStrategy;
import uk.ac.mmu.game.players.Player;

/**
 * ExactEndStrategy requires players to land exactly on their tail end.
 * End logic uses GameBoard lengths, not Player indices.
 */
public class ExactEndStrategy implements EndStrategy {
    private final GameBoard board;

    public ExactEndStrategy(GameBoard board) {
        this.board = board;
    }

    @Override
    public boolean hasReachedEnd(Player player, int currentIndex) {
        int tailEndIndex = board.getBoardLength() + board.getTailEndLength() - 1;
        return currentIndex == tailEndIndex;
    }

    @Override
    public int calculateOvershoot(Player player, int currentIndex) {
        int tailEndIndex = board.getBoardLength() + board.getTailEndLength() - 1;
        return Math.max(0, currentIndex - tailEndIndex);
    }

    @Override
    public boolean isValidMove(Player player, int currentIndex, int roll, int boardLength, int tailLength, int stepsTaken) {
        int proposedTotalSteps = stepsTaken + roll;
        int tailEndIndex = boardLength + tailLength - 1;

        return proposedTotalSteps <= tailEndIndex;

    }
}

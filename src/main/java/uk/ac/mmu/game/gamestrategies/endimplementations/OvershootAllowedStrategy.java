package uk.ac.mmu.game.gamestrategies.endimplementations;

import uk.ac.mmu.game.board.GameBoard;
import uk.ac.mmu.game.gamestrategies.EndStrategy;
import uk.ac.mmu.game.players.Player;

/**
 * OvershootAllowedStrategy lets players finish even if they roll past their tail end.
 * Players can finish even if they roll past their tail end.
 */
public class OvershootAllowedStrategy implements EndStrategy {
    private final GameBoard board;

    public OvershootAllowedStrategy(GameBoard board) {
        this.board = board;
    }

    @Override
    public boolean hasReachedEnd(Player player, int currentIndex) {
        int tailEndIndex = board.getBoardLength() + board.getTailEndLength() - 1;
        return currentIndex >= tailEndIndex;
    }

    @Override
    public int calculateOvershoot(Player player, int currentIndex) {
        int tailEndIndex = board.getBoardLength() + board.getTailEndLength() - 1;
        return Math.max(0, currentIndex - tailEndIndex);
    }

    @Override
    public boolean isValidMove(Player player, int currentIndex, int roll, int boardLength, int tailLength, int stepsTaken) {
        return true;
    }
}
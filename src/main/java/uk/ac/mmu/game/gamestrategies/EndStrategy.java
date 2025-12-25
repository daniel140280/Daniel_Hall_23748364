package uk.ac.mmu.game.gamestrategies;

/**
 * EndStrategy defines how a player finishes the game.
 */

import uk.ac.mmu.game.players.Player;

/**
 * Checks if a move is valid before it happens.
 * @param stepsTaken - The total absolute steps the player has taken from their start.
 */

public interface EndStrategy {
    boolean hasReachedEnd(Player player, int currentIndex);
    int calculateOvershoot(Player player, int currentIndex);
    //Check a move is valid before applying it.
    boolean isValidMove(Player player, int currentIndex, int roll, int boardLength, int tailLength, int stepsTaken);
}

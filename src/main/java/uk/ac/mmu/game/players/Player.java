package uk.ac.mmu.game.players;

/**
 * Declaring Player as an interface (abstract type) to support any future Player requirements that require shared actions.
 * Each implementation of Player will provide which concrete implementation to apply (instantiate).
 */
public interface Player {
    String getName();                               //Player name.
    int getStartIndex();                            //Players starting position (index)
    String getColorCode();                          //ANSI colour code used for console output and improved visibility of player.
}

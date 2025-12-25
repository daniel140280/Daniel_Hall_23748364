package uk.ac.mmu.game.gamestate;

import uk.ac.mmu.game.playersgamepositions.PlayersInGameContext;
import uk.ac.mmu.game.rungame.GameEngine;

public interface GameState {
    void handleDiceRoll(GameEngine context, PlayersInGameContext playerContext, int roll);
    String toString();
}

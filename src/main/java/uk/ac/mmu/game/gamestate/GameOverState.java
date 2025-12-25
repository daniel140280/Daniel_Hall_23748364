package uk.ac.mmu.game.gamestate;

import uk.ac.mmu.game.playersgamepositions.PlayersInGameContext;
import uk.ac.mmu.game.rungame.GameEngine;

public class GameOverState implements GameState {
    @Override
    public void handleDiceRoll(GameEngine context, PlayersInGameContext playerContext, int roll) {
        //Game Over should print for additional attempts to play
        System.out.println("\nGame Over: The game has already ended.");
    }
    @Override
    public String toString() {
        return "Game Over";
    }
}

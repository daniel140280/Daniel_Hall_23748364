package uk.ac.mmu.game.gamestate;

import uk.ac.mmu.game.playersgamepositions.PlayersInGameContext;
import uk.ac.mmu.game.rungame.GameEngine;

public class InPlayState implements GameState {
    @Override
    public void handleDiceRoll(GameEngine context, PlayersInGameContext playerContext, int roll) {
        context.executeMoveLogic(playerContext, roll);

        // Validation to check if state moves to GameOver state.
        if (playerContext.isFinished()) {
            context.setGameState(new GameOverState());
        }
    }
    @Override
    public String toString() {
        return "In Play";
    }
}

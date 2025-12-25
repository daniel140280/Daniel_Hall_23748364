package uk.ac.mmu.game.test.rungame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.ac.mmu.game.players.RedPlayer;
import uk.ac.mmu.game.rungame.GameEngine;
import uk.ac.mmu.game.builders.GameTestBuilder;
import uk.ac.mmu.game.test.mocks.MockGameListener;

public class GameEngineTest {
    private RedPlayer red;
    private MockGameListener listener;
    @BeforeEach
    public void setup() {
        red = new RedPlayer();
        listener = new MockGameListener();
    }
    @Test
    @DisplayName("Use Case: State transitions from Ready to In Play to Game Over")
    public void testLifecycleTransitions() {
        GameEngine engine = new GameTestBuilder()
                .withPlayers(red)
                .withDiceRolls(20)              //20 rolls deemed sufficient to full end to end state transitions.
                .addListener(listener)
                .build();

        engine.playGame();

        //1. Did it move from Ready to In Play on the first turn?
        assertTrue(listener.getTransitions().contains("Ready->In Play"),
                "Game should transition to In Play on first roll");

        //2. Did it move to Game Over when Red won?
        assertTrue(listener.getTransitions().contains("In Play->Game Over"),
                "Game should transition to Game Over upon winning");
    }
    @Test
    @DisplayName("Use Case: Game terminates at MAX_MOVES if no one wins")
    public void testInfiniteLoopSafeguard() {
        // Roll used will result in an overshoot (forfeit)
        GameEngine engine = new GameTestBuilder()
                .withPlayers(red)
                .withDiceRolls(99)
                .addListener(listener)
                .build();

        engine.playGame();

        //1. Did the failsafe break the loop and set Game Over?
        assertTrue(listener.wasGameOverCalled(), "GameOver should be triggered by safeguard");
        assertTrue(listener.getTransitions().contains("In Play->Game Over"),
                "Safeguard should force the state to Game Over");
    }
    @Test
    @DisplayName("Use Case: Any further moves after Game Over should be ignored by state machine")
    public void testPostGameOverBlocking() {
        GameEngine engine = new GameTestBuilder()
                .withPlayers(red)
                .withDiceRolls(20)
                .addListener(listener)
                .build();

        engine.playGame();

        int eventsBeforeExtraRoll = listener.getEvents().size();

        // 1. Try and manually force a turn after the game loop is finished
        engine.takeTurn(null, 5);

        //2. Validate no move events recorded
        assertEquals(eventsBeforeExtraRoll, listener.getEvents().size(),
                "The state machine should have blocked the move after Game Over");
    }
}
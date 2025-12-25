package uk.ac.mmu.game.test.gamestrategies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.ac.mmu.game.board.GameBoard;
import uk.ac.mmu.game.board.SmallGameBoard;
import uk.ac.mmu.game.gamestrategies.EndStrategy;
import uk.ac.mmu.game.gamestrategies.HitStrategy;
import uk.ac.mmu.game.gamestrategies.endimplementations.ExactEndStrategy;
import uk.ac.mmu.game.gamestrategies.endimplementations.OvershootAllowedStrategy;
import uk.ac.mmu.game.gamestrategies.hitimplementations.AllowHitStrategy;
import uk.ac.mmu.game.gamestrategies.hitimplementations.ForfeitOnHitStrategy;
import uk.ac.mmu.game.players.BluePlayer;
import uk.ac.mmu.game.players.Player;
import uk.ac.mmu.game.players.RedPlayer;
import uk.ac.mmu.game.playersgamepositions.PlayersInGameContext;
import uk.ac.mmu.game.playersgamepositions.PlayersMoveHistory;
import uk.ac.mmu.game.playersgamepositions.PlayersPosition;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for game strategies in isolation.
 */
public class StrategyTest {
    private GameBoard smallBoard;
    private Player redPlayer;
    private Player bluePlayer;

    @BeforeEach
    public void setup() {
        smallBoard = new SmallGameBoard();
        redPlayer = new RedPlayer();
        bluePlayer = new BluePlayer();
    }

    // ========== END STRATEGY TESTS ==========

    @Test
    @DisplayName("ExactEndStrategy: hasReachedEnd only true at exact position")
    public void testExactEndStrategy() {
        EndStrategy strategy = new ExactEndStrategy(smallBoard);
        int tailEnd = 20; // 18 + 3 - 1

        assertTrue(strategy.hasReachedEnd(redPlayer, tailEnd),
                "Should reach end at exact position");
        assertFalse(strategy.hasReachedEnd(redPlayer, tailEnd - 1),
                "Should not reach end before exact position");
        assertFalse(strategy.hasReachedEnd(redPlayer, tailEnd + 1),
                "Should not reach end past exact position (overshoot)");
    }

    @Test
    @DisplayName("ExactEndStrategy: calculateOvershoot returns correct values")
    public void testExactOvershootCalculation() {
        EndStrategy strategy = new ExactEndStrategy(smallBoard);
        int tailEnd = 20;

        assertEquals(0, strategy.calculateOvershoot(redPlayer, tailEnd),
                "No overshoot at exact position");
        assertEquals(0, strategy.calculateOvershoot(redPlayer, tailEnd - 1),
                "No overshoot before end");
        assertEquals(2, strategy.calculateOvershoot(redPlayer, tailEnd + 2),
                "Overshoot of 2");
    }

    @Test
    @DisplayName("OvershootAllowedStrategy: hasReachedEnd true at or past end")
    public void testOvershootAllowedStrategy() {
        EndStrategy strategy = new OvershootAllowedStrategy(smallBoard);
        int tailEnd = 20;

        assertTrue(strategy.hasReachedEnd(redPlayer, tailEnd),
                "Should reach end at exact position");
        assertFalse(strategy.hasReachedEnd(redPlayer, tailEnd - 1),
                "Should not reach end before position");
        assertTrue(strategy.hasReachedEnd(redPlayer, tailEnd + 1),
                "Should reach end even with overshoot");
        assertTrue(strategy.hasReachedEnd(redPlayer, tailEnd + 5),
                "Should reach end even with large overshoot");
    }

    // ========== HIT STRATEGY TESTS ==========

    @Test
    @DisplayName("ForfeitOnHitStrategy: blocks move when position occupied")
    public void testForfeitOnHit() {
        HitStrategy strategy = new ForfeitOnHitStrategy();

        PlayersPosition redPos = new PlayersPosition(redPlayer, smallBoard);
        redPos.setBoardIndex(5);
        PlayersInGameContext redContext = new PlayersInGameContext(redPos, new PlayersMoveHistory());

        PlayersPosition bluePos = new PlayersPosition(bluePlayer, smallBoard);
        bluePos.setBoardIndex(10);
        PlayersInGameContext blueContext = new PlayersInGameContext(bluePos, new PlayersMoveHistory());

        Map<Player, PlayersInGameContext> allPlayers = new HashMap<>();
        allPlayers.put(redPlayer, redContext);
        allPlayers.put(bluePlayer, blueContext);

        // Red tries to move to empty position 7
        assertTrue(strategy.canMoveToPosition(redPlayer, 7, allPlayers, smallBoard),
                "Should allow move to empty position");

        // Red tries to move to Blue's position 10
        assertFalse(strategy.canMoveToPosition(redPlayer, 10, allPlayers, smallBoard),
                "Should block move to occupied position");

        // Blue can stay on own position
        assertTrue(strategy.canMoveToPosition(bluePlayer, 10, allPlayers, smallBoard),
                "Player can stay on own position");
    }

    @Test
    @DisplayName("AllowHitStrategy: allows all moves")
    public void testAllowHit() {
        HitStrategy strategy = new AllowHitStrategy();

        PlayersPosition redPos = new PlayersPosition(redPlayer,smallBoard);
        redPos.setBoardIndex(5);
        PlayersInGameContext redContext = new PlayersInGameContext(redPos, new PlayersMoveHistory());

        PlayersPosition bluePos = new PlayersPosition(bluePlayer,smallBoard);
        bluePos.setBoardIndex(10);
        PlayersInGameContext blueContext = new PlayersInGameContext(bluePos, new PlayersMoveHistory());

        Map<Player, PlayersInGameContext> allPlayers = new HashMap<>();
        allPlayers.put(redPlayer, redContext);
        allPlayers.put(bluePlayer, blueContext);

        assertTrue(strategy.canMoveToPosition(redPlayer, 7, allPlayers, smallBoard),
                "Should allow move to empty position");
        assertTrue(strategy.canMoveToPosition(redPlayer, 10, allPlayers, smallBoard),
                "Should allow move to occupied position");
    }
}

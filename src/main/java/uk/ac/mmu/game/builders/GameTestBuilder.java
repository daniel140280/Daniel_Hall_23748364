package uk.ac.mmu.game.builders;

import uk.ac.mmu.game.board.GameBoard;
import uk.ac.mmu.game.board.SmallGameBoard;
import uk.ac.mmu.game.dice.DiceShaker;
import uk.ac.mmu.game.dice.FixedDiceShaker;
import uk.ac.mmu.game.gameobserver.GameListener;
import uk.ac.mmu.game.gamestrategies.EndStrategy;
import uk.ac.mmu.game.gamestrategies.HitStrategy;
import uk.ac.mmu.game.gamestrategies.endimplementations.ExactEndStrategy;
import uk.ac.mmu.game.gamestrategies.hitimplementations.ForfeitOnHitStrategy;
import uk.ac.mmu.game.players.*;
import uk.ac.mmu.game.rungame.GameConfiguration;
import uk.ac.mmu.game.rungame.GameEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Test builder for easier game setup in tests.
 * Provides sensible defaults and fluent API.
 */
public class GameTestBuilder {
    private GameBoard board = new SmallGameBoard();
    private Player[] players = new Player[]{ new RedPlayer(), new BluePlayer(), new GreenPlayer(), new YellowPlayer()};
    private DiceShaker dice = new FixedDiceShaker(1, 1, 1, 1);
    private EndStrategy endStrategy;
    private HitStrategy hitStrategy;
    private List<GameListener> listeners = new ArrayList<>();

    public GameTestBuilder() {
        this.endStrategy = new ExactEndStrategy(board);
        this.hitStrategy = new ForfeitOnHitStrategy();
    }

    public GameTestBuilder withBoard(GameBoard board) {
        this.board = board;
        this.endStrategy = new ExactEndStrategy(board); // Update strategy
        return this;
    }

    public GameTestBuilder withPlayers(Player... players) {
        this.players = players;
        return this;
    }

    public GameTestBuilder withDiceRolls(Integer... rolls) {
        this.dice = new FixedDiceShaker(rolls).withRepeatLast();
        return this;
    }

    public GameTestBuilder withEndStrategy(EndStrategy strategy) {
        this.endStrategy = strategy;
        return this;
    }

    public GameTestBuilder withHitStrategy(HitStrategy strategy) {
        this.hitStrategy = strategy;
        return this;
    }

    public GameTestBuilder addListener(GameListener listener) {
        this.listeners.add(listener);
        return this;
    }

    public GameEngine build() {
        GameConfiguration config = new GameConfiguration(
                players, board, dice, endStrategy, hitStrategy, listeners
        );
        return new GameEngine(config);
    }

    public GameEngine buildAndPlay() {
        GameEngine engine = build();
        engine.playGame();
        return engine;
    }
}
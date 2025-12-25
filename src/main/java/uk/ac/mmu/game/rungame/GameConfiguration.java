package uk.ac.mmu.game.rungame;

import uk.ac.mmu.game.board.GameBoard;
import uk.ac.mmu.game.dice.DiceShaker;
import uk.ac.mmu.game.factories.gateway.*;
import uk.ac.mmu.game.gameconfig.*;
import uk.ac.mmu.game.gameobserver.GameListener;
import uk.ac.mmu.game.gamestrategies.EndStrategy;
import uk.ac.mmu.game.gamestrategies.HitStrategy;
import uk.ac.mmu.game.players.Player;

import java.util.List;

/**
 * Single transparent object for all setup.
 * Holds players, board, dice, strategies, listeners.
 */

public class GameConfiguration {
    private final Player[] players;
    private final GameBoard board;
    private final DiceShaker dice;
    private final EndStrategy endStrategy;
    private final HitStrategy hitStrategy;
    private final List<GameListener> listeners;

    public GameConfiguration(Player[] players, GameBoard board, DiceShaker dice,
                             EndStrategy endStrategy, HitStrategy hitStrategy,
                             List<GameListener> listeners) {
        this.players = players;
        this.board = board;
        this.dice = dice;
        this.endStrategy = endStrategy;
        this.hitStrategy = hitStrategy;
        this.listeners = listeners;
    }

    //Convenience Constructor
    /*
    This constructor uses the gateways/adapters to build the concrete instantiations of
    Player[], GameBoard, DiceShaker, HitStrategy, and EndStrategy from the enums.
    That way, you can declare simulations transparently.
     */
    public GameConfiguration(PlayerOption playerOpt,
                             DiceOption diceOpt,
                             BoardOption boardOpt,
                             HitOption hitOpt,
                             EndOption endOpt,
                             List<GameListener> listeners) {
        this.board = BoardFactoryGateway.createBoard(boardOpt);
        this.players = PlayerFactoryGateway.createPlayers(playerOpt);
        this.dice = DiceFactoryGateway.createDice(diceOpt);
        this.hitStrategy = HitFactoryGateway.createHitStrategy(hitOpt);
        this.endStrategy = EndFactoryGateway.createEndStrategy(board, endOpt);
        this.listeners = listeners;
    }

    // Getters
    public Player[] getPlayers() {
        return players;
    }

    public GameBoard getBoard() {
        return board;
    }

    public DiceShaker getDice() {
        return dice;
    }

    public HitStrategy getHitStrategy() {
        return hitStrategy;
    }

    public EndStrategy getEndStrategy() {
        return endStrategy;
    }

    public List<GameListener> getListeners() {
        return listeners;
    }
}

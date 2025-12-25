package uk.ac.mmu.game.rungame;

import uk.ac.mmu.game.board.GameBoard;
import uk.ac.mmu.game.dice.DiceShaker;
import uk.ac.mmu.game.gameconfig.*;
import uk.ac.mmu.game.gameobserver.GameListener;
import uk.ac.mmu.game.gamestate.GameOverState;
import uk.ac.mmu.game.gamestate.GameState;
import uk.ac.mmu.game.gamestate.ReadyState;
import uk.ac.mmu.game.gamestrategies.EndStrategy;
import uk.ac.mmu.game.players.Player;
import uk.ac.mmu.game.playersgamepositions.PlayersInGameContext;
import uk.ac.mmu.game.playersgamepositions.PlayersMoveHistory;
import uk.ac.mmu.game.playersgamepositions.PlayersPosition;
import uk.ac.mmu.game.playersgamepositions.StandardMoveStrategy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * GameEngine runs the game loop.
 * Accepts GameConfiguration (DIP, SOLID).
 * - Winner tracking added.
 * - Tail check requires isInTail() AND >= tailEndIndex.
 */
public class GameEngine {
    private final Player[] players;
    private final GameBoard board;
    private final DiceShaker dice;
    private final EndStrategy endStrategy;
    private final List<GameListener> listeners;
    private final Map<Player, PlayersInGameContext> playerContexts = new LinkedHashMap<>();
    private final StandardMoveStrategy moveStrategy;
    private GameState state;
    private Player winner;
    public Player getWinner() { return winner; }
    private int totalGameMoves = 0;
    private final int MAX_MOVES = 100; //This is a safeguard against potential infinite loops where exact end game is played with 2 dice.



    public GameEngine(GameConfiguration config) {
        this.players = config.getPlayers();
        this.board = config.getBoard();
        this.dice = config.getDice();
        this.endStrategy = config.getEndStrategy();
        this.listeners = config.getListeners();


        // Initialize player contexts
        for (Player player : players) {
            PlayersPosition position = new PlayersPosition(player, board);
            PlayersMoveHistory history = new PlayersMoveHistory();
            playerContexts.put(player, new PlayersInGameContext(position, history));
        }

        this.moveStrategy = new StandardMoveStrategy(
                board,
                config.getHitStrategy(),
                config.getEndStrategy(),
                listeners,
                playerContexts
        );
        this.state = new ReadyState();
    }
    /**
     * Context method to handle state transitions
     */
    public void setGameState(GameState newState){
        String oldStateName = (this.state == null) ? "START" : this.state.toString();
        this.state = newState;

        //Notification of state change via listeners
        for(GameListener listener : listeners) {
            listener.onStateTransition(oldStateName, newState.toString());
        }
    }
    /**
     * Method to handle moves, whereby the GameEngine asks the State 'what should it do with the dice roll?'.
     */
    public void takeTurn(PlayersInGameContext context, int roll) {
        state.handleDiceRoll(this, context, roll);
    }

    /**
     * Method used by the InPlay state to validate moveStrategy.
     * StandardMoveStrategy will be set to finished if that is the case.
     */
    public void executeMoveLogic(PlayersInGameContext context, int roll) {
        moveStrategy.move(context, roll);
    }


//    // The States need this to trigger your move logic
//    public StandardMoveStrategy getMoveStrategy() {
//        return this.moveStrategy;
//    }
//
//    // The ReadyState needs this to call handleMove again after transitioning
//    public GameState getGameState() {
//        return this.state;
//    }

    public GameEngine(PlayerOption playerOpt,
                      DiceOption diceOpt,
                      BoardOption boardOpt,
                      HitOption hitOpt,
                      EndOption endOpt,
                      List<GameListener> listeners) {
        this(new GameConfiguration(playerOpt, diceOpt, boardOpt, hitOpt, endOpt, listeners));
    }

    public void playGame() {
        winner = null;
        totalGameMoves = 0;

        // Loop continues until State Machine hits Game Over or maxMove safety limit is reached.
        while (!(state instanceof GameOverState) && totalGameMoves < MAX_MOVES) {
            for (Player player : players) {
                PlayersInGameContext context = playerContexts.get(player);
                if (context.isFinished()){
                    continue;
                }

                int roll = dice.shake();
                totalGameMoves++;

                this.takeTurn(context, roll);

                if (context.isFinished()) {
                    winner = player;
                    break;
                }
            }
        }
        if (totalGameMoves >= MAX_MOVES && winner == null) {
            System.out.println("\nGame terminated: Reached maximum move limit of " + MAX_MOVES);
            this.setGameState(new GameOverState());
        }
        // Notify listeners
        for (GameListener listener : listeners) {
            listener.onGameOver(players, playerContexts, totalGameMoves);
        }
        if (winner != null) {
            System.out.println("\n🏆 Winner: " + winner.getColorCode() + winner.getName() + "\u001B[0m");
        }
        // Demonstrating that extra rolls after game won, prints "Game Over" state
        System.out.println("\n[Test of Game state if inducing an extra roll after win]");
        this.takeTurn(playerContexts.get(players[0]), 6);
    }
}

//------------------------------
//public void playGame() {
//    // First checking the game state is over.
//    if (state instanceof GameOverState) {
//        state.handleMove(this, null, 0);
//        return;
//    }
//
//    winner = null;
//    boolean gameOver = false;
//    totalGameMoves = 0;
//    while (!gameOver) {
//        for (Player player : players) {
//            PlayersInGameContext context = playerContexts.get(player);
//            // Skip finished players
//            if (context.isFinished()) {
//                continue;
//            }
//
//            int roll = dice.shake();
//            totalGameMoves ++;
//            state.handleMove(this, context, roll);
////                moveStrategy.move(context, roll);
//            // Only check win condition if move was successful and player hasn't forfeited.
//            if (endStrategy.hasReachedEnd(player, context.getPlayersPosition().getBoardIndex())) {
//                context.setFinished(true);
//                winner = player;
//                gameOver = true;
//                break;
//            }
//        }
//    }
//    // Notify listeners
//    for (GameListener listener : listeners) {
//        listener.onGameOver(players, playerContexts, totalGameMoves);
//    }
//    if (winner != null) {
//        System.out.println("\n🏆 Winner: " + winner.getColorCode() + winner.getName() + "\u001B[0m");
//    }
//}
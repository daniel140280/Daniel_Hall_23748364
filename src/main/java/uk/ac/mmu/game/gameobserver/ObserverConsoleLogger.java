package uk.ac.mmu.game.gameobserver;

import uk.ac.mmu.game.helpers.ConsoleColor;
import uk.ac.mmu.game.players.Player;
import uk.ac.mmu.game.playersgamepositions.PlayersInGameContext;

import java.util.Map;

//Class is stateless. Context is passed in via method parameters as the logger shouldn't hold state.
/**
 * SRP: Only responsible for console output formatting and logging.
 * Does NOT make game logic decisions.
 */
public class ObserverConsoleLogger implements GameListener{

    PlayersInGameContext playersPosition;
    /**
     * Method to track when a player rolls the dice and attempts to move.
     * It will store the roll and updates the move positions.
     */
    @Override
    public void onSuccessfulMove(Player player, PlayersInGameContext context, String fromPosition, String toPosition, int roll) {
        String message = String.format(
                "%s turn %d rolled %d with the dice | moves from %s to %s | successful move",
                player.getName(), context.getMoveCount(), roll, fromPosition, toPosition
        );
        // Wrap the entire message in the player's color
        System.out.println(ConsoleColor.consoleColor(message, player.getColorCode()));
        // Record successful move in player move history
        context.getPlayersHistory().add("Successful roll, moved to " + toPosition);
    }
    /**
     * Method to output a 'hit' if a players move is blocked by another player.
     */
    @Override
    public void onBlockedMove(Player player, PlayersInGameContext context, String fromPosition, String attemptedPosition, int roll) {
        String message = String.format(
                "%s turn %d rolled %d with the dice | move forfeited, hit another player at %s | stays on %s",
                player.getName(), context.getMoveCount(), roll, attemptedPosition, fromPosition
        );
        System.out.println(ConsoleColor.consoleColor(message, player.getColorCode()));
        context.getPlayersHistory().add("Move forfeited (hit), stays on " + fromPosition);
    }
    /**
     * Method to output when a player successfully reaches the end (WIN), whether they need to hit the end exactly to can overshoot.
     */
    @Override
    public void onEndReached(Player player, PlayersInGameContext context, String fromPosition, String toPosition, int overshoot, int roll) {
        String message;
        if(overshoot == 0) {
            message = String.format(
                    "%s turn %d rolled %d with the dice | landed exactly on the end at %s, so we have a winner",
                    player.getName(), context.getMoveCount(), roll, toPosition
            );
            context.getPlayersHistory().add("🎉 Reached end at " + toPosition);
        } else {
            message = String.format(
                    "%s turn %d rolled %d with the dice | overshot by %d but allowed, winner at %s!",
                    player.getName(), context.getMoveCount(), roll, overshoot, toPosition
            );
            context.getPlayersHistory().add("🎉 Reached end (overshoot allowed) at " + toPosition);
        }
        System.out.println(ConsoleColor.consoleColor(message, player.getColorCode()));
    }
    /**
     * Method to output when a player overshoots with a strategy that forbids it (FORFEIT).
     */
    @Override
    public void onEndForfeit(Player player, PlayersInGameContext context, String fromPosition, int overshoot, int roll) {
        String message = String.format(
                "%s turn %d rolled %d with the dice | overshot by %d, move forfeited, stays on %s",
                player.getName(), context.getMoveCount(), roll, overshoot, fromPosition
        );
        System.out.println(ConsoleColor.consoleColor(message, player.getColorCode()));
        context.getPlayersHistory().add("Overshoot. Move forfeited, stays on " + fromPosition);
    }
    @Override
    public void onGameOver(Player[] players, Map<Player, PlayersInGameContext> contexts, int totalGameMoves) {
        System.out.println("\nEnd of game status:");
        Player winner = null;
        for (Player player : players) {
            if (contexts.get(player).isFinished()) {
                winner = player;
                break;
            }
        }
        if (winner != null) {
            System.out.printf("%s wins in %d turns, total moves in game %d.%n",
                    winner.getName(), contexts.get(winner).getMoveCount(), totalGameMoves);
        }
    }
    /**
     * Method to output game state transitions.
     */
    @Override
    public void onStateTransition(String oldState, String newState){
        String message = String.format("\nGAME STATE : %s -> %s", oldState, newState);
        System.out.println(ConsoleColor.consoleColor(message, "\u001B[35m"));
    }
}

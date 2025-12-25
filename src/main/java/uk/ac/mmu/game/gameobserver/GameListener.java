package uk.ac.mmu.game.gameobserver;

import uk.ac.mmu.game.players.Player;
import uk.ac.mmu.game.playersgamepositions.PlayersInGameContext;

import java.util.Map;

public interface GameListener {

    void onSuccessfulMove(Player player, PlayersInGameContext context, String fromPosition, String toPosition, int roll);
    void onBlockedMove(Player player, PlayersInGameContext context, String fromPosition, String attemptedPosition, int roll);
    /**
     * Method to output when a player successfully reaches the end (exact land or allowed overshoot).
     */
    void onEndReached(Player player, PlayersInGameContext context, String fromPosition, String toPosition, int overshoot, int roll);
    /**
     * Method to output when a player overshoots with a strategy that forbids it (forfeit).
     */
    void onEndForfeit(Player player, PlayersInGameContext context, String fromPosition, int overshoot, int roll);
    void onGameOver(Player[] players, Map<Player, PlayersInGameContext> contexts, int totalGameMoves);
    void onStateTransition(String oldState, String newState);
}



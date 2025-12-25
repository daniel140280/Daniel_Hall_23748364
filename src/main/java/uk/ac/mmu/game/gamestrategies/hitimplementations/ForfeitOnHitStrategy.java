package uk.ac.mmu.game.gamestrategies.hitimplementations;

import uk.ac.mmu.game.board.GameBoard;
import uk.ac.mmu.game.gamestrategies.HitStrategy;
import uk.ac.mmu.game.players.Player;
import uk.ac.mmu.game.playersgamepositions.PlayersInGameContext;

import java.util.Map;

/**
 * ForfeitOnHitStrategy - Tail positions are LINEAR, not modular.
 */
public class ForfeitOnHitStrategy implements HitStrategy {

    @Override
    public boolean canMoveToPosition(Player currentPlayer, int targetIndex,
                                     Map<Player, PlayersInGameContext> allPlayers, GameBoard board) {
        int boardLength = board.getBoardLength();

        // If target is in tail, NO collision possible (separate tails)
        if (targetIndex >= boardLength) {
            return true;  // Always allow - players have separate tails
        }

        // Only check collisions on shared board
        for (Map.Entry<Player, PlayersInGameContext> entry : allPlayers.entrySet()) {
            Player other = entry.getKey();
            if (other.equals(currentPlayer)) continue;

            int otherIndex = entry.getValue().getPlayersPosition().getBoardIndex();

            // If other player is in tail, they can't collide with shared board
            if (otherIndex >= boardLength) continue;

            // Both players are on shared board - check if same position
            if (targetIndex == otherIndex) {
                return false;  // Collision detected!
            }
        }
        return true;  // No collision
    }
}
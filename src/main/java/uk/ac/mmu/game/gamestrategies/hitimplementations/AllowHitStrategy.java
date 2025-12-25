package uk.ac.mmu.game.gamestrategies.hitimplementations;

import uk.ac.mmu.game.board.GameBoard;
import uk.ac.mmu.game.gamestrategies.HitStrategy;
import uk.ac.mmu.game.players.Player;
import uk.ac.mmu.game.playersgamepositions.PlayersInGameContext;

import java.util.Map;


public class AllowHitStrategy implements HitStrategy {
    @Override
    public boolean canMoveToPosition(Player currentPlayer, int targetIndex, Map<Player, PlayersInGameContext> allPlayers, GameBoard board) {
        return true; // Always allow move.
    }
}
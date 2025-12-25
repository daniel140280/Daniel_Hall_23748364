package uk.ac.mmu.game.gamestrategies;

import uk.ac.mmu.game.board.GameBoard;
import uk.ac.mmu.game.players.Player;
import uk.ac.mmu.game.playersgamepositions.PlayersInGameContext;

import java.util.Map;

public interface HitStrategy {
    boolean canMoveToPosition(Player currentPlayer, int targetIndex, Map<Player, PlayersInGameContext> allPlayers, GameBoard board);
}
package uk.ac.mmu.game.playersgamepositions;

import uk.ac.mmu.game.board.GameBoard;
import uk.ac.mmu.game.players.Player;

/*
Value Object used to track the position of the players as they traverse the board.
Strategies will determine whether board positions are valid or forfeit.
History will be kept to track game statistics.
Observer will listen to position progress and print relevant information to the console.
 */
/**
 * SRP: Tracks runtime position only.
 *  Does NOT handle game logic or strategy decisions.
 */

public class PlayersPosition {
    private int boardIndex;                             // Tracking current board index based on the player in context.
    private boolean indexInTail;                        // Supporting whether player is in their tail position.
    private final Player player;
    private final int boardLength;
    private final int tailLength;

    public PlayersPosition(Player player, GameBoard board){
        this.player = player;
        this.boardIndex = player.getStartIndex();       //When a player is created, we automatically assign their starting board index position.
        this.indexInTail = false;
        this.boardLength = board.getBoardLength();
        this.tailLength = board.getTailEndLength();
    }

    //Methods to manage the Players game moves depending on game strategies applied.
    public int getBoardIndex() {
        return boardIndex;
    }
    public void setBoardIndex(int boardIndex) {
        this.boardIndex = boardIndex;
    }
    public boolean isInTail() {
        return indexInTail;
    }
    public void setInTail(boolean inTail) {
        this.indexInTail = inTail;
    }
    public Player getPlayer() {
        return player;
    }


    /**
     * Calculates tail offset dynamically from boardIndex
     */
    @Override
    public String toString() {
        if (!indexInTail) {
            if(boardIndex == player.getStartIndex()){
                return "Home (position " + (boardIndex +1) + ")";
            }
            return "position " + (boardIndex + 1);
        }
        String initial = player.getName().substring(0, 1);
        int tailPosition = boardIndex - boardLength + 1;

        if (tailPosition >= tailLength) {
            return initial + "End";
        }
        return "Tail position " + (initial + tailPosition);
    }
}

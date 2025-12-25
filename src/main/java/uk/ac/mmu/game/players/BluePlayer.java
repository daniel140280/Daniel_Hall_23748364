package uk.ac.mmu.game.players;

/**
 * Tail indices are calculated using GameBoard abstraction.
 * Player only knows its start index; everything else comes from the board abstraction.
 */

public class BluePlayer implements Player {
    private final String name = "Blue";
    private final int startIndex = 9;               //Always starts at same index regardless of board size.

    private final String colorCode = "\u001B[34m";  //Blue colour.

    public BluePlayer(){

    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getStartIndex() {
        return startIndex;
    }

    @Override
    public String getColorCode() {
        return colorCode;
    }
}

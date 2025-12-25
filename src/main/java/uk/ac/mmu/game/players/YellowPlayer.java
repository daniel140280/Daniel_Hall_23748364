package uk.ac.mmu.game.players;

/**
 * SRP: Player only knows identity and start index.
 * No tail logic here.
 */

public class YellowPlayer implements Player{
    private final String name = "Yellow";
    private final int startIndex = 27;               //Always starts at same index regardless of board size.

    private final String colorCode = "\u001B[33m";  //Yellow colour.

    public YellowPlayer(){
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


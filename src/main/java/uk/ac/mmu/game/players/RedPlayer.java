package uk.ac.mmu.game.players;

/**
 * SRP: Player only knows identity and start index.
 * No tail logic here.
 */

public class RedPlayer implements Player{
    private final String name = "Red";
    private final int startIndex = 0;               //Always starts at same index regardless of board size.
    private final String colorCode = "\u001B[31m";  //Red colour.


    public RedPlayer(){

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

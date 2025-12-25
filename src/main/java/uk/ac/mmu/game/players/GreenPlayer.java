package uk.ac.mmu.game.players;

/**
 * SRP: Player only knows identity and start index.
 * No tail logic here.
 */

public class GreenPlayer implements Player{
        private final String name = "Green";
        private final int startIndex = 18;               //Always starts at same index regardless of board size.

        private final String colorCode = "\u001B[32m";  //Green colour.

        public GreenPlayer(){
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


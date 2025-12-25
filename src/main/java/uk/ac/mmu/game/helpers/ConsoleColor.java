package uk.ac.mmu.game.helpers;
/*
This helper method is purely to support colouring in the console output on the game simulation.
 */
public class ConsoleColor {
    private static final String RESET = "\u001B[0m";
    public static String consoleColor(String message, String colorCode){
        return colorCode + message + RESET;
    }
}

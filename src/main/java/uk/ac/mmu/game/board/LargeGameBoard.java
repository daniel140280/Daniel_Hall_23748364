package uk.ac.mmu.game.board;

public class LargeGameBoard implements GameBoard {
    private static final int BOARD_LENGTH = 36;                                 //Number of shared board positions per player.
    private static final int END_LENGTH = 6;                                    //Number of end positions per player.
    private static final int TOTAL_LENGTH = BOARD_LENGTH + END_LENGTH;

    @Override
    public int getBoardLength() {
        return BOARD_LENGTH;
    };

    @Override
    public int getTailEndLength() {
        return END_LENGTH;
    };

    @Override
    public int getTotalBoardLength() {
        return TOTAL_LENGTH;
    };

    @Override
    public boolean isEndPosition(int index) {
        return index >= BOARD_LENGTH && index < TOTAL_LENGTH;
    };

    @Override
    public boolean isValidPosition(int index) {
        return index >= 0 && index < TOTAL_LENGTH;
    };
}

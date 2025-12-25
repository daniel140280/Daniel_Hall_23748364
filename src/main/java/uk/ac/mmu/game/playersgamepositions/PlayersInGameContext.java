package uk.ac.mmu.game.playersgamepositions;

//PlayersInGameContext will be used to hold players state based on game rule strategies applied.
public class PlayersInGameContext {
    private PlayersPosition playersPosition;
    private int moveCount;
    private int stepsTaken;
    private PlayersMoveHistory playersHistory;
    private boolean finished;


    //PlayersInGameContext needs to understand the in game positions, moves attempted and history.
    public PlayersInGameContext(PlayersPosition playersPosition, PlayersMoveHistory playersMoveHistory){
        this.playersPosition = playersPosition;
        this.playersHistory = playersMoveHistory;
        this.stepsTaken = 0;
        this.finished = false;
    }

    public PlayersPosition getPlayersPosition() {
        return playersPosition;
    }

    public void setPlayersPosition(PlayersPosition playersPosition) {
        this.playersPosition = playersPosition;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void increaseMoveCount() {
        moveCount++;
    }

    public int getStepsTaken() {
        return stepsTaken;
    }

    public void advanceStepsTaken(int stepsTaken) {
        this.stepsTaken += stepsTaken;
    }

    public PlayersMoveHistory getPlayersHistory() {
        return playersHistory;
    }
    public boolean isFinished() { return finished; }
    public void setFinished(boolean finished) { this.finished = finished; }


}

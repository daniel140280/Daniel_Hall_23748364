package uk.ac.mmu.game.test.mocks;

import uk.ac.mmu.game.gameobserver.GameListener;
import uk.ac.mmu.game.players.Player;
import uk.ac.mmu.game.playersgamepositions.PlayersInGameContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MockGameListener implements GameListener {
    private final List<String> transitions = new ArrayList<>();
    private final List<String> events = new ArrayList<>();
    private boolean gameOverCalled = false;

    @Override
    public void onSuccessfulMove(Player p, PlayersInGameContext c, String from, String to, int roll) {
        events.add("MOVE: " + p.getName() + " from " + from + " to " + to);
    }
    @Override
    public void onBlockedMove(Player p, PlayersInGameContext c, String from, String attempted, int roll) {
        events.add("BLOCKED: " + p.getName() + " at " + from);
    }
    @Override
    public void onEndReached(Player p, PlayersInGameContext c, String from, String to, int overshoot, int roll) {
        events.add("WIN: " + p.getName());
    }
    @Override
    public void onEndForfeit(Player p, PlayersInGameContext c, String from, int overshoot, int roll) {
        events.add("FORFEIT: " + p.getName());
    }
    @Override
    public void onGameOver(Player[] players, Map<Player, PlayersInGameContext> contexts, int totalMoves) {
        this.gameOverCalled = true;
    }
    @Override
    public void onStateTransition(String oldState, String newState) {
        transitions.add(oldState + "->" + newState);
    }

    // Verification Helpers
    public List<String> getTransitions() { return transitions; }
    public boolean wasGameOverCalled() { return gameOverCalled; }
    public List<String> getEvents() { return events; }
}
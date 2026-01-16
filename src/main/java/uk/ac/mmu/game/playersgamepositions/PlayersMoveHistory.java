package uk.ac.mmu.game.playersgamepositions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Value Object to keep abreast of a Players game moves.
public class PlayersMoveHistory {
    private List<String> history = new ArrayList<>();

    //Method to update the Player moves history as the game progresses.
    public void add(String move) {
        history.add(move);
    }

    //Method to return the total moves made by the Player.
    public List<String> getAllMoves() {
        return Collections.unmodifiableList(history);
    }
}

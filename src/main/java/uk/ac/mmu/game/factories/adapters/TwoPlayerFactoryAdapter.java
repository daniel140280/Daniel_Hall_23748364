package uk.ac.mmu.game.factories.adapters;

import uk.ac.mmu.game.factories.PlayerFactory;
import uk.ac.mmu.game.gameconfig.PlayerOption;
import uk.ac.mmu.game.players.BluePlayer;
import uk.ac.mmu.game.players.Player;
import uk.ac.mmu.game.players.RedPlayer;

public class TwoPlayerFactoryAdapter implements PlayerFactory {
    @Override
    public Player[] createPlayers(PlayerOption option) {
        return new Player[]{ new RedPlayer(), new BluePlayer() };
    }
}
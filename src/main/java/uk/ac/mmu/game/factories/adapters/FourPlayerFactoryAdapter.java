package uk.ac.mmu.game.factories.adapters;

import uk.ac.mmu.game.factories.PlayerFactory;
import uk.ac.mmu.game.gameconfig.PlayerOption;
import uk.ac.mmu.game.players.*;

public class FourPlayerFactoryAdapter implements PlayerFactory {
    @Override
    public Player[] createPlayers(PlayerOption option) {
        return new Player[]{ new RedPlayer(), new BluePlayer(), new GreenPlayer(), new YellowPlayer() };
    }
}

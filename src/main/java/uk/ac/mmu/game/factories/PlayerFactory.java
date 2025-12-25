package uk.ac.mmu.game.factories;

import uk.ac.mmu.game.gameconfig.PlayerOption;
import uk.ac.mmu.game.players.Player;

/*
Player factory abstraction utilises the configuration enums to determine the concrete instantiation.
 */
public interface PlayerFactory {
    Player[] createPlayers(PlayerOption option);        // TWO or FOUR player
}

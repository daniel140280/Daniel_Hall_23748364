package uk.ac.mmu.game.factories;

import uk.ac.mmu.game.gameconfig.HitOption;
import uk.ac.mmu.game.gamestrategies.HitStrategy;

/*
Strategy factory abstraction utilises the configuration enums to determine the concrete instantiation.
 */
public interface HitFactory {
    HitStrategy createHitStrategy(HitOption option);   // ALLOW or FORBID
}

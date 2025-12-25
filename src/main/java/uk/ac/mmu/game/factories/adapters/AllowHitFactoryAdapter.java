package uk.ac.mmu.game.factories.adapters;

import uk.ac.mmu.game.factories.HitFactory;
import uk.ac.mmu.game.gameconfig.HitOption;
import uk.ac.mmu.game.gamestrategies.HitStrategy;
import uk.ac.mmu.game.gamestrategies.hitimplementations.AllowHitStrategy;

public class AllowHitFactoryAdapter implements HitFactory {
    @Override
    public HitStrategy createHitStrategy(HitOption option) {
        return new AllowHitStrategy();
    }
}

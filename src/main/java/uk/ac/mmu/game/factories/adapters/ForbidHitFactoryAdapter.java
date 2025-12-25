package uk.ac.mmu.game.factories.adapters;

import uk.ac.mmu.game.factories.HitFactory;
import uk.ac.mmu.game.gameconfig.HitOption;
import uk.ac.mmu.game.gamestrategies.HitStrategy;
import uk.ac.mmu.game.gamestrategies.hitimplementations.ForfeitOnHitStrategy;

public class ForbidHitFactoryAdapter implements HitFactory {

    public ForbidHitFactoryAdapter(){
    };
    @Override
    public HitStrategy createHitStrategy(HitOption option) {
        return new ForfeitOnHitStrategy();
    }
}

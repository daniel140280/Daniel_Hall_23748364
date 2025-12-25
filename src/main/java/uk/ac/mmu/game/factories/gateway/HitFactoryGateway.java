package uk.ac.mmu.game.factories.gateway;

import uk.ac.mmu.game.factories.HitFactory;
import uk.ac.mmu.game.factories.adapters.AllowHitFactoryAdapter;
import uk.ac.mmu.game.factories.adapters.ForbidHitFactoryAdapter;
import uk.ac.mmu.game.gameconfig.HitOption;
import uk.ac.mmu.game.gamestrategies.HitStrategy;

import java.util.HashMap;
import java.util.Map;
/**
 * Gateway for HitStrategy creation.
 * Uses a registry of adapters keyed by HitOption.
 */

public class HitFactoryGateway {
    private static final Map<HitOption, HitFactory> hitRegistry = new HashMap<>();

    static {
        hitRegistry.put(HitOption.ALLOW, new AllowHitFactoryAdapter());
        hitRegistry.put(HitOption.FORBID, new ForbidHitFactoryAdapter());
    }

    public static HitStrategy createHitStrategy(HitOption option) {
        HitFactory factory = hitRegistry.get(option);
        if (factory == null){
            throw new IllegalArgumentException("Unsupported HitOption: " + option);
        }
        return factory.createHitStrategy(option);
    }
}

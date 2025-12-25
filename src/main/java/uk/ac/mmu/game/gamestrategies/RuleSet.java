package uk.ac.mmu.game.gamestrategies;

public class RuleSet {
    private final boolean requireExactEnd;
    private final boolean allowsPlayerHit;

    public RuleSet(boolean requireExactEnd, boolean allowsPlayerHit){
        this.requireExactEnd = requireExactEnd;
        this.allowsPlayerHit = allowsPlayerHit;
    }

    public boolean requireExactEnd() {
        return requireExactEnd;
    }

    public boolean allowsPlayerHit() {
        return allowsPlayerHit;
    }

}

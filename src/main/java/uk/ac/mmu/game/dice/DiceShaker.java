package uk.ac.mmu.game.dice;
/**
 * Declaring DiceShaker as an interface (abstract type) to act as Dice strategy.
 * This avoids the compiler creating various instances that are not required.
 * Remains flexible for switching between single and double dice rolls.
 * It also decouples dice logic from all other game logic.
 * Allowing us at runtime time only, which concrete implementation to apply (instantiate).
 */
public interface DiceShaker {
    int shake();
}
package uk.ac.mmu.game.runsimulations;

//CONSIDER ADDING WORDING, SEE SCENARIO 2, WHEREBY PLAYER HITS ANOTHER, BUT THATS JUST REFERENCE, NO FORFEIT. ALSO WHEN WIN, MOVE FROM AND TO.

import org.springframework.stereotype.Component;
import uk.ac.mmu.game.board.LargeGameBoard;
import uk.ac.mmu.game.board.SmallGameBoard;
import uk.ac.mmu.game.builders.GameTestBuilder;
import uk.ac.mmu.game.gameobserver.ObserverConsoleLogger;
import uk.ac.mmu.game.gamestrategies.endimplementations.ExactEndStrategy;
import uk.ac.mmu.game.gamestrategies.endimplementations.OvershootAllowedStrategy;
import uk.ac.mmu.game.gamestrategies.hitimplementations.AllowHitStrategy;
import uk.ac.mmu.game.gamestrategies.hitimplementations.ForfeitOnHitStrategy;
import uk.ac.mmu.game.players.BluePlayer;
import uk.ac.mmu.game.players.GreenPlayer;
import uk.ac.mmu.game.players.RedPlayer;
import uk.ac.mmu.game.players.YellowPlayer;

@Component
public class ScenarioRunner {

    public void runScenarioOneA() {
        System.out.println("\n=== BASIC GAME : SCENARIO 1A: Blue Wins (Dice Roll sequence - 12, 12, 7, 8) ===");

        new GameTestBuilder()
                .withBoard(new SmallGameBoard())
                .withPlayers(new RedPlayer(), new BluePlayer())
                .withDiceRolls(12, 12, 7, 8)
                .withHitStrategy(new AllowHitStrategy())
                .withEndStrategy(new OvershootAllowedStrategy(new SmallGameBoard()))
                .addListener(new ObserverConsoleLogger())
                .buildAndPlay();
    }

    public void runScenarioOneB() {
        System.out.println("\n=== BASIC GAME : SCENARIO 1B: Red Wins (Dice Roll sequence - 12, 12, 6, 6, 2) ===");

        new GameTestBuilder()
                .withBoard(new SmallGameBoard())
                .withPlayers(new RedPlayer(), new BluePlayer())
                .withDiceRolls(12, 12, 6, 6, 2)
                .withHitStrategy(new AllowHitStrategy())
                .withEndStrategy(new OvershootAllowedStrategy(new SmallGameBoard()))
                .addListener(new ObserverConsoleLogger())
                .buildAndPlay();
    }

    public void runScenarioTwo() {
        System.out.println("\n=== BASIC GAME : SCENARIO 2: Red Wins (Dice Roll sequence - 8, 2, 3, 4, 9) ===");

        new GameTestBuilder()
                .withBoard(new SmallGameBoard())
                .withPlayers(new RedPlayer(), new BluePlayer())
                .withDiceRolls(8, 2, 3, 4, 9)
                .withHitStrategy(new AllowHitStrategy())
                .withEndStrategy(new OvershootAllowedStrategy(new SmallGameBoard()))
                .addListener(new ObserverConsoleLogger())
                .buildAndPlay();
    }
    public void runScenarioTest() {
        System.out.println("\n=== BASIC GAME SCENARIO 2: Red Wins (Dice Roll sequence - 8, 2, 3, 4, 9) ===");

//        List<GameListener> listeners = List.of(new ObserverConsoleLogger());
//        DiceShaker dice;
//        GameEngine engine = new GameEngine(PlayerOption.TWO, dice = new FixedDiceShaker(), BoardOption.SMALL, HitOption.ALLOW, EndOption.OVERSHOOT_ALLOWED, listeners);
//        //Run the game.
//        engine.playGame();
    }

    public void runScenarioThree() {
        System.out.println("\n=== BASIC GAME : SCENARIO 3: Blue Wins through overshoot (Dice Roll sequence - 12, 12, 7, 11) ===");

        new GameTestBuilder()
                .withBoard(new SmallGameBoard())
                .withPlayers(new RedPlayer(), new BluePlayer())
                .withDiceRolls(12, 12, 7, 11)
                .withHitStrategy(new AllowHitStrategy())
                .withEndStrategy(new OvershootAllowedStrategy(new SmallGameBoard()))
                .addListener(new ObserverConsoleLogger())
                .buildAndPlay();
    }

    public void runScenarioFour() {
        System.out.println("\n=== BASIC GAME SINGLE DIE : SCENARIO 4: Blue Wins using single die (Dice Roll sequence - 6, 6, 6, 6, 3, 4, 3, 4) ===");

        new GameTestBuilder()
                .withBoard(new SmallGameBoard())
                .withPlayers(new RedPlayer(), new BluePlayer())
                .withDiceRolls(6, 6, 6, 6, 3, 4, 3, 4)
                .withHitStrategy(new AllowHitStrategy())
                .withEndStrategy(new OvershootAllowedStrategy(new SmallGameBoard()))
                .addListener(new ObserverConsoleLogger())
                .buildAndPlay();
    }

    public void runScenarioFive() {
        System.out.println("\n=== EXACT END AND PLAYER HIT FORFEIT : SCENARIO 5: Red Wins (Dice Roll sequence - 12, 12, 12, 9, 8) ===");

        new GameTestBuilder()
                .withBoard(new SmallGameBoard())
                .withPlayers(new RedPlayer(), new BluePlayer())
                .withDiceRolls(12, 12, 12, 9, 8)
                .withHitStrategy(new ForfeitOnHitStrategy())
                .withEndStrategy(new ExactEndStrategy(new SmallGameBoard()))
                .addListener(new ObserverConsoleLogger())
                .buildAndPlay();
    }

    public void runScenarioSix() {
        System.out.println("\n=== EXACT END AND PLAYER HIT FORFEIT : SCENARIO 6: Blue Wins (Dice Roll sequence - 8, 2, 3, 12, 9, 6) ===");

        new GameTestBuilder()
                .withBoard(new SmallGameBoard())
                .withPlayers(new RedPlayer(), new BluePlayer())
                .withDiceRolls(8, 2, 3, 12, 9, 6)
                .withHitStrategy(new ForfeitOnHitStrategy())
                .withEndStrategy(new ExactEndStrategy(new SmallGameBoard()))
                .addListener(new ObserverConsoleLogger())
                .buildAndPlay();
    }

    public void runScenarioSeven() {
        System.out.println("\n=== ADVANCED FEATURES - LARGE BOARD, 4 PLAYERS, BASIC RULES : SCENARIO 7: Yellow Wins (Dice Roll sequence - 7,3,8,5,7,6,8,7,6,8,2,4,4,8,5,7,8,3,9,9,7,5,7,9) ===");

        new GameTestBuilder()
                .withBoard(new LargeGameBoard())
                .withPlayers(new RedPlayer(), new BluePlayer(), new GreenPlayer(), new YellowPlayer())
                .withDiceRolls(7,3,8,5,7,6,8,7,6,8,2,4,4,8,5,7,8,3,9,9,7,5,7,9)
                .withHitStrategy(new AllowHitStrategy())
                .withEndStrategy(new OvershootAllowedStrategy(new LargeGameBoard()))
                .addListener(new ObserverConsoleLogger())
                .buildAndPlay();
    }

    public void runScenarioEight() {
        System.out.println("\n=== ADVANCED FEATURES - LARGE BOARD, 4 PLAYERS, HIT PLAYER AND EXACT END OR FORFEIT : SCENARIO 8: Yellow Wins (Dice Roll sequence - 11,11,8,10,10,7,2,4,6,8,4,9,9,10,7,11,10,8,5,7) ===");

        new GameTestBuilder()
                .withBoard(new LargeGameBoard())
                .withPlayers(new RedPlayer(), new BluePlayer(), new GreenPlayer(), new YellowPlayer())
                .withDiceRolls(11,11,8,10,10,7,2,4,6,8,4,9,9,10,7,11,10,8,5,7)
                .withHitStrategy(new ForfeitOnHitStrategy())
                .withEndStrategy(new ExactEndStrategy(new LargeGameBoard()))
                .addListener(new ObserverConsoleLogger())
                .buildAndPlay();
    }

    public void runScenarioNine() {
        System.out.println("\n=== STATE MACHINE FEATURE - BASIC GAME : SCENARIO 9: Blue Wins (Dice Roll sequence - 12,12,7,8,12,12) ===");

        new GameTestBuilder()
                .withBoard(new SmallGameBoard())
                .withPlayers(new RedPlayer(), new BluePlayer())
                .withDiceRolls(12,12,7,8,12,12)
                .withHitStrategy(new AllowHitStrategy())
                .withEndStrategy(new OvershootAllowedStrategy(new SmallGameBoard()))
                .addListener(new ObserverConsoleLogger())
                .buildAndPlay();
    }
}



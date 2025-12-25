package uk.ac.mmu.game.runsimulations;

import org.springframework.stereotype.Component;
import uk.ac.mmu.game.gameconfig.*;
import uk.ac.mmu.game.gameobserver.GameListener;
import uk.ac.mmu.game.gameobserver.ObserverConsoleLogger;
import uk.ac.mmu.game.rungame.GameEngine;

import java.util.List;

/*
The method is a facade factory method, bringing all the Game set-up requirements together to run the various Game Simulations.
Transparent declaration of options.
Uses gateways/adapters to build configuration.
 *      Loops through all combinations of simulation parameters.
 *      Transparent declaration of each simulation run.
 */
@Component
public class RunGameSimulations {

    public void runAllGameSimulations(){

        List<GameListener> listeners = List.of(new ObserverConsoleLogger());

        // Loop through all game variations.
        for(PlayerOption playerOption : PlayerOption.values()){
            for(DiceOption diceOption : DiceOption.values()){
                for(BoardOption boardOption : BoardOption.values()){
                    for(HitOption hitOption : HitOption.values()){
                        for(EndOption endOption : EndOption.values()){

                            //Print on the simulation scenario.
                            System.out.println("--------------Running simulation--------------\n");
                            System.out.printf("Game consists of %s players, %s dice, a %s board, the Hit strategy is %s and %s End strategy is played", playerOption, diceOption, boardOption, hitOption, endOption);
                            System.out.println(); //Blank line added for improved visibility.

                            //Game simulations now constructed directly through Game set-up enums.
                            GameEngine engine = new GameEngine(playerOption, diceOption, boardOption, hitOption, endOption, listeners);

                            //Run the game.
                            engine.playGame();
                        }
                    }
                }
            }
        }
    }
}

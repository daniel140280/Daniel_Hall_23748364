package uk.ac.mmu.game.rungame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.mmu.game.runsimulations.RunGameSimulations;
import uk.ac.mmu.game.runsimulations.ScenarioRunner;

@Service
public class RunGame {

    @Autowired
    RunGameSimulations simulations;
    @Autowired
    ScenarioRunner runner;
    public void executeGame(){
        simulations.runAllGameSimulations();

        runner.runScenarioOneA();
        runner.runScenarioOneB();
        runner.runScenarioTwo();
        runner.runScenarioThree();
        runner.runScenarioFour();
        runner.runScenarioFive();
        runner.runScenarioSix();
        runner.runScenarioSeven();
        runner.runScenarioEight();
        runner.runScenarioNine();

        System.out.println("All Done");
    }
}

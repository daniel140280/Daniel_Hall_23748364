package uk.ac.mmu.game.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import uk.ac.mmu.game.rungame.RunGame;

@Component
public class StartUp {
    @Autowired
    RunGame game;

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        game.executeGame();
    }
}

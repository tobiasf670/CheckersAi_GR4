package junitTest;

import Controller.GameController;
import Moves.Move;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by malthe on 11/24/17.
 */
public class testGameController {
    GameController gameController;

    @Before
    public void init() {
        this.gameController = new GameController();
    }

    @Test
    public void testReadUserInput() {
        Move result = gameController.readUserInput();
    }
}

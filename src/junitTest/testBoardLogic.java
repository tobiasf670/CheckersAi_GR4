package junitTest;

import Board.BoardLogic;
import org.junit.Before;

/**
 * Created by malthe on 11/19/17.
 */
public class testBoardLogic {

    BoardLogic boardLogic;

    @Before
    public void init() {
        this.boardLogic = new BoardLogic();
    }
}

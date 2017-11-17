package junitTest;
import Board.Board;
import Heuristics.HeuristicCalculator;
import enums.Side;
import Player.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

/**
 * Created by malthe on 11/17/17.
 */
public class testHeuristicCalculator {

    private Board board;
    private Player red;
    private Player black;
    private HeuristicCalculator calculator;

    @Before
    public void init() {
        calculator = new HeuristicCalculator();
        red = new Player(Side.RED);
        black = new Player(Side.BLACK);

        board = new Board(red, black);
        board.init();


    }
    @Test
    public void testInitBoard() {

        double result = calculator.CalculateHeuristic(board, red);
        Assert.assertEquals(0.0,result, 0);
    }

    @Test
    public void testOtherBoard() {
        //remove two black

        board.removeChecker(new Point(0,0));
        board.removeChecker(new Point(0,2));
    }
}

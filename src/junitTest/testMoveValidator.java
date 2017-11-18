package junitTest;
import Board.Board;
import Moves.Move;
import Player.Player;
import Moves.MoveValidator;
import enums.CheckerType;
import enums.Side;
import Board.BoardField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

/**
 * Created by malthe on 11/17/17.
 */
public class testMoveValidator {

    MoveValidator validator;
    Board board;
    Player red;
    Player black;

    @Before
    public void init() {

        this.validator = new MoveValidator();
        this.red = new Player(Side.RED);
        this.black = new Player(Side.BLACK);
        BoardField empty = new BoardField(false);
        BoardField redField = new BoardField(this.red, CheckerType.RED, true);
        BoardField redKing = new BoardField(this.red, CheckerType.RED_KING, true);

        BoardField[][] gameBoard = new BoardField[][]{
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, redKing, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { redField, empty, empty, empty, redField, empty, redField, empty},

        };

        board = new Board(this.red,black,gameBoard);
        board.numberOfBlackCheckers = 0;
        board.numberOfBlackKings = 0;
        board.numberOfRedCheckers = 3;
        board.numberOfRedKings = 1;

    }

    @Test
    public void testRegularCheckerMovement() {
        BoardField checker = board.getBoardField(new Point(7,0));

        Move move = new Move(new Point(7,0),new Point(6,1));
        Move move1 = new Move(new Point(7,0), new Point(5,2));

        boolean isValidMove = validator.isValidMove(red, checker, move, false);

        boolean isNotValidMove = validator.isValidMove(red, checker,move1, false);

        Assert.assertTrue(isValidMove);
        Assert.assertFalse(isNotValidMove);




    }

    @Test
    public void testKingMovement() {
        BoardField king = board.getBoardField(new Point(1,2));

        Move move = new Move(new Point(1,2),new Point(0,1));
        Move move1 = new Move(new Point(1,2), new Point(2,3));
        Move move2 = new Move(new Point(1,2), new Point(6,6));

        boolean isValidMove = validator.isValidMove(red, king, move, false);

        boolean isAlsoValidMove = validator.isValidMove(red, king,move1, false);

        boolean isInvalidMove = validator.isValidMove(red, king, move2, false);

        Assert.assertTrue(isValidMove);
        Assert.assertTrue(isAlsoValidMove);
        Assert.assertFalse(isInvalidMove);
    }
}

package junitTest;
import Board.Board;
import Interfaces.IBoard;
import Moves.Move;
import Player.Player;
import Board.BoardField;
import Board.BoardLogic;
import enums.CheckerType;
import enums.Side;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.List;

/**
 * Created by malthe on 11/19/17.
 */
public class testBoardLogic {

    BoardLogic boardLogic;
    Player black;
    Player red;
    Board board;

    @Before
    public void init() {
        this.boardLogic = new BoardLogic();
        this.black = new Player(Side.BLACK);
        this.red = new Player(Side.RED);
        this.board = new Board(red, black);
        board.init();
    }

    @Test
    public void testgetAllValidMoves() {

        List<Move> moves =  boardLogic.getAllvalideMoves(red, (IBoard)board);


        List<Move> movesBlack =  boardLogic.getAllvalideMoves(black, (IBoard)board);

        int test = 10;

    }

    @Test
    public void testValidMoves() {

    }

    @Test
    public void testGetJumpMoves() {

    }

    @Test
    public void testGetAllCheckers() {

        List<BoardField> allCheckers = boardLogic.getAllCheckers(this.red, this.board);
        List<BoardField> allCheckersBlack = boardLogic.getAllCheckers(this.black, this.board);
        Assert.assertTrue(allCheckers.size()==12);
        Assert.assertTrue(allCheckersBlack.size() == 12);
        int x= 10;

    }

    @Test
    public void testMakeMove() {
        BoardField empty = new BoardField(false);
        BoardField red = new BoardField(this.red, CheckerType.RED, true);
        BoardField redKing = new BoardField(this.red, CheckerType.RED_KING, true);
        BoardField black = new BoardField(this.black, CheckerType.BLACK, true);

        BoardField[][] field = new BoardField[][]{
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, redKing, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, red, empty, empty, empty, empty, empty},
                { empty, black, empty, empty, empty, empty, empty, empty},
                { red, empty, empty, empty, red, empty, red, empty},

        };

        Board board = new Board(field);

        Move move = new Move(new Point(6,1), new Point(4,3), true);
        boardLogic.makeMove(board, move ,Side.BLACK, new Player(Side.BLACK));

        Assert.assertTrue(board.getBoardField(new Point(4,3)).isOccupied);
        Assert.assertFalse(board.getBoardField(new Point(5,2)).isOccupied);

        BoardField[][] field1 = new BoardField[][]{
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, redKing, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, red, empty, empty, empty, empty, empty, empty},
                { empty, empty, black, empty, empty, empty, empty, empty},
                { empty, black, empty, empty, empty, empty, empty, empty},
                { red, empty, empty, empty, red, empty, red, empty},

        };

        Board board1 = new Board(field1);
        Move move1 = new Move(new Point(5,2), new Point(3,0), true);
        boardLogic.makeMove(board1, move1, Side.BLACK, new Player(Side.BLACK));
        boolean test = board1.getBoardField(new Point(3,0)).isOccupied;
        boolean test2 = board1.getBoardField(new Point(5,2)).isOccupied;
        boolean test3 = board1.getBoardField(new Point(4,1)).isOccupied;
        Assert.assertTrue(test);
        Assert.assertFalse(test2);
        Assert.assertFalse(test3);


    }

    @Test
    public void testTakeFreeJumpMove() {

        Board boardTest = new Board(new Player(Side.RED), new Player(Side.BLACK));
        boardTest.init();
        //boardTest.print();
        boardTest.setBoardField(4,3, CheckerType.BLACK, new Player(Side.RED));
        boardTest.setBoardField(1,0, CheckerType.EMPTY, null);
        boardTest.getBoardField(new Point(1,0)).isOccupied = false;
        boardTest.print();
        Move move = new Move(new Point(5,4), new Point(3,2), true);
        //BoardField field = board.getBoardField(new Point(3,4));
        boardLogic.makeMove(boardTest, move, Side.RED, new Player(Side.RED));
        boardTest.print();


    }
}

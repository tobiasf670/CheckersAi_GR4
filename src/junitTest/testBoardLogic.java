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

        board = new Board(this.red,black);
        board.init();
        board.numberOfBlackCheckers = 0;
        board.numberOfBlackKings = 1;
        board.numberOfRedCheckers = 3;
        board.numberOfRedKings = 1;
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

    /*
    public void testTakeFreeJumpMove() {

        Board boardTest = new Board(new Player(Side.WHITE), new Player(Side.BLACK));
        boardTest.init();
        //boardTest.print();
        boardTest.setBoardField(4,3, CheckerType.BLACK, new Player(Side.WHITE));
        boardTest.setBoardField(1,0, CheckerType.EMPTY, null);
        boardTest.getBoardField(new Point(1,0)).isOccupied = false;
        boardTest.print();
        Move move = new Move(new Point(5,4), new Point(3,2), true);
        //BoardField field = board.getBoardField(new Point(3,4));
        boardLogic.makeMove(boardTest, move, Side.WHITE, new Player(Side.WHITE));
        boardTest.print();


    }*/

    @Test
    public void testKingMove() {
        BoardField toMove = board.getBoardField(new Point(7,0));
        Move move = new Move(new Point(7,0), new Point(2,0), false);
        board.getBoardField(new Point(0,2)).checkerType = CheckerType.RED;
        boolean result = boardLogic.isKingMove(toMove,move,red);
        //Assert.assertTrue(result);
    }

    @Test
    public void testAllValidMovesContainsKingMoves() {

        BoardField field = board.getBoardField(new Point(7,2));
        field.checkerType = CheckerType.BLACK_KING;
        field.owner = black;
        board.print();
        List<Move> blackMoves = this.boardLogic.getAllvalideMoves(black, board);
        Assert.assertTrue(blackMoves.size()==7);

        BoardField field1 = board.getBoardField(new Point(5,0));
        field1.checkerType = CheckerType.EMPTY;
        field1.isOccupied = false;
        //field
        blackMoves = this.boardLogic.getAllvalideMoves(black, board);
        Assert.assertTrue(blackMoves.size()==1);

        BoardField field2 = board.getBoardField(new Point(5,4));
        field2.checkerType = CheckerType.EMPTY;
        field2.isOccupied = false;
        blackMoves = this.boardLogic.getAllvalideMoves(black, board);
        Assert.assertTrue(blackMoves.size()==2);

        boardLogic.makeMove(board, new Move(new Point(7,2), new Point(5,4),true),Side.BLACK, black);
        boardLogic.makeMove(board, new Move(new Point(5,4), new Point(4,3),false),Side.BLACK, black);
        board.print();
        //boardLogic.makeMove(board, new Move(new Point(7,2), new Point(5,0),true),Side.BLACK, black);

        int x = 10;
    }

    @Test
    public void testValidMovesWhenJump() {

        BoardField field = board.getBoardField(new Point(1,6));
        field.owner = red;
        field.checkerType = CheckerType.RED;
        BoardField field1 = board.getBoardField(new Point(2,7));
        //field1.owner = null;
        field1.checkerType = CheckerType.EMPTY;
        field1.isOccupied = false;
        board.print();
        List<Move> moves = this.boardLogic.getAllvalideMoves(black,board);

        int x = 10;
    }
}

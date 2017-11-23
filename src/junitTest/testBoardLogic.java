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
        Move move = new Move(new Point(6,1), new Point(5,3));
        boardLogic.makeMove(board, move ,Side.BLACK, new Player(Side.BLACK));
        board.print();
        int x = 10;

    }
}

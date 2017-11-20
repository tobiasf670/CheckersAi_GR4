package junitTest;
import Board.Board;
import Interfaces.IBoard;
import Moves.Move;
import Player.Player;
import Board.BoardField;
import Board.BoardLogic;
import enums.Side;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
}

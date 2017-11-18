package junitTest;

import Player.Player;
import Board.BoardField;
import enums.CheckerType;
import enums.Side;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by malthe on 11/17/17.
 */
public class testBoardField {
    Player redPlayer;
    BoardField red;
    BoardField redKing;
    Player blackPlayer;
    BoardField black;
    BoardField blackKing;
    BoardField empty;

    @Before
    public void init() {
        this.redPlayer = new Player(Side.RED);
        this.blackPlayer = new Player(Side.BLACK);
        this.red = new BoardField(redPlayer, CheckerType.RED, true);
        this.black = new BoardField(blackPlayer, CheckerType.BLACK, true);
        this.redKing = new BoardField(redPlayer, CheckerType.RED_KING, true);
        this.blackKing = new BoardField(blackPlayer, CheckerType.BLACK_KING, true);
        this.empty = new BoardField(false);


    }

    @Test
    public void testEquals() {
        //Assert.assertTrue(red.equals(red));
        //Assert.assertFalse(red.equals(black));
        Assert.assertFalse(black.equals(empty));
        //Assert.assertFalse(redKing.equals(blackKing));
    }
}

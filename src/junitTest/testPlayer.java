package junitTest;
import Player.Player;
import enums.Side;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by malthe on 11/17/17.
 */
public class testPlayer {
    Player red;
    Player black;
    @Before
    public void init() {
        red = new Player(Side.RED);
        black = new Player(Side.BLACK);
    }

    @Test
    public void testEquals() {
        Assert.assertTrue(red.equals(red));
        Assert.assertFalse(red.equals(black));
    }
}

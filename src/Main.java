import Board.Board;
import Board.BoardLogic;
import Moves.Move;
import Player.Player;
import enums.Side;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malthe on 11/17/17.
 */
public class Main
{


    public static void main(String[] args) {
         List<Move> all = new ArrayList<>();
        Player p1 = new Player(Side.RED);
        Player p2 = new Player(Side.BLACK);
         Board board = new Board(p1,p2);
        BoardLogic BL = new BoardLogic();
        board.init();
        all = BL.getAllvalideMoves(p1,board);
        board.print();
        Board testClone = board.clone();
       // System.out.println(all.size());
        //System.out.println("Moves"+all.toString());
        System.out.println("Moves"+BL.getAllvalideMoves(p1,testClone));

    }
}

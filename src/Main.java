import AI.AImoveCalculator;
import Board.Board;
import Board.BoardLogic;
import Heuristics.HeuristicCalculator;
import Moves.Move;
import Moves.MoveValidator;
import Player.Player;
import enums.Side;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by malthe on 11/17/17.
 */
public class Main
{


    public static void main(String[] args) {
        List<Move> all = new ArrayList<>();

                BoardLogic BL = new BoardLogic();
                BL.createPlayer(Side.RED);
                BL.createPlayer(Side.BLACK);
                Player p1 = BL.getPlayers().get(0);
                Player p2 = BL.getPlayers().get(1);
                Board board = new Board(p1,p2);
                board.init();
                Board testClone = board.clone();

                MoveValidator vm = new MoveValidator();
                HeuristicCalculator hc = new HeuristicCalculator();
                AImoveCalculator ai = new AImoveCalculator(vm,hc,BL);
            //double p4 =    ai.minimax(board,p1,4,true,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);
        Move m = null;
        m = ai.bestMove(testClone,p1);
        System.out.println("");
        System.out.println("*************");
               System.out.println("Det bedste move jeg kan lave er:   "+m);
               board.print();
    }
}

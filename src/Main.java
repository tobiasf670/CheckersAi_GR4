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
import java.util.Scanner;

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
                MoveValidator vm = new MoveValidator();
                HeuristicCalculator hc = new HeuristicCalculator();
                AImoveCalculator ai = new AImoveCalculator(vm,hc,BL);

        List<Move> getAllMoves = BL.getAllvalideMoves(p1,board);
        BL.makeMove(board,getAllMoves.get(0),p1.side,p1);
        board.print();
        BL.makeMove(board,ai.bestMove(board,p2),p2.side,p2);
        board.print();

              /*  while(true){
                    board.print();
                    System.out.println("Make a move");
                    List<Move> getAllMoves = BL.getAllvalideMoves(p1,board);
                    System.out.println(getAllMoves);
                    Scanner scanner = new Scanner(System.in);

                    int input = scanner.nextInt();
                    BL.makeMove(board,getAllMoves.get(input),p1.side,p1);

                    Move aiMove = ai.bestMove(board.clone(),p2);
                    BL.makeMove(board,aiMove,p2.side,p2);

                }
                */
    }
}

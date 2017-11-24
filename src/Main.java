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
                Player red = new Player(Side.RED);
                Player black = new Player(Side.BLACK);
                Board board = new Board(red,black);
                board.init();
                Board test = new Board(red,black);
                test.init();
                MoveValidator vm = new MoveValidator();
                HeuristicCalculator hc = new HeuristicCalculator();
                AImoveCalculator ai = new AImoveCalculator(vm,hc,BL);

        List<Move> getAllMoves = BL.getAllvalideMoves(red,board);
        BL.makeMove(test,getAllMoves.get(0),red.side,red);
        BL.makeMove(board,getAllMoves.get(0),red.side,red);
        board.print();
        Move bestMove = ai.bestMove(test,black);
        BL.makeMove(board,bestMove,black.side,black);
       System.out.println(bestMove);
        board.print();
        //bestMove = ai.bestMove(board,black);
        //System.out.println(bestMove);
        //BL.makeMove(board,bestMove,black.side,black);
       //board.print();
        test.print();

        //BL.makeMove(board,ai.bestMove(board,black),black.side,black);
        //board.print();

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

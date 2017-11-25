package Controller;
import Board.Board;
import AI.AImoveCalculator;
import Board.BoardLogic;
import Heuristics.HeuristicCalculator;
import Moves.Move;
import Moves.MoveValidator;
import Player.Player;
import enums.Side;
import Moves.Move;
import java.util.List;

import java.awt.*;
import java.util.Scanner;

/**
 * Created by malthe on 11/24/17.
 */
public class GameController {

    private Player red;
    private Player black;
    private Board board;
    MoveValidator moveValidator;
    private AImoveCalculator aImoveCalculator;
    private BoardLogic boardLogic;
    private HeuristicCalculator heuristicCalculator;
    private boolean isHumanTurn;

    public GameController() {

        this.red = new Player(Side.RED);
        this.black = new Player(Side.BLACK);
        this.board = new Board(red,black);
        board.init();
        this.moveValidator = new MoveValidator();
        this.heuristicCalculator = new HeuristicCalculator();
        this.boardLogic = new BoardLogic();
        this.aImoveCalculator = new AImoveCalculator(moveValidator, heuristicCalculator, this.boardLogic);
        isHumanTurn = true;


    }

    public void gameLoop() {
        String aiMoveMsg ="";
        while(true) {
            board.print();
            if(aiMoveMsg.length()>1) {
                System.out.println(aiMoveMsg);
            }
            if(isHumanTurn) {
                Move userMove = readUserInput();
                boardLogic.makeMove(this.board, userMove, Side.RED, red);
                if(userMove.isJumpMove && boardLogic.getJumpMoves(board,red).size()>0) {
                    isHumanTurn = true;
                } else {
                    isHumanTurn = false;
                }


            } else {
                Move aiMove = aImoveCalculator.bestMove(board, black);
                boardLogic.makeMove(board,aiMove,Side.BLACK, black);
                aiMoveMsg = "Last turn the AI moved "+aiMove;
                if(!aiMove.isJumpMove)
                    isHumanTurn = true;

            }
            System.out.println("");
        }
    }

    public Move readUserInput() {

        List<Move> mandatoryMoves = boardLogic.getAllvalideMoves(red,board);
        System.out.print("Mandatory Moves : ");
        for(Move move2 : mandatoryMoves) {
            if(move2.isJumpMove) {
                System.out.print(move2+ " ");
            }
        }
        System.out.println("");
        System.out.println("Enter your move : ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        char[] lineArr = line.toCharArray();
        Move move;
        try{
            move = new Move(new Point(Integer.parseInt(lineArr[0]+""),Integer.parseInt(lineArr[1]+"")),
                    new Point(Integer.parseInt(lineArr[2]+""),Integer.parseInt(lineArr[3]+"")), false);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input, try again.. ");
            return readUserInput();
        }


        for(Move move1 : mandatoryMoves) {
            if(move1.equals(move)) {
                return move1;
            }
        }
        /*if(mandatoryMoves.contains(move)) {
            return move;
        }*/
        System.out.println("Not valid move, try again.. ");
        return readUserInput();
    }


}

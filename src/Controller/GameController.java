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
        Scanner scanner;
        while(true) {
            board.print();
            if(isHumanTurn) {
                Move userMove = readUserInput();
                boardLogic.makeMove(this.board, userMove, Side.RED, red);
                isHumanTurn = false;

            } else {
                Move aiMove = aImoveCalculator.bestMove(board, black);
                boardLogic.makeMove(board,aiMove,Side.BLACK, black);
                isHumanTurn = true;
            }

        }
    }

    public Move readUserInput() {
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

        List<Move> validMoves = boardLogic.validmoves(board.getBoardField(
                new Point(Integer.parseInt(lineArr[0]+""), Integer.parseInt(lineArr[1]+""))), this.board, this.red);

        for(Move move1 : validMoves) {
            if(move1.equals(move)) {
                return move1;
            }
        }
        System.out.println("Not valid move, try again.. ");
        return readUserInput();
    }


}

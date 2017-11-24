package Controller;
import Board.Board;
import AI.AImoveCalculator;
import Board.BoardLogic;
import Heuristics.HeuristicCalculator;
import Moves.Move;
import Moves.MoveValidator;
import Player.Player;
import enums.Side;

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

    public GameController() {

        this.red = new Player(Side.RED);
        this.black = new Player(Side.BLACK);
        this.board = new Board(red,black);
        board.init();
        this.moveValidator = new MoveValidator();
        this.heuristicCalculator = new HeuristicCalculator();
        this.boardLogic = new BoardLogic();
        this.aImoveCalculator = new AImoveCalculator(moveValidator, heuristicCalculator, this.boardLogic);


    }

    public void gameLoop() {
        while(true) {

        }
    }

    public Move readUserInput() {

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        char[] lineArr = line.toCharArray();
        int x = 10;
        try{
            return new Move(new Point(Integer.parseInt(lineArr[0]+""),Integer.parseInt(lineArr[1]+"")),
                    new Point(Integer.parseInt(lineArr[2]+""),Integer.parseInt(lineArr[3]+"")), false);
        } catch (IndexOutOfBoundsException e) {
            return readUserInput();
        }

    }


}

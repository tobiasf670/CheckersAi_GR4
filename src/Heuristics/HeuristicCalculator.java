package Heuristics;

import Board.Board;
import Interfaces.IHeuristicCalculator;
import Player.Player;
import enums.Side;

/**
 * Created by malthe on 11/17/17.
 */
import Board.BoardLogic;
public class HeuristicCalculator implements IHeuristicCalculator{

    BoardLogic logic;

    public HeuristicCalculator() {
        this.logic = new BoardLogic();
    }

    private final double kingFactor = 1.2;
    @Override
    public double CalculateHeuristic(Board board, Player player) {

        double valueForRed = board.numberOfRedCheckers+(board.numberOfRedKings*kingFactor);
        double valueForBlack = board.numberOfBlackCheckers + (board.numberOfBlackKings*kingFactor);
        //double jumpMovefactor = jumpMoveFactor(board, changePlayer(player,board));
        if(player.side == Side.BLACK) {
            double result = valueForBlack-valueForRed;
            return(result);
        }
        double result = valueForRed-valueForBlack;
        return(result);
    }

    private double jumpMoveFactor(Board board, Player player) {
        int jumpMoves = logic.getJumpMoves(board,player).size();
        return 0.2 * jumpMoves;

    }

    public Player changePlayer(Player player, Board board){
        return board.getOpponent(player);
    }
}

package Heuristics;

import Board.Board;
import Interfaces.IHeuristicCalculator;
import Player.Player;
import enums.Side;

/**
 * Created by malthe on 11/17/17.
 */
public class HeuristicCalculator implements IHeuristicCalculator{

    private final double kingFactor = 1.2;
    @Override
    public double CalculateHeuristic(Board board, Player player) {

        if(player.side == Side.BLACK) {
            return(board.numberOfBlackCheckers + (board.numberOfBlackKings*kingFactor)
            -(board.numberOfRedCheckers+(board.numberOfRedKings*kingFactor)));
        }
        return(board.numberOfRedCheckers + (board.numberOfRedKings*kingFactor)
                -(board.numberOfBlackCheckers+(board.numberOfBlackKings*kingFactor)));
    }
}

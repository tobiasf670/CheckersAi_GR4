package AI;
import Board.Board;
import Interfaces.*;
import Player.Player;
import Moves.Move;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.max;

/**
 * Created by malthe on 11/19/17.
 */
public class AImoveCalculator implements IAi {

    IMoveValidator moveValidator;
    IHeuristicCalculator heuristicCalculator;
    IBoardLogic boardLogic;

    private static final int maxSearchDepth = 8;

    public AImoveCalculator(IMoveValidator moveValidator, IHeuristicCalculator heuristicCalculator,
                            IBoardLogic boardLogic) {
        this.moveValidator = moveValidator;
        this.heuristicCalculator = heuristicCalculator;
        this.boardLogic = boardLogic;
    }

    @Override
    public Move bestMove(IBoard b, Player player) {
        List<Double> heuristicScores = new ArrayList<>();
        List<Move> allValidMoves = boardLogic.getAllvalideMoves(player,b);

        for(Move move : allValidMoves) {
            Board clone = b.clone();
            boardLogic.makeMove(clone,move);
            //for each possible AI move, calculate the heuristic with minimax
            heuristicScores.add(minimax(clone, player,1, true, 0.0,0.0));
        }

        double bestScore = Collections.max(heuristicScores);

        return allValidMoves.get(heuristicScores.indexOf(bestScore));
    }

    private double minimax(Board board, Player player, int searchDepth,
                           boolean maximizingPlayer, double alpha, double beta) {

        //Base case : Recursion ends here
        if(searchDepth > maxSearchDepth) {

            //return the heuristic score
            return heuristicCalculator.CalculateHeuristic(board, player);
        }

        return 0.0;
    }
}

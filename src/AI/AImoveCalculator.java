package AI;
import Board.Board;
import Interfaces.*;
import Player.Player;
import Moves.Move;
import enums.Side;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.max;

/**
 * Created by malthe on 11/19/17.
 * AI - Logic
 */
public class AImoveCalculator implements IAi {

    IMoveValidator moveValidator;
    IHeuristicCalculator heuristicCalculator;
    IBoardLogic boardLogic;

    private static final int maxSearchDepth = 8;

    //Constructor.
    public AImoveCalculator(IMoveValidator moveValidator, IHeuristicCalculator heuristicCalculator,
                            IBoardLogic boardLogic) {
        this.moveValidator = moveValidator;
        this.heuristicCalculator = heuristicCalculator;
        this.boardLogic = boardLogic;
    }

    //Calculates the best move for the AI.
    @Override
    public Move bestMove(IBoard b, Player player) {
        List<Double> heuristicScores = new ArrayList<>();
        List<Move> allValidMoves = boardLogic.getAllvalideMoves(player,b);

        for(Move move : allValidMoves) {
            Board clone = b.clone();
            boardLogic.makeMove(clone,move,player.side,player);
            //for each possible AI move, calculate the heuristic with minimax
            heuristicScores.add(minimax(clone, player,4, true, 0.0,0.0));
        }
        System.out.println("size : "+heuristicScores.size());
        double bestHeuristic = 0.0;
        int indexOfBest = 0;
        for(int i = 0; i<heuristicScores.size(); i++){
            if(heuristicScores.get(i) >= bestHeuristic){
                indexOfBest = i;
                bestHeuristic = heuristicScores.get(i);

            }
        }
        //double bestScore = Collections.max(heuristicScores);

        return allValidMoves.get(heuristicScores.indexOf(bestHeuristic));
    }

    //MinMax with alpha beta phruning algorithm created.
    public double minimax(Board board, Player player, int searchDepth,
                           boolean maximizingPlayer, double alpha, double beta) {

        //Base case : Recursion ends here
        if(searchDepth == 0) {
            //return the heuristic score
            return heuristicCalculator.CalculateHeuristic(board, player);
        }

        Board boards = null;


           List<Move> moveICanMake = boardLogic.getAllvalideMoves(player,board) ;


        double v = 0;

        if(maximizingPlayer){
            v = Double.NEGATIVE_INFINITY;
            for(int i = 0;i< moveICanMake.size();i++) {
               
                boards = board.clone();

                boardLogic.makeMove(boards,moveICanMake.get(i),player.side,player);

                double res = minimax(boards,changePlayer(player, board),searchDepth -1,!maximizingPlayer,alpha,beta);

                v = Math.max(res,v);
                alpha = Math.max(alpha,v);

                if(alpha >= beta){
                   break;
                }

            }
        }
          else
        {
            v = Double.POSITIVE_INFINITY;

            for(int i = 0;i<moveICanMake.size();i++){
                boards = board.clone();
                boardLogic.makeMove(boards,moveICanMake.get(i),player.side,player);
                double res = minimax(boards,changePlayer(player, board),searchDepth-1,!maximizingPlayer,alpha,beta);

                v = Math.min(res,v);
                alpha = Math.min(alpha,v);

                if(alpha>=beta){
                    break;
                }
            }
        }

        return v;
    }

    //Swiches back to player after AI have made a move.
    public Player changePlayer(Player player, Board board){
        return board.getOpponent(player);
    }
}

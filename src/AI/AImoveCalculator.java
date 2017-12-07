package AI;
import Board.Board;
import Interfaces.*;
import Player.Player;
import Moves.Move;
import enums.Side;
import enums.Side;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.Collections.max;

/**
 * Created by malthe on 11/19/17.
 * AI - Logic
 */
public class AImoveCalculator implements IAi {

    IMoveValidator moveValidator;
    IHeuristicCalculator heuristicCalculator;
    IBoardLogic boardLogic;

    private static final int maxSearchDepth = 10;

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
            //for each possible AI move (board state), calculate the heuristic with minimax

            heuristicScores.add(minimax(clone, changePlayer(player, clone),
                    1, false, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));

        }
        double bestHeuristic = Double.NEGATIVE_INFINITY;
        for(int i = 0; i<heuristicScores.size(); i++){
            if(heuristicScores.get(i) >= bestHeuristic){
                bestHeuristic = heuristicScores.get(i);

            }
        }

        //The move we end up making for the AI
        Move toReturn = returnRandomMove(allValidMoves, heuristicScores, bestHeuristic);

        //return allValidMoves.get(heuristicScores.indexOf(bestHeuristic));
        return toReturn;
    }



    //MinMax with alpha beta phruning algorithm created.
    public double minimax(Board board, Player player, int searchDepth,
                           boolean isMaximizingPlayer, double alpha, double beta) {

        //Base case : Recursion ends here
        if(searchDepth == maxSearchDepth) {
            //return the heuristic score
            return heuristicCalculator.CalculateHeuristic(board, player);
        }

        double v = 0;

        if(isMaximizingPlayer){
            v = Double.NEGATIVE_INFINITY;
            for(Move move : boardLogic.getAllvalideMoves(player,board)) {

                Board cloneBoard = board.clone();
                boardLogic.makeMove(cloneBoard,move,player.side,player);
                //If the isumpMove
                if(move.isJumpMove) {
                    //we might as well keep the piece moving if has additional free moves
                    keepMovingIfPossible(cloneBoard,player,move);
                }
                double res = minimax(cloneBoard,changePlayer(player, board),searchDepth +1,
                        !isMaximizingPlayer,alpha,beta);

                v = Math.max(res,v);
                alpha = Math.max(alpha,v);
                //prune
                if(alpha >= beta){
                   break;
                }

            }
        }
          else
        {
            v = Double.POSITIVE_INFINITY;

            for(Move move : boardLogic.getAllvalideMoves(player,board)){

                Board cloneBoard = board.clone();
                boardLogic.makeMove(cloneBoard,move,player.side,player);
                if(move.isJumpMove) {
                    keepMovingIfPossible(cloneBoard,player,move);
                }
                double res = minimax(cloneBoard,changePlayer(player, board),searchDepth+1,
                        !isMaximizingPlayer,alpha,beta);

                v = Math.min(res,v);
                beta = Math.min(alpha,v);
                //prune
                if(alpha>=beta){
                    break;
                }
            }
        }

        return v;
    }

    //Takes all the moves + scores + the best heuristic and this method is the on we end up calling for the final move.
    private Move returnRandomMove(List<Move> moves, List<Double> scores, double bestHeuristic) {

        for(int i = 0; i < scores.size(); i++)
        {
            if(scores.get(i) < bestHeuristic)
            {
                scores.remove(i);
                moves.remove(i);
                i--;
            }
        }
        int randomNum = ThreadLocalRandom.current().nextInt(0, moves.size());


        return moves.get(randomNum);
    }

    private void keepMovingIfPossible(Board board, Player player, Move move) {

        List<Move> movesFromHere = boardLogic.getJumpMoves(board, player);

        for(Move move1 : movesFromHere) {
            if(move1.isJumpMove) {
                if(move.getGoaly() == move1.getStarty() && move.getGoalx() == move1.getStartx()) {
                    boardLogic.makeMove(board,move1,player.side,player);
                    keepMovingIfPossible(board, player, move1);
                }
            } else {
                break;
            }

        }

    }

    //Swiches back to player after AI have made a move.
    public Player changePlayer(Player player, Board board){
        return board.getOpponent(player);
    }
}

package Interfaces;
import Board.Board;
import Player.Player;
/**
 * Created by malthe on 11/17/17.
 */
public interface IHeuristicCalculator {

    double CalculateHeuristic(Board board, Player player);
}

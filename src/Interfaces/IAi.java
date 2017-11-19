package Interfaces;
import Board.Board;
import Player.Player;
import Moves.Move;

/**
 * Created by malthe on 11/19/17.
 */
public interface IAi {

    Move bestMove(IBoard b, Player player);
}

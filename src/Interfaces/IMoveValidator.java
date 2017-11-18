package Interfaces;
import Board.BoardField;
import Player.Player;
import Moves.Move;

/**
 * Created by malthe on 11/17/17.
 */
public interface IMoveValidator {

    boolean isValidMove(Player player, BoardField boardField, Move move, boolean isJumpMove);

    boolean isMovingOwnChecker(Player player, BoardField boardField);

}

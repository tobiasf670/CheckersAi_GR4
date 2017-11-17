package Moves;

import Board.BoardField;
import Interfaces.IMoveValidator;
import Player.Player;
import enums.CheckerType;
import enums.Side;

/**
 * Created by malthe on 11/17/17.
 */
public class MoveValidator implements IMoveValidator {
    @Override
    public boolean isValidMove(Player player, BoardField boardField, Move move) {
        if(!isMovingOwnChecker(player,boardField) || !isMovingHorizontal(move) || !isMovingVertical(move)) {
            return false;
        }
        if(boardField.checkerType == CheckerType.RED || boardField.checkerType == CheckerType.BLACK) {

            //Check if the checker is moving in the right vertical direction
            if(!isMovingCheckerRightDirection(player.side, move)) {
                return false;
            }

        }


        //the kings should pass the above tests

        return true;

    }

    private boolean isMovingHorizontal(Move move) {
        int horizontalMovement = move.getStartx()-move.getGoalx();
        if(horizontalMovement == 1 || horizontalMovement == -1) {
            return true;
        }
        return false;
    }

    private boolean isMovingVertical(Move move) {
         int verticalMovement = move.getStarty() - move.getGoaly();
        if(verticalMovement == 1 || verticalMovement == -1) {
            return true;
        }
        return false;
    }

    private boolean isMovingCheckerRightDirection(Side side, Move move) {
        //Black checkers can only move down
        int verticalMovement = move.getStarty() - move.getGoaly();
        if (side == Side.BLACK && verticalMovement != 1) {
            return false;
        }
        //Red checkers can only move up
        if(side == Side.RED && verticalMovement != -1) {
            return false;
        }

        return true;

    }

    @Override
    public boolean isMovingOwnChecker(Player player, BoardField boardField) {
        if(player.side == boardField.owner.side) {
            return true;
        }
        return false;
    }
}

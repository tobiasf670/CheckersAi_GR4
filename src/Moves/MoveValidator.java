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
    public boolean isValidMove(Player player, BoardField boardField, Move move, boolean isJumpMove) {


        //if moving beyond the border of the board
        if(move.getGoaly() > 7 || move.getGoaly()< 0 || move.getGoalx() >7 || move.getGoalx() < 0) {
            return false;
        }

        if(!isMovingOwnChecker(player,boardField) || !isMovingHorizontal(move, isJumpMove)
                || !isMovingVertical(move, isJumpMove)) {
            return false;
        }
        if(boardField.checkerType == CheckerType.RED || boardField.checkerType == CheckerType.BLACK) {

            //Check if the checker is moving in the right vertical direction
            if(!isMovingCheckerRightDirection(player.side, move, isJumpMove)) {
                return false;
            }

        }


        //the kings should pass the above tests

        return true;

    }

    private int jumpFactor(boolean isJumpMove) {
        if(isJumpMove)
            return 2;

        return 1;
    }

    private boolean isMovingHorizontal(Move move, boolean isJumpMove) {
        int jumpFactor = jumpFactor(isJumpMove);
        int horizontalMovement = move.getStartx()-move.getGoalx();
        if(horizontalMovement == jumpFactor || horizontalMovement == -jumpFactor) {
            return true;
        }
        return false;
    }

    private boolean isMovingVertical(Move move, boolean isJumpMove) {
        int jumpFactor = jumpFactor(isJumpMove);
        int verticalMovement = move.getStarty() - move.getGoaly();
        if(verticalMovement == jumpFactor || verticalMovement == -jumpFactor) {
            return true;
        }
        return false;
    }

    private boolean isMovingCheckerRightDirection(Side side, Move move, boolean isJumpMove) {
        int jumpFactor = jumpFactor(isJumpMove);
        //Black checkers can only move down
        int verticalMovement = move.getStartx() - move.getGoalx();
        if (side == Side.BLACK && verticalMovement != -jumpFactor) {
            return false;
        }
        //Red checkers can only move up
        if(side == Side.RED && verticalMovement != jumpFactor) {
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

    public boolean isMovingUp(Move m) {
        if(m.getGoaly()-m.getStarty() > 0) {
            return true;
        }
        return false;
    }
    public boolean isMovingRight(Move move) {
        if(move.getGoalx()-move.getStartx() > 0) {
            return true;
        }
        return false;
    }
}

package Board;

import Interfaces.IBoardLogic;
import Moves.MoveValidator;
import Player.Player;
import enums.CheckerType;
import Moves.Move;
import enums.Side;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by malthe on 11/17/17.
 */
public class BoardLogic implements IBoardLogic {
    MoveValidator moveValidator;
    public BoardLogic() {
        this.moveValidator = new MoveValidator();
    }

    @Override
    public List<Move> getAllvalideMoves(Player p, Board board) {
        List<Move> moves = new ArrayList<>();

        for(BoardField boardField : board.getBoardFields(p)) {
            moves.addAll(validmoves(boardField, board, p));
        }
        return moves;
    }

    @Override
    public List<Move> validmoves(BoardField boardField, Board board, Player player) {
        Point startPosition = boardField.boardPosition;
        //Right up
        Move move = new Move(startPosition, new Point(startPosition.x+1, startPosition.y+1));
        //Left up
        Move move1 = new Move(startPosition, new Point(startPosition.x-1,startPosition.y+1));
        //Right down
        Move move2 = new Move(startPosition, new Point(startPosition.x+1,startPosition.y-1));
        //Left down
        Move move3 = new Move(startPosition, new Point(startPosition.x-1,startPosition.y-1));

        List<Move> validMoves = new ArrayList<>();

        if(moveValidator.isValidMove(player, boardField,move, false) &&
                !isFieldTaken(board,move))
            validMoves.add(move);

        if(moveValidator.isValidMove(player, boardField, move1, false) &&
                !isFieldTaken(board,move1))
            validMoves.add(move1);

        if(moveValidator.isValidMove(player, boardField, move2, false) &&
                !isFieldTaken(board,move2))
            validMoves.add(move2);

        if(moveValidator.isValidMove(player, boardField, move3, false) &&
                !isFieldTaken(board,move3))
            validMoves.add(move3);

        validMoves.addAll(getJumpMoves(boardField, board));

        return validMoves;
    }

    private boolean isFieldTaken(Board board, Move move) {
        if(board.getBoardField(new Point(move.getGoalx(),move.getGoaly())).isOccupied)
            return false;

        return true;
    }

    private List<Move> getJumpMoves(BoardField boardField, Board board) {
        List<Move> jumpMoves = new ArrayList<>();
        Point startPosition = boardField.boardPosition;
        //Right up
        BoardField rightUp = board.getBoardField(new Point(startPosition.x+1,startPosition.y+1));
        //Left up
        BoardField leftUp = board.getBoardField(new Point(startPosition.x-1, startPosition.y+1));
        //Right down
        BoardField rightDown = board.getBoardField(new Point(startPosition.x+1,startPosition.y-1));

        //Left down
        BoardField leftDown = board.getBoardField(new Point(startPosition.x-1,startPosition.y-1));

        Move rightUpJumpMove = new Move(startPosition, new Point(startPosition.x+2, startPosition.y+2));
        if(rightUp.isOccupied && rightUp.owner.side!=boardField.owner.side &&
                moveValidator.isValidMove(boardField.owner, boardField,rightUpJumpMove, true) &&
                !isFieldTaken(board,rightUpJumpMove)) {
                jumpMoves.add(rightUpJumpMove);
        }

        Move leftUpJumpMove = new Move(startPosition, new Point(startPosition.x-2, startPosition.y+2));
        if(leftUp.isOccupied && leftUp.owner.side!=boardField.owner.side &&
                moveValidator.isValidMove(boardField.owner, boardField,leftUpJumpMove, true) &&
                !isFieldTaken(board,leftUpJumpMove)) {
            jumpMoves.add(leftUpJumpMove);
        }

        Move rightDownJumpMove = new Move(startPosition, new Point(startPosition.x+2, startPosition.y-2));
        if(rightDown.isOccupied && rightDown.owner.side!=boardField.owner.side &&
                moveValidator.isValidMove(boardField.owner, boardField,rightDownJumpMove, true) &&
                !isFieldTaken(board,rightDownJumpMove)) {
            jumpMoves.add(rightDownJumpMove);
        }

        Move leftDownJumpMove = new Move(startPosition, new Point(startPosition.x-2, startPosition.y-2));
        if(leftDown.isOccupied && leftDown.owner.side!=boardField.owner.side &&
                moveValidator.isValidMove(boardField.owner, boardField,leftDownJumpMove, true)&&
                !isFieldTaken(board,leftDownJumpMove)) {
            jumpMoves.add(leftDownJumpMove);
        }


        return jumpMoves;

    }




    @Override
    public List<Move> getJumpMoves(Board b, Player p) {
        return null;
    }

    @Override
    public List<BoardField> getAllCheckers(Player p, Board board) {
        List<BoardField> allCheckers = new ArrayList<BoardField>();
        for(int i = 0; i <8; i++) {
            for(int j = 0; j <8; j++) {
                BoardField field = board.getBoardField(new Point(i,j));
                if(field.isOccupied && field.owner.equals(p)) {
                    allCheckers.add(field);
                }
            }
        }
        return allCheckers;
    }

    @Override
    public Boolean makeMove(Move m) {
        return null;
    }
}

package Board;

import Interfaces.IBoard;
import Interfaces.IBoardLogic;
import Moves.MoveValidator;
import Player.Player;
import enums.CheckerType;
import Moves.Move;
import enums.Side;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by malthe on 11/17/17.
 */
public class BoardLogic implements IBoardLogic {
    MoveValidator moveValidator;
    List<Player> Players = new ArrayList<Player>();
    public BoardLogic() {
        this.moveValidator = new MoveValidator();
    }

    @Override
    public List<Move> getAllvalideMoves(Player p, IBoard board) {
        List<Move> moves = new ArrayList<>();
        List<BoardField> playerFields = board.getBoardFields(p);
        for(BoardField boardField : playerFields) {
            moves.addAll(validmoves(boardField, board, p));
        }
        return filterForForcedMoves(moves);
    }

    private List<Move> filterForForcedMoves(List<Move> moves) {
        boolean hasForCedMoves = false;
        for(Move move : moves) {
            if(move.isJumpMove) {
                hasForCedMoves = true;
                break;
            }
        }
        if(hasForCedMoves) {
            Iterator<Move> iterator = moves.iterator();
            while(iterator.hasNext()) {
                if(!iterator.next().isJumpMove) {
                    iterator.remove();
                }
            }
        }
        return moves;
    }

    @Override
    public List<Move> validmoves(BoardField boardField, IBoard board, Player player) {
        List<Move> mandatoryJumpMoves = getJumpMoves(boardField, board);
        if(!mandatoryJumpMoves.isEmpty()) {
            return mandatoryJumpMoves;
        }
        Point startPosition = boardField.boardPosition;
        //Right up
        Move move = new Move(startPosition, new Point(startPosition.x+1, startPosition.y+1),false);
        //Left up
        Move move1 = new Move(startPosition, new Point(startPosition.x-1,startPosition.y+1), false);
        //Right down
        Move move2 = new Move(startPosition, new Point(startPosition.x+1,startPosition.y-1), false);
        //Left down
        Move move3 = new Move(startPosition, new Point(startPosition.x-1,startPosition.y-1), false);

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

        //validMoves.addAll(getJumpMoves(boardField, board));

        return validMoves;
    }

    private boolean isFieldTaken(IBoard board, Move move) {


        if(!board.getBoardField(new Point(move.getGoalx(),move.getGoaly())).isOccupied) {

            return false;
        }
        return true;
    }

    private boolean isValidBoardPosition(Point p) {
        if(p.x >7 || p.y >7 || p.x <0 || p.y <0)
            return false;
        return true;
    }

    public List<Move> getJumpMoves(BoardField boardField, IBoard board) {
        List<Move> jumpMoves = new ArrayList<>();

        Point startPosition = boardField.boardPosition;
        //Right up
        Point rightUpGoal = new Point(startPosition.x+1,startPosition.y-1);
        Point rightUpJumpMoveGoal = new Point(startPosition.x+2, startPosition.y-2);
        if(isValidBoardPosition(rightUpGoal)&&isValidBoardPosition(rightUpJumpMoveGoal)) {
            BoardField rightUp = board.getBoardField(rightUpGoal);
            Move rightUpJumpMove = new Move(startPosition, rightUpJumpMoveGoal, true);
            if(rightUp.isOccupied && rightUp.owner.side!=boardField.owner.side &&
                    moveValidator.isValidMove(boardField.owner, boardField,rightUpJumpMove, true) &&
                    !isFieldTaken(board,rightUpJumpMove)) {
                jumpMoves.add(rightUpJumpMove);
            }
        }

        //Left up
        Point leftUpGoal = new Point(startPosition.x-1, startPosition.y-1);
        Point leftUpJumpMoveGoal = new Point(startPosition.x-2, startPosition.y-2);
        if(isValidBoardPosition(leftUpGoal)&&isValidBoardPosition(leftUpJumpMoveGoal)) {
            BoardField leftUp = board.getBoardField(leftUpGoal);
            Move leftUpJumpMove = new Move(startPosition, leftUpJumpMoveGoal,true);
            if(leftUp.isOccupied && leftUp.owner.side!=boardField.owner.side &&
                    moveValidator.isValidMove(boardField.owner, boardField,leftUpJumpMove, true) &&
                    !isFieldTaken(board,leftUpJumpMove)) {
                jumpMoves.add(leftUpJumpMove);
            }
        }

        //Right down
        Point rightDownGoal = new Point(startPosition.x+1,startPosition.y+1);
        Point rightDownJumpMoveGoal = new Point(startPosition.x+2, startPosition.y+2);

        if(isValidBoardPosition(rightDownGoal) && isValidBoardPosition(rightDownJumpMoveGoal)) {
            BoardField rightDown = board.getBoardField(rightDownGoal);
            Move rightDownJumpMove = new Move(startPosition, rightDownJumpMoveGoal, true);
            if(rightDown.isOccupied && rightDown.owner.side!=boardField.owner.side &&
                    moveValidator.isValidMove(boardField.owner, boardField,rightDownJumpMove, true) &&
                    !isFieldTaken(board,rightDownJumpMove)) {
                jumpMoves.add(rightDownJumpMove);
            }
        }


        //Left down
        Point leftDownGoal = new Point(startPosition.x-1,startPosition.y+1);
        Point leftDownJumpMoveGoal = new Point(startPosition.x-2, startPosition.y+2);
        if(isValidBoardPosition(leftDownGoal)&&isValidBoardPosition(leftDownJumpMoveGoal)) {
            BoardField leftDown = board.getBoardField(leftDownGoal);
            Move leftDownJumpMove = new Move(startPosition, leftDownJumpMoveGoal, true);
            if(leftDown.isOccupied && leftDown.owner.side!=boardField.owner.side &&
                    moveValidator.isValidMove(boardField.owner, boardField,leftDownJumpMove, true)&&
                    !isFieldTaken(board,leftDownJumpMove)) {
                jumpMoves.add(leftDownJumpMove);
            }
        }


        return jumpMoves;

    }




    @Override
    public List<Move> getJumpMoves(Board b, Player p) {

        List<Move> jumpMoves = new ArrayList<>();

        int i = 0;

        for(BoardField boardField : getAllCheckers(p,b)) {

            i++;
            jumpMoves.addAll(getJumpMoves(boardField,b));
        }

        return jumpMoves;
    }

    @Override
    public List<BoardField> getAllCheckers(Player p, Board board) {
        List<BoardField> allCheckers = new ArrayList<BoardField>();
        for(int i = 0; i <8; i++) {
            for(int j = 0; j <8; j++) {
                BoardField field = board.getBoardField(new Point(i,j));
                if(field.owner != null){
                    if(field.isOccupied && field.owner.equals(p)) {
                        allCheckers.add(field);
                    }
                }

            }
        }
        return allCheckers;
    }

    @Override
    public void createPlayer( Side s) {
        Player p = new Player(s);

        Players.add(p);

    }

    @Override
    public List<Player> getPlayers() {
        return Players;
    }

    @Override
    public boolean makeMove(Board board, Move m, Side s,Player p) {

        if(m == null){
            return false;
        }

        int sx = m.getStartx();
        int sy = m.getStarty();
        int gx = m.getGoalx();
        int gy = m.getGoaly();
        Point start = new Point(sx,sy);
        Point goal = new Point(gx,gy);

        BoardField startField = board.getBoardField(start);
        /*if(s == Side.BLACK){
            type = CheckerType.BLACK;
        }
        else{
            type = CheckerType.WHITE;
        }*/

        CheckerType type = startField.checkerType;
        board.removeChecker(start);
        board.setBoardField(gx,gy,type,p);
        BoardField goalField = board.getBoardField(goal);
        
        if(isKingMove(goalField, m, p)) {
            promoteToKing(board, goalField);
        }

        if(m.isJumpMove) {
            removeEnemyPiece(m, p, board);
            //check if additional "free" jump moves has to be performed

            //Let the AI run alphabeta for one additional turn instead
           // takeFreeJumpMoves(goal, board, s, p);
        }

        return true;
    }

    public void promoteToKing(Board board, BoardField boardField) {
        if(boardField.owner.side == Side.RED) {
            boardField.checkerType = CheckerType.RED_KING;
        } else {
            boardField.checkerType = CheckerType.BLACK_KING;
        }
    }

    /*private void takeFreeJumpMoves(Point position, Board board, Side side, Player player) {
        BoardField start = board.getBoardField(new Point(position.x,position.y));
        List<Move> jumpMoves = getJumpMoves(start,board);

        for(Move move : jumpMoves) {
            if(move.getStartx() == position.x && move.getStarty() == position.y) {
                makeMove(board, move, side, player);
                break;
            }
        }
    }*/



    private void removeEnemyPiece(Move move, Player player, Board b) {
        int checkerToRemoveX = move.getStartx();
        int checkerToRemoveY = move.getStarty();
        if(moveValidator.isMovingUp(move)) {
            checkerToRemoveY++;
        } else {
            checkerToRemoveY--;
        }
        if(moveValidator.isMovingRight(move)) {
            checkerToRemoveX++;
        } else {
            checkerToRemoveX--;
        }
        b.updateBoardScore(new Point(checkerToRemoveX, checkerToRemoveY));
        b.removeChecker(new Point(checkerToRemoveX, checkerToRemoveY));

    }

    public boolean isKingMove(BoardField field, Move move, Player player) {
        if(field.checkerType == CheckerType.BLACK || field.checkerType == CheckerType.RED) {
            if(player.side == Side.RED) {
                if(move.getGoalx() == 0) {
                    return true;
                }
            } else if(player.side == Side.BLACK) {
                if(move.getGoalx() == 7) {
                    return true;
                }
            }
        }

        return false;
    }
}

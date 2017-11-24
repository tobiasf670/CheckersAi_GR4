package Board;

import enums.CheckerType;
import Player.Player;

import java.awt.*;

/**
 * Created by malthe on 11/17/17.
 */
public class BoardField {

    public Player owner;
    public CheckerType checkerType;
    public boolean isOccupied;
    public Point boardPosition;

    public BoardField() {

    }

    public BoardField(Player owner, CheckerType checkerType, boolean isOccupied) {
        this.owner = owner;
        this.checkerType = checkerType;
        this.isOccupied = isOccupied;
    }
    public BoardField(Player owner, CheckerType checkerType, boolean isOccupied, Point boardPosition) {
        this.owner = owner;
        this.checkerType = checkerType;
        this.isOccupied = isOccupied;
        this.boardPosition = boardPosition;
    }

    public BoardField(boolean isOccupied) {
        this.isOccupied = isOccupied;
        this.boardPosition = boardPosition;
        this.checkerType = CheckerType.EMPTY;
    }

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        //Check if o is an instance of Complex or not
          //"null instanceof [type]" also returns false
        if (!(o instanceof BoardField)) {
            return false;
        }

        BoardField object = (BoardField) o;

        if(!object.isOccupied && !isOccupied) {
            return true;
        }
        /*
        if(object.boardPosition.y != boardPosition.y || object.boardPosition.x != boardPosition.x) {
            return false;
        }*/
        if(object.isOccupied && !isOccupied) {
            return false;
        }
        if(!object.isOccupied && isOccupied) {
            return false;
        }
        if(!object.owner.equals(owner)) {
            return false;
        }
        return true;
    }

    public Point getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(int x, int y) {
        if(this.boardPosition==null){
            this.boardPosition = new Point(x,y);
        } else {
            boardPosition.x = x;
            boardPosition.y = y;
        }

    }

    @Override
    public String toString() {
        if(checkerType==CheckerType.BLACK) {
            return "x";
        } else if(checkerType == CheckerType.RED) {
            return "o";
        } else if(checkerType == CheckerType.BLACK_KING) {
            return "X";
        } else if(checkerType == CheckerType.RED_KING) {
            return "O";
        }
        //must be empty
        return "";
    }

    public BoardField copy() {
        BoardField copy = new BoardField(this.owner, this.checkerType, this.isOccupied, this.boardPosition);
        return copy;
    }
}

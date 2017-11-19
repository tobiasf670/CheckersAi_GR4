package Board;
import Player.Player;
import Interfaces.IBoard;
import enums.CheckerType;
import enums.Side;

import java.awt.*;
import java.util.*;
import java.util.List;


public class Board implements IBoard {

    public static final int boardYsize = 8;
    public static final int boardXsize = 8;

    private Player red;
    private Player black;


    public int numberOfBlackCheckers;
    public int numberOfBlackKings;
    public int numberOfRedCheckers;
    public int numberOfRedKings;

    private BoardField[][] gameBoard;

    public Board(Player red, Player black) {

        this.black = black;
        this.red = red;


    }
    //for testing
    public Board(Player red, Player black, BoardField[][] gameBoard) {
        this.black = black;
        this.red = red;
        this.gameBoard = gameBoard;
    }

    public void init() {
        //Empty
        BoardField empty = new BoardField(false);
        //Black occupied

        BoardField black = new BoardField(this.black, CheckerType.BLACK, true);
        BoardField red = new BoardField(this.red, CheckerType.RED, true);

        this.gameBoard = new BoardField[][]{
                { empty, black, empty, black, empty, black, empty, black},
                { black, empty, black, empty, black, empty, black, empty},
                { empty, black, empty, black, empty, black, empty, black},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { red, empty, red, empty, red, empty, red, empty},
                { empty, red, empty, red, empty, red, empty, red},
                { red, empty, red, empty, red, empty, red, empty},

        };


        this.numberOfBlackCheckers = 12;
        this.numberOfBlackKings = 0;
        this.numberOfRedCheckers = 12;
        this.numberOfRedKings = 0;
    }


    @Override
    public Board clone() {
        return null;
    }

    @Override
    public void print() {

    }

    public BoardField getBoardField(Point point) {
        return gameBoard[point.x][point.y];
    }

    @Override
    public boolean removeChecker(Point point) {
        BoardField field = gameBoard[point.x][point.y];
        if(!field.isOccupied) {
            return false;
        }
        field.isOccupied = false;
        if(field.owner.side == Side.BLACK) {
            if(field.checkerType == CheckerType.BLACK_KING) {
                numberOfBlackKings--;
            } else {
                numberOfBlackCheckers--;
            }
        } else {
            if(field.checkerType == CheckerType.RED_KING) {
                numberOfRedKings--;
            } else {
                numberOfRedCheckers--;
            }
        }
        field.checkerType = CheckerType.EMPTY;

        return true;
    }

    @Override
    public List<BoardField> getBoardFields(Player player) {
        List<BoardField> boardFields = new ArrayList<BoardField>();
        for(BoardField boardField[] : gameBoard) {
            for(int i = 0; i<8; i++) {
                if(boardField[i].owner.side==player.side) {
                    boardFields.add(boardField[i]);
                }
            }
        }
        return boardFields;
    }

    public void initTestBoarda() {
        //Empty
        BoardField empty = new BoardField(false);
        BoardField red = new BoardField(this.red, CheckerType.RED, true);
        BoardField redKing = new BoardField(this.red, CheckerType.RED_KING, true);

        this.gameBoard = new BoardField[][]{
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, redKing, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { red, empty, empty, empty, red, empty, red, empty},

        };


        this.numberOfBlackCheckers = 12;
        this.numberOfBlackKings = 0;
        this.numberOfRedCheckers = 12;
        this.numberOfRedKings = 0;
    }
}

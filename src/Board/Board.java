package Board;
import Player.Player;
import Interfaces.IBoard;
import enums.CheckerType;
import enums.Side;

import java.awt.*;
import java.util.*;
import java.util.List;


public class Board implements IBoard {

    //Board size
    public static final int boardYsize = 8;
    public static final int boardXsize = 8;

    //Players
    private Player red;
    private Player black;

    //Statics of the table
    public int numberOfBlackCheckers;
    public int numberOfBlackKings;
    public int numberOfRedCheckers;
    public int numberOfRedKings;

    //Board array
    private BoardField[][] gameBoard;

    //Board contructor
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

    public Board(BoardField[][] cloneBoard) {
        numberOfRedCheckers = 0;
        numberOfBlackCheckers =0;
        numberOfBlackKings =0;
        numberOfRedKings=0;

        this.gameBoard = cloneBoard;

        for(int i =0;i<8;i++){
            for(int j=0;j<8;j++){
                CheckerType type = getBoardField(new Point(i,j)).checkerType;
                if(type == CheckerType.BLACK){
                    numberOfBlackCheckers++;
                }
                else if(type == CheckerType.BLACK_KING){
                    numberOfBlackKings++;
                }
                else if(type == CheckerType.RED){
                    numberOfRedCheckers++;
                }
                else if(type == CheckerType.RED_KING){
                    numberOfRedKings++;
                }
            }
        }
        setBoardCoordinates();

    }

    public void init() {
        //Empty
        BoardField empty = new BoardField(false);
        //Black occupied

        BoardField black = new BoardField(this.black, CheckerType.BLACK, true);
        BoardField red = new BoardField(this.red, CheckerType.RED, true);


        /*this.gameBoard = new BoardField[][]{
                { empty, black, empty, black, empty, black, empty, black},
                { black, empty, black, empty, black, empty, black, empty},
                { empty, black, empty, black, empty, black, empty, black},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { red, empty, red, empty, red, empty, red, empty},
                { empty, red, empty, red, empty, red, empty, red},
                { red, empty, red, empty, red, empty, red, empty},

        };*/

        //Creating the BoardField in a double array with 8 fields in x and y axis.
        this.gameBoard = new BoardField[8][8];

        //The start board
        gameBoard[0] = getBoardRow(this.black,0,true);
        gameBoard[1] = getBoardRow(this.black,1,false);
        gameBoard[2] = getBoardRow(this.black,2,true);
        gameBoard[3] = getEmptyRow(3);
        gameBoard[4] = getEmptyRow(4);
        gameBoard[5] = getBoardRow(this.red,5,false);
        gameBoard[6] = getBoardRow(this.red,6,true);
        gameBoard[7] = getBoardRow(this.red,7,false);
        this.numberOfBlackCheckers = 12;
        this.numberOfBlackKings = 0;
        this.numberOfRedCheckers = 12;
        this.numberOfRedKings = 0;
    }

    private BoardField[] getEmptyRow(int rowNumber) {
        BoardField[] row = new BoardField[8];
        for(int i = 0; i<8; i++) {
            row[i] = new BoardField(false);
            row[i].setBoardPosition(rowNumber,i);
        }
        return row;
    }

    private BoardField[] getBoardRow(Player player, int rowNumber, boolean evenRow) {
        CheckerType type;
        if(player.side == Side.BLACK) {
            type = CheckerType.BLACK;
        } else {
            type = CheckerType.RED;
        }
        BoardField[] row = new BoardField[8];
        if(evenRow) {
            for(int i = 0; i <8; i++) {
                if(i%2==0) {
                    row[i] = new BoardField(false);
                    row[i].setBoardPosition(rowNumber,i);
                } else {
                    row[i] = new BoardField(player, type, true, new Point(rowNumber,i));
                }
            }
        } else {
            for(int i = 0; i <8; i++) {
                if(i%2!=0) {
                    row[i] = new BoardField(false);
                    row[i].setBoardPosition(rowNumber,i);
                } else {
                    row[i] = new BoardField(player, type, true, new Point(rowNumber,i));
                }
            }
        }
        return row;

    }


    @Override
    public Board clone() {

        /*BoardField[][] cloneBoard = new BoardField[8][8];

        for(int i = 0;i<gameBoard.length;i++){
            for (int j =0;j<gameBoard.length;j++){
                cloneBoard [i][j] = gameBoard[i][j];
            }
        }*/
        BoardField [][] copy = copyGameBoard(this.gameBoard);
        Board clone = new Board(this.red, this.black,copy);
        clone.numberOfBlackCheckers = this.numberOfBlackCheckers;
        clone.numberOfRedCheckers = this.numberOfRedCheckers;
        clone.numberOfRedKings = this.numberOfRedKings;
        clone.numberOfBlackKings = this.numberOfBlackKings;
        return clone;

    }

    //Virker fint, Printer ikke korrekt.
    @Override
    public void print() {

        int cellSize = 2;
        int rowLength = boardYsize * cellSize + boardXsize + 1;
        final char[] array = new char[rowLength];
        Arrays.fill(array, '-');
        String rowDivider = new String(array);
        System.out.print("  ");
        for(int f = 0; f<8; f++) {

            System.out.print(f+ "  ");
        }
        System.out.println("");

        for(int i = 0; i < gameBoard.length; i++)
        {

            System.out.println(rowDivider);
            for(int j = 0; j < gameBoard[i].length; j++)
            {
                System.out.printf("|%"+cellSize+"s",gameBoard[i][j]);
                if(j == (gameBoard[i].length - 1)) System.out.println("|");
            }
        }
        System.out.println(rowDivider);

    }

    public BoardField getBoardField(Point point) {
        try{
            return gameBoard[point.x][point.y];
        }catch(IndexOutOfBoundsException e) {
            return null;
        }

        //throw(new IndexOutOfBoundsException(point +"out of bounds"));
    }

    @Override
    public void setBoardField(int row, int col, CheckerType type,Player p) {

        gameBoard[row][col] = new BoardField(p, type, true, new Point(row,col));
        //gameBoard[row][col].checkerType = type ;

    }

    public void updateBoardScore(Point positionOfKilledPiece) {
        BoardField field = gameBoard[positionOfKilledPiece.x][positionOfKilledPiece.y];
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
    }

    //Logic for removing checker
    @Override
    public boolean removeChecker(Point point) {
        BoardField field = gameBoard[point.x][point.y];
        if(!field.isOccupied) {
            return false;
        }
        field.isOccupied = false;
        field.checkerType = CheckerType.EMPTY;

        return true;
    }

    @Override
    public List<BoardField> getBoardFields(Player player) {
        List<BoardField> boardFields = new ArrayList<BoardField>();
        for(int j = 0; j<8; j++) {
            for(int i = 0; i<8; i++) {
                BoardField curField = gameBoard[j][i];
                if(curField.isOccupied)
                if(curField.owner.side==player.side) {
                    boardFields.add(curField);
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
        BoardField black = new BoardField(this.black, CheckerType.BLACK, true);


        this.gameBoard = new BoardField[][]{
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, redKing, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, empty, empty, empty, empty, empty, empty, empty},
                { empty, black, empty, empty, empty, empty, empty, empty},
                { red, empty, empty, empty, red, empty, red, empty},

        };


        this.numberOfBlackCheckers = 12;
        this.numberOfBlackKings = 0;
        this.numberOfRedCheckers = 12;
        this.numberOfRedKings = 0;
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void setBoardCoordinates() {
        for(int i = 0; i<8; i++)
            for(int j = 0; j<8; j++)
                gameBoard[i][j].boardPosition = new Point(i,j);
    }

    public void initTestBoardForJumpMoves() {

        gameBoard[0] = getBoardRow(this.black,0,true);
        gameBoard[1] = getBoardRow(this.black,1,false);
        gameBoard[2] = getBoardRow(this.black,2,true);
        gameBoard[3] = getEmptyRow(3);
        gameBoard[4] = getEmptyRow(4);
        gameBoard[5] = getBoardRow(this.red,5,false);
        gameBoard[6] = getBoardRow(this.red,6,true);
        gameBoard[7] = getBoardRow(this.red,7,false);
        this.numberOfBlackCheckers = 12;
        this.numberOfBlackKings = 0;
        this.numberOfRedCheckers = 12;
        this.numberOfRedKings = 0;

    }

    public Player getOpponent(Player player) {

        if(red.side == player.side) {
            return black;
        }
        return red;

    }
    private BoardField[][] copyGameBoard(BoardField[][] gameBoard) {
        BoardField[][] newBoardField = new BoardField[8][8];

        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                newBoardField[i][j]=gameBoard[i][j].copy();

        return newBoardField;
    }
}

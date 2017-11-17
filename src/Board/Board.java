package Board;
import Player.Player;
import Interfaces.IBoard;
import enums.CheckerType;


public class Board implements IBoard {

    public static final int boardYsize = 8;
    public static final int boardXsize = 8;

    private Player one;
    private Player two;


    public int numberOfBlackCheckers;
    public int numberOfBlackKings;
    public int numberOfRedCheckers;
    public int numberOfRedKings;

    private BoardField[][] gameBoard;

    public Board(Player one, Player two) {

        this.one = one;
        this.two = two;

    }

    private void init() {
        //Empty
        BoardField empty = new BoardField(false);
        //Black occupied
        BoardField black = new BoardField(two, CheckerType.BLACK, true);
        BoardField red = new BoardField(one, CheckerType.RED, true);

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


        this.numberOfRedKings = 0;
        this.numberOfBlackKings = 0;
        this.numberOfRedCheckers = 12;
        this.numberOfRedKings = 12;
    }


    @Override
    public CheckerType[][] clone() {
        return new CheckerType[0][];
    }

    @Override
    public void print() {

    }
}

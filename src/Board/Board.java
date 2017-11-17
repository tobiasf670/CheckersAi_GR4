package Board;

import Interfaces.IBoard;
import enums.CheckerType;


public class Board implements IBoard {

    public int numberOfBlackCheckers;
    public int numberOfBlackKings;
    public int numberOfRedCheckers;
    public int numberOfRedKings;

    private BoardField[][] gameBoard;

    public Board() {

    }


    @Override
    public CheckerType[][] clone() {
        return new CheckerType[0][];
    }

    @Override
    public void print() {

    }
}

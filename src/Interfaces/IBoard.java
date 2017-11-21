package Interfaces;

import Board.BoardField;
import Player.Player;
import enums.CheckerType;
import java.util.List;
import Board.Board;

import java.awt.*;

public interface IBoard {

    Board clone();
    void print();
    boolean removeChecker(Point point);
    List<BoardField> getBoardFields(Player player);
    public BoardField getBoardField(Point point);
    public void setBoardField(int row, int col,CheckerType type,Player p);
}

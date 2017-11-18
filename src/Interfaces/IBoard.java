package Interfaces;

import Board.BoardField;
import Player.Player;
import enums.CheckerType;
import java.util.List;

import java.awt.*;

public interface IBoard {

    CheckerType[][] clone();
    void print();
    boolean removeChecker(Point point);
    List<BoardField> getBoardFields(Player player);
}

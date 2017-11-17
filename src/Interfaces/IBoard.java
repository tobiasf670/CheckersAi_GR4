package Interfaces;

import enums.CheckerType;

import java.awt.*;

public interface IBoard {

    CheckerType[][] clone();
    void print();
    boolean removeChecker(Point point);
}

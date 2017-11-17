package Interfaces;

import enums.CheckerType;

public interface IBoard {

    CheckerType[][] clone();
    void print();
}

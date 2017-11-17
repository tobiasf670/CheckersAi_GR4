package Interfaces;
import Moves.Move;
import enums.CheckerType;
import Player.Player;
import Board.Board;
import enums.Side;

import java.util.List;

public interface IBoardLogic {

    List<Move> getAllvalideMoves(Player p);
    List<Move> validmoves(CheckerType t);

    Boolean makeMove(Move m);
    List<Move> getJumpMoves(Board b, Player p);
}
///
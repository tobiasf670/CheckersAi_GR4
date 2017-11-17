package Interfaces;
import Board.Move;
import enums.CheckerType;
import Player.Player;
import Board.Board;
import java.util.List;

public interface IBoardLogic {

    List<Move> getAllvalideMoves(Player p);
    List<Move> validmoves(CheckerType t);//er ikke helt sikker :)

    Boolean makeMove(Move m);
    List<Move> getJumpMoves(Board b, Player p);
}
///
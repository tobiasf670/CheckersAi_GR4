package Interfaces;
import Moves.Move;
import Player.Player;
import Board.Board;
import Board.BoardField;
import enums.Side;

import java.util.List;

public interface IBoardLogic {

    List<Move> getAllvalideMoves(Player p, IBoard board);
    List<Move> validmoves(BoardField boardField, IBoard board, Player player);

    boolean makeMove(Board board,Move m,Side s,Player p);
    List<Move> getJumpMoves(Board b, Player p);
    List<BoardField> getAllCheckers(Player p, Board board);
    void createPlayer(Side s);

    List<Player> getPlayers();
}
///
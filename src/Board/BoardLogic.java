package Board;

import Interfaces.IBoardLogic;
import Player.Player;
import enums.CheckerType;
import Moves.Move;
import enums.Side;

import java.util.List;

/**
 * Created by malthe on 11/17/17.
 */
public class BoardLogic implements IBoardLogic {

    @Override
    public List<Move> getAllvalideMoves(Player p) {
        return null;
    }

    @Override
    public List<Move> validmoves(CheckerType t) {
        return null;
    }


    @Override
    public List<Move> getJumpMoves(Board b, Player p) {
        return null;
    }

    @Override
    public Boolean makeMove(Move m) {
        return null;
    }
}

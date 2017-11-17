import java.util.List;

public interface IBoardLogic {

    List<Move>  getAllvalideMoves(Player p);
    Type validmoves(Type t);//er ikke helt sikker :)
    Double getheuristics(Board b);
    Boolean makeMove(Move m);
    List<Move> getJumpMoves(Board b,Player p);
}
///
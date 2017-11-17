package Player;

public class Player {

    public enum Side {
        RED, BLACK
    }

    Side side;

    public Player(Side side) {
        this.side = side;
    }
}

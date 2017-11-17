package Board;

import enums.CheckerType;
import Player.Player;
/**
 * Created by malthe on 11/17/17.
 */
public class BoardField {

    public Player owner;
    public CheckerType checkerType;
    public boolean isOccupied;

    public BoardField() {

    }

    public BoardField(Player owner, CheckerType checkerType, boolean isOccupied) {
        this.owner = owner;
        this.checkerType = checkerType;
        this.isOccupied = isOccupied;
    }

    public BoardField(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
}

package Player;


import enums.Side;

public class Player {


    public Side side;

    public Player(Side side) {
        this.side = side;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        //Check if o is an instance of Complex or not
        //"null instanceof [type]" also returns false
        if (!(o instanceof Player)) {
            return false;
        }
        Player object = (Player) o;

        if(object.side != side) {
            return false;
        }
        return true;
    }
}

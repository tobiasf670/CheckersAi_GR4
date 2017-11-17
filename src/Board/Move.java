package Board;

import java.awt.*;

public class Move {

    private int starty, startx;
    private int goaly, goalx;

    public Move(Point start, Point goal) {
        starty = start.y;
        startx = start.x;
        goaly = goal.y;
        goalx = goal.x;
    }
}

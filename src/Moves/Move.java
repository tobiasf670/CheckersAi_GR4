package Moves;

import java.awt.*;

public class Move {

    private int starty, startx;
    private int goaly, goalx;
    public boolean isJumpMove;

    public Move(Point start, Point goal, boolean isJumpMove) {
        starty = start.y;
        startx = start.x;
        goaly = goal.y;
        goalx = goal.x;
        this.isJumpMove = isJumpMove;
    }

    public int getStarty() {
        return starty;
    }

    public void setStarty(int starty) {
        this.starty = starty;
    }

    public int getStartx() {
        return startx;
    }

    public void setStartx(int startx) {
        this.startx = startx;
    }

    public int getGoaly() {
        return goaly;
    }

    public void setGoaly(int goaly) {
        this.goaly = goaly;
    }

    public int getGoalx() {
        return goalx;
    }

    public void setGoalx(int goalx) {
        this.goalx = goalx;
    }

    @Override
    public String toString() {
    return "start: "+startx+", "+starty+" End: "+goalx+" , "+goaly;
    }
}

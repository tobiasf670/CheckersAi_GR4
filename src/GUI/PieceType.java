package GUI;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public enum PieceType {
    WHITE(1), BLACK(-1), KINGWHITE(0), KINGBLACK(0);

    final int moveDir;

    PieceType(int moveDir) {
        this.moveDir = moveDir;
    }
}

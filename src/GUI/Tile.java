package GUI;

import javafx.scene.paint.Color;
        import javafx.scene.shape.Rectangle;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class Tile extends Rectangle {

    private Piece piece;

    boolean light;

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Tile(boolean light, int x, int y) {
        this.light = light;
        setWidth(CheckersApp.TILE_SIZE);
        setHeight(CheckersApp.TILE_SIZE);

        relocate(x * CheckersApp.TILE_SIZE, y * CheckersApp.TILE_SIZE);

        setFill(light ? Color.valueOf("#feb") : Color.valueOf("#582"));
    }

    public void setColor(Color color){

        setFill(color);
    }
    public boolean getlight(){
    return  light;
    }

}

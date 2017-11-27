package GUI;

        import javafx.scene.layout.StackPane;
        import javafx.scene.paint.Color;
        import javafx.scene.shape.Ellipse;

        import static GUI.CheckersApp.TILE_SIZE;


/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class Piece extends StackPane {

    private PieceType type;

    private double mouseX, mouseY;
    private double oldX, oldY;
    Ellipse ellipse;

    public PieceType getType() {
        return type;
    }

    public void setType( PieceType p) {
      this.type = p ;
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public Piece(PieceType type, int x, int y, boolean p) {
        this.type = type;

        move(x, y);

        Ellipse bg = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        bg.setFill(Color.BLACK);

        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(TILE_SIZE * 0.03);

        bg.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        bg.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 + TILE_SIZE * 0.07);

        ellipse = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        Color setColor = Color.valueOf("#12edda");
        if(type == PieceType.WHITE){
            setColor = Color.valueOf("#ffffff");
        }
        else if(type == PieceType.KINGWHITE){
            setColor = Color.valueOf("#0c1d8c");
        }
        else if(type == PieceType.BLACK){
            setColor =Color.valueOf("#050505");
        }
        else if(type == PieceType.KINGBLACK){
            setColor =Color.valueOf("#e8ef09");
        }
        ellipse.setFill(setColor);

      //  ellipse.setFill(type == PieceType.WHITE || type == PieceType.KING
        //        ? Color.valueOf("#c40003") : Color.valueOf("#fff9f4"));

        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(TILE_SIZE * 0.03);

        ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2);

        getChildren().addAll(bg, ellipse);
        if(p && type == PieceType.BLACK){
            setOnMousePressed(e -> {
                mouseX = e.getSceneX();
                mouseY = e.getSceneY();
            });
            setOnMouseDragged(e -> {
                relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
            });
        }
        if(!p && type == PieceType.WHITE){
            setOnMousePressed(e -> {
                mouseX = e.getSceneX();
                mouseY = e.getSceneY();
            });
            setOnMouseDragged(e -> {
                relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
            });
        }



    }

    public void move(int x, int y) {
        oldX = x * TILE_SIZE;
        oldY = y * TILE_SIZE;
        relocate(oldX, oldY);
    }

    public void abortMove() {
        relocate(oldX, oldY);
    }

    public void changeColor(Color setColor){
        ellipse.setFill(setColor);
    }
}

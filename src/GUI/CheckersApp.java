package GUI;

import AI.AImoveCalculator;
import Board.Board;
import Board.BoardLogic;
import Controller.GameController;
import Heuristics.HeuristicCalculator;
import Moves.Move;
import Moves.MoveValidator;
import Player.Player;
import enums.Side;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Optional;

public class CheckersApp extends Application {

    public static final int TILE_SIZE = 50;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();

    Boolean booleanPlayer1 = false; // false white, true black
    Boolean booleanAI = false; // false black, true white
    AImoveCalculator ai ;
    Board boardAI ;
    Player playerAI ;
    Player playerHuman;
    BoardLogic bl;

    private Parent createContent() {

        //Setup game
         bl = new BoardLogic();
        bl.createPlayer(Side.RED);// black on gui
        bl.createPlayer(Side.BLACK); // white on gui

        Player red = new Player(bl.getPlayers().get(0).side);
        Player black = new Player(bl.getPlayers().get(1).side);
        boardAI = new Board(red,black);
        boardAI.init();
        MoveValidator vm = new MoveValidator();
        HeuristicCalculator hc = new HeuristicCalculator();
        ai = new AImoveCalculator(vm,hc,bl);

        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(tileGroup, pieceGroup);

        ButtonType player1 = new ButtonType("Spiller 1 (Sort)", ButtonBar.ButtonData.OK_DONE);
        ButtonType player2 = new ButtonType("Spiller 2 (Hvid)", ButtonBar.ButtonData.OK_DONE);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Vil du være spiller 1(Sort) eller Spiller 2 (Hvid)", player1, player2, ButtonType.CANCEL);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == player1) {
            booleanPlayer1 = true;
            booleanAI = false;
            System.out.println("Du er spiller 1");
            playerAI = red;
            playerHuman = black;
        }
        if (result.isPresent() && result.get() == player2) {
            booleanPlayer1 = false;
            booleanAI = true ;
            System.out.println("Du er spiller 2");
            playerAI = black;
            playerHuman = red;
        }




        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);


                Piece piece = null;

                if (y <= 2 && (x + y) % 2 != 0) {

                        piece = makePiece(PieceType.WHITE, x, y);


                }

                if (y >= 5 && (x + y) % 2 != 0) {
                        piece = makePiece(PieceType.BLACK, x, y);

                }

                if (piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }



        return root;
    }

    private MoveResult tryMove(Piece piece, int newX, int newY) {
        if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
            return new MoveResult(MoveType.NONE);
        }

        int x0 = toBoard(piece.getOldX());
        int y0 = toBoard(piece.getOldY());

        if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir) {
            if(newY == 0 && piece.getType() == PieceType.BLACK){
                piece.setType(PieceType.KINGBLACK);
                piece.changeColor(Color.valueOf("#e8ef09"));
            }
            if(newY == 7 && piece.getType() == PieceType.WHITE){
                piece.setType(PieceType.KINGWHITE);
                piece.changeColor(Color.valueOf("#0c1d8c"));
            }
            return new MoveResult(MoveType.NORMAL);
        } else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir * 2) {

            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;

            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                if(piece.getType() == PieceType.BLACK && board[x1][y1].getPiece().getType() != PieceType.KINGBLACK
                        || piece.getType() == PieceType.WHITE && board[x1][y1].getPiece().getType() != PieceType.KINGWHITE)
                {
                    return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
                }

            }


        }
        if (Math.abs(newX - x0) == 1 && (newY - y0 == 1 || newY - y0 == -1 )&& piece.getType() == PieceType.KINGWHITE) {
            return new MoveResult(MoveType.KING_WHITE);
        } else if (Math.abs(newX - x0) == 2 && (newY - y0 == 1 * 2 || newY - y0 == -1 * 2)&& piece.getType() == PieceType.KINGWHITE) {

            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;

            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != PieceType.WHITE &&  piece.getType() == PieceType.KINGWHITE) {
                return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
            }


        }

        if (Math.abs(newX - x0) == 1 && (newY - y0 == 1 || newY - y0 == -1 )&& piece.getType() == PieceType.KINGBLACK) {
            return new MoveResult(MoveType.KING_BLACK);
        } else if (Math.abs(newX - x0) == 2 && (newY - y0 == 1 * 2 || newY - y0 == -1 * 2 )&& piece.getType() == PieceType.KINGBLACK) {
            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;

            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != PieceType.BLACK) {
                return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
            }


        }
        return new MoveResult(MoveType.NONE);
    }

    private int toBoard(double pixel) {
        return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("CheckersApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Piece makePiece(PieceType type, int x, int y) {
        Piece piece = new Piece(type, x, y,booleanPlayer1);

        if(booleanPlayer1 && type == PieceType.BLACK) {

            piece.setOnMouseReleased(e -> {
                int newX = toBoard(piece.getLayoutX());
                int newY = toBoard(piece.getLayoutY());

                MoveResult result;


                if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT) {
                    result = new MoveResult(MoveType.NONE);

                } else {
                    result = tryMove(piece, newX, newY);

                }

                int x0 = toBoard(piece.getOldX());
                int y0 = toBoard(piece.getOldY());

                switch (result.getType()) {
                    case NONE:
                        piece.abortMove();
                        break;
                    case NORMAL:
                        System.out.println(newX+" humanog Y"+newY);
                        piece.move(newX, newY);
                        board[x0][y0].setPiece(null);
                        board[newX][newY].setPiece(piece);
                        Move human = new Move(new Point(y0,x0),new Point(newY,newX),false);
                        bl.makeMove(boardAI,human,playerAI.side,playerAI);
                        Move aiMove =ai.bestMove(boardAI,playerHuman);
                        int aiX0 = aiMove.getStarty();
                        int aiY0 = aiMove.getStartx();
                        int aiNewX = aiMove.getGoaly();
                        int aiNewY = aiMove.getGoalx();
                        System.out.println("sq vi igang");
                        MoveResult noob;
                        noob = tryMove(board[aiX0][aiY0].getPiece(),aiNewX,aiNewY);
                        board[aiX0][aiY0].getPiece().move(aiNewX,aiNewY);
                        bl.makeMove(boardAI,aiMove,playerHuman.side,playerHuman);
                        System.out.println(aiX0+" og Y"+aiY0);
                        System.out.println(board[aiX0][aiY0].getPiece().getType().name());
                        System.out.println(aiNewX+" og Y"+aiNewY);
                        System.out.println(noob.getType());
                        break;
                    case KING_WHITE:
                        piece.move(newX, newY);
                        board[x0][y0].setPiece(null);
                        board[newX][newY].setPiece(piece);
                        break;
                    case KING_BLACK:
                        piece.move(newX, newY);
                        board[x0][y0].setPiece(null);
                        board[newX][newY].setPiece(piece);
                        break;
                    case KILL:
                        piece.move(newX, newY);
                        board[x0][y0].setPiece(null);
                        board[newX][newY].setPiece(piece);

                        Piece otherPiece = result.getPiece();
                        board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                        pieceGroup.getChildren().remove(otherPiece);
                        break;
                }
            });

        }


        if(!booleanPlayer1 && type == PieceType.WHITE){
            piece.setOnMouseReleased(e -> {
                int newX = toBoard(piece.getLayoutX());
                int newY = toBoard(piece.getLayoutY());

                MoveResult result;


                if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT) {
                    result = new MoveResult(MoveType.NONE);
                } else {
                    result = tryMove(piece, newX, newY);
                }

                int x0 = toBoard(piece.getOldX());
                int y0 = toBoard(piece.getOldY());

                switch (result.getType()) {
                    case NONE:
                        piece.abortMove();
                        break;
                    case NORMAL:
                        piece.move(newX, newY);
                        board[x0][y0].setPiece(null);
                        board[newX][newY].setPiece(piece);
                        break;
                    case KING_WHITE:
                        piece.move(newX, newY);
                        board[x0][y0].setPiece(null);
                        board[newX][newY].setPiece(piece);
                        break;
                    case KING_BLACK:
                        piece.move(newX, newY);
                        board[x0][y0].setPiece(null);
                        board[newX][newY].setPiece(piece);
                        break;
                    case KILL:
                        piece.move(newX, newY);
                        board[x0][y0].setPiece(null);
                        board[newX][newY].setPiece(piece);

                        Piece otherPiece = result.getPiece();
                        board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                        pieceGroup.getChildren().remove(otherPiece);
                        break;
                }
            });
        }

        return piece;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
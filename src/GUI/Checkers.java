package GUI;

import BoardLogic.FieldState;
import Controller.CheckersController;
import BoardLogic.Board;
import javax.swing.JFrame;

public class Checkers extends JFrame
{

   private GUIBoard board;
   CheckersController controller;

   public Checkers(String title, CheckersController controller)
   {

      super(title);
       this.controller = controller;
      setDefaultCloseOperation(EXIT_ON_CLOSE);

      this.board = new GUIBoard(this.controller);
      setContentPane(board);

      pack();
      setVisible(true);
   }
   public GUIBoard getBoard() {
      return board;
   }

   public void updateBoard(Board board) {

       FieldState gameBoard[][] = board.getGameBoard();
       for(int i = 1; i <9; i++) {

           int test;

           for(int j = 1; j < 9; j++) {
               FieldState state = gameBoard[i-1][j-1];
               if(state == FieldState.BLACK) {
                   this.board.add(new Checker(CheckerType.BLACK_REGULAR), i, j);
               } else if(state == FieldState.RED) {
                   this.board.add(new Checker(CheckerType.RED_REGULAR), i, j);
               }

           }

       }
   }

}
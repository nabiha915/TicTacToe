import java.util.Scanner;

public class TicTacToe {

  // variables have been declared
  int cols = 3;
  int rows = 3;
  int totalTurns = 0;
  int winner; 

  enum GameState {
    OVER, RUNNING
  }

  // the current state of the game is over
  static GameState currentState = GameState.OVER;

  // creating our 2d array that helps form the board
  GridSquare[][] board;

  public void setup() {

    board = new GridSquare[rows][cols];

    int position = 1;

    // this for loop deals with the row coordinates
    for (int r = 0; r < rows; r++) {
      // this for loop deals with the column coordinates
      for (int c = 0; c < cols; c++) {
        board[r][c] = new GridSquare(position);
        position++;
      }
    }

    currentState = GameState.RUNNING;
    playGame();
  }

  public void playGame() {

    while (currentState == GameState.RUNNING) {
      displayBoard();
      makeMove();
    }

    if (currentState == GameState.OVER) {
      displayGameOver();
    }
  }

  public void displayBoard() {

    System.out
        .println("\n  " + board[0][0].drawSpace() + " | " + board[0][1].drawSpace() + "  | " + board[0][2].drawSpace());

    System.out.println(" ___|____|___ ");

    System.out
        .println("\n  " + board[1][0].drawSpace() + " | " + board[1][1].drawSpace() + "  | " + board[1][2].drawSpace());

    System.out.println(" ___|____|___ ");

    System.out
        .println("\n  " + board[2][0].drawSpace() + " | " + board[2][1].drawSpace() + "  | " + board[2][2].drawSpace());

    System.out.println("    |     |    ");

    System.out.println("\n");

  }

  public void makeMove(){

    System.out.println("Player " + getPlayer() + " choose a position: ");

    Scanner in = new Scanner(System.in);
    int spot = in.nextInt();

    for (int i = 0; i < cols; i++) {
      for (int j = 0; j < rows; j++) {
        if (board[i][j].state == -1 && board[i][j].pos == spot) {
          board[i][j].state = totalTurns % 2;
          totalTurns++;

          checkWin(i, j, board[i][j].state); 
        
        }
      }
    }
  }

  public void displayGameOver() {

    displayBoard();

    System.out.println("Game Over");
    
    if (winner == 0) {
      System.out.println("Player O Wins!!!");
    }
    if (winner == 1) {
      System.out.println("Player X Wins!!!");
    }
    if (totalTurns == 9) {
      System.out.println("The game is a Tie!!");
    }
  }

  public char getPlayer() {

    //whether if the totalturns number is even or odd 
    //if it is even then it will be player O's turn 
    //if it is odd then it will be player X's turn 
    if (totalTurns % 2 == 0) {
      return 'O';
    }
    //for all odd numbers the turn will go to player X 
    return 'X';
  }

  //check for the win going in every direction
  public void checkWin(int x, int y, int turn){ 

    int colWin = 0; 
    int rowWin = 0; 
    int diag1Win = 0; 
    int diag2Win = 0; 
    // for int num to 3
    // Check 3 columns
    // If neighbor matches turn, increase colWin by 1.
    // Check 3 squares in row
    // If neighbor matches turn, increase rowWin by 1.
    // Check 3 squares in diagonal
        // If neighbor matches turn, increase diag1Win by 1.
    // Check 3 squares in 2nd diagonal.
        // If neighbor matches turn, increase diag2Win by 1.
//If colWin, rowWin, diag1Win, or diag2Win equals 3, set winner to be turn.


    for (int next = 0; next < 3; next++) {
      if (board[x][next].state == turn) {
        colWin++;
      }
      
      if (board[next][y].state == turn) {
        rowWin++;
      }
      if (board[next][next].state == turn) {
        diag1Win++;
      }
      if (board[next][2 - next].state == turn) {
        diag2Win++;
      }
    }
    
    if(colWin == 3 || rowWin == 3 || diag1Win == 3 || diag2Win == 3) {
      if (winner != -1) {
        currentState = GameState.OVER;
      }
    }
    if (totalTurns == 9) {
      currentState = GameState.OVER;
    }
  }
}

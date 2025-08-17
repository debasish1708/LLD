import java.util.Arrays;
import java.util.Scanner;

class TicTacToe{
    public int[][] board;
    public static TicTacToe game;

    public TicTacToe() {
        game = this;
        System.out.println("Initializing Tic Tac Toe board...");
        board = new int[3][3];
        for(int[] i:board){
            Arrays.fill(i,-1);
        }
        game.startGame();
    }
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void startGame(){
        System.out.println("Starting a new game...");
        printBoard();
    }
    public static int moveCount = 0;
    public boolean makeMove(int player, int row, int col) {
        if(row<0||col<0||row>2||col>2){
            System.out.println("Invalid move. Try again.");
            return false;
        }
        if (board[row][col] == -1) {
            board[row][col] = player;
            moveCount++;
            return true;
        } else {
            System.out.println("Invalid move. Try again.");
        }
        return false;
    }
    public static boolean playGameUntilDrawOrWin(){
        if(moveCount == 9){
            System.out.println("It's a draw!");
            return true;
        }

        // check rows and columns
        for (int i = 0; i < 3; i++) {
            if (game.board[i][0] != -1 && game.board[i][0] == game.board[i][1] && game.board[i][1] == game.board[i][2]) {
                System.out.println("Player " + game.board[i][0] + " wins!");
                return true;
            }
            if (game.board[0][i] != -1 && game.board[0][i] == game.board[1][i] && game.board[1][i] == game.board[2][i]) {
                System.out.println("Player " + game.board[0][i] + " wins!");
                return true;
            }
        }


        // Check diagonals
        if (game.board[1][1] != -1 &&
            ((game.board[0][0] == game.board[1][1] && game.board[1][1] == game.board[2][2]) ||
             (game.board[0][2] == game.board[1][1] && game.board[1][1] == game.board[2][0]))){
                System.out.println("Player " + game.board[1][1] + " wins!");
                return true;
             }
        return false;
    }
   
    private static int[] getMoveInput(Scanner scanner, int player) {
        System.out.print(player + " Enter row (0-2): ");
        int row = scanner.nextInt();
        System.out.print(player + " Enter col (0-2): ");
        int col = scanner.nextInt();
        return new int[]{row, col};
    }

    public static void main(String[] args) {
       new TicTacToe();
       try{
        Scanner scanner = new Scanner(System.in);
        boolean isX = true;
        while(!playGameUntilDrawOrWin()){
            int player = isX ? 1 : 2;
            int[] move = getMoveInput(scanner, player);
            while(!game.makeMove(player, move[0], move[1])){
                move = getMoveInput(scanner, player);
            }
            isX = !isX;
            game.printBoard();
            System.out.println();
        }
        scanner.close();
       }catch(Exception e){
           System.out.println("Error occurred: " + e.getMessage());
       }
    }
}
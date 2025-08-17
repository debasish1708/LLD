import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeOptimize {
    public static int count=1;
    public static int n;
    public static int[][] board;
    public static Player[] players;
    static class Player{
        int id;
        String name;
        int[] countRow;
        int[] countCol;
        int countDiagonal;
        int countAntiDiagonal;
        public Player(String name) {
            this.id=count++;
            this.name=name;
            this.countRow=new int[n];
            this.countCol=new int[n];
            this.countDiagonal=0;
            this.countAntiDiagonal=0;
        }
        public static Player getPlayer(int id){
            return players[id];
        }
        public String toString(){
            return "Player{" +
                    "id=" + this.id +
                    ", name='" + this.name + '\'' +
                    '}';
        }
    }
    public TicTacToeOptimize(Scanner scanner) {
        try {
            System.out.print("Enter board size (n x n): ");
            n = scanner.nextInt();
            board = new int[n][n];
            players = new Player[2];
            scanner.nextLine(); // consume leftover newline
            for(int i=0;i<2;i++){
                System.out.print("Enter Player " + (i + 1) + " Name:");
                String playerName = scanner.nextLine();
                players[i] = new Player(playerName);
            }
            resetBoard();
            printBoard();
        } catch(Exception e) {
           System.out.println(e.getMessage());
        }
    }

    public static void resetBoard(){
        for(int[] i:board){
            Arrays.fill(i, -1);
        }
    }
    public static void printBoard(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isWin = false;
    public static int totalCount=0;
    public static boolean isValidMove = true;

    // 1st makeMove in O(1) time
    public static void makeMove(int player, int row, int col){
        // validate row and col
        if(row<0||row>=n||col<0||col>=n){
            System.out.println("Out of bounds");
            isValidMove = false;
            return;
        }
        if(board[row][col]!=-1){
            System.out.println("Cell already occupied by: " + Player.getPlayer(board[row][col]-1).name);
            isValidMove = false;
            return;
        }
        // play the move
        board[row][col]=player+1;
        totalCount++;
        isValidMove=true;

        // update the player row, col, diagonal and anti-diagonal counts
        Player currPlayer = Player.getPlayer(player);
        currPlayer.countRow[row]++;
        currPlayer.countCol[col]++;
        if(row==col){
            currPlayer.countDiagonal++;
        }
        if(row+col==n-1) {
            currPlayer.countAntiDiagonal++;
        }
        if(currPlayer.countRow[row]==n||currPlayer.countCol[col]==n
                ||currPlayer.countDiagonal==n||currPlayer.countAntiDiagonal==n){
            System.out.println("Player " + currPlayer.name + " wins!");
            isWin = true;
            return;
        }
        if(totalCount==n*n){
            System.out.println("Game is a draw!");
            isWin=false;
            return;
        }
        printBoard();
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new TicTacToeOptimize(scanner);

        // start the game
        try {
            boolean isA = true;
            while(!isWin){
                int playerId = isA ? 0 : 1;
                Player currentPlayer = Player.getPlayer(playerId);
                System.out.print(currentPlayer.name + "'s turn. Enter Move (row col): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                makeMove(playerId, row, col);
                if(!isValidMove){
                    continue;
                }
                isA = !isA;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

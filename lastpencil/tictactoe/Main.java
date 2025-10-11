package tictactoe;
import java.util.Scanner;

public class Main {
    public static char[][] board = {
            {' ',' ',' '},
            {' ',' ',' '},
            {' ',' ',' '}
    };
    public static int coordinateX;
    public static int coordinateY;
    public static boolean thisTurnIsX = true;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printBoard(board);
        String gameResult = " ";

        while(true){
            String coordinateInput = sc.nextLine();
            if(areAllValidInts(coordinateInput)){
                String[] coordinateNumber = coordinateInput.split(" ");
                coordinateX = Integer.parseInt(coordinateNumber[0]);
                coordinateY = Integer.parseInt(coordinateNumber[1]);
                if(replaceAndValidate(coordinateX-1, coordinateY-1)) {
                    printBoard(board);
                }

                if(checkGameCondition(board).equals("X wins") || checkGameCondition(board).equals("O wins") || checkGameCondition(board).equals("Draw")){
                    gameResult = checkGameCondition(board);
                    break;
                }
            }
        }
        System.out.println(gameResult);
        sc.close();
    }

    public static void printBoard(char[][] board) {
        // แสดงตาราง
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    public static String checkGameCondition(char[][] board) {
        // หาผู้แพ้ชนะ
        //"Draw" when no side has a three in a row and the grid has no empty cells.
        //"X wins" when the grid has three X’s in a row (including diagonals).
        //"O wins" when the grid has three O’s in a row (including diagonals).
        boolean xWin = false;
        boolean oWin = false;

        for (int i = 0; i < 3; i++) {
            // row
            if(board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]){
                if(board[i][0] == 'X'){
                    xWin = true;
                }
                if(board[i][0] == 'O'){
                    oWin = true;
                }
            }

            // column
            if(board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]){
                if(board[0][i] == 'X'){
                    xWin = true;
                }
                if(board[0][i] == 'O'){
                    oWin = true;
                }
            }

            // เอียงขวา
            if(board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]){
                if(board[0][0] == 'X'){
                    xWin = true;
                }
                if(board[0][0] == 'O'){
                    oWin = true;
                }
            }

            // เอียงซ้าย
            if(board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]){
                if(board[0][2] == 'X'){
                    xWin = true;
                }
                if(board[0][2] == 'O'){
                    oWin = true;
                }
            }
        }

        // หาช่องว่าง
        boolean emptyCell = false;
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') {
                    emptyCell = true;
                    break;
                }
            }
        }

        if (xWin) {
            return "X wins";
        } else if (oWin) {
            return "O wins";
        } else if (emptyCell) {
            return "not finish";
        } else {
            return "Draw";
        }
    }

    public static boolean replaceAndValidate(int x, int y){


        if((x<0 || x>2) || (y<0 || y>2)){
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        else {
            if (board[x][y] == 'X' || board[x][y] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
                return false;
            } else {
                if(thisTurnIsX){
                    board[x][y] = 'X';
                    thisTurnIsX = false;
                    return true;
                }
                else{
                    board[x][y] = 'O';
                    thisTurnIsX = true;
                    return true;
                }
            }
        }
    }

    public static boolean isValidInt(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(input.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean areAllValidInts(String input) {
        boolean isValid = true;

        if (input == null || input.trim().isEmpty()) {
            System.out.println("You should enter numbers!");
            isValid = false;
            return isValid;
        }

        else {
            String[] parts = input.split(" ");
            for (String part : parts) {
                if (!isValidInt(part.trim())) {
                    isValid = false;
                    break;
                }
            }
        }

        if (!isValid) {
            System.out.println("You should enter numbers!");
        }
        return isValid;
    }
}
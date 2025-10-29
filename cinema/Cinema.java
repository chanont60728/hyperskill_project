package cinema;
import java.util.ArrayList;
import java.util.Scanner;

public class Cinema {
    public static int rowNum;
    public static int seatsPerRow;
    public static int rowSelect;
    public static int seatNumberSelect;
    public static ArrayList<Integer> rowNumUI = new ArrayList<>();
    public static ArrayList<ArrayList<Character>> seatUI = new ArrayList<>();
    public static int totalTicketsPurchased = 0;
    public static int currentIncome = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // create row and seat number
        System.out.println("Enter the number of rows:");
        rowNum = sc.nextInt();
        createNumberUI(rowNumUI,rowNum);
        System.out.println("Enter the number of seats in each row:");
        seatsPerRow = sc.nextInt();
        createSeatUI(seatUI,rowNum,seatsPerRow);
        System.out.println();

        // create menu and reserve
        while(true){
            createMenu();
            int menuSelect = sc.nextInt();
            if(menuSelect == 1){
                // create UI
                System.out.println();
                System.out.println("Cinema:");
                showAllUI(seatUI,seatsPerRow);
            }
            else if (menuSelect == 2) {
                while(true){
                    System.out.println();
                    System.out.println("Enter a row number:");
                    rowSelect = sc.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    seatNumberSelect = sc.nextInt();
                    if(validator()){
                        break;
                    }
                }

                System.out.println();
                System.out.println("Ticket price: $"+calculateIncomePerSelect(rowNum,seatsPerRow,rowSelect));
                System.out.println();
                seatUI.get(rowSelect-1).set(seatNumberSelect-1,'B');
                totalTicketsPurchased+=1;
                currentIncome+=calculateIncomePerSelect(rowNum,seatsPerRow,rowSelect);
            }
            else if (menuSelect == 3) {
                System.out.println();
                System.out.println("Number of purchased tickets: "+totalTicketsPurchased);
                System.out.printf("Percentage: %.2f%%",((double)totalTicketsPurchased*100/((double)rowNum*(double)seatsPerRow)));
                System.out.println();
                System.out.println("Current income: $"+currentIncome);
                System.out.println("Total income: $"+calculateTotalIncome(rowNum,seatsPerRow));
                System.out.println();
            }
            else if (menuSelect == 0) {
                break;
            }
        }
        sc.close();
    }

    public static int calculateIncomePerSelect(int rowNum, int seatsPerRow, int rowSelect){
        if(rowNum*seatsPerRow<=60){
            return 10;
        }
        else{
            int frontRows = rowNum/2;

            //int backRows = rowNum-frontRows;
            if(rowSelect<=frontRows){
                return 10;
            }
            else{
                return 8;
            }
        }
    }

    public static void createNumberUI(ArrayList<Integer> UI, int totalInput){
        for(int i=1; i<=totalInput; i++){
            UI.add(i);
        }
    }

    public static void createSeatUI(ArrayList<ArrayList<Character>> seatUI, int rowNumUI, int seatsPerRowUI){
        for(int i=0; i<rowNumUI; i++){
            ArrayList<Character> temp = new ArrayList<>();
            for(int j=0; j<seatsPerRowUI; j++){
                temp.add('S');
            }
            seatUI.add(temp);
        }
    }

    public static void showAllUI(ArrayList<ArrayList<Character>> seatUI, int seatsPerRow){
        System.out.print(" ");
        for(int i=1; i<=seatsPerRow; i++){
            System.out.print(" "+i);
        }
        System.out.println();
        for (int i = 0; i < seatUI.size(); i++) {
            System.out.print(i+1);
            for (int j = 0; j < seatUI.get(i).size(); j++) {
                System.out.print(" "+seatUI.get(i).get(j));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void createMenu(){
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static boolean validator(){
        if (rowSelect > rowNum || seatNumberSelect > seatsPerRow) {
            System.out.println();
            System.out.println("Wrong input!");
            return false;
        }
        else if(seatUI.get(rowSelect-1).get(seatNumberSelect-1) == 'B'){
            System.out.println();
            System.out.println("That ticket has already been purchased!");
            return false;
        }
        else{
            return true;
        }
    }

    public static int calculateTotalIncome(int rowNum, int seatsPerRow){
        if(rowNum*seatsPerRow<=60){
            return rowNum*seatsPerRow*10;
        }
        else{
            int frontRows = rowNum/2;
            int backRows = rowNum-frontRows;
            return (frontRows*seatsPerRow*10)+(backRows*seatsPerRow*8);
        }
    }
}
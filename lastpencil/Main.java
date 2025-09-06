package lastpencil;
import java.util.Scanner;

public class Main {
    public static int pencilAmount;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // ข้อความเปิด
        System.out.println("How many pencils would you like to use:");

        // validate จำนวนดินสอให้เป็นตัวเลข
        while(true) {
            String pencilNumberString = sc.nextLine();
            if (Validator.numberValidator(pencilNumberString)) {
                int pencilNumberInt = Integer.parseInt(pencilNumberString);
                // validate จำนวนดินสอให้มากกว่า 0
                if (Validator.numberMoreThanZero(pencilNumberInt)) {
                    pencilAmount = pencilNumberInt;
                    break;
                } else {
                    System.out.println("The number of pencils should be positive");
                }
            } else {
                System.out.println("The number of pencils should be numeric");
            }
        }

        System.out.println("Who will be the first (John, Jack):");

        // ใส่ player เริ่มต้น
       while(true){
           String startPlayerInput = sc.next();

           if(Validator.onlyJohnOrJack(startPlayerInput)){
               for(int i = 1; i <= pencilAmount; i++){
                   System.out.print("|");
               }
               System.out.println();

               pencilPlayer player = new pencilPlayer(startPlayerInput);

               while(pencilAmount > 0) {
                   if (startPlayerInput.equals("John")) {
                       startPlayerInput = player.humanPlay();
                   } else if (startPlayerInput.equals("Jack")) {
                       startPlayerInput = player.botPlay();
                   } else{
                       break;
                   }
               }

               break;
           }
           else{
               System.out.println("'Choose between 'John' and 'Jack''");
           }
       }
        sc.close();
    }
}

package lastpencil;
import java.util.Scanner;
import static lastpencil.Main.pencilAmount;

public class pencilPlayer {
    String name;

    public pencilPlayer(String player) {
        this.name = player;
    }

    // John เล่น
    public String humanPlay() {
        System.out.println("John's turn!");

        // validate จำนวนดินสอให้เป็นตัวเลข
        Scanner sc = new Scanner(System.in);

        while (true) {
            String numberReducer = sc.next();
            if (Validator.numberValidator(numberReducer)) {
                if(Validator.onlyOneTwoThree(Integer.parseInt(numberReducer))) {
                    if(Integer.parseInt(numberReducer)<=pencilAmount) {
                        pencilAmount = pencilAmount - Integer.parseInt(numberReducer);
                        if (pencilAmount == 0) {
                            System.out.println("Jack won!");
                            return "Jack won!";
                        } else {
                            for (int i = 1; i <= pencilAmount; i++) {
                                System.out.print("|");
                            }
                            System.out.println();
                            break;
                        }
                    }
                    else{
                        System.out.println("too many pencils");
                    }
                }
                else{
                    System.out.println("Possible values: '1', '2' or '3'");
                }
            }
            else {
                System.out.println("Possible values: '1', '2' or '3'");
            }
        }
        return "Jack";
    }

    // Jack เล่น
    public String botPlay(){
        System.out.println("Jack's turn:");

        if(pencilAmount == 1){
            System.out.println(1); // ถ้าเหลือดินสออยู่ 1 แท่ง ให้ bot ดึงออกแล้วคนชนะ
            System.out.println("John won!");
            return "John won!";
        }

        else {
            int numberReducer = switch (pencilAmount % 4) {
                case 0 -> 3; // ถ้าเหลือเศษ 0 ให้ดึงออก 3
                case 3 -> 2; // ถ้าเหลือเศษ 3 ให้ดึงออก 2
                case 2 -> 1; // ถ้าเหลือเศษ 2 ให้ดึงออก 1
                default -> new java.util.Random().nextInt(3) + 1; // ถ้าเหลือเศษ 1 ให้ดึงออก random
            };
            System.out.println(numberReducer);
            pencilAmount = pencilAmount - numberReducer;
            for (int i = 1; i <= pencilAmount; i++) {
                System.out.print("|");
            }
            System.out.println();
            return "John";
        }
    }
}



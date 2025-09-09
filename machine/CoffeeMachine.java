package machine;
import java.util.Scanner;

public class CoffeeMachine {
    public static int waterMl = 400;
    public static int milkMl = 540;
    public static int coffeeBeansG = 120;
    public static int disposableCups = 9;
    public static int money = 550;
    public static int[] espressoFormula = {1, 250, 0, 16, 1, 4};
    public static int[] latteFormula = {2, 350, 75, 20, 1, 7};
    public static int[] cappuccinoFormula = {3, 200, 100, 12, 1, 6};
    public static int countToClean = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String action = "N/A";

        while(!action.equals("exit")) {
            System.out.println();
            System.out.println("Write action (buy, fill, take, clean, remaining, exit):");
            action = sc.nextLine();
            switch (action) {
                case "fill" -> fill(sc);
                case "take" -> take();
                case "buy" -> buy(sc);
                case "remaining" -> showDetail(waterMl, milkMl, coffeeBeansG, disposableCups, money);
                case "clean" -> clean();
            }
        }
        sc.close();
    }

    public static void showDetail(int waterMl, int milkMl, int coffeeBeansG, int disposableCups, int money){
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.println(waterMl+" ml of water");
        System.out.println(milkMl+" ml of milk");
        System.out.println(coffeeBeansG+" g of coffee beans");
        System.out.println(disposableCups+" disposable cups");
        System.out.println("$"+money+" of money");
    }

    public static void fill(Scanner sc){
        System.out.println("Write how many ml of water you want to add:");
        int waterMlAdd = sc.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        int milkMlAdd = sc.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        int coffeeGramAdd = sc.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        int cupsAdd = sc.nextInt();

        CoffeeMachine.waterMl = CoffeeMachine.waterMl+waterMlAdd;
        CoffeeMachine.milkMl = CoffeeMachine.milkMl+milkMlAdd;
        CoffeeMachine.coffeeBeansG = CoffeeMachine.coffeeBeansG+coffeeGramAdd;
        CoffeeMachine.disposableCups = CoffeeMachine.disposableCups+cupsAdd;
    }

    public static void take(){
        System.out.println("I gave you $"+CoffeeMachine.money);
        CoffeeMachine.money = 0;
    }

    public static void buy(Scanner sc) {
        if (countToClean == 10) {
            System.out.println("I need cleaning!");
        } else {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
            String buyInput = sc.nextLine();
            if (buyInput.equals("back")) {

            } else {

                int coffeeType = Integer.parseInt(buyInput);

                if (resourceValidatorAndShow(coffeeType)) {
                    switch (coffeeType) {
                        case 1:
                            CoffeeMachine.waterMl = CoffeeMachine.waterMl - 250;
                            CoffeeMachine.coffeeBeansG = CoffeeMachine.coffeeBeansG - 16;
                            CoffeeMachine.money = CoffeeMachine.money + 4;
                            CoffeeMachine.disposableCups = CoffeeMachine.disposableCups - 1;
                            break;
                        case 2:
                            CoffeeMachine.waterMl = CoffeeMachine.waterMl - 350;
                            CoffeeMachine.milkMl = CoffeeMachine.milkMl - 75;
                            CoffeeMachine.coffeeBeansG = CoffeeMachine.coffeeBeansG - 20;
                            CoffeeMachine.money = CoffeeMachine.money + 7;
                            CoffeeMachine.disposableCups = CoffeeMachine.disposableCups - 1;
                            break;
                        case 3:
                            CoffeeMachine.waterMl = CoffeeMachine.waterMl - 200;
                            CoffeeMachine.milkMl = CoffeeMachine.milkMl - 100;
                            CoffeeMachine.coffeeBeansG = CoffeeMachine.coffeeBeansG - 12;
                            CoffeeMachine.money = CoffeeMachine.money + 6;
                            CoffeeMachine.disposableCups = CoffeeMachine.disposableCups - 1;
                            break;
                    }

                    countToClean = countToClean + 1;
                }
            }
        }
    }
    public static boolean resourceValidatorAndShow(int coffeeType){
        switch (coffeeType){
            case 1:
                if(CoffeeMachine.waterMl >= espressoFormula[1] && CoffeeMachine.coffeeBeansG >= espressoFormula[3] && CoffeeMachine.disposableCups >= espressoFormula[4]){
                    System.out.println("I have enough resources, making you a coffee!");
                    return true;
                } else if (CoffeeMachine.waterMl < espressoFormula[1]) {
                    System.out.println("Sorry, not enough water!");
                    return false;
                } else if (CoffeeMachine.coffeeBeansG < espressoFormula[3]) {
                    System.out.println("Sorry, not enough coffee beans!");
                    return false;
                } else if (CoffeeMachine.disposableCups < espressoFormula[4]) {
                    System.out.println("Sorry, not enough disposable cups!");
                    return false;
                }

            case 2:
                if(CoffeeMachine.waterMl >= latteFormula[1] && CoffeeMachine.milkMl >= latteFormula[2] && CoffeeMachine.coffeeBeansG >= latteFormula[3] && CoffeeMachine.disposableCups >= latteFormula[4]){
                    System.out.println("I have enough resources, making you a coffee!");
                    return true;
                } else if (CoffeeMachine.waterMl < latteFormula[1]) {
                    System.out.println("Sorry, not enough water!");
                    return false;
                } else if (CoffeeMachine.milkMl < latteFormula[2]) {
                    System.out.println("Sorry, not enough milk!");
                    return false;
                } else if (CoffeeMachine.coffeeBeansG < latteFormula[3]) {
                    System.out.println("Sorry, not enough coffee beans!");
                    return false;
                } else if (CoffeeMachine.disposableCups < latteFormula[4]) {
                    System.out.println("Sorry, not enough disposable cups!");
                    return false;
                }

            case 3:
                if(CoffeeMachine.waterMl >= cappuccinoFormula[1] && CoffeeMachine.milkMl >= cappuccinoFormula[2] && CoffeeMachine.coffeeBeansG >= cappuccinoFormula[3] && CoffeeMachine.disposableCups >= cappuccinoFormula[4]){
                    System.out.println("I have enough resources, making you a coffee!");
                    return true;
                } else if (CoffeeMachine.waterMl < cappuccinoFormula[1]) {
                    System.out.println("Sorry, not enough water!");
                    return false;
                } else if (CoffeeMachine.milkMl < cappuccinoFormula[2]) {
                    System.out.println("Sorry, not enough milk!");
                    return false;
                } else if (CoffeeMachine.coffeeBeansG < cappuccinoFormula[3]) {
                    System.out.println("Sorry, not enough coffee beans!");
                    return false;
                } else if (CoffeeMachine.disposableCups < cappuccinoFormula[4]) {
                    System.out.println("Sorry, not enough disposable cups!");
                    return false;
                }
            default:
                return false;
        }
    }

    public static void clean(){
        countToClean = 0;
        System.out.println("I have been cleaned!");
    }
}
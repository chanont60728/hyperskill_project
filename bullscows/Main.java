package bullscows;
import java.util.*;

public class Main {
    public static int bullsAmount = 0;
    public static int cowsAmount = 0;
    public static String secretCode = "";
    public static String secretCodeAll = "0123456789abcdefghijklmnopqrstivwxyz";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        String lengthOfRandom = sc.nextLine();

        try {
            int lengthOfRandomNumber = Integer.parseInt(lengthOfRandom);
            if(lengthOfRandomNumber == 0){
                System.exit(0);
            }

            System.out.println("Input the number of possible symbols in the code:");
            String possibleSymbols = sc.nextLine();

            try{
                int possibleSymbolsNumber = Integer.parseInt(possibleSymbols);
                if (validator(lengthOfRandomNumber, possibleSymbolsNumber)) {
                    System.out.println("The secret is prepared: " + "*".repeat(lengthOfRandomNumber) + changeSecretCodeLengthShow(possibleSymbolsNumber));
                    System.out.println("Okay, let's start a game!");
                    generateRandom(lengthOfRandomNumber, possibleSymbolsNumber);
                    int turn = 1;
                    while (true) {
                        System.out.println("Turn " + turn + ":");
                        String numberGuessString = sc.nextLine();
                        char[] splitGuessInput = numberGuessString.toCharArray();
                        checker(splitGuessInput);
                        showResult();
                        turn += 1;

                        if (bullsAmount == lengthOfRandomNumber) {
                            System.out.println("Congratulations! You guessed the secret code.");
                            break;
                        }
                    }
                }
            } catch (NumberFormatException e){
                System.out.println("Error: \""+possibleSymbols+"\" isn't a valid number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: \""+lengthOfRandom+"\" isn't a valid number.");
        }

        sc.close();
    }

    public static void checker(char[] guess) {
        if(guess.length <= secretCode.length()) {
            bullsAmount = 0;
            cowsAmount = 0;

            char[] secret = secretCode.toCharArray();
            boolean[] secretUsed = new boolean[secret.length];
            boolean[] guessUsed = new boolean[guess.length];

            // First pass: find bulls
            for (int i = 0; i < guess.length; i++) {
                if (guess[i] == secret[i]) {
                    bullsAmount++;
                    secretUsed[i] = true;
                    guessUsed[i] = true;
                }
            }

            // Second pass: find cows
            for (int i = 0; i < guess.length; i++) {
                if (!guessUsed[i]) {
                    for (int j = 0; j < secret.length; j++) {
                        if (!secretUsed[j] && guess[i] == secret[j]) {
                            cowsAmount++;
                            secretUsed[j] = true;
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void showResult(){
        if(bullsAmount == 0 && cowsAmount == 0){
            System.out.println("Grade: None.");
        }
        else if (bullsAmount == 0 && cowsAmount > 0) {
            if(cowsAmount == 1){
                System.out.printf("Grade: %d cow.",cowsAmount);
                System.out.println();
            }
            else{
                System.out.printf("Grade: %d cows.",cowsAmount);
                System.out.println();
            }
        }
        else if (bullsAmount > 0 && cowsAmount == 0) {
            if(bullsAmount == 1){
                System.out.printf("Grade: %d bull.",bullsAmount);
                System.out.println();
            }
            else{
                System.out.printf("Grade: %d bulls.",bullsAmount);
                System.out.println();
            }
        }
        else{
            if(bullsAmount == 1 && cowsAmount == 1){
                System.out.printf("Grade: %d bull and %d cow.",bullsAmount,cowsAmount);
                System.out.println();
            }
            else if (bullsAmount == 1 && cowsAmount > 1) {
                System.out.printf("Grade: %d bull and %d cows.",bullsAmount,cowsAmount);
                System.out.println();
            }
            else if (bullsAmount > 1 && cowsAmount == 1) {
                System.out.printf("Grade: %d bulls and %d cow.",bullsAmount,cowsAmount);
                System.out.println();
            }
            else if (bullsAmount > 1 && cowsAmount > 1) {
                System.out.printf("Grade: %d bulls and %d cows.",bullsAmount,cowsAmount);
                System.out.println();
            }
        }
    }

    public static void generateRandom(int length, int possibleSymbolsNumber) {
        List<Character> chars = new ArrayList<>();
        for (char c : secretCodeAll.substring(0,possibleSymbolsNumber).toCharArray()) {
            chars.add(c);
        }
        Collections.shuffle(chars);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.get(i));
        }
        secretCode = sb.toString();
    }

    public static String changeSecretCodeLengthShow(int possibleSymbolsNumber){
        if(possibleSymbolsNumber <= 10){
            return " (0-"+secretCodeAll.substring(0,possibleSymbolsNumber).charAt(possibleSymbolsNumber-1)+").";
        }
        else{
            return " (0-9, a-"+secretCodeAll.substring(0,possibleSymbolsNumber).charAt(possibleSymbolsNumber-1)+").";
        }
    }

    public static boolean validator(int lengthOfRandomNumber, int possibleSymbolsNumber){
        if(lengthOfRandomNumber>possibleSymbolsNumber){
            System.out.println("Error: it's not possible to generate a code with a length of "+lengthOfRandomNumber+" with "+possibleSymbolsNumber+" unique symbols.");
            return false;
        }

        if(possibleSymbolsNumber>36){
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return false;
        }

        return true;
    }
}
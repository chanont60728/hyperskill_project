package lastpencil;

public class Validator {
    public static boolean numberValidator(String input){
        if(input == null || input.trim().isEmpty()){
            return false;
        }
        try{
            Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public static boolean numberMoreThanZero(int number){
        return number>0;
    }

    public static boolean onlyJohnOrJack(String name){
        return "John".equals(name) || "Jack".equals(name);
    }

    public static boolean onlyOneTwoThree(int number){
         return number >= 1 && number <=3;
    }
}

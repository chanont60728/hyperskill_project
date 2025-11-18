package battleship;
import java.util.ArrayList;

public class ship {
    String name;
    int length;
    int damageLength;
    ArrayList<String> coordinatePart = new ArrayList<>();
    ArrayList<String> damagePart = new ArrayList<>();
    ArrayList<String> coordinateAdjacent = new ArrayList<>();

    public ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.damageLength = 0;
    }

    public void recordCoordinatePart(String coordinateInput){
        String[] arrayCoordinate = coordinateInput.split(" ");

        // front equal
        // add ship coordinate
        if (arrayCoordinate[0].charAt(0) == arrayCoordinate[1].charAt(0)) {
            // ascending
            if(Integer.parseInt(arrayCoordinate[0].substring(1)) <= Integer.parseInt(arrayCoordinate[1].substring(1))){
                for(int i = Integer.parseInt(arrayCoordinate[0].substring(1)); i<=Integer.parseInt(arrayCoordinate[1].substring(1)); i++){
                    coordinatePart.add(String.valueOf(arrayCoordinate[0].charAt(0))+i);
                }
                // add adjacent coordinate
                coordinateAdjacent.add(String.valueOf(arrayCoordinate[0].charAt(0)) + (Integer.parseInt(arrayCoordinate[0].substring(1)) - 1));
                coordinateAdjacent.add(String.valueOf((char)(arrayCoordinate[0].charAt(0) - 1)) + (Integer.parseInt(arrayCoordinate[0].substring(1)) - 1));
                coordinateAdjacent.add(String.valueOf((char)(arrayCoordinate[0].charAt(0) + 1)) + (Integer.parseInt(arrayCoordinate[0].substring(1)) - 1));
                coordinateAdjacent.add(String.valueOf(arrayCoordinate[1].charAt(0)) + (Integer.parseInt(arrayCoordinate[1].substring(1)) + 1));
                coordinateAdjacent.add(String.valueOf((char)(arrayCoordinate[1].charAt(0) - 1)) + (Integer.parseInt(arrayCoordinate[1].substring(1)) + 1));
                coordinateAdjacent.add(String.valueOf((char)(arrayCoordinate[1].charAt(0) + 1)) + (Integer.parseInt(arrayCoordinate[1].substring(1)) + 1));
                for (String j : coordinatePart) {
                    coordinateAdjacent.add((char)(j.charAt(0) - 1) + j.substring(1));
                    coordinateAdjacent.add((char)(j.charAt(0) + 1) + j.substring(1));
                }
            }
            // descending
            else{
                for(int i = Integer.parseInt(arrayCoordinate[0].substring(1)); i>=Integer.parseInt(arrayCoordinate[1].substring(1)); i--){
                    coordinatePart.add(String.valueOf(arrayCoordinate[0].charAt(0))+i);
                }
                // add adjacent coordinate
                coordinateAdjacent.add(String.valueOf(arrayCoordinate[0].charAt(0)) + (Integer.parseInt(arrayCoordinate[0].substring(1)) + 1));
                coordinateAdjacent.add(String.valueOf((char)(arrayCoordinate[0].charAt(0) - 1)) + (Integer.parseInt(arrayCoordinate[0].substring(1)) + 1));
                coordinateAdjacent.add(String.valueOf((char)(arrayCoordinate[0].charAt(0) + 1)) + (Integer.parseInt(arrayCoordinate[0].substring(1)) + 1));
                coordinateAdjacent.add(String.valueOf(arrayCoordinate[1].charAt(0)) + (Integer.parseInt(arrayCoordinate[1].substring(1)) - 1));
                coordinateAdjacent.add(String.valueOf((char)(arrayCoordinate[1].charAt(0) - 1)) + (Integer.parseInt(arrayCoordinate[1].substring(1)) - 1));
                coordinateAdjacent.add(String.valueOf((char)(arrayCoordinate[1].charAt(0) + 1)) + (Integer.parseInt(arrayCoordinate[1].substring(1)) - 1));
                for (String j : coordinatePart) {
                    coordinateAdjacent.add((char)(j.charAt(0) - 1) + j.substring(1));
                    coordinateAdjacent.add((char)(j.charAt(0) + 1) + j.substring(1));
                }
            }
        }
        // back equal
        else if (arrayCoordinate[0].substring(1).equals(arrayCoordinate[1].substring(1))) {
            // ascending
            if(arrayCoordinate[0].charAt(0)<=arrayCoordinate[1].charAt(0)){
                for (char i = arrayCoordinate[0].charAt(0); i <= arrayCoordinate[1].charAt(0); i++) {
                    coordinatePart.add(i+arrayCoordinate[0].substring(1));
                }

                // add adjacent coordinate
                coordinateAdjacent.add((char)(arrayCoordinate[0].charAt(0) - 1) +arrayCoordinate[0].substring(1));
                coordinateAdjacent.add((char)(arrayCoordinate[0].charAt(0) - 1) + String.valueOf(Integer.parseInt(arrayCoordinate[0].substring(1))+1));
                coordinateAdjacent.add((char)(arrayCoordinate[0].charAt(0) - 1) + String.valueOf(Integer.parseInt(arrayCoordinate[0].substring(1))-1));
                coordinateAdjacent.add((char)(arrayCoordinate[1].charAt(0) + 1) +arrayCoordinate[1].substring(1));
                coordinateAdjacent.add((char)(arrayCoordinate[1].charAt(0) + 1) + String.valueOf(Integer.parseInt(arrayCoordinate[0].substring(1))+1));
                coordinateAdjacent.add((char)(arrayCoordinate[1].charAt(0) + 1) + String.valueOf(Integer.parseInt(arrayCoordinate[0].substring(1))-1));

                for(String j : coordinatePart){
                    coordinateAdjacent.add(j.charAt(0) + String.valueOf(Integer.parseInt(j.substring(1))+1));
                    coordinateAdjacent.add(j.charAt(0) + String.valueOf(Integer.parseInt(j.substring(1))-1));
                }
            }

            // descending
            else{
                for (char i = arrayCoordinate[0].charAt(0); i >= arrayCoordinate[1].charAt(0); i--) {
                    coordinatePart.add(i+arrayCoordinate[0].substring(1));
                }

                // add adjacent coordinate
                coordinateAdjacent.add((char)(arrayCoordinate[0].charAt(0) + 1) +arrayCoordinate[0].substring(1));
                coordinateAdjacent.add((char)(arrayCoordinate[0].charAt(0) + 1) + String.valueOf(Integer.parseInt(arrayCoordinate[0].substring(1))+1));
                coordinateAdjacent.add((char)(arrayCoordinate[0].charAt(0) + 1) + String.valueOf(Integer.parseInt(arrayCoordinate[0].substring(1))-1));
                coordinateAdjacent.add((char)(arrayCoordinate[1].charAt(0) - 1) +arrayCoordinate[1].substring(1));
                coordinateAdjacent.add((char)(arrayCoordinate[1].charAt(0) - 1) + String.valueOf(Integer.parseInt(arrayCoordinate[0].substring(1))+1));
                coordinateAdjacent.add((char)(arrayCoordinate[1].charAt(0) - 1) + String.valueOf(Integer.parseInt(arrayCoordinate[0].substring(1))-1));

                for(String j : coordinatePart){
                    coordinateAdjacent.add(j.charAt(0) + String.valueOf(Integer.parseInt(j.substring(1))+1));
                    coordinateAdjacent.add(j.charAt(0) + String.valueOf(Integer.parseInt(j.substring(1))-1));
                }
            }
        }
        coordinateAdjacent.removeIf(s -> s.matches(".*[^A-J0-9].*") || s.endsWith("0") || s.endsWith("11"));
    }

    public boolean validateShipLength(String coordinateInput){
        String[] arrayCoordinate = coordinateInput.split(" ");
        if(Integer.parseInt(arrayCoordinate[0].substring(1)) == Integer.parseInt(arrayCoordinate[1].substring(1))){
            if(Math.abs(arrayCoordinate[0].charAt(0) - arrayCoordinate[1].charAt(0))+1 != length){
                return false;
            }
        }

        if(arrayCoordinate[0].charAt(0) == arrayCoordinate[1].charAt(0)){
            if(Math.abs(Integer.parseInt(arrayCoordinate[0].substring(1)) - Integer.parseInt(arrayCoordinate[1].substring(1)))+1 != length){
                return false;
            }
        }

        return true;
    }

    public void damageStatus(String coordinateInput){
        if(!damagePart.contains(coordinateInput)){
            damagePart.add(coordinateInput);
            damageLength++;
        }
    }
}
package battleship;

import java.util.ArrayList;

public class map {
    ArrayList<ArrayList<String>> UI;
    ArrayList<ArrayList<String>> fogUI;
    String[] mapRow;
    String[] mapColumn;

    map(ArrayList<ArrayList<String>> UI, String[] mapRow, String[] mapColumn) {
        this.UI = UI;
        this.fogUI = new ArrayList<>();
        for (ArrayList<String> row : UI) {
            this.fogUI.add(new ArrayList<>(row)); // copy each row
        }
        this.mapRow = mapRow;
        this.mapColumn = mapColumn;
    }

    public void addBlankSymbol(String symbol) {
        for (int i = 0; i < mapRow.length; i++) {
            UI.add(new ArrayList<>());
            fogUI.add(new ArrayList<>());
            for (int j = 0; j < mapColumn.length; j++) {
                UI.get(i).add(symbol);
                fogUI.get(i).add(symbol);
            }
        }
    }

    public void showUI(String type) {
        System.out.print("  ");
        for (String s : mapColumn) {
            System.out.print(s + " ");
        }
        System.out.println();

        for (int i = 0; i < mapRow.length; i++) {
            System.out.print(mapRow[i] + " ");
            for (int j = 0; j < mapColumn.length; j++) {
                if(type.equals("normal")){
                    System.out.print(UI.get(i).get(j) + " ");
                }
                else if (type.equals("fog")) {
                    System.out.print(fogUI.get(i).get(j) + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean coordinateValidator(String coordinateInput) {
        String[] arrayCoordinate = coordinateInput.split(" ");

        // if first coordinate is A-J
        boolean bothInMapRow;
        boolean firstInMapRow = false;
        boolean secondInMapRow = false;
        for (String str : mapRow) {
            if (arrayCoordinate[0].charAt(0) == str.charAt(0)) {
                firstInMapRow = true;
            }

            if (arrayCoordinate[1].charAt(0) == str.charAt(0)) {
                secondInMapRow = true;
            }
        }
        bothInMapRow = firstInMapRow && secondInMapRow;

        // if last coordinate is in 1-10
        boolean bothInMapColumn;
        boolean firstInMapColumn = false;
        boolean secondInMapColumn = false;

        for (String str : mapColumn) {
            if (arrayCoordinate[0].substring(1).equals(str)) {
                firstInMapColumn = true;
            }
            if (arrayCoordinate[1].substring(1).equals(str)) {
                secondInMapColumn = true;
            }
        }
        bothInMapColumn = firstInMapColumn && secondInMapColumn;

        boolean notDiagonal = false;
        // if coordinate is diagonal
        if ((arrayCoordinate[0].substring(0, 1).equals(arrayCoordinate[1].substring(0, 1))) || (arrayCoordinate[0].substring(1).equals(arrayCoordinate[1].substring(1)))) {
            notDiagonal = true;
        }

        return bothInMapRow && bothInMapColumn && notDiagonal;
    }

    public void changeSymbol(String coordinateInput, String newSymbol) {
        UI.get(coordinateInput.charAt(0) - 65).set(Integer.parseInt(coordinateInput.substring(1))-1, newSymbol);

        if(!newSymbol.equals("O")){
            fogUI.get(coordinateInput.charAt(0) - 65).set(Integer.parseInt(coordinateInput.substring(1))-1, newSymbol);
        }
    }

    public boolean validateShipPosition(String coordinateInput, String newSymbol) {
        // change char ASCII to int ('A' = 65 - 65 = 0) to calculate with arraylist index
        if (UI.get(coordinateInput.charAt(0) - 65).get(Integer.parseInt(coordinateInput.substring(1))-1).equals(newSymbol)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkShootPosition(String coordinateInput, String shipSymbol, String damageSymbol) {
        // change char ASCII to int ('A' = 65 - 65 = 0) to calculate with arraylist index
        // hit
        if (UI.get(coordinateInput.charAt(0) - 65).get(Integer.parseInt(coordinateInput.substring(1))-1).equals(shipSymbol) ||
                UI.get(coordinateInput.charAt(0) - 65).get(Integer.parseInt(coordinateInput.substring(1))-1).equals(damageSymbol)
        ) {
            return true;
        }
        // not hit
        else {
            return false;
        }
    }

    public boolean checkGameEnd(String shipSymbol){
        for (int i = 0; i < mapRow.length; i++) {
            for (int j = 0; j < mapColumn.length; j++) {
                if(UI.get(i).get(j).equals(shipSymbol)){
                    return false;
                }
            }
        }
        return true;
    }
}

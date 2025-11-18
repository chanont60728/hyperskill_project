package battleship;

import java.util.Scanner;

public class player {
    String name;
    player(String name){
        this.name = name;
    }

    public void placeShipCoordinate(ship ship, Scanner sc, map mapPlayer, String shipSymbol, String UIType){
        // Enter coordinate
        System.out.printf("Enter the coordinates of the %s (%d cells):", ship.name, ship.length);
        System.out.println();

        outerLoop:
        while (true) {
            String shipCoordinate = sc.nextLine();
            System.out.println();
            if (ship.validateShipLength(shipCoordinate)) {
                if (mapPlayer.coordinateValidator(shipCoordinate)) {
                    ship.recordCoordinatePart(shipCoordinate);
                    for (String co : ship.coordinateAdjacent) {
                        if (mapPlayer.validateShipPosition(co, shipSymbol)) {
                        } else {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            ship.coordinateAdjacent.clear();
                            ship.coordinatePart.clear();
                            continue outerLoop;
                        }
                    }
                    break;
                }
                else {
                    System.out.println("Error! Wrong ship location! Try again:");
                }
            }
            else {
                System.out.println("Error! Wrong length of the " + ship.name + "! Try again:");
            }
        }

        for (String co : ship.coordinatePart) {
            mapPlayer.changeSymbol(co, shipSymbol);
        }

        mapPlayer.showUI(UIType);
    }

    public void placeShootCoordinate(ship ship, String shootCoordinate, map mapDamage, String shipSymbol, String damageSymbol, String missSymbol) {
        // start shoot
        // hit new spot or old spot
        if (mapDamage.checkShootPosition(shootCoordinate, shipSymbol, damageSymbol)) {
            mapDamage.changeSymbol(shootCoordinate, damageSymbol);
            for (String co : ship.coordinatePart) {
                if (co.equals(shootCoordinate)) {
                    ship.damageStatus(shootCoordinate);
                    if (ship.damageLength == ship.length) {
                        // one ship sank
                        System.out.println("You sank a ship!");
                        System.out.println("Press Enter and pass the move to another player");
                    } else {
                        // no ship sank
                        System.out.println("You hit a ship!");
                        System.out.println("Press Enter and pass the move to another player");
                    }
                }
            }
        }
        // miss
        else {
            mapDamage.changeSymbol(shootCoordinate, missSymbol);
            System.out.println("You missed!");
            System.out.println("Press Enter and pass the move to another player");
        }
    }
}

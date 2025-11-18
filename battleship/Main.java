package battleship;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<String>> playingMap1 = new ArrayList<>();
        ArrayList<ArrayList<String>> playingMap2 = new ArrayList<>();
        ArrayList<ship> army1 = new ArrayList<>();
        ArrayList<ship> army2 = new ArrayList<>();
        String[] mapRow = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String[] mapColumn = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String blankSymbol = "~";
        String shipSymbol = "O";
        String damageSymbol = "X";
        String missSymbol = "M";
        String normalUI = "normal";
        String fogUI = "fog";

        // create player
        player player1 = new player("Player 1");
        player player2 = new player("Player 2");

        // create map
        map mapPlayer1 = new map(playingMap1, mapRow, mapColumn);
        map mapPlayer2 = new map(playingMap2, mapRow, mapColumn);
        mapPlayer1.addBlankSymbol(blankSymbol);
        mapPlayer2.addBlankSymbol(blankSymbol);

        // create ship army1
        army1.add(new ship("Aircraft Carrier", 5));
        army1.add(new ship("Battleship", 4));
        army1.add(new ship("Submarine", 3));
        army1.add(new ship("Cruiser", 3));
        army1.add(new ship("Destroyer", 2));

        // create ship army2
        army2.add(new ship("Aircraft Carrier", 5));
        army2.add(new ship("Battleship", 4));
        army2.add(new ship("Submarine", 3));
        army2.add(new ship("Cruiser", 3));
        army2.add(new ship("Destroyer", 2));

        // player 1 place ship coordinate
        System.out.println(player1.name+", place your ships on the game field");
        mapPlayer1.showUI(normalUI);

        for(ship ship: army1){
            player1.placeShipCoordinate(ship, sc, mapPlayer1, shipSymbol, normalUI);
        }
        System.out.println("Press Enter and pass the move to another player");
        System.out.print(sc.nextLine());

        // player 2 place ship coordinate
        System.out.println(player2.name+", place your ships on the game field");
        mapPlayer2.showUI(normalUI);
        for(ship ship: army2){
            player2.placeShipCoordinate(ship, sc, mapPlayer2, shipSymbol, normalUI);
        }
        System.out.println("Press Enter and pass the move to another player");
        System.out.print(sc.nextLine());

        // shoot
        while(true){
            // player 1 shoot
            mapPlayer2.showUI(fogUI);
            System.out.println("---------------------");
            mapPlayer1.showUI(normalUI);
            System.out.println("Player 1, it's your turn:");
            String p1Shoot = sc.nextLine();
            for(ship ship: army2) {
                player1.placeShootCoordinate(ship, p1Shoot, mapPlayer2, shipSymbol, damageSymbol, missSymbol);
            }

            // all ship sank (game end)
            if(mapPlayer2.checkGameEnd(shipSymbol)){
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            }
            sc.nextLine();

            // player 2 shoot
            mapPlayer1.showUI(fogUI);
            System.out.println("---------------------");
            mapPlayer2.showUI(normalUI);
            System.out.println("Player 2, it's your turn:");
            String p2Shoot = sc.nextLine();
            for(ship ship: army1){
                player2.placeShootCoordinate(ship, p2Shoot, mapPlayer1, shipSymbol, damageSymbol, missSymbol);
            }

            // all ship sank (game end)
            if(mapPlayer1.checkGameEnd(shipSymbol)){
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            }
            sc.nextLine();
        }
        sc.close();
    }
}
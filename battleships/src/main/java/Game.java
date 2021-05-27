import InputOutput.Input;
import boards.Board;
import fields.Coordinates;
import fields.ShipPart;
import players.ComputerPlayer;
import players.ComputerPlayerEasy;
import players.HumanPlayer;
import players.Player;
import ships.Ship;
import ships.ShipFactory;
import ships.ShipType;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public final static Game INSTANCE = new Game();
    public int boardSize;
    private Player player1;
    private Player player2;
    private configurations config;
    private Game() {
        mainMenu();
        Util.INSTANCE.init(boardSize);
        config = configurations.BASIC;
        player1 = new HumanPlayer("Daniel");

    }
    private void mainMenu() {
        boardSize = 10;
    }

    public void playRound(Player player){
        player.getPlayerBoard().print();
        Coordinates input = player.getSingleCd();
        System.out.println("input " + input);
    }
    public void placeShips(Player player){
        for (ShipType shipType : config.getShipsConfig()){
            player.getPlayerBoard().print();
            System.out.println("Ship type " + shipType);
            List<Coordinates> placingCoordinates = getValidShipPlacement(player, shipType.getLength());
            Ship ship = new Ship(shipType, placingCoordinates);
            player.setShipOnBoard(ship);
            System.out.println("input " + placingCoordinates);
            System.out.println("ship parts " + ship.getParts());
        }


    }
    private List<Coordinates> getValidShipPlacement(Player player, int shipLength) {
        List<Coordinates> placingCoordinates = new ArrayList<>();
        boolean coordinatesWithoutShipsNextTo = false;
        while (!coordinatesWithoutShipsNextTo) {
            Coordinates[] input = player.getDoubleCd();
            Coordinates startPoint = input[0];
            Coordinates endPoint = input[1];
            placingCoordinates = startPoint.getCoordinatesInRangeTo(endPoint, shipLength);
            coordinatesWithoutShipsNextTo = areNextToFieldsEmpty(placingCoordinates, player.getPlayerBoard());
        }
        System.out.println(placingCoordinates);
        return placingCoordinates;
    }

    private boolean areNextToFieldsEmpty(List<Coordinates> placingCoordinates, Board checkedBoard) {
        for (Coordinates coordinates : placingCoordinates) {
            for (Coordinates nextToCoordinates : coordinates.getNextFields()){
                boolean notThisShipPart = !placingCoordinates.contains(nextToCoordinates);
                boolean shipOnNextField = checkedBoard.isShipOnField(nextToCoordinates);
                System.out.println(notThisShipPart + " " + shipOnNextField);
                if (notThisShipPart && shipOnNextField){
                    return false;
                }
            }
        }
        return true;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void servePlacingPhase() {
        placeShips(player1);
//        placeShips(player2);
    }
}

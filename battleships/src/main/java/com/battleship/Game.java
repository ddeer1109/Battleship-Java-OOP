package com.battleship;

import com.battleship.fields.Coordinates;
import com.battleship.players.ComputerPlayer;
import com.battleship.players.Player;
import com.battleship.ships.Ship;
import com.battleship.ships.ShipType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public final static Game INSTANCE = new Game();
    private int boardSize;
    private Player player1;
    private Player player2;
    private GameConfiguration config;

    private Game() {

    }
    protected void init(int boardSize, GameConfiguration config, Player player1, Player player2) {
        this.boardSize = boardSize;
        this.config = config;
        this.player1 = player1;
        this.player2 = player2;
    }
    protected void servePlacingPhase() {
        placeShips(player1);
        placeShips(player2);
    }
    private void placeShips(Player player){
        for (ShipType shipType : config.getShipsConfig()){
            player.getPlayerBoard().print();
            Battleship.INSTANCE.display.nicknameTurn(player.getName(), "placing phase");
            Battleship.INSTANCE.display.shipTypeInfo(shipType);

            List<Coordinates> placingCoordinates = getValidShipPlacement(player, shipType.getLength(), true);
            Ship ship = new Ship(shipType, placingCoordinates);
            player.setShipOnBoard(ship);
            player.getShips().add(ship);

            Battleship.INSTANCE.display.placingInfo(ship.getParts());
        }

    }
    private List<Coordinates> getValidShipPlacement(Player player, int shipLength) {
        List<Coordinates> placingCoordinates = new ArrayList<>();
        boolean coordinatesWithoutShipsNextTo = false;

        while (!coordinatesWithoutShipsNextTo || placingCoordinates.size() < shipLength) {
            Coordinates startPoint = player.getSingleCd();

            if (player.getPlayerBoard().isShipOnField(startPoint)) {
                Battleship.INSTANCE.display.shipOnField(startPoint);
                continue;
            }

            Coordinates endPoint = player.getSingleCd();
            placingCoordinates = startPoint.getCoordinatesInRangeTo(endPoint, shipLength);
            if (placingCoordinates.size() == 0)
                Battleship.INSTANCE.display.incorrectPlacingInfo(startPoint, endPoint);
            if (placingCoordinates.size() < shipLength)
                Battleship.INSTANCE.display.incorrectPlacingInfo(placingCoordinates, shipLength);
            coordinatesWithoutShipsNextTo = player.getPlayerBoard().areNextToFieldsEmpty(placingCoordinates);

        }
        return placingCoordinates;
    }



    private List<Coordinates> getValidShipPlacement(Player player, int shipLength, boolean auto) {
        List<Coordinates> placingCoordinates = new ArrayList<>();
        boolean coordinatesWithoutShipsNextTo = false;

        while (!coordinatesWithoutShipsNextTo || placingCoordinates.size() < shipLength) {

            Coordinates [] computerCoordinates = ComputerPlayer.getPlacingCoordinates(player.getPlayerBoard());

            Coordinates startPoint = computerCoordinates[0];
            Coordinates endPoint = computerCoordinates[1];

            if (player.getPlayerBoard().isShipOnField(startPoint)) {
                continue;
            }
            placingCoordinates = startPoint.getCoordinatesInRangeTo(endPoint, shipLength);

            coordinatesWithoutShipsNextTo = player.getPlayerBoard().areNextToFieldsEmpty(placingCoordinates);
        }
        return placingCoordinates;
    }



    protected void serveShootingPhase() {
    }

    private void playShootingTurn(Player player){
        player.getPlayerBoard().print();
        Coordinates input = player.getSingleCd();
    }

    public int getBoardSize() {
        return boardSize;
    }
}

package com.battleship;

import com.battleship.fields.Coordinates;
import com.battleship.fields.FieldState;
import com.battleship.fields.Shot;
import com.battleship.players.ComputerPlayer;
import com.battleship.players.HumanPlayer;
import com.battleship.players.Player;
import com.battleship.ships.Ship;
import com.battleship.ships.ShipType;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public final static Game INSTANCE = new Game();
    private int boardSize;
    private Player player1;
    private Player player2;
    private GameConfiguration config;
    protected Player winner;

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
        // Service placing ship for player. Possible auto placement or manual for human.
        Battleship.INSTANCE.display.newLine(20);
        boolean confirmedAutoPlacement = false;
        List<Coordinates> placingCoordinates;
        if (player instanceof HumanPlayer){
            confirmedAutoPlacement = Battleship.INSTANCE.input.autoPlacementDecision(player.getName());
        }
        for (ShipType shipType : config.getShipsConfig()){

            if (confirmedAutoPlacement || player instanceof ComputerPlayer) {
                placingCoordinates = getValidShipPlacement(player, shipType.getLength());
            } else {
                Battleship.INSTANCE.display.displayPlacingScreen(player, shipType);
                placingCoordinates = getValidShipPlacement((HumanPlayer)player, shipType.getLength());
            }

            Ship ship = new Ship(shipType, placingCoordinates);
            player.getShips().add(ship);
            player.setShipOnBoard(ship);
        }
        Battleship.INSTANCE.display.displayPlacingScreen(player);
        Battleship.INSTANCE.input.pressEnterToContinue();
    }

    private List<Coordinates> getValidShipPlacement(HumanPlayer player, int shipLength) {
        // Human based placing - with UI and manual coordinates choice

        List<Coordinates> placingCoordinates = new ArrayList<>();
        boolean coordinatesWithoutShipsNextTo = false;

        // Valid input until correct placing
        while (!coordinatesWithoutShipsNextTo || placingCoordinates.size() < shipLength) {

            Coordinates startPoint = player.getSingleCd();

            if (player.getPlayerBoard().isShipOnField(startPoint)) {
                Battleship.INSTANCE.display.shipOnField(startPoint);
                continue;
            }
            Coordinates endPoint = player.getSingleCd();
            placingCoordinates = startPoint.getCoordinatesInRangeTo(endPoint, shipLength);
            placingCoordinates  = player.filterIgnoredFields(placingCoordinates);
            if (placingCoordinates.size() == 0)
                Battleship.INSTANCE.display.incorrectPlacingInfo(startPoint, endPoint);
            if (placingCoordinates.size() < shipLength)
                Battleship.INSTANCE.display.incorrectPlacingInfo(placingCoordinates, shipLength);
            coordinatesWithoutShipsNextTo = player.getPlayerBoard().areNextToFieldsEmpty(placingCoordinates);
        }
        return placingCoordinates;
    }

    private List<Coordinates> getValidShipPlacement(Player player, int shipLength) {
        // AI based placing - automatic, randomized, without UI

        List<Coordinates> placingCoordinates = new ArrayList<>();
        boolean coordinatesWithoutShipsNextTo = false;

        // Valid input until correct placing
        while (!coordinatesWithoutShipsNextTo || placingCoordinates.size() < shipLength) {

            Coordinates [] computerCoordinates = player.getRandomPlacingCoordinates();

            Coordinates startPoint = computerCoordinates[0];
            if (player.getPlayerBoard().isShipOnField(startPoint)) {
                continue;
            }
            Coordinates endPoint = computerCoordinates[1];
            placingCoordinates = startPoint.getCoordinatesInRangeTo(endPoint, shipLength);
            placingCoordinates = player.filterIgnoredFields(placingCoordinates);
            coordinatesWithoutShipsNextTo = player.getPlayerBoard()
                    .areNextToFieldsEmpty(placingCoordinates, true);
        }
        return placingCoordinates;
    }



    protected void serveShootingPhase() {

        player1.resetIgnoredFields();
        player2.resetIgnoredFields();

        boolean opponentAlive = true;
        Player currentPlayer = player1;
        Player waitingPlayer = player2;

        while (opponentAlive) {
            playShootingTurn(currentPlayer, waitingPlayer);

            // Check if opponent isAlive;
            opponentAlive = waitingPlayer.isAlive();
            // Change turn
            currentPlayer = currentPlayer==player1 ? player2 : player1;
            waitingPlayer = waitingPlayer==player1 ? player2 : player1;
        }
        setTheWinner();
    }

    private boolean evaluatingWinner() {
        boolean player1isAlive = player1.isAlive();
        boolean player2isAlive = player2.isAlive();
        return player2isAlive && player1isAlive;
    }

    private void setTheWinner() {
        winner = !player1.isAlive()?player2:player1;
        Battleship.INSTANCE.display.greetWinner(winner);
        Battleship.INSTANCE.display.finalScreen(player1, player2);
        Battleship.INSTANCE.display.greetWinner(winner);
    }

    private void playShootingTurn(Player player, Player opponent){
        // Service full player shot turn

        Shot shot;
        do {
            Battleship.INSTANCE.display.newLine(25);
            Battleship.INSTANCE.display.printBoards(player.getPlayerBoard().getFields(), player.getOpponentCopyBoard().getFields());
            Battleship.INSTANCE.display.nicknameTurn(player.getName(), " is choosing target ...\n\n\n");
            Battleship.INSTANCE.input.pressEnterToContinue();

            Coordinates input = player.getSingleCd();

            shot = new Shot(input, player, opponent);
            shot.executeShot();

            Battleship.INSTANCE.display.newLine(25);
            Battleship.INSTANCE.display.printBoards(player.getPlayerBoard().getFields(), player.getOpponentCopyBoard().getFields());
            Battleship.INSTANCE.display.nicknameTurn(player.getName(), ("=== has shot to " + input + " ==>"));
            Battleship.INSTANCE.display.shotResultInfo(shot.getObjOnField(), shot.getSunkShip());
            Battleship.INSTANCE.input.pressEnterToContinue();

        } while(shot.getFieldStateAfterShot() != FieldState.MISSED && evaluatingWinner());
    }

    public int getBoardSize() {
        return boardSize;
    }
}

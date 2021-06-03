package com.battleship.players;

import com.battleship.Battleship;
import com.battleship.InputOutput.CoordinatesGetter;
import com.battleship.boards.Board;
import com.battleship.boards.BoardFactory;
import com.battleship.fields.Coordinates;
import com.battleship.fields.FieldState;
import com.battleship.fields.ShipPart;
import com.battleship.ships.Ship;
import com.battleship.util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Player implements CoordinatesGetter {


    private final String name;
    protected HashSet<Coordinates> fieldsToIgnore = new HashSet<>();
    protected List<Coordinates> hitShipsAlive = new ArrayList<>();
    private final Board playerBoard;
    private final Board opponentCopyBoard;
    private final List<Ship> ships = new ArrayList<Ship>();
    public boolean isAlive;

    public Player(String name) {
        this.name = name;
        this.playerBoard = BoardFactory.createNewBoard();
        this.opponentCopyBoard = BoardFactory.createNewBoard();
        isAlive = true;
    }
    public String getName() {
        return name;
    }
    @Override
    public Coordinates getSingleCd() {
        return Battleship.INSTANCE.input.getHumanSingleCd();
    }
    @Override
    public Coordinates[] getDoubleCd() {
        return new Coordinates[0];
    }

    public Board getPlayerBoard() {
        return playerBoard;
    }
    public Board getOpponentCopyBoard() {
        return opponentCopyBoard;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShipOnBoard(Ship ship) {
        for (ShipPart part : ship.getParts()){
            playerBoard.setShipPart(part);

            Coordinates partCoord = part.getPosition();

            fieldsToIgnore.add(partCoord);
            fieldsToIgnore.addAll(partCoord.getNextFields());
        }
        fieldsToIgnore = new HashSet<>(fieldsToIgnore);
    }

    public Ship getShipByPart(ShipPart part) {
        for (Ship ship : ships){
            if (ship.getParts().contains(part))
                return ship;
        }
        return null;
    }

    public void markShotResult(Coordinates coords, FieldState newState) {
        opponentCopyBoard.getObjectOnField(coords).setState(newState);
    }
    public void markSunkShipParts(List<ShipPart> sunkShipParts) {
        opponentCopyBoard.markSunkShipParts(sunkShipParts);
    }

    public boolean isAlive() {
        // At least one ship is alive

        int countOfShipsAlive = ships.stream()
                                                .filter(ship -> ship.isAlive())
                                                .collect(Collectors.toList()).size();
        return countOfShipsAlive > 0;
    }

    public void resetIgnoredFields() {
        // Clears ignored fields after placing phase
        fieldsToIgnore.clear();
    }

    public List<Coordinates> filterIgnoredFields(List<Coordinates> nextToFields) {
        // Filters out nextToFields of coordinates of not null elements present in fieldsToIgnore or hitShipsAlive

        List<Coordinates> notIgnoredFields = nextToFields
                                                        .stream()
                                                        .filter(
                                                                field ->
                                                                        !fieldsToIgnore.contains(field) &&
                                                                        !hitShipsAlive.contains(field) &&
                                                                        field != null
                                                        )
                                                        .collect(Collectors.toList());

        return notIgnoredFields;
    }

    public Coordinates[] getRandomPlacingCoordinates(){
        return Util.INSTANCE.getRandomPlacingCoordinates();
    }
}

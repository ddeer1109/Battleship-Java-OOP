package com.battleship.boards;

import com.battleship.Battleship;
import com.battleship.fields.*;

import java.util.List;

public class Board {
    private Square[][] fields;
    public Board(int boardSize) {
        this.fields = new Square[boardSize][boardSize];
        initFields();
    }
    public void print() {
        Battleship.INSTANCE.display.printBoard(fields);
    }
    private void initFields() {
        for (int i = 0; i < this.fields.length; i++) {
            for (int j = 0;  j < this.fields.length; j++) {
                    setWater(i, j);
                }
            }
        }
    private void setWater(int i, int j){
        Coordinates coordinates = new Coordinates(i, j);
        this.fields[i][j] = new Water(coordinates);
    }

    public void setShipPart(ShipPart part) {
        this.fields[part.getPosition().getX()][part.getPosition().getY()] = part;
    }


    public boolean areNextToFieldsEmpty(List<Coordinates> placingCoordinates, boolean withoutInfo) {
        for (Coordinates coordinates : placingCoordinates) {
            for (Coordinates nextToCoordinates : coordinates.getNextFields()){
                boolean notThisShipPart = !placingCoordinates.contains(nextToCoordinates);
                boolean shipOnNextField = isShipOnField(nextToCoordinates);
                if (notThisShipPart && shipOnNextField){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean areNextToFieldsEmpty(List<Coordinates> placingCoordinates) {
        for (Coordinates coordinates : placingCoordinates) {
            for (Coordinates nextToCoordinates : coordinates.getNextFields()){
                boolean notThisShipPart = !placingCoordinates.contains(nextToCoordinates);
                boolean shipOnNextField = isShipOnField(nextToCoordinates);
                if (notThisShipPart && shipOnNextField){
                    Battleship.INSTANCE.display.shipOnNextField(nextToCoordinates);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isShipOnField(Coordinates coordinates) {
        return getObjectOnField(coordinates).getState() == FieldState.SHIP;
    }

    public Square getObjectOnField(Coordinates coordinates) {
        return fields[coordinates.getX()][coordinates.getY()];
    }


    public Square[][] getFields() {
        return fields;
    }

    public void setSunkShipFieldsState(List<ShipPart> sunkShipParts) {
        for (ShipPart part : sunkShipParts) {
            getObjectOnField(part.getPosition())
                    .setState(FieldState.SUNK_SHIP);
        }
    }
}

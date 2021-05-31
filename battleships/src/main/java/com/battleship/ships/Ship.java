package com.battleship.ships;

import com.battleship.Battleship;
import com.battleship.fields.Coordinates;
import com.battleship.fields.FieldState;
import com.battleship.fields.ShipPart;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private List<ShipPart> parts = new ArrayList<>();

    public boolean isAlive() {
        return isAlive;
    }

    private boolean isAlive;
    private ShipType type;

    public Ship(ShipType type, List<Coordinates> positionCoordinates) {
        this.type = type;
        parts = ShipFactory.buildParts(type, positionCoordinates);
        isAlive = true;
    }
    public List<ShipPart> getParts() {
        return parts;
    }
    public boolean isSunk() {
        boolean allHit = true;
        for (ShipPart part : parts) {
            FieldState state = part.getState();
            if (state == FieldState.SHIP) {
                return false;
            }
        }
        sunkShip();
        return allHit;
    }
    void sunkShip() {
        isAlive = false;
        for (ShipPart part : parts) {
            part.setState(FieldState.SUNK_SHIP);
        }
    }
    @Override
    public String toString() {
        return "\n"+Battleship.INSTANCE.display.tab.repeat(5) + type.name() + "  { " + parts + "}";
    }
}

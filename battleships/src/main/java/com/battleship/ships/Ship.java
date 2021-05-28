package com.battleship.ships;

import com.battleship.fields.Coordinates;
import com.battleship.fields.ShipPart;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private List<ShipPart> parts = new ArrayList<>();
    private boolean isAlive;

    public Ship(ShipType type, List<Coordinates> positionCoordinates) {
        type = type;
        parts = ShipFactory.buildParts(type, positionCoordinates);
        isAlive = true;
    }
    public List<ShipPart> getParts() {
        return parts;
    }

}

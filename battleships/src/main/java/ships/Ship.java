package ships;

import fields.Coordinates;
import fields.ShipPart;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private List<ShipPart> parts = new ArrayList<>();
    private boolean isAlive;

    public Ship(ShipType type, List<Coordinates> positionCoordinates) {
        type = type;
        parts = ShipFactory.buildParts(type, positionCoordinates);
        isAlive = true;
        System.out.println(parts);
    }

    public List<ShipPart> getParts() {
        return parts;
    }

}

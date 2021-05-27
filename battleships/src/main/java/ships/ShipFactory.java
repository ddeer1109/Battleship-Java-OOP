package ships;

import fields.Coordinates;
import fields.ShipPart;

import java.util.ArrayList;
import java.util.List;

public class ShipFactory {
    public static List<ShipPart> buildParts(ShipType type, List<Coordinates> placingCoordinates) {
        List<ShipPart> shipParts = new ArrayList<>();
        for (int i = 0; i < type.getLength(); i++){
            ShipPart part = new ShipPart(placingCoordinates.get(i), type.getSymbol());
            shipParts.add(part);
        }
        return shipParts;
    }
}

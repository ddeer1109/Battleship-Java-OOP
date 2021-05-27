import ships.ShipType;

import java.util.ArrayList;
import java.util.List;

public enum configurations {
    BASIC(10),
    ;

    List<ShipType> shipsConfig = new ArrayList<ShipType>();
    int boardSize;

    configurations(int boardSize) {
        setBasicConfig();
        this.boardSize = boardSize;
    }

    void setBasicConfig() {
        shipsConfig.add(ShipType.Carrier);
        shipsConfig.add(ShipType.Carrier);
        shipsConfig.add(ShipType.Cruiser);
        shipsConfig.add(ShipType.Cruiser);
        shipsConfig.add(ShipType.Battleship);
        shipsConfig.add(ShipType.Submarine);

    }

    public  List<ShipType> getShipsConfig() {
        return shipsConfig;
    }
}

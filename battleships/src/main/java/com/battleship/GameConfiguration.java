package com.battleship;

import com.battleship.ships.ShipType;

import java.util.ArrayList;
import java.util.List;

public enum GameConfiguration {
    BASIC(10),
    ;

    List<ShipType> shipsConfig = new ArrayList<ShipType>();
    int boardSize;

    GameConfiguration(int boardSize) {
        setBasicConfig();
        this.boardSize = boardSize;
    }

    private void setBasicConfig() {
        shipsConfig.add(ShipType.Carrier);
        shipsConfig.add(ShipType.Cruiser);
        shipsConfig.add(ShipType.Battleship);
        shipsConfig.add(ShipType.Battleship);
        shipsConfig.add(ShipType.Submarine);
        shipsConfig.add(ShipType.Submarine);
        shipsConfig.add(ShipType.Destroyer);
        shipsConfig.add(ShipType.Carrier);
        shipsConfig.add(ShipType.Cruiser);
        shipsConfig.add(ShipType.Cruiser);
    }

    public  List<ShipType> getShipsConfig() {
        return shipsConfig;
    }
}

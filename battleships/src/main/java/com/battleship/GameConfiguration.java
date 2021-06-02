package com.battleship;

import com.battleship.ships.ShipType;

import java.util.ArrayList;
import java.util.List;

public enum GameConfiguration {
    BASIC(10),
    SMALL(5),
    ;
    private List<ShipType> shipsConfig = new ArrayList<>();
    private final int boardSize;

    GameConfiguration(int boardSize) {
        this.boardSize = boardSize;
        setShipsConfig();
    }

    private void setShipsConfig() {
        switch(boardSize) {
            case 10:
                shipsConfig = for10x10();
                break;
            case 5:
                shipsConfig = for5x5();
                break;
            default:
        }
    }
    private List<ShipType> for10x10(){
        return new ArrayList<>(){{
                    add(ShipType.Carrier);
                    add(ShipType.Cruiser);
                    add(ShipType.Battleship);
                    add(ShipType.Battleship);
                    add(ShipType.Submarine);
                    add(ShipType.Submarine);
                    add(ShipType.Destroyer);
                    add(ShipType.Carrier);
                    add(ShipType.Cruiser);
                    add(ShipType.Cruiser);}};
    }
    private List<ShipType> for5x5(){
        return new ArrayList<>(){{
                    add(ShipType.Carrier);
                    add(ShipType.Battleship);
//                    add(ShipType.Submarine);
                    add(ShipType.Carrier);
                    add(ShipType.Cruiser);}};
    }
    public  List<ShipType> getShipsConfig() {
        return shipsConfig;
    }

    public int getBoardSize() {
        return boardSize;
    }
}

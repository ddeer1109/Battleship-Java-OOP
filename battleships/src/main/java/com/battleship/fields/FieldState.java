package com.battleship.fields;

import com.battleship.ships.ShipType;
import com.battleship.util.Symbols;

public enum FieldState {
    WATER,
    SHIP,
    HIT_PART,
    SUNK_SHIP,
    MISSED,
    ;
    private ShipType shipType;

    void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public String getSymbol(){
        String symbol = "";
        switch(this) {
            case WATER:
                symbol = Symbols.WATER.getSymbol();
                break;
            case HIT_PART:
                symbol = Symbols.HIT_PART.getSymbol();
                break;
            case SUNK_SHIP:
                symbol = Symbols.SUNK_SHIP.getSymbol();
                break;
            case MISSED:
                symbol = Symbols.MISSED.getSymbol();
                break;
            case SHIP:
                symbol = shipType.getSymbol();
                break;
        }
        return symbol;
    }
}

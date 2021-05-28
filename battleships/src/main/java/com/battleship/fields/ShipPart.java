package com.battleship.fields;

import com.battleship.ships.ShipType;

public class ShipPart extends Square {
    FieldState state = FieldState.SHIP;

    public ShipPart(Coordinates coordinates, ShipType shipType) {
        super(coordinates, shipType.getSymbol());
        state.setShipType(shipType);
    }

    @Override
    public String getSymbol() {
        return super.getSymbol();
    }

    @Override
    public void setSymbol(String symbol) {
        super.setSymbol(symbol);
    }

    @Override
    public void setState(FieldState state) {
        super.setState(state);
    }

    @Override
    public String toString() {
        return "\n{ " + state +
                " =='" + symbol + '\'' +
                " ==" + position +
                " }";
    }

    @Override
    public FieldState getState() {
        return state;
    }

}

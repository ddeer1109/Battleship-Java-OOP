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
        this.state = state;
        this.symbol = state.getSymbol();
    }

    @Override
    public String toString() {
        return  " "+state.name().substring(0,4) +
                " " + getPosition();
    }

    @Override
    public FieldState getState() {
        return state;
    }

}

package com.battleship.fields;

import com.battleship.util.Symbols;

public class Water extends Square{
    public Water(Coordinates coordinates) {
        super(coordinates);
        state = FieldState.WATER;
        symbol = Symbols.WATER.getSymbol();
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
    public FieldState getState() {
        return super.getState();
    }

    @Override
    public void setState(FieldState state) {
        super.setState(state);
    }
}

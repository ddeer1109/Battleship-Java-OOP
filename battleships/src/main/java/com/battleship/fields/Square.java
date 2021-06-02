package com.battleship.fields;

public abstract class Square{

    protected String symbol = null;
    protected FieldState state;
    private final Coordinates position;
    public Square(Coordinates coordinates) {
        position = coordinates;
    }
    public Square(Coordinates coordinates, String symbol) {
        position = coordinates;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public FieldState getState() {
        return state;
    }

    public void setState(FieldState newState) {
        state = newState;
        this.symbol = state.getSymbol();
    }

    public Coordinates getPosition() {
        return position;
    }
}

package util;

public enum Symbols {
    WATER("~"),
    HIT_PART("X"),
    SUNK_SHIP("#"),
    MISSED("M"),
    Carrier("c"),
    Cruiser("C"),
    Battleship("B"),
    Submarine("S"),
    Destroyer("D");

    private String symbol;

    Symbols(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}

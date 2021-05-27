package ships;

import util.Symbols;

public enum ShipType {
    Carrier(1, Symbols.Carrier.getSymbol()),
    Cruiser(2, Symbols.Cruiser.getSymbol()),
    Battleship(3, Symbols.Battleship.getSymbol()),
    Submarine(4, Symbols.Submarine.getSymbol()),
    Destroyer(5, Symbols.Destroyer.getSymbol())
    ;

    @Override
    public String toString() {
        return name() +" "+"{" +
                "shipLength=" + shipLength +
                ", symbol='" + symbol + '\'' +
                '}';
    }

    private final int shipLength;
    private final String symbol;

    ShipType(int shipLength, String symbol) {
        this.shipLength = shipLength;
        this.symbol = symbol;
    }
    public int getLength() {
        return this.shipLength;
    }
    public String getSymbol() {
        return symbol;
    }

}


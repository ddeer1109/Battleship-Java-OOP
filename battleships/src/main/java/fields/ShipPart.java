package fields;

public class ShipPart extends Square {
    FieldState state = FieldState.SHIP;

    @Override
    public String toString() {
        return "{ " + state +
                " =='" + symbol + '\'' +
                " ==" + position +
                " }";
    }

    public ShipPart(Coordinates coordinates, String symbol) {
        super(coordinates, symbol);
        this.state = FieldState.SHIP;
    }
}

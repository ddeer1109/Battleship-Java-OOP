package fields;

import util.Symbols;

public class Water extends Square{
    public Water(Coordinates coordinates) {
        super(coordinates);
        state = FieldState.WATER;
        symbol = Symbols.WATER.getSymbol();
    }
}

package fields;

public abstract class Square{

    protected String symbol = null;
    protected FieldState state;
    public Coordinates position;
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

    public void setState(FieldState state) {
        this.state = state;
    }

}

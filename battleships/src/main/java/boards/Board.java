package boards;

import InputOutput.Display;
import fields.*;

public class Board {
    private Square[][] fields;
    public Board(int boardSize) {
        this.fields = new Square[boardSize][boardSize];
        initFields();
    }
    public void print() {
        Display.printBoard(fields);
    }
    private void initFields() {
        for (int i = 0; i < this.fields.length; i++) {
            for (int j = 0;  j < this.fields.length; j++) {
                    setWater(i, j);
                }
            }
        }
    private void setWater(int i, int j){
        Coordinates coordinates = new Coordinates(i, j);
        this.fields[i][j] = new Water(coordinates);
    }

    public void setShipPart(ShipPart part) {
        this.fields[part.position.getX()][part.position.getY()] = part;
    }

    public Square getObjectOnField(Coordinates coordinates) {
        return fields[coordinates.getX()][coordinates.getY()];
    }

    public boolean isShipOnField(Coordinates coordinates) {
        return getObjectOnField(coordinates).getState() == FieldState.SHIP;
    }
}

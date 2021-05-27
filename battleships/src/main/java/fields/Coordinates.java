package fields;

import InputOutput.Display;
import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Coordinates {
    private int x;
    private int y;
    public Coordinates nextUp;
    public Coordinates nextRight;
    public Coordinates nextDown;
    public Coordinates nextLeft;

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }


    private void setNextToCoordinates() {
        nextUp = x > 0? new Coordinates((getX()-1), getY()): null;
        nextRight = y < Util.INSTANCE.getBoardSize()-1 ? new Coordinates(getX(), (getY()+1)): null;
        nextDown = x < Util.INSTANCE.getBoardSize()-1? new Coordinates((getX()+1), getY()): null;
        nextLeft = y > 0? new Coordinates(getX(), (getY()-1)): null;
    }

    public Coordinates getNextRight() {
        return nextRight;
    }

    public Coordinates getNextDown() {
        return nextDown;
    }

    public Coordinates getNextLeft() {
        return nextLeft;
    }

    private Coordinates getNextUp() {
        return nextUp;
    }


    public List<Coordinates> getNextFields(){
        setNextToCoordinates();
        List<Coordinates> nextFields = new ArrayList<>();
        nextFields.add(getNextUp());
        nextFields.add(getNextRight());
        nextFields.add(getNextDown());
        nextFields.add(getNextLeft());
        return nextFields;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void incrementX(int value) {
         x+=value;
    }
    public void incrementY(int value) {
         y+=value;
    }

    public List<Coordinates> getCoordinatesInRangeTo(Coordinates endPoint, int distance) {
        List<Coordinates> coordinatesInRange = new ArrayList<>();
        int stepX = 0, stepY = 0;
        Coordinates startPos = new Coordinates(getX(), getY());

        int deltaX = endPoint.getX() - startPos.getX();
        int deltaY = endPoint.getY() - startPos.getY();
        if (deltaX != 0 && deltaY != 0) {
            Display.incorrectRange(startPos, endPoint);
            return coordinatesInRange;
        }
        if (deltaX == 0)
            stepY = deltaY> 0 ? 1 : -1;
        else
            stepX = deltaX> 0 ? 1 : -1;
        for (int i = 0;  i < distance; i++){
            coordinatesInRange.add(new Coordinates(startPos.getX(), startPos.getY()));
            startPos.incrementX(stepX);
            startPos.incrementY(stepY);
        }
        return coordinatesInRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}

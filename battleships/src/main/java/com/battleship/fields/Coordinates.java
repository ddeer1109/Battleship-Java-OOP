package com.battleship.fields;

import com.battleship.Game;
import com.battleship.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Coordinates {
    private int x;
    private int y;
    public Coordinates nextUp;
    public Coordinates nextRight;
    public Coordinates nextDown;
    public Coordinates nextLeft;

    @Override
    public String toString() {
        try {
            return "[ " +
                    "" + Util.getAlphabet()[y] +
                    "" + (x + 1) +
                    " ]";
        }catch (IndexOutOfBoundsException err) {
            return "No string";
        }
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setNextToCoordinates() {
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

    public Coordinates getNextUp() {
        return nextUp;
    }


    public List<Coordinates> getNextLeftRight(){
        List<Coordinates> nextFields = new ArrayList<>();
        nextFields.add(getNextLeft());
        nextFields.add(getNextRight());
        return nextFields;
    }

    public List<Coordinates> getNextTopBot(){
        List<Coordinates> nextFields = new ArrayList<>();
        nextFields.add(getNextUp());
        nextFields.add(getNextDown());
        return nextFields;
    }


    public List<Coordinates> getNextFields(){
        setNextToCoordinates();
        List<Coordinates> nextFields = new ArrayList<>();
        List<Coordinates> notNullNextToFields = new ArrayList<>();
        nextFields.add(getNextUp());
        nextFields.add(getNextRight());
        nextFields.add(getNextDown());
        nextFields.add(getNextLeft());
        notNullNextToFields = nextFields.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return notNullNextToFields;
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
        Coordinates startPos = new Coordinates(getX(), getY());
        int stepX = 0, stepY = 0;
        int deltaX = endPoint.getX() - startPos.getX();
        int deltaY = endPoint.getY() - startPos.getY();
        if (coordinatesAreDiagonal(deltaX, deltaY))
            return coordinatesInRange;

        if (deltaX == 0)
            stepY = deltaY> 0 ? 1 : -1;
        else
            stepX = deltaX> 0 ? 1 : -1;

        for (int i = 0;  i < distance; i++){
            if (startPos.coordinatesOutOfRange())
                return coordinatesInRange;
            coordinatesInRange.add(new Coordinates(startPos.getX(), startPos.getY()));
            startPos.incrementX(stepX); startPos.incrementY(stepY);
        }
        return coordinatesInRange;
    }

    private boolean coordinatesAreDiagonal(int deltaX, int deltaY) {
        boolean coordinatesInDiagonalRange = deltaX != 0 && deltaY != 0;
        return  coordinatesInDiagonalRange;
    }

    private boolean coordinatesOutOfRange() {
        boolean outOfRangeX = x >= Game.INSTANCE.getBoardSize() || x < 0;
        boolean outOfRangeY = y >= Game.INSTANCE.getBoardSize() || y < 0;
        return outOfRangeX || outOfRangeY;
    }

    public boolean isXDirected(Coordinates another) {
        return this.x == another.getX();
    }
    public boolean isYDirected(Coordinates another) {
        return this.y == another.getY();
    }
    public boolean isInLineWith(Coordinates another) {
        if (isXDirected(another)) {
            return Math.abs(this.y - another.getY()) >= 1;
        }else if (isYDirected(another)){
            return Math.abs(this.x - another.getX()) >= 1;
        } else if (isXDirected(another) && isYDirected(another));
            return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Coordinates)) return false;
        Coordinates o = (Coordinates) obj;
        return o.x == this.x && o.y == this.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}

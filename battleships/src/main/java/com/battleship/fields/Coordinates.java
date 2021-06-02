package com.battleship.fields;

import com.battleship.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Coordinates {
    private final int x;
    private final int y;
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


    public Coordinates getNextRight() {
        return y < Util.INSTANCE.getBoardSize()-1 ?
                new Coordinates(getX(), (getY()+1)) :
                null;
    }

    public Coordinates getNextDown() {
        return x < Util.INSTANCE.getBoardSize()-1 ?
                new Coordinates((getX()+1), getY()) :
                null;
    }

    public Coordinates getNextLeft() {
        return y > 0 ?
                new Coordinates(getX(), (getY()-1)) :
                null;
    }

    public Coordinates getNextUp() {
        return x > 0 ?
                new Coordinates((getX()-1), getY()) :
                null;
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
        List<Coordinates> nextFields = new ArrayList<>();
        List<Coordinates> notNullNextToFields = new ArrayList<>();

        nextFields.addAll(getNextTopBot());
        nextFields.addAll(getNextLeftRight());

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

    public List<Coordinates> getCoordinatesInRangeTo(Coordinates endPoint, int distance) {
        List<Coordinates> coordinatesInRange = new ArrayList<>();
        int deltaX = endPoint.getX() - getX();
        int deltaY = endPoint.getY() - getY();

        Coordinates nextCoordinates = getNextCoordinatesByDelta(deltaX, deltaY);
        for (int i = 0;  i < distance; i++){
            if (nextCoordinates == null)
                return coordinatesInRange;

            nextCoordinates = nextCoordinates.getNextCoordinatesByDelta(deltaX, deltaY);
            coordinatesInRange.add(nextCoordinates);
        }
        return coordinatesInRange;
    }

    private Coordinates getNextCoordinatesByDelta(int deltaX, int deltaY) {
        if (deltaX == 0) {
            // X is not changing - move in horizontal direction
            return deltaY > 0 ?
                    getNextRight() : getNextLeft();
        }
        else if (deltaY == 0) {
            // Y is not changing - move in vertical direction
            return deltaX > 0 ?
                    getNextUp() : getNextDown();
        }
            // else - Incorrect pair - coordinates in diagonal line
        return null;
    }

    public boolean isXDirected(Coordinates another) {
        return this.x == another.getX();
    }
    public boolean isYDirected(Coordinates another) {
        return this.y == another.getY();
    }
    public boolean isInLineWith(Coordinates another) {
        // Checks if one of this co-ordinates is same as another
        // both same returns false
        if (isXDirected(another)) {
            return Math.abs(this.y - another.getY()) >= 1;
        }else if (isYDirected(another)){
            return Math.abs(this.x - another.getX()) >= 1;
        }
        return false;
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

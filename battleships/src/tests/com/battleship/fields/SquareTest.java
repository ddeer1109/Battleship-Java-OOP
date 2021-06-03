package com.battleship.fields;

import com.battleship.ships.ShipType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    @Test
    void getSymbol() {


        Square water = new Water(new Coordinates(0,0));
        System.out.println(water.getState().getSymbol());
        assertTrue(water.getState().getSymbol() == " ");
        assertTrue(water.getState().getSymbol() == FieldState.WATER.getSymbol());


        ShipType type = ShipType.Carrier;
        ShipPart shipPart = new ShipPart(new Coordinates(0,0), type);

        assertTrue(shipPart.getState().getSymbol() == type.getSymbol());

    }

    @Test
    void getState() {
        Square water = new Water(new Coordinates(0,0));
        System.out.println(water.toString());
        assertTrue(
                water.getState() instanceof FieldState
                        && water.getState() == FieldState.WATER
        );
    }

    @Test
    void setState() {
        Square water = new Water(new Coordinates(0,0));
        System.out.println(water.toString());
        water.setState(FieldState.SUNK_SHIP);
        System.out.println(water.toString());

        assertTrue(
                water.getState() instanceof FieldState
                        && water.getState() == FieldState.SUNK_SHIP
        );
        water.setState(FieldState.WATER);
        System.out.println(water.toString());

        assertTrue(
                water.getState() instanceof FieldState
                        && water.getState() == FieldState.WATER
        );
        water.setState(FieldState.MISSED);
        System.out.println(water.toString());

        assertTrue(
                water.getState() instanceof FieldState
                        && water.getState() == FieldState.MISSED
        );

        water.setState(FieldState.WATER);
        water.setState(FieldState.HIT_PART);
        System.out.println(water.toString());

        assertTrue(
                water.getState() instanceof FieldState
                        && water.getState() == FieldState.HIT_PART
        );
    }

    @Test
    void getPosition() {
    }
}
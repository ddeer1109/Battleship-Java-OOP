package com.battleship.boards;

import com.battleship.fields.Coordinates;
import com.battleship.fields.FieldState;
import com.battleship.fields.ShipPart;
import com.battleship.fields.Water;
import com.battleship.ships.ShipType;
import com.battleship.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    int boardSize;
    Board testedBoard;

    @BeforeEach
    private void setUp(){
        boardSize = 3;
        testedBoard = new Board(boardSize);
    }

    @Test
    public void testInitBoard() {
        int boardSize = 10;
        Board tested = new Board(boardSize);
        assertAll(
                () -> assertEquals(FieldState.WATER,
                        tested.getFields()[0][0].getState()),
                () -> assertEquals(FieldState.WATER,
                        tested.getFields()[boardSize-1][boardSize-1].getState()),
                () -> assertEquals(
                        tested.getFields().length, boardSize),
                () -> assertEquals(
                        tested.getFields()[0].length, boardSize)
        );
    }

    @Test
    public void shouldFreshInitBoardIsFilledWithWater(){
        Arrays.stream(testedBoard.getFields()).forEach(
                row -> Arrays.stream(row).forEach(
                        field -> assertInstanceOf(Water.class, field)));
    }


    @Test
    public void shouldShipSetOnCoordinatesIsDetectableByIsShipOnFieldMethod(){
        Coordinates coordinates = new Coordinates(0, 0);
        ShipPart part = new ShipPart(coordinates, ShipType.Carrier);

        testedBoard.setShipPart(part);
        Coordinates coordinatesWithoutShip = new Coordinates(0, 1);

        assertTrue(testedBoard.isShipOnField(coordinates));
        assertFalse(testedBoard.isShipOnField(coordinatesWithoutShip));
    }


}

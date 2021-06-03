package com.battleship.boards;

import com.battleship.fields.FieldState;
import com.battleship.fields.Water;
import com.battleship.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

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

}
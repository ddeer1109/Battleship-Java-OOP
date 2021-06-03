package com.battleship.fields;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {
    int boardSize = 10;
//    @Test
//    public void testCoordinates() {
//        Coordinates tested = new Coordinates(-1,1);
//        System.out.println(tested);
//        assertTrue(tested != null, "Failed to build object");
//    }
    @BeforeEach
    public void beforeEachTest(){
        Coordinates.setValidRange(boardSize);
    }

    @Test
    public void testCoordinates(){


//        testGetCoordinatesInRangeTo();
    }

    @Test
    public void testGetPreviousAxisX() {

        System.out.println("testGetPreviousAxisX");
        Coordinates shouldNotPass = new Coordinates(0,3);
        Coordinates shouldPass = new Coordinates(1,3);
        System.out.println("\nshould pass");
        System.out.print( shouldPass + " -> ");
        System.out.print(shouldPass.getNextTop());

        System.out.println("\nshould pass NOT");
        System.out.print( shouldNotPass + " -> ");
        System.out.print(shouldNotPass.getNextTop());
//        System.out.println(shouldNotPass.getPreviousAxisX());

        assertTrue(shouldPass.getNextTop() != null && shouldNotPass.getNextTop() == null, "There is no next up for first vertical coordinate.");

    }
    @Test
    public void testGetNextAxisY() {

        System.out.println("testGetNextAxisY");
        Coordinates shouldNotPass = new Coordinates(0, Coordinates.getValidRange());
        Coordinates shouldPass = new Coordinates(1,Coordinates.getValidRange()-1);
        System.out.println("\nshould pass");
        System.out.print( shouldPass + " -> ");
        System.out.print(shouldPass.getNextRight());

        System.out.println("\nshould pass NOT");
        System.out.print( shouldNotPass + " -> ");
        System.out.print(shouldNotPass.getNextRight());
//        System.out.println(shouldNotPass.getNextAxisY());

        assertTrue(shouldPass.getNextRight() != null && shouldNotPass.getNextRight() == null, "There is no next bottom for last vertical coordinate.");

    }
    @Test
    public void testGetNextAxisX() {

        System.out.println("testGetNextAxisX");
        Coordinates shouldNotPass = new Coordinates(Coordinates.getValidRange(),3);
        Coordinates shouldPass = new Coordinates(Coordinates.getValidRange()-1,3);
        System.out.println("\nshould pass");
        System.out.print( shouldPass + " -> ");
        System.out.print(shouldPass.getNextDown());

        System.out.println("\nshould pass NOT");
        System.out.print( shouldNotPass + " -> ");
        System.out.print(shouldNotPass.getNextDown());
//        System.out.println(shouldNotPass.getNextAxisX());

        assertTrue(shouldPass.getNextDown() != null && shouldNotPass.getNextDown() == null, "There is no next right for last horizontal coordinate.");


    }
    @Test
    public void testGetPreviousAxisY() {

        System.out.println("testGetPreviousAxisY");

        Coordinates shouldNotPass = new Coordinates(0,0);
        Coordinates shouldPass = new Coordinates(1,1);

        System.out.println("\nshould pass");
        System.out.print( shouldPass + " -> ");
        System.out.print(shouldPass.getNextLeft());

        System.out.println("\nshould pass NOT");
        System.out.print( shouldNotPass + " -> ");
        System.out.print(shouldNotPass.getNextLeft());
//        System.out.println(shouldNotPass.getPreviousAxisY());

        assertTrue(shouldPass.getNextLeft() != null
                        && shouldNotPass.getNextLeft() == null,
                "There is no next left for first horizontal coordinate.");

    }
    @Test
    public void testGetCoordinatesInRangeTo(){

        int shipLength = 3;

        Coordinates startPoint = new Coordinates(7, 7);
        Coordinates shouldNotPass = new Coordinates(9, 6);
        Coordinates shouldNotPass1 = new Coordinates(9, 7);
        Coordinates shouldPass = new Coordinates(4, 7);

        List<Coordinates> shouldBeNull = startPoint.getCoordinatesInRangeTo(shouldNotPass, shipLength);
        List<Coordinates> sizeShouldBeLessThanShipLength = startPoint.getCoordinatesInRangeTo(shouldNotPass1, shipLength);
        List<Coordinates> shouldBeShipsLength = startPoint.getCoordinatesInRangeTo(shouldPass, shipLength);

        System.out.println("\n\nTesting getting coordinates in range to. Ship length: "+shipLength);
        System.out.println("Null\n\t\t"+ shouldBeNull);
        System.out.println("Not enough fields too small\n\t\t"+ sizeShouldBeLessThanShipLength);
        System.out.println("OK\n\t\t"+ shouldBeShipsLength);
        assertTrue(
                shouldBeNull.size() != shipLength &&
                        sizeShouldBeLessThanShipLength.size() != shipLength &&
                        shouldBeShipsLength.size() == shipLength
                );
    }
    @Test
    public void testIsInLineWith(){

    }
}
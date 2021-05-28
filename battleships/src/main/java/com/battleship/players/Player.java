package com.battleship.players;

import com.battleship.InputOutput.CoordinatesGetter;
import com.battleship.boards.Board;
import com.battleship.boards.BoardFactory;
import com.battleship.fields.Coordinates;
import com.battleship.fields.ShipPart;
import com.battleship.ships.Ship;

import java.util.ArrayList;
import java.util.List;

public abstract class Player implements CoordinatesGetter {


    private String name;

    private Board playerBoard;
    private Board opponentCopyBoard;
    private List<Ship> ships = new ArrayList<Ship>();
    public Player(String name) {
        this.name = name;
        this.playerBoard = BoardFactory.createNewBoard();
        this.opponentCopyBoard = BoardFactory.createNewBoard();
    }

    @Override
    public Coordinates getSingleCd() {
        return null;
    }

    @Override
    public Coordinates[] getDoubleCd() {
        return new Coordinates[0];
    }

    public void setShipOnBoard(Ship ship) {
        for (ShipPart part : ship.getParts()){
            playerBoard.setShipPart(part);
        }
    }
    public Board getPlayerBoard() {
        return playerBoard;
    }

    public Board getOpponentCopyBoard() {
        return opponentCopyBoard;
    }

    public String getName() {
        return name;
    }

    public List<Ship> getShips() {
        return ships;
    }

}

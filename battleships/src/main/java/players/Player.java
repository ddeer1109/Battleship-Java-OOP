package players;

import InputOutput.CoordinatesGetter;
import boards.Board;
import boards.BoardFactory;
import fields.Coordinates;
import fields.ShipPart;
import ships.Ship;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public abstract class Player implements CoordinatesGetter {
    public String name;

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

}

package com.battleship.players;

import com.battleship.Battleship;
import com.battleship.boards.Board;
import com.battleship.fields.Coordinates;
import com.battleship.ships.Ship;
import com.battleship.util.Util;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class HumanPlayer extends Player{

    public HumanPlayer(String name) {
        super(name);
    }
    @Override
    public Coordinates getSingleCd() {
        return Battleship.INSTANCE.input.getHumanSingleCd();
    }

    @Override
    public Coordinates[] getDoubleCd() {
        return Battleship.INSTANCE.input.getHumanDoubleCd();
    }

    @Override
    public void setShipOnBoard(Ship ship) {
        super.setShipOnBoard(ship);
    }

    @Override
    public Board getPlayerBoard() {
        return super.getPlayerBoard();
    }

    @Override
    public Board getOpponentCopyBoard() {
        return super.getOpponentCopyBoard();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public List<Ship> getShips() {
        return super.getShips();
    }

}

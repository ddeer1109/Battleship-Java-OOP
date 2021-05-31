package com.battleship.players;

import com.battleship.Battleship;
import com.battleship.fields.Coordinates;


public class ComputerPlayerMedium extends ComputerPlayer {
    public ComputerPlayerMedium(String name) {
        super(name+"_EASY");
    }

    @Override
    public Coordinates getSingleCd() {
        Coordinates shotCoords;
        shotCoords = super.getRandomCdExcludeIgnoredFields();
        if (hitShipsAlive.size() != 0){
            shotCoords = super.getNextRandomCdNextToHitPart();
        }
        return shotCoords;
    }

    @Override
    public boolean isAlive() {
        return super.isAlive();
    }

    @Override
    public Coordinates[] getDoubleCd() {
        return super.getRandomPlacingCoordinates();
    }
}

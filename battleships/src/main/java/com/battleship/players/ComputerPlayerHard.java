package com.battleship.players;

import com.battleship.fields.Coordinates;

public class ComputerPlayerHard extends ComputerPlayer{
    public ComputerPlayerHard(String name) {
        super(name+"_HARD");
    }

    @Override
    public Coordinates getSingleCd() {
        Coordinates shotCoords;
        shotCoords = super.getRandomCdExcludeIgnoredFields();
        if(hitShipsAlive.size() >= 2){
            shotCoords = super.getRandomCdOfTwoHitsDirection();
        }
        else if (hitShipsAlive.size() != 0){
            shotCoords = super.getNextRandomCdNextToHitPart();
        }
        return shotCoords;
    }

    @Override
    public boolean isAlive() {
        return super.isAlive();
    }
}

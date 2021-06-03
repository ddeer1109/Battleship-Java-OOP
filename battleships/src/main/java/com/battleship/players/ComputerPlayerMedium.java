package com.battleship.players;

import com.battleship.fields.Coordinates;


public class ComputerPlayerMedium extends ComputerPlayer {
    public ComputerPlayerMedium(String name) {
        super(name+"_Medium");
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

}

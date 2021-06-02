package com.battleship.players;

import com.battleship.fields.Coordinates;

public class ComputerPlayerEasy extends ComputerPlayer {
    public ComputerPlayerEasy(String name) {
        super(name+"_Easy");
    }

    @Override
    public Coordinates getSingleCd() {
        return getRandomCdExcludeIgnoredFields();
    }
}

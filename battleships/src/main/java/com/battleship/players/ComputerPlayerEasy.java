package com.battleship.players;

import com.battleship.fields.Coordinates;

public class ComputerPlayerEasy extends ComputerPlayer {
    public ComputerPlayerEasy(String name) {
        super(name+"Easy");
    }

    @Override
    public Coordinates getSingleCd() {
        return null;
    }

    @Override
    public Coordinates[] getDoubleCd() {
        return new Coordinates[0];
    }
}

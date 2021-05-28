package com.battleship.players;

import com.battleship.Game;
import com.battleship.boards.Board;
import com.battleship.fields.Coordinates;

import java.util.List;
import java.util.Random;

public abstract class ComputerPlayer extends Player{
    public ComputerPlayer(String name) {
        super(name+"AI");
    }
    public static Coordinates [] getPlacingCoordinates(Board playerBoard) {

        Coordinates [] coordinates = new Coordinates[2];
        Random generator = new Random();
        int x = generator.nextInt(Game.INSTANCE.getBoardSize());
        int y = generator.nextInt(Game.INSTANCE.getBoardSize());
        coordinates[0] = new Coordinates(x,y);
        List<Coordinates> nextTwoFields = coordinates[0].getNextFields();
        coordinates[1] = nextTwoFields.get(generator.nextInt(nextTwoFields.size()));
        System.out.println(coordinates.toString());
        return coordinates;
    }
}

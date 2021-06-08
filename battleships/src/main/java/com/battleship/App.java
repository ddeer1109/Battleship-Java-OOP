package com.battleship;

public class App {
    public static void main(String[] args)  {

        Battleship.INSTANCE.init();
        Battleship.INSTANCE.startGame();
    }
}

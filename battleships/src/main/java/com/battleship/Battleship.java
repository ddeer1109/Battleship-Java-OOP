package com.battleship;

import com.battleship.InputOutput.Display;
import com.battleship.InputOutput.Input;
import com.battleship.players.Player;
import com.battleship.players.PlayerCreator;
import com.battleship.util.Util;

public class Battleship {
    public final static Battleship INSTANCE = new Battleship();
    public Display display;
    public Input input;
    private GameConfiguration config;
    private int boardSize;
    private Battleship() {
    }

    protected void init() {
        mainMenu();

        Util.INSTANCE.init(boardSize);
        input = new Input();
        display = new Display();
        display.initWelcome();


        PlayerCreator player1 = PlayerCreator.HUMAN;
        PlayerCreator player2 = PlayerCreator.AI_EASY;
        player1.setPlayer(); player2.setPlayer();

        Game.INSTANCE.init(boardSize, config, player1.getPlayer(), player2.getPlayer());
        Game.INSTANCE.servePlacingPhase();
        Game.INSTANCE.serveShootingPhase();
    }

    private void mainMenu() {
        boardSize = 10;
        config = GameConfiguration.BASIC;
    }


}

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

    void init() {
        mainMenu();

        Util.INSTANCE.init(boardSize);
        input = new Input();
        display = new Display();
        display.initWelcome();
        input.pressEnterToContinue();

        Player player1 = PlayerCreator.AI_HARD.getPlayer();
        Player player2 = PlayerCreator.AI_EASY.getPlayer();

        Game.INSTANCE.init(boardSize, config, player1, player2);
        Game.INSTANCE.servePlacingPhase();
        Game.INSTANCE.serveShootingPhase();
    }

    private void mainMenu() {
        boardSize = 10;
        config = GameConfiguration.BASIC;
    }


}

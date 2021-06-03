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

    private Player player1;
    private Player player2;

    public boolean isTest = false;
    private Battleship() {
        input = new Input();
        display = new Display();
    }
    public void initTest(int boardSize){

    }
    void init() {
        display.initWelcome();
        input.pressEnterToContinue();

        if (!isTest) {
            customGameInit();
        } else{
            testGameInit();
        }

        Game.INSTANCE.init(boardSize, config, player1, player2);

        Game.INSTANCE.servePlacingPhase();
        Game.INSTANCE.serveShootingPhase();
    }
    void customGameInit() {
        mainMenu();

        Util.INSTANCE.init(boardSize);
        display.setUiComponents();

        player1 = input.playerType().retrieveNewPlayerObject();
        player2 = input.playerType().retrieveNewPlayerObject();

    }

    void testGameInit() {
        testConfig();

        Util.INSTANCE.init(boardSize);
        display.setUiComponents();

        player1 = PlayerCreator.AI_HARD.retrieveNewPlayerObject();
        player2 = PlayerCreator.AI_HARD.retrieveNewPlayerObject();
    }

    protected void mainMenu() {
        display.boardConfigMenu();
        config = input.getGameConfig();
        boardSize = config.getBoardSize();
    }
    protected void testConfig() {
//        display.boardConfigMenu();
        config = GameConfiguration.BASIC;
        boardSize = config.getBoardSize();
    }


}

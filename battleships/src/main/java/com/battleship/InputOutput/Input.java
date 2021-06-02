package com.battleship.InputOutput;

import com.battleship.Battleship;
import com.battleship.GameConfiguration;
import com.battleship.fields.Coordinates;
import com.battleship.players.PlayerCreator;
import com.battleship.util.Util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    public Scanner sc = new Scanner(System.in);
    public Input() {
    }
    public String playerNickname(int num) {
        Battleship.INSTANCE.display.nicknameInput(num);
        sc.nextLine();
        String input = sc.nextLine();
        return input;
    }

    public void pressEnterToContinue(){
        Battleship.INSTANCE.display.newLine(2);
        System.out.print("Press enter to continue ... ");
        sc.nextLine();
    }

    public Coordinates getHumanSingleCd() {

        String[] cord = new String[2];
        do {
//            Scanner sc = new Scanner(System.in);
            Battleship.INSTANCE.display.askForCd();
            String input = sc.nextLine().toUpperCase();
            try {
                cord[0] = String.valueOf(input.charAt(0));
                cord[1] = input.substring(1);
            } catch (Exception err) {
                continue;
            }
        } while (!Battleship.INSTANCE.input.areCoordinatesInRange(cord));
        int x = Util.INSTANCE.rowsDictionary.get(cord[1]);
        int y = Util.INSTANCE.colsDictionary.get(cord[0]);
        Coordinates coordinates = new Coordinates(x, y);
        return coordinates;
    }
    public Coordinates[] getHumanDoubleCd() {
        Coordinates[] doubleCd = new Coordinates[2];
        doubleCd[0] = getHumanSingleCd();
        Battleship.INSTANCE.display.singleCdInfo(doubleCd[0]);
        doubleCd[1] = getHumanSingleCd();
        return doubleCd;
    }
    public boolean areCoordinatesInRange(String[] inputData) {
        boolean inRange = false;
        try {
            boolean colCorrect = Util.INSTANCE.colsDictionary.get(inputData[0]) != null;
            boolean rowCorrect = Util.INSTANCE.rowsDictionary.get(inputData[1]) != null;
            if(colCorrect && rowCorrect)
                return true;
        }
        catch (NullPointerException ex) {
            Battleship.INSTANCE.display.outOfBoardInfo();
        }
        Battleship.INSTANCE.display.outOfBoardInfo();
        return inRange;
    }
    public boolean autoPlacementDecision(String name) {
        Battleship.INSTANCE.display.autoPlacementDecision(name);
        sc.nextLine();
        String input = sc.nextLine();
        boolean confirmed = input == "";
        return confirmed;
    }
    public GameConfiguration getGameConfig() {
        try {
            switch(sc.nextInt()) {
                case 10:
                    return GameConfiguration.BASIC;
                case 5:
                    return GameConfiguration.SMALL;
                default:
                    return GameConfiguration.SMALL;
            }
        } catch (InputMismatchException err) {
            return GameConfiguration.SMALL;
        }
    }
    public PlayerCreator playerType() {
        Battleship.INSTANCE.display.playerChoiceMenu();
        try {
            switch(sc.nextInt()) {
                case 0:
                    return PlayerCreator.HUMAN;
                case 1:
                    return PlayerCreator.AI_EASY;
                case 2:
                    return PlayerCreator.AI_MEDIUM;
                case 3:
                    return PlayerCreator.AI_HARD;
                default:
                    return PlayerCreator.AI_EASY;
            }
        } catch (InputMismatchException err) {
            return PlayerCreator.AI_MEDIUM;
        }
    }
}

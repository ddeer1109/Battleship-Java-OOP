package com.battleship.InputOutput;

import com.battleship.Battleship;
import com.battleship.fields.Coordinates;
import com.battleship.util.Util;

import java.util.List;
import java.util.Scanner;

public class Input {
    public Scanner sc = new Scanner(System.in);
    public Input() {
    }
    public String playerNickname(int num) {
        Battleship.INSTANCE.display.nicknameInput(num);
        String input = sc.nextLine();
        return input;
    }

    public void pressEnterToContinue(){
        System.out.println("\n\nPress enter to continue ... ");
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
        String input = sc.nextLine();
        System.out.println("INput ["+input+"]");
        boolean confirmed = input == "";
        return confirmed;
    }
}

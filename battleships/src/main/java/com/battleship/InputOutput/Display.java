package com.battleship.InputOutput;

import com.battleship.fields.Coordinates;
import com.battleship.fields.ShipPart;
import com.battleship.fields.Square;
import com.battleship.ships.ShipType;
import com.battleship.util.Util;

import java.util.List;
import java.util.Locale;

public class Display {
    String tab = "\t\t\t\t";
    String hr;
     String insideSeparator;
     String rightSideSeparator;
     int boardSize = 10;

    public Display() {
        setUiComponents();
    }

    public void initWelcome() {
        tabulator();
        System.out.print("--------------------------------------------------------------------------------------------\n\n");
        System.out.print(tab+tab+"WELCOME TO BATTLESHIPS GAME\n\n");
        tabulator();
        System.out.print("--------------------------------------------------------------------------------------------");
        newLine();
        newLine();
        newLine();
    }

    private void setUiComponents() {
        boardSize = Util.INSTANCE.getBoardSize();
        hr = tab + "-".repeat(boardSize + 3 * boardSize + 1 );
        insideSeparator = " | ";
        rightSideSeparator = " |";
    }
    public void printBoard(Square[][] fields) {

        printLettersLine();
        for (int i = 0; i < boardSize; i++) {
            printNumIndexString(i);

            for (int j = 0;  j < boardSize; j++) {
                String letter = fields[i][j].getSymbol();
                if (j == boardSize-1) {
                    System.out.print(letter + rightSideSeparator);
                } else {
                    System.out.print(letter + insideSeparator);
                }
            }
            newLine();
            horizontalLine();
        }
    }

    public void horizontalLine() {
        System.out.println("\t"+hr);
    }
    private void tabulator() { System.out.print("\t");}
    private void newLine() { System.out.print("\n");}
    public void nicknameInput(int num) {
        String order = num==1?"first":"second";
        tabulator();
        System.out.print(String.format("Type nickname for %s player: ", order));

    }
    public void nicknameTurn(String nickname, String turnType) {
        tabulator();
        System.out.println(String.format("%s's %s turn", nickname, turnType));
        newLine();
    }
    private void printLettersLine() {
        String line = "\t  ";
        for (int i = 0; i < boardSize; i++) {
            if (i != boardSize-1) {
                line += Util.INSTANCE.getLetterIndexes()[i] + "   ";
            } else {
                line += Util.INSTANCE.getLetterIndexes()[i] + "  ";
            }
        }
        newLine();newLine();newLine();newLine();
        System.out.println(tab+line);
        System.out.println("\t"+hr);
    }
    private void printNumIndexString(int index) {
        System.out.print(String.format(tab+"%d\t| ", index+1));
    }

    public void singleCdInfo(Coordinates coord) {
        tabulator();
        System.out.println("Selected coordinates: "+coord);
    }
    public void incorrectRange(Coordinates start, Coordinates end) {
        tabulator();
        System.out.println(String.format("Incorrect coordinates range from %s to %s", start, end));
    }
    public void outOfBoardInfo() {
        tabulator();
        System.out.print("Coordinates out of board range.");
    }
    public void shipTypeInfo(ShipType type) {
        tabulator();
        System.out.println("Ship type " + type);
    }
    public void placingInfo(List<ShipPart> parts) {
        tabulator();
        tabulator();
        System.out.println("ship parts " + parts);
    }
    public void shipOnNextField(Coordinates shipCoordinates) {
        newLine();
        tabulator();
        System.out.println(String.format("incorrect input. Ship next to on coordinates: %s".toUpperCase(), shipCoordinates));
        newLine();
    }

    public void shipOnField(Coordinates coordinates){
        newLine();
        tabulator();
        System.out.println(String.format("incorrect input. Ship on coordinates: %s".toUpperCase(), coordinates));
        newLine();
    }


    public void askForCd() {
        tabulator();
        System.out.print("Type correct coordinates e.g. 'A1': ");
    }

    public void incorrectPlacingInfo(Coordinates startPoint, Coordinates endPoint) {
        newLine();
        tabulator();
        System.out.println(
                String.format(
                        ("Coordinates %s  %s are not in horizontal nor vertical orientation ")
                        .toUpperCase(), startPoint, endPoint));
        newLine();
    }
    public void incorrectPlacingInfo(List<Coordinates> placingCoordinates, int shipLength) {
        newLine();
        tabulator();
        System.out.println(
                String.format(
                        ("Coordinates %s cannot fit ship with length %s ")
                                .toUpperCase(), placingCoordinates, shipLength));
        newLine();
    }

}

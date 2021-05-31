package com.battleship.InputOutput;

import com.battleship.Battleship;
import com.battleship.fields.Coordinates;
import com.battleship.fields.ShipPart;
import com.battleship.fields.Square;
import com.battleship.players.Player;
import com.battleship.ships.Ship;
import com.battleship.ships.ShipType;
import com.battleship.util.Util;

import java.util.List;

public class Display {
    public String tab = "\t\t\t\t";
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
    public void printBoards(Square[][] fields1, Square[][] fields2) {

        printLettersLine(true);
        for (int i = 0; i < boardSize; i++) {
            tabulator();
            printNumIndexString(i);
            String contentLine = "";
            for (int j = 0;  j < boardSize; j++) {
                String letter1 = fields1[i][j].getSymbol();
                if (j == boardSize-1) {
                    contentLine += letter1 + rightSideSeparator;
                } else {
                    contentLine += letter1 + insideSeparator;
                }
            }
            contentLine += "\t\t\t";
            contentLine += String.format("%d\t| ", i+1);
            for (int j = 0;  j < boardSize; j++) {
                String letter2 = fields2[i][j].getSymbol();
                if (j == boardSize-1) {
                    contentLine += letter2 + rightSideSeparator;
                } else {
                    contentLine += letter2 + insideSeparator;
                }
            }
            System.out.print(contentLine);
            newLine();
            horizontalLine(true);
        }

    }

    public void horizontalLine() {
        System.out.println("\t"+hr);
    }
    public void horizontalLine(boolean dbl) {
        System.out.println("\t\t"+hr+hr);
    }
    private void tabulator() { System.out.print("\t");}
    private void newLine() { System.out.print("\n");}
    public void nicknameInput(int num) {
        String order = num==1?"first":"second";
        tabulator();
        System.out.print(String.format("Type nickname for %s player: ", order));

    }
    public void nicknameTurn(String nickname, String turnType) {
        System.out.println("");
        System.out.print(tab+tab+String.format("=== %s's ===\n %s %s turn", nickname, tab+tab, turnType));
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
        newLine();
        System.out.println(tab+line);
        System.out.println("\t"+hr);
    }
    private void printLettersLine(boolean forDoubleBoard) {
        String line = "";
        for (int i = 0; i < boardSize; i++) {
            if (i != boardSize-1) {
                line += Util.INSTANCE.getLetterIndexes()[i] + "   ";
            } else {
                line += Util.INSTANCE.getLetterIndexes()[i]+ "  ";
            }
        }
        newLine();
        System.out.println(tab+"\t\t  "+line+tab+"  "+line);
        System.out.println("\t\t"+hr+hr);
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
    public void autoPlacementDecision(String name) {
        tabulator();
        System.out.print(name+" do auto placement? \n\tType anything to place manually, enter to confirm auto: ");
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

    public void shotResultInfo(Square objOnField, Ship sunkShip) {
        System.out.print(tab+tab+tab);
        switch (objOnField.getState()){
            case WATER:
                System.out.print("Missed on : " + objOnField.getPosition());
                break;
            case HIT_PART:
                System.out.print("Shooted ship >>> " + objOnField.getPosition());
                break;
            case MISSED:
                System.out.print("Missed again ...");
                break;
            case SUNK_SHIP:
                System.out.print("Sunk ship +++ " + (sunkShip==null?objOnField.getPosition()+" already sunk.":sunkShip));
                break;
        }
        newLine();
    }
    public void displayPlacingScreen(Player player, ShipType shipType) {
        player.getPlayerBoard().print();
        Battleship.INSTANCE.display.nicknameTurn(player.getName(), "placing phase");
        Battleship.INSTANCE.display.shipTypeInfo(shipType);
    }
    public void displayPlacingScreen(Player player) {
        player.getPlayerBoard().print();
        Battleship.INSTANCE.display.nicknameTurn(player.getName(), "placing phase finished");
        System.out.println(player.getShips());
    }

    public void greetWinner(Player winner) {
        String nameLengthBasedLine = " - ".repeat(winner.getName().length());
        String whiteSpace = " ".repeat((nameLengthBasedLine).length());
        newLine();newLine();newLine();
        tabulator();tabulator();
        System.out.println((nameLengthBasedLine+nameLengthBasedLine+" ========= : ") + "_".repeat(winner.getName().length()) + (" : =========  "+nameLengthBasedLine+nameLengthBasedLine));
        newLine();
        tabulator();
        tabulator();
        System.out.println(whiteSpace+whiteSpace+" WINNER IS :\t" + winner.getName() + "\t: IS WINNER ");
        newLine();
        tabulator();
        tabulator();
        System.out.println((whiteSpace+nameLengthBasedLine+" : ") + whiteSpace + (" : "+nameLengthBasedLine));
    }

    public void playerFleet(Player player) {
        newLine();
        System.out.println(player.getName() + " === V FLEET V");
        System.out.println(player.getShips().toString());
    }

    public void finalScreen(Player player1, Player player2) {
        Battleship.INSTANCE.display.playerFleet(player1);
        Battleship.INSTANCE.display.playerFleet(player2);
        Battleship.INSTANCE.display.printBoards(player1.getPlayerBoard().getFields(), player2.getPlayerBoard().getFields());
    }
    public void newLine(int count) {
        for (int i=0; i<count; i++) {
            System.out.println("");
        }
    }
}

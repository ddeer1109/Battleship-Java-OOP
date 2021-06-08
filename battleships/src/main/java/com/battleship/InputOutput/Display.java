package com.battleship.InputOutput;

import com.battleship.Battleship;
import com.battleship.GameConfiguration;
import com.battleship.fields.Coordinates;
import com.battleship.fields.ShipPart;
import com.battleship.fields.Shot;
import com.battleship.fields.Square;
import com.battleship.players.Player;
import com.battleship.players.PlayerCreator;
import com.battleship.ships.Ship;
import com.battleship.ships.ShipType;
import com.battleship.util.Util;

import java.util.List;

public class Display {
    public String tab = "\t\t\t\t\t\t\t\t";
    String hr;
     String insideSeparator;
     String rightSideSeparator;
     int boardSize = 10;

    public Display() {
    }
    public void setUiComponents(int boardSize) {
        this.boardSize = boardSize;
        hr = tab + "-".repeat(boardSize + 3 * boardSize + 1 );
        insideSeparator = " | ";
        rightSideSeparator = " |";
    }
    public void horizontalLine() {
        System.out.println("\t"+hr);
    }
    public void horizontalLine(boolean dbl) {
        System.out.println("\t\t"+hr+"\t"+hr);
    }
    private void tabulator() { System.out.print("\t");}
    private void newLine() { System.out.print("\n");}
    public void newLine(int count) {
        for (int i=0; i<count; i++) {
            System.out.println();
        }
    }

    public void initWelcome() {
        newLine(5);
        tabulator();
        System.out.print(tab+"--------------------------------------------------------------------------------------------\n\n");
        System.out.print(tab+tab+"WELCOME TO BATTLESHIPS GAME\n\n");
        tabulator();
        System.out.print(tab+"--------------------------------------------------------------------------------------------");
        newLine();
        newLine();
        newLine();
    }

    public void boardConfigMenu() {
        System.out.println("Choose board configuration:\n");
        for (GameConfiguration config : GameConfiguration.values()) {
            System.out.println(String.format("%d. %s config.",config.getBoardSize(), config.name()));
        }
    }
    public void nicknameInput(int num) {
        String order = num==1?"first":"second";
        tabulator();
        System.out.print(String.format("Type nickname for %s player: ", order));
    }
    public void playerChoiceMenu() {
        System.out.println(String.format("Choose player type for player%d :\n", (PlayerCreator.getPlayerCounter() + 1)));
        for (PlayerCreator playerType : PlayerCreator.values()) {
            System.out.println(String.format(
                    "%d. %s player type.",
                    playerType.getPlayerType(),
                    playerType.name()));
        }
    }


    public void autoPlacementDecision(String name) {
        tabulator();
        System.out.print(name+" do auto placement? \n\tType anything to place manually, enter to confirm auto: ");
    }
    public void displayPlacingScreen(Player player, ShipType shipType) {
        player.getPlayerBoard().print();
        Battleship.INSTANCE.display.nicknameTurn(player.getName(), "placing phase");
        Battleship.INSTANCE.display.shipTypeInfo(shipType);
    }
    public void displayPlacingScreen(Player player) {
        System.out.println(player.getShips());
        player.getPlayerBoard().print();
        Battleship.INSTANCE.display.nicknameTurn(player.getName(), "placing phase finished");

    }
    public void playerFleet(Player player, boolean isFirstPlayer) {
        newLine();
        System.out.println(player.getName() + " === V FLEET V");
        for (Ship ship : player.getShips()){
            System.out.println(ship.toString(isFirstPlayer));
        }
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
            contentLine += tab;
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
        System.out.println(tab+"\t\t  "+line+tab+"\t  "+line);
        horizontalLine(true);
    }
    private void printNumIndexString(int index) {
        System.out.print(String.format(tab+"%d\t| ", index+1));
    }


    public void nicknameTurn(String nickname, String turnType) {
        System.out.println();
        System.out.print(tab+String.format("\t  | %s's |  : %s %s", nickname, boardSize==10?tab.substring(0, tab.length()-2):"\t", turnType));
    }
    public void singleCdInfo(Coordinates coord) {
        tabulator();
        System.out.println("Selected coordinates: "+coord);
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


    public void beforeShootingUI(Player player){
        Battleship.INSTANCE.display.newLine(25);
        Battleship.INSTANCE.display.printBoards(player.getPlayerBoard().getFields(), player.getOpponentCopyBoard().getFields());
        Battleship.INSTANCE.display.nicknameTurn(player.getName(), " is choosing target ...\n\n\n");
        Battleship.INSTANCE.input.pressEnterToContinue();
    }
    public void afterShootingUI(Player player, Shot shot) {
        Square shootObject = shot.getObjOnField();
        Battleship.INSTANCE.display.newLine(25);
        Battleship.INSTANCE.display.printBoards(player.getPlayerBoard().getFields(), player.getOpponentCopyBoard().getFields());
        Battleship.INSTANCE.display.nicknameTurn(player.getName(), ("=== has shot to " + shootObject.getPosition() + " ==>"));
        Battleship.INSTANCE.display.shotResultInfo(shootObject, shot.getSunkShip());
        Battleship.INSTANCE.input.pressEnterToContinue();
    }
    public void finishedGameUI(Player winner, Player player1, Player player2){
        Battleship.INSTANCE.display.greetWinner(winner);
        Battleship.INSTANCE.display.finalScreen(player1, player2);
        Battleship.INSTANCE.display.greetWinner(winner);
    }
    public void shotResultInfo(Square objOnField, Ship sunkShip) {
        System.out.print(boardSize==10?tab.substring(0, tab.length()-2):"\t\t");
        switch (objOnField.getState()){
            case WATER:
                System.out.print("...  MISSED   ...   " + "\n\n");
                break;
            case HIT_PART:
                System.out.print("---> HIT ship <--- " + "\n\n");
                break;
            case MISSED:
                System.out.print("... Missed again ...\n\n");
                break;
            case SUNK_SHIP:
                System.out.print("!+++ SUNK ship +++! \n" + (sunkShip==null?objOnField.getPosition()+" already sunk.":sunkShip));
                break;
        }
        newLine();
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


    public void finalScreen(Player player1, Player player2) {
        Battleship.INSTANCE.display.playerFleet(player1, true);
        Battleship.INSTANCE.display.printBoards(player1.getPlayerBoard().getFields(), player1.getOpponentCopyBoard().getFields());
        newLine(2);
        Battleship.INSTANCE.display.playerFleet(player2, true);
        Battleship.INSTANCE.display.printBoards(player2.getPlayerBoard().getFields(), player2.getOpponentCopyBoard().getFields());
    }


}

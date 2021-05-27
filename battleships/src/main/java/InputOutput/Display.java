package InputOutput;

import fields.Coordinates;
import fields.Square;
import util.Util;

public class Display {
    static String hr;
    static String insideSeparator;
    static String rightSideSeparator;
    static int boardSize;

    public static void setUiComponents() {
        boardSize = Util.INSTANCE.getBoardSize();
        hr = "-".repeat(boardSize + 3 * boardSize + 1 );
        insideSeparator = " | ";
        rightSideSeparator = " |";
    }
    public static void printBoard(Square[][] fields) {

        Display.printLettersLine();
        for (int i = 0; i < boardSize; i++) {
            Display.printNumIndexString(i);

            for (int j = 0;  j < boardSize; j++) {
                String letter = fields[i][j].getSymbol();
                if (j == boardSize-1) {
                    System.out.print(letter + rightSideSeparator);
                } else {
                    System.out.print(letter + insideSeparator);
                }
            }
            System.out.println();
            System.out.println("\t"+hr);
        }
    }

    static void printLettersLine() {
        String line = "";
        for (int i = 0; i < boardSize; i++) {
            if (i != boardSize-1) {
                line += Util.INSTANCE.getLetterIndexes()[i] + "   ";
            } else {
                line += Util.INSTANCE.getLetterIndexes()[i] + "  ";
            }
        }
        System.out.println("\n\n\n");
        System.out.println("\t  " + line);
        System.out.println("\t"+hr);
    }
    static void printNumIndexString(int index) {
        System.out.print(String.format("%d\t| ", index+1));
    }
    static void printPlacingInfo(Coordinates coord) {
        System.out.println("Placing on field "+coord);
    }

    public static void incorrectRange(Coordinates start, Coordinates end) {
        System.out.println(String.format("Incorrect coordinates range from %s to %s", start, end));
    }
}

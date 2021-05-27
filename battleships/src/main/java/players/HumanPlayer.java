package players;

import InputOutput.Input;
import fields.Coordinates;
import util.Util;


import java.util.Arrays;
import java.util.Scanner;

public class HumanPlayer extends Player{

    public HumanPlayer(String name) {
        super(name);
    }
    @Override
    public Coordinates getSingleCd() {

            String[] cord = new String[2];
            do {
                Scanner sc = new Scanner(System.in);
                System.out.print(String.format("Type your %s correct coordinates e.g. 'A1': ", "move"));
                String input = sc.nextLine().toUpperCase();
                try {
                    cord[0] = String.valueOf(input.charAt(0));
                    cord[1] = input.substring(1);
                } catch (Exception err) {
                    continue;
                }
                System.out.println(Arrays.toString(cord) + " user input");
            } while (!Input.areCoordinatesInRange(cord));
            int x = Util.INSTANCE.rowsDictionary.get(cord[1]);
            int y = Util.INSTANCE.colsDictionary.get(cord[0]);
            Coordinates coordinates = new Coordinates(x, y);
            return coordinates;
    }

    @Override
    public Coordinates[] getDoubleCd() {
        Coordinates[] doubleCd = new Coordinates[2];
        doubleCd[0] = getSingleCd();
        doubleCd[1] = getSingleCd();
        return doubleCd;
    }

}

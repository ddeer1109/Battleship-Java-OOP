package InputOutput;

import util.Util;

import java.util.Arrays;
import java.util.Scanner;

public class Input {


    public static boolean areCoordinatesInRange(String[] inputData) {
        try {
            boolean colCorrect = Util.INSTANCE.colsDictionary.get(inputData[0]) != null;
            boolean rowCorrect = Util.INSTANCE.rowsDictionary.get(inputData[1]) != null;
            if(colCorrect && rowCorrect)
                return true;
        }
        catch (NullPointerException ex) {
            return false;
        }
        return false;
    }


}

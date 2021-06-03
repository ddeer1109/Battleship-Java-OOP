package com.battleship.util;

import com.battleship.fields.Coordinates;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class Util {
    public static Util INSTANCE = new Util();


    private char[] letterIndexes;
    private int boardSize;

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public Dictionary<String, Integer> rowsDictionary = new Hashtable<String, Integer>();
    public Dictionary<String, Integer> colsDictionary = new Hashtable<String, Integer>();
    private final Random gen = new Random();
    private Util() {
    }

    public void init(int boardSize) {
        this.boardSize = boardSize;
        Coordinates.setValidRange(boardSize);
        setLetterIndexesArray(boardSize);
        createCoordinatesDictionaries();
    }
    public static char[] getAlphabet() {
        return "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
    }



    private void createCoordinatesDictionaries() {
        for (int i = 0; i < letterIndexes.length; i++) {
            String s = String.valueOf(letterIndexes[i]);
            colsDictionary.put(s, i);
        }
        for (int i = 0; i < letterIndexes.length; i++) {
            int rowPosition = i + 1;
            String s = String.valueOf(rowPosition);
            rowsDictionary.put(s, i);
        }
    }

    private void setLetterIndexesArray (int size) {
        char[] alphabet = getAlphabet();
        letterIndexes = new char[size];
        for (int i = 0; i < size; i++) {
            letterIndexes[i] = alphabet[i];
        }
    }

    public char[] getLetterIndexes() {
        return letterIndexes;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getRandomIntInBoardRange() {
        return gen.nextInt(boardSize);
    }

    public int getRandomIntInRange(int range) {
        return gen.nextInt(range);
    }


    public Coordinates[] getRandomPlacingCoordinates(){
        Coordinates [] coordinates = new Coordinates[2];
        int x = Util.INSTANCE.getRandomIntInBoardRange();
        int y = Util.INSTANCE.getRandomIntInBoardRange();
        coordinates[0] = new Coordinates(x,y);
        List<Coordinates> nextTwoFields = coordinates[0].getNextFields();
        coordinates[1] = nextTwoFields.get(
                Util.INSTANCE.getRandomIntInRange(nextTwoFields.size()));
        return coordinates;
    }

}

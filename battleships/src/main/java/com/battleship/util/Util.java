package com.battleship.util;

import java.util.Dictionary;
import java.util.Hashtable;

public class Util {
    public static Util INSTANCE = new Util();


    private char[] letterIndexes;
    private int boardSize;
    public Dictionary<String, Integer> rowsDictionary = new Hashtable<String, Integer>();
    public Dictionary<String, Integer> colsDictionary = new Hashtable<String, Integer>();
    private Util() {
    }

    public void init(int boardSize) {
        this.boardSize = boardSize;
        setLetterIndexesArray(boardSize);
        createCoordinatesDictionaries();

    }
    public static char[] getAlphabet() {
        return "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
    }



    void createCoordinatesDictionaries() {
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

    void setLetterIndexesArray (int size) {
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

}

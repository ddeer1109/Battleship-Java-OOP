package com.battleship.boards;

import com.battleship.util.Util;

public class BoardFactory {
    public static Board createNewBoard() {
        return new Board(Util.INSTANCE.getBoardSize());
    }
}

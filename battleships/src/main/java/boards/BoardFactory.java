package boards;

import util.Util;

public class BoardFactory {
    public static Board createNewBoard() {
        return new Board(Util.INSTANCE.getBoardSize());
    }
}

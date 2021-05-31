package com.battleship.players;

public enum PlayerCreator {
    HUMAN(0),
    AI_EASY(1),
    AI_MEDIUM(2),
    AI_HARD(3),
    ;
    private static int playerCounter = 0;
    private int playerType;
    private Player player;

    PlayerCreator(int type) {
        playerType = type;
    }

    public void setPlayer() {
        playerCounter++;
//        String nickname = Battleship.INSTANCE.input.playerNickname(playerCounter);
        String nickname = "Player" + playerCounter;
        switch (playerType) {
            case 0:
                player = new HumanPlayer(nickname);
                break;
            case 1:
                player = new ComputerPlayerEasy(nickname);
                break;
            case 2:
                player = new ComputerPlayerMedium(nickname);
                break;
            case 3:
                player = new ComputerPlayerHard(nickname);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + playerType);
        }
    }

    public Player getPlayer() {
        setPlayer();
        return player;
    }

}

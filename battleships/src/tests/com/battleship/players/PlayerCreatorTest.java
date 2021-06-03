package com.battleship.players;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerCreatorTest {

    @Test
    public void retrieveNewPlayerObject(){
        assertTrue(
                PlayerCreator.AI_EASY
                        .retrieveNewPlayerObject() instanceof ComputerPlayerEasy &&
                PlayerCreator.AI_MEDIUM
                        .retrieveNewPlayerObject() instanceof ComputerPlayerMedium &&
                PlayerCreator.AI_HARD
                        .retrieveNewPlayerObject() instanceof ComputerPlayerHard &&
                PlayerCreator.HUMAN
                        .retrieveNewPlayerObject() instanceof HumanPlayer
        );
    }

}
package com.battleship.fields;

import com.battleship.players.Player;
import com.battleship.ships.Ship;

import java.util.concurrent.TimeUnit;

public class Shot extends Coordinates{
    public FieldState getFieldStateAfterShot() {
        return fieldStateAfterShot;
    }

    public Square getObjOnField() {
        return objOnField;
    }

    public Ship getSunkShip() {
        return sunkShip;
    }

    private final Player attackingPlayer;
    private final Player attackedPlayer;

    private Square objOnField;
    private FieldState fieldStateAfterShot;


    private Ship sunkShip;

    public Shot(Coordinates coords, Player attacker, Player attacked) {
        super(coords.getX(), coords.getY());
        attackingPlayer = attacker;
        attackedPlayer = attacked;
    }

    public void executeShot(){
        try {
            objOnField = attackedPlayer.getPlayerBoard().getObjectOnField(this);
            switch (objOnField.getState())
            {
                case WATER:
                    serviceMissHit();
                    TimeUnit.MILLISECONDS.sleep(10);
                    break;
                case SHIP:
                    serviceHittingShip();
                    serviceSinkingValidation((ShipPart)objOnField);
                    break;
                case SUNK_SHIP:
                    fieldStateAfterShot = FieldState.SUNK_SHIP;
                    attackingPlayer.markShotResult(this, fieldStateAfterShot);
                    break;
                case MISSED:
                    System.out.println("Missed again");
                    break;
            }

        }catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    public void serviceMissHit() {
        fieldStateAfterShot = FieldState.MISSED;
        attackingPlayer.markShotResult(this, fieldStateAfterShot);
    }
    public void serviceHittingShip() {
        objOnField.setState(FieldState.HIT_PART);
        fieldStateAfterShot = FieldState.HIT_PART;
        attackingPlayer.markShotResult(this, fieldStateAfterShot);
    }
    public void serviceSinkingValidation(ShipPart hitPart) {
        Ship hitShip = attackedPlayer.getShipByPart(hitPart);
        hitShip.isSunk();

        if (!hitShip.isAlive()) {
            attackingPlayer.markSunkShipParts(hitShip.getParts());
            fieldStateAfterShot = FieldState.SUNK_SHIP;
            sunkShip = hitShip;
        }
    }
}

package com.battleship.fields;

import com.battleship.players.Player;
import com.battleship.ships.Ship;

import java.util.concurrent.TimeUnit;

public class Shot extends Coordinates{
    public FieldState getFieldState() {
        return fieldState;
    }

    public Square getObjOnField() {
        return objOnField;
    }

    public Ship getSunkShip() {
        return sunkShip;
    }

    private Player attackingPlayer;
    private Player attackedPlayer;
    private Square objOnField;

    private FieldState fieldState;


    private Ship sunkShip;

    public Shot(Coordinates coords, Player attacker, Player attacked) {
        super(coords.getX(), coords.getY());
        attackingPlayer = attacker;
        attackedPlayer = attacked;
    }

    public void executeShot(){
        try {
            objOnField = attackedPlayer.getPlayerBoard().getObjectOnField(this);
            switch (objOnField.getState()){
                case WATER:
                    serviceMissHit();
                    TimeUnit.MILLISECONDS.sleep(10);
                    break;
                case SHIP:
                    serviceHittingShip();
                    serviceSinkingValidation((ShipPart)objOnField);
                    break;
                case SUNK_SHIP:
                    fieldState = fieldState.SUNK_SHIP;
                    attackingPlayer.markShotResult(this, fieldState);
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
        fieldState = FieldState.MISSED;
        attackingPlayer.markShotResult(this, fieldState);
    }
    public void serviceHittingShip() {
        objOnField.setState(FieldState.HIT_PART);
        fieldState = FieldState.HIT_PART;
        attackingPlayer.markShotResult(this, fieldState);
    }
    public void serviceSinkingValidation(ShipPart hitPart) {
        Ship hitShip = attackedPlayer.getShipByPart(hitPart);
        hitShip.isSunk();
        if (!hitShip.isAlive()) {
            attackingPlayer.markSunkShipParts(hitShip.getParts());
            fieldState = FieldState.SUNK_SHIP;
            sunkShip = hitShip;
        }
    }
}

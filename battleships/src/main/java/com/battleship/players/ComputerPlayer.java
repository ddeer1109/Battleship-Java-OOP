package com.battleship.players;

import com.battleship.fields.Coordinates;
import com.battleship.fields.FieldState;
import com.battleship.fields.ShipPart;
import com.battleship.util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ComputerPlayer extends Player{


    public ComputerPlayer(String name) {
        super(name+"AI");
    }

    @Override
    public String toString() {
        return "ComputerPlayer{" +
                "hitShipsAlive=" + hitShipsAlive +
                ", fieldsToIgnore=" + fieldsToIgnore +
                '}';
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
    @Override
    public boolean isAlive() {
        return super.isAlive();
    }

    @Override
    public void markShotResult(Coordinates coords, FieldState newState) {
        super.markShotResult(coords, newState);
        if (newState == FieldState.HIT_PART) {
            hitShipsAlive.add(coords);
            System.out.println("HITTED : " + hitShipsAlive);
        } else {
            fieldsToIgnore.add(coords);
            System.out.println("to ignore " + fieldsToIgnore);
        }
    }

    @Override
    public void markSunkShipParts(List<ShipPart> sunkShipParts) {
        super.markSunkShipParts(sunkShipParts);
        HashSet<Coordinates> newFieldsToIgnore = new HashSet<>();
        for (ShipPart part : sunkShipParts) {
            newFieldsToIgnore.addAll(part.getPosition().getNextFields());
            newFieldsToIgnore.add(part.getPosition());
            hitShipsAlive.remove(part.getPosition());
        }
        fieldsToIgnore.addAll(newFieldsToIgnore);
        fieldsToIgnore = new HashSet<>(fieldsToIgnore);
        System.out.println("IGNORED FIELDS ====> "+fieldsToIgnore);
    }

    // "Easy" AI && base for rest ---
    // ---------------
    protected Coordinates getRandomCdExcludeIgnoredFields() {
        Coordinates coord;
        do {
            coord = getRandomCdInBoardRange();
        } while (fieldsToIgnore.contains(coord));
        return coord;
    }
    protected Coordinates getRandomCdInBoardRange(){
        return new Coordinates(
                Util.INSTANCE.getRandomIntInBoardRange(),
                Util.INSTANCE.getRandomIntInBoardRange()
        );
    }

    // "Medium" AI ---
    // ---------------
    protected Coordinates getNextRandomCdNextToHitPart(){
        boolean isShotNotPossible = true;
        Coordinates shotCoords = null;
        int index = 0;

        while (isShotNotPossible) {
            List<Coordinates> notIgnoredFields = getPossibleShotsInAllDirections(index);

            if (notIgnoredFields.size() == 0) {
                index++; continue;
            }
            int randomIndex = Util.INSTANCE.getRandomIntInRange(notIgnoredFields.size());
            shotCoords = notIgnoredFields.get(randomIndex);
            isShotNotPossible = fieldsToIgnore.contains(shotCoords);
            fieldsToIgnore.add(shotCoords);
        }
        return shotCoords;
    }
    private List<Coordinates> getPossibleShotsInAllDirections(int hitPartIndex) {
        List<Coordinates> nextToFields = hitShipsAlive.get(hitPartIndex).getNextFields();
        return filterIgnoredFields(nextToFields);
    }

    // "Hard" AI ---
    // ---------------
    protected Coordinates getRandomCdOfTwoHitsDirection(){
        List<Coordinates> sameDirected = new ArrayList<Coordinates>();
        List<Coordinates> fieldsNextToInDirection = new ArrayList<Coordinates>();

        for (int i=0; i < hitShipsAlive.size(); i++) {
            sameDirected.addAll(getCoordinatesWithSameOrientation(i));
        }
        String direction = sameDirected.get(0)
                .isXDirected(sameDirected.get(1)) ? "X" : "Y";

        for (Coordinates hitCd : sameDirected) {
            fieldsNextToInDirection.addAll(
                    getNextToFieldsInDirection(hitCd, direction));
        }
        sameDirected = filterIgnoredFields(fieldsNextToInDirection);
        return sameDirected.get(Util.INSTANCE.getRandomIntInRange(sameDirected.size()));
    }
    private List<Coordinates> getCoordinatesWithSameOrientation(int hitPartIndex) {
        List<Coordinates> sameDirection = new ArrayList<Coordinates>();
        Coordinates checked  = hitShipsAlive.get(hitPartIndex);
        sameDirection = hitShipsAlive.stream()
                .filter(coordinates -> checked.isInLineWith(coordinates))
                .collect(Collectors.toList());
        return sameDirection;
    }
    private List<Coordinates> getNextToFieldsInDirection(Coordinates checkedCoord, String orientation) {
        List<Coordinates> nextToFields = new ArrayList<Coordinates>();
        checkedCoord.setNextToCoordinates();
        if (orientation == "Y") {
            nextToFields.addAll(checkedCoord.getNextTopBot());
        } else {
            nextToFields.addAll(checkedCoord.getNextLeftRight());
        }
        return nextToFields;
    }

}

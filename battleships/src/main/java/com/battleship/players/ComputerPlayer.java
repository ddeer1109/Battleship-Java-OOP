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
        super(name+"_AI");
    }

    @Override
    public String toString() {
        return "ComputerPlayer{" +
                "hitShipsAlive=" + hitShipsAlive +
                ", fieldsToIgnore=" + fieldsToIgnore +
                '}';
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
//            System.out.println("HITTED : " + hitShipsAlive);
        } else {
            fieldsToIgnore.add(coords);
//            System.out.println("to ignore " + fieldsToIgnore);
        }
    }

    @Override
    public void markSunkShipParts(List<ShipPart> sunkShipParts) {
        super.markSunkShipParts(sunkShipParts);

        HashSet<Coordinates> newFieldsToIgnore = new HashSet<>();

        for (ShipPart part : sunkShipParts) {
            newFieldsToIgnore.addAll(part.getPosition()
                                                        .getNextFields());
            newFieldsToIgnore.add(part.getPosition());
            hitShipsAlive.remove(part.getPosition());
        }
        fieldsToIgnore.addAll(newFieldsToIgnore);
        fieldsToIgnore = new HashSet<>(fieldsToIgnore);
//        System.out.println("IGNORED FIELDS ====> "+fieldsToIgnore);
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
        // Get random coordinates next to hit field
        boolean isShotNotPossible = true;
        Coordinates shotCoords = null;
        int hitFieldIndex = 0;

        while (isShotNotPossible) {
            List<Coordinates> uncheckedValidCoords = getUncheckedCoordinatesNextToHitField(hitFieldIndex);
            if (uncheckedValidCoords.size() == 0) {
                hitFieldIndex++;
                continue;
            }
            int randomIndex = Util.INSTANCE.getRandomIntInRange(uncheckedValidCoords.size());
            shotCoords = uncheckedValidCoords.get(randomIndex);
            isShotNotPossible = fieldsToIgnore.contains(shotCoords);
            fieldsToIgnore.add(shotCoords);
        }
        return shotCoords;
    }
    private List<Coordinates> getUncheckedCoordinatesNextToHitField(int hitPartIndex) {
        List<Coordinates> nextToFields = hitShipsAlive.get(hitPartIndex).getNextFields();
        return filterIgnoredFields(nextToFields);
    }

    // "Hard" AI ---
    // ---------------
    protected Coordinates getRandomCdOfTwoHitsDirection(){

        List<Coordinates> sameDirected = getCoordinatesWithSameOrientation();
        List<Coordinates> fieldsNextToInDirection = new ArrayList<Coordinates>();

        String direction = sameDirected.get(0)
                .isXDirected(sameDirected.get(1)) ? "X" : "Y";

        // Get coordinates next to init coordinates list in proper direction
        for (Coordinates hitCd : sameDirected) {
            fieldsNextToInDirection.addAll(
                    getNextToFieldsInDirection(hitCd, direction));
        }
        // Filter out coordinates which were yet checked
        sameDirected = filterIgnoredFields(fieldsNextToInDirection);

        // Returns random one
        return sameDirected.get(Util.INSTANCE.getRandomIntInRange(sameDirected.size()));
    }
    private List<Coordinates> getCoordinatesWithSameOrientation() {
        // get all coordinates which have one of coordinate equal to other hit ship part
        List<Coordinates> sameDirectionCoordinates = new ArrayList<Coordinates>();
        for (int index=0; index < hitShipsAlive.size(); index++) {
            Coordinates checked  = hitShipsAlive.get(index);
            sameDirectionCoordinates.addAll(
                    hitShipsAlive
                            .stream()
                            .filter(coordinates -> checked.isInLineWith(coordinates))
                            .collect(Collectors.toList())
            );
        }
        return sameDirectionCoordinates;
    }
    private List<Coordinates> getNextToFieldsInDirection(Coordinates checkedCoord, String orientation) {
        List<Coordinates> nextToFields = new ArrayList<Coordinates>();
        if (orientation == "Y") {
            nextToFields.addAll(checkedCoord.getNextTopBot());
        } else {
            nextToFields.addAll(checkedCoord.getNextLeftRight());
        }
        return nextToFields;
    }

}

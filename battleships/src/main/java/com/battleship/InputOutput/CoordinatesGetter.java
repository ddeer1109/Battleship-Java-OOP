package com.battleship.InputOutput;

import com.battleship.fields.Coordinates;

public interface CoordinatesGetter {
    Coordinates getSingleCd();
    Coordinates[] getDoubleCd();
}

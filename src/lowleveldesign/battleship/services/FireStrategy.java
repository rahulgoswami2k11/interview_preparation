package lowleveldesign.battleship.services;

import lowleveldesign.battleship.model.Coordinate;

public interface FireStrategy {
    Coordinate fireMissile(int xLower, int xUpper, int yLower, int yUpper);
}

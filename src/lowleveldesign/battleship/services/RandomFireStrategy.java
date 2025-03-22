package lowleveldesign.battleship.services;

import lowleveldesign.battleship.model.Coordinate;

import java.util.Random;

public class RandomFireStrategy implements FireStrategy {


    @Override
    public Coordinate fireMissile(int xLower, int xUpper, int yLower, int yUpper) {

        Random random = new Random();
        return new Coordinate(xLower + random.nextInt(xUpper-xLower), yLower + random.nextInt(yUpper-yLower));

    }
}

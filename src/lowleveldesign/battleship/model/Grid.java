package lowleveldesign.battleship.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class Grid {
    private Coordinate lowerLeft;
    private Coordinate upperLeft;
    private Coordinate lowerRight;
    private Coordinate upperRight;
    private Ship ship;
    private Player player;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grid grid)) return false;
        return Objects.equals(getLowerLeft(), grid.getLowerLeft()) && Objects.equals(getUpperLeft(), grid.getUpperLeft()) && Objects.equals(getLowerRight(), grid.getLowerRight()) && Objects.equals(getUpperRight(), grid.getUpperRight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLowerLeft(), getUpperLeft(), getLowerRight(), getUpperRight());
    }
}

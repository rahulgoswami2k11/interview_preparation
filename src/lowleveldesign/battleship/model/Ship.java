package lowleveldesign.battleship.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class Ship {
    String id;
    Player player;
    boolean isDestroyed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ship ship)) return false;
        return Objects.equals(getId(), ship.getId()) && Objects.equals(getPlayer(), ship.getPlayer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPlayer());
    }
}

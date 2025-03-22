package lowleveldesign.battleship.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {
    private String id;

    public Player(String id) {
        this.id = id;
    }
}

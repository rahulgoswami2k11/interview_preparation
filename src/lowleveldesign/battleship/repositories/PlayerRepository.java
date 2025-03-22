package lowleveldesign.battleship.repositories;

import lowleveldesign.battleship.model.Player;

public interface PlayerRepository {
    Player createPlayer(Player player);
    Player getPlayerById(String id);
}

package lowleveldesign.battleship.services;

import lowleveldesign.battleship.model.Player;

public interface PlayerService {
    Player createPlayer(String id);

    Player getPlayerById(String id);
}

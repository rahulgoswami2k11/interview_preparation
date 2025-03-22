package lowleveldesign.battleship.repositories;

import lowleveldesign.battleship.model.Player;

import java.util.HashMap;
import java.util.Map;

public class InMemoryPlayerRepository implements PlayerRepository {

    Map<String, Player> playerMap;

    public InMemoryPlayerRepository() {
        this.playerMap = new HashMap<>();
    }

    @Override
    public Player createPlayer(Player player) {
        playerMap.put(player.getId(), player);
        return player;
    }

    @Override
    public Player getPlayerById(String id) {
        return playerMap.get(id);
    }
}

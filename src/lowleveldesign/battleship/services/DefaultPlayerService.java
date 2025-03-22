package lowleveldesign.battleship.services;

import lowleveldesign.battleship.model.Player;
import lowleveldesign.battleship.repositories.PlayerRepository;

public class DefaultPlayerService implements PlayerService {
    private PlayerRepository playerRepository;

    public DefaultPlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player createPlayer(String id) {
        Player player = new Player(id);
        return playerRepository.createPlayer(player);
    }

    @Override
    public Player getPlayerById(String id) {
        return playerRepository.getPlayerById(id);
    }
}

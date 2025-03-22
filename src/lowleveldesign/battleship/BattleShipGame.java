package lowleveldesign.battleship;

import lowleveldesign.battleship.model.Player;
import lowleveldesign.battleship.repositories.InMemoryGameRepository;
import lowleveldesign.battleship.repositories.InMemoryPlayerRepository;
import lowleveldesign.battleship.services.*;

public class BattleShipGame {
    public static void main(String[] args) {
        PlayerService playerService = new DefaultPlayerService(new InMemoryPlayerRepository());
        GameService gameService = new DefaultGameService(
                new InMemoryGameRepository(),
                new RandomFireStrategy()
        );

        Player player1 = playerService.createPlayer("Player#A");
        Player player2 = playerService.createPlayer("Player#B");
        gameService.initGame(6, player1, player2);

        //addShip(“SH1”, size = 2, 1, 5, 4, 4)
        gameService.addShip("SH1", 2, 1, 5, 4, 4,
                player1, player2);

        gameService.viewBattleShip();

        gameService.startGame(6, player1, player2);

        gameService.viewBattleShip();
    }
}

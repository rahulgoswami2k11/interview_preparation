package lowleveldesign.battleship.services;

import lowleveldesign.battleship.model.Grid;
import lowleveldesign.battleship.model.Player;

public interface GameService {

    Grid[][] initGame(int N, Player player1, Player player2);

    Grid[][] addShip(String id, int size, int playerAX, int playerAY,
                     int playerBX, int playerBY, Player player1, Player player2);

    void startGame(int N, Player player1, Player player2);

    void viewBattleShip();
}

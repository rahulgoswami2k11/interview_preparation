package lowleveldesign.battleship.repositories;

import lowleveldesign.battleship.model.Coordinate;
import lowleveldesign.battleship.model.Grid;
import lowleveldesign.battleship.model.Player;
import lowleveldesign.battleship.model.Ship;

import java.util.List;
import java.util.Set;

public interface GameRepository {
    Grid[][] initializeBoard(int N, Grid[][] gameBoard, Player player1, Player player2);

    Grid[][] addShip(Ship ship1, List<Grid> ship1Grids, Player player1,
                              Ship ship2, List<Grid> ship2Grids, Player player2);

    Grid getGridByCoordinate(Coordinate coordinate);

    Ship getShipByCoordinate(Coordinate coordinate);

    void removeShip(Ship ship);

    Set<Ship> getShipsOfPlayer(Player player);

    Grid[][] getGameBoard();
}

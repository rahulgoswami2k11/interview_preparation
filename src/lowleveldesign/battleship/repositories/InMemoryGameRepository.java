package lowleveldesign.battleship.repositories;

import lowleveldesign.battleship.model.Coordinate;
import lowleveldesign.battleship.model.Grid;
import lowleveldesign.battleship.model.Player;
import lowleveldesign.battleship.model.Ship;

import java.util.*;

public class InMemoryGameRepository implements GameRepository {
    int N;
    private Player player1;
    private Player player2;
    private Grid[][] gameBoard;
    private Map<Ship, List<Grid>> shipGridMapping;
    private Map<Player, Set<Ship>> playerShipsMapping;

    public InMemoryGameRepository() {
        shipGridMapping = new HashMap<>();
        playerShipsMapping = new HashMap<>();
    }

    @Override
    public Grid[][] initializeBoard(int N, Grid[][] gameBoard, Player player1, Player player2) {
        this.N = N;
        this.player1 = player1;
        this.player2 = player2;
        this.gameBoard = gameBoard;
        return gameBoard;
    }

    public Grid[][] addShip(Ship ship1, List<Grid> ship1Grids, Player player1,
                            Ship ship2, List<Grid> ship2Grids, Player player2) {
        shipGridMapping.put(ship1, ship1Grids);
        shipGridMapping.put(ship2, ship2Grids);
        Set<Ship> player1Ships = playerShipsMapping.getOrDefault(player1, new HashSet<>());
        player1Ships.add(ship1);
        playerShipsMapping.put(player1, player1Ships);


        Set<Ship> player2Ships = playerShipsMapping.getOrDefault(player2, new HashSet<>());
        player2Ships.add(ship2);
        playerShipsMapping.put(player2, player2Ships);
        return gameBoard;
    }

    @Override
    public Grid getGridByCoordinate(Coordinate coordinate) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                Grid grid = gameBoard[i][j];

                if(grid.getLowerLeft().equals(coordinate)) {
                    return grid;
                }
            }
        }

        return null;
    }


    public Ship getShipByCoordinate(Coordinate coordinate) {
        for(Map.Entry<Ship, List<Grid>> entry: shipGridMapping.entrySet()) {
            for(Grid grid: entry.getValue()) {
                if(grid.getLowerLeft().equals(coordinate) ||
                        grid.getUpperLeft().equals(coordinate) ||
                        grid.getLowerRight().equals(coordinate) ||
                        grid.getUpperRight().equals(coordinate)
                 ) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    public void removeShip(Ship ship) {
        ship.setDestroyed(true);
        Player player = ship.getPlayer();
        Set<Ship> ships = playerShipsMapping.get(player);
        ships.remove(ship);

    }

    public Set<Ship> getShipsOfPlayer(Player player) {
        return playerShipsMapping.get(player);
    }

    @Override
    public Grid[][] getGameBoard() {
       return gameBoard;
    }
}

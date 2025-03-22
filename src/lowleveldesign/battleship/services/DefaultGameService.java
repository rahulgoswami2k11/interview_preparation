package lowleveldesign.battleship.services;

import lowleveldesign.battleship.model.Coordinate;
import lowleveldesign.battleship.model.Grid;
import lowleveldesign.battleship.model.Player;
import lowleveldesign.battleship.model.Ship;
import lowleveldesign.battleship.repositories.GameRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DefaultGameService implements GameService {

    FireStrategy fireStrategy;

    private GameRepository gameRepository;

    public DefaultGameService(GameRepository gameRepository,
                              FireStrategy fireStrategy) {
        this.gameRepository = gameRepository;
        this.fireStrategy = fireStrategy;
    }

    @Override
    public Grid[][] initGame(int N, Player player1, Player player2) {
        Grid[][] gameBoard = new Grid[N][N];

        for(int i=0; i<N/2; i++) {
            for(int j=0; j<N; j++) {
                Coordinate lowerLeft = new Coordinate(i, j);
                Coordinate upperLeft = new Coordinate(i, j+1);
                Coordinate lowerRight = new Coordinate(i+1, j);
                Coordinate upperRight = new Coordinate(i+1, j+1);
                gameBoard[i][j] = new Grid(lowerLeft, upperLeft, lowerRight, upperRight, null, player1);
            }
        }

        for(int i=N/2; i<N; i++) {
            for(int j=0; j<N; j++) {
                Coordinate lowerLeft = new Coordinate(i, j);
                Coordinate upperLeft = new Coordinate(i, j+1);
                Coordinate lowerRight = new Coordinate(i+1, j);
                Coordinate upperRight = new Coordinate(i+1, j+1);
                gameBoard[i][j] = new Grid(lowerLeft, upperLeft, lowerRight, upperRight, null, player2);
            }
        }

        return gameRepository.initializeBoard(N, gameBoard, player1, player2);
    }

    @Override
    public Grid[][] addShip(String id, int size, int playerAX, int playerAY, int playerBX, int playerBY,
                            Player player1, Player player2) {

        Ship player1Ship = new Ship(id + "P1", player1, false);
        Ship player2Ship = new Ship(id + "P2", player2, false);

        int startX = playerAX - (size / 2);
        int endX = playerAX + (size / 2);
        int startY = playerAY - (size / 2);
        int endY = playerAY + (size / 2);

        List<Grid> ship1Grids = new ArrayList<>();
        List<Grid> ship2Grids = new ArrayList<>();

        for(int i=startX; i<endX; i++) {
            for(int j=startY; j<endY; j++) {
                Grid grid = gameRepository.getGridByCoordinate(new Coordinate(i, j));
                grid.setShip(player1Ship);
                ship1Grids.add(grid);
            }
        }

        startX = playerBX - (size / 2);
        endX = playerBX + (size / 2);
        startY = playerBY - (size / 2);
        endY = playerBY + (size / 2);

        for(int i=startX; i<endX; i++) {
            for(int j=startY; j<endY; j++) {
                Grid grid = gameRepository.getGridByCoordinate(new Coordinate(i, j));
                grid.setShip(player2Ship);
                ship2Grids.add(grid);
            }
        }

        return gameRepository.addShip(player1Ship, ship1Grids, player1, player2Ship, ship2Grids, player2);
    }

    @Override
    public void startGame(int N, Player player1, Player player2) {
        boolean player1Turn = true;

        while(!isGameEnd(player1Turn ? player1 : player2 )) {
            Coordinate coordinate;
            if(player1Turn) {
                coordinate = fireStrategy.fireMissile(N/2, N, 0, N);
                Ship ship = gameRepository.getShipByCoordinate(coordinate);
                if(ship != null) {
                    System.out.println("PlayerA’s turn: Missile fired at " + coordinate + " : Hit" +
                            "PlayerB’s ship with id " + ship.getId());

                    gameRepository.removeShip(ship);
                } else {
                    System.out.println("PlayerA’s turn: Missile fired at " + coordinate +  " : Miss");
                }
            } else {
                coordinate = fireStrategy.fireMissile(0, N/2, 0, N);
                Ship ship = gameRepository.getShipByCoordinate(coordinate);
                if(ship != null) {
                    System.out.println("PlayerB’s turn: Missile fired at " + coordinate + " : Hit" +
                            "PlayerA’s ship with id " + ship.getId());
                    gameRepository.removeShip(ship);
                } else {
                    System.out.println("PlayerB’s turn: Missile fired at " + coordinate +  " : Miss");
                }
            }

            player1Turn = !player1Turn;
        }
    }

    @Override
    public void viewBattleShip() {
        Grid[][] gameBoard = gameRepository.getGameBoard();

        for(int i=0; i<gameBoard.length; i++) {
            for(int j=0; j<gameBoard[0].length; j++) {
                Grid grid = gameRepository.getGridByCoordinate(new Coordinate(i, j));
                System.out.println(grid);
            }
        }
    }


    private boolean isGameEnd(Player player) {
        Set<Ship> playerShips = gameRepository.getShipsOfPlayer(player);
        if(playerShips == null || playerShips.isEmpty()) {
            System.out.println("Game Ended!, " + player +" lost");
            return true;
        }
        return false;
    }
}

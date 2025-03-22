package lowleveldesign.chessgame;

/* *
*
* 8 * 8 board
* Piece
*   1. King (Raja) => Can move in all the 8 directions, one square at a time : 1 count
*   2. Queen (Wazir) => Can move in 8 directions, as far as it wishes : 1 count
*   3. Rook (Elephant) => Can move in 4 directions, as far as it wishes : 2 count
*   4. Bishop (Camel) => Can move in diagonal directions, as far as it wishes : 2 count
*   5. Knight (Horse) => Can move 2 square straight and one with changed direction : 2 count
*   6. Pawn (Sipahi) => Can move one square forward, but 2 square only at first time : 8 count
*
* */


import lombok.Data;
import lowleveldesign.chessgame.exception.InvalidMoveException;
import lowleveldesign.chessgame.piece.Piece;

import java.util.Scanner;

@Data
public class ChessGame {
    private Board board;
    private Player[] players;
    private int currentPlayer;

    public ChessGame() {
        board = new Board();
        players = new Player[]{new Player(Color.WHITE), new Player(Color.BLACK)};
        currentPlayer = 0;
    }

    public void start() {
        while(!isGameOver()) {
            Player player = players[currentPlayer];
            Move move = getPlayerMove(player);

            try {
                makeMove(player, move);
            } catch (InvalidMoveException e) {
                System.out.println("Invalid move");
                continue;
            }

            currentPlayer = (currentPlayer + 1) % 2;
        }

        displayResult();
    }

    private void makeMove(Player player, Move move) {
        Piece piece = move.getPiece();
        int destRow = move.getDestRow();
        int destCol = move.getDestCol();

        if(board.isValidMove(piece, destRow, destCol)) {
            int sourceRow = piece.getRow();
            int sourceCol = piece.getCol();
            board.setPiece(sourceRow, sourceCol, null);
            board.setPiece(destRow, destCol, piece);
            piece.setRow(destRow);
            piece.setCol(destCol);
        } else {
            throw new InvalidMoveException("Invalid move");
        }
    }

    private Move getPlayerMove(Player player) {
        Scanner scanner = new Scanner(System.in);
        int sourceRow = scanner.nextInt();
        int sourceCol = scanner.nextInt();

        int destRow = scanner.nextInt();
        int destCol = scanner.nextInt();

        Piece piece = board.getPiece(sourceRow, sourceCol);
        if(piece == null || piece.getColor() != player.getColor()) {
            throw new RuntimeException("Invalid move");
        }

        return new Move(piece, destRow, destCol);
    }

    private boolean isGameOver() {
        return board.isCheckMate(Color.WHITE) ||
                board.isCheckMate(Color.BLACK);
    }

    private void displayResult() {

    }
}

package lowleveldesign.chessgame.piece;

import lowleveldesign.chessgame.Color;

public class Queen extends Piece {
    public Queen(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean canMove(int destRow, int destCol) {
        int rowDiff = Math.abs(destRow - getRow());
        int colDiff = Math.abs(destCol - getCol());
        return ((rowDiff == colDiff) || (getRow() == destRow) || (getCol() == destCol));
    }
}

package lowleveldesign.chessgame.piece;

import lowleveldesign.chessgame.Color;

public class King extends Piece {
    public King(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean canMove(int destRow, int destCol) {
        int rowDiff = Math.abs(destRow - getRow());
        int colDiff = Math.abs(destCol - getCol());

        return rowDiff < 1 && colDiff < 1;
    }
}

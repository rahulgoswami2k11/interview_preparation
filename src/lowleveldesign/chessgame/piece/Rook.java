package lowleveldesign.chessgame.piece;

import lowleveldesign.chessgame.Color;

public class Rook extends Piece {
    public Rook(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean canMove(int destRow, int destCol) {
        return (getRow() == destRow) || (getCol() == destCol);
    }
}

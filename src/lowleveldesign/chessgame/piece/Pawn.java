package lowleveldesign.chessgame.piece;

import lowleveldesign.chessgame.Color;

public class Pawn extends Piece {
    public Pawn(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean canMove(int destRow, int destCol) {
        return false;
    }
}

package lowleveldesign.chessgame.piece;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lowleveldesign.chessgame.Color;

@Getter
@Setter
@AllArgsConstructor
public abstract class Piece {
    private Color color;
    private int row;
    private int col;

    public abstract boolean canMove(int destRow, int destCol);
}

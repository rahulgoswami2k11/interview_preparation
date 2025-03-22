package lowleveldesign.chessgame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lowleveldesign.chessgame.piece.Piece;

@Data
@AllArgsConstructor
public class Move {
    private final Piece piece;
    private final int destRow;
    private final int destCol;
}

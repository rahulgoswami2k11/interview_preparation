package lowleveldesign.chessgame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lowleveldesign.chessgame.piece.Piece;

@Data
@AllArgsConstructor
public class Player {
    Color color;
}

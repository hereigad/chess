package game;

import chess.Piece;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private boolean side;
    private List<Piece> captured;

    public Player(boolean side) {
        this.side = side;
        captured = new ArrayList<>();
    }

    public boolean getSide() {
        return side;
    }

    public List<Piece> getCaptured() {
        return captured;
    }
}

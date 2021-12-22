package chess;

public class Rook extends Piece {
    public Rook(int[] position) {
        super(position);
        this.name = "Rook";
    }

    @Override
    public boolean validateMovement(int[] movement, Piece capturable) {
        return movement[0] == position[0] || movement[1] == position[1];
    }
}

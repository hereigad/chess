package chess;

public class Pawn extends Piece {
    public Pawn(int[] position, boolean color) {
        super(position, color);
        this.name = "p";
    }

    @Override
    public boolean validateMovement(int[] movement, Piece capturable) {
        return (movement[0] == position[0] && movement[1] == position[1] + 1 && capturable == null)
                || ((movement[0] == position[0] + 1 || movement[0] == position[0] - 1) && movement[1] == position[1] + 1 && capturable != null);
    }
}

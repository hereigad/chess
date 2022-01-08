package chess;

public class Knight extends Piece {
    public Knight(int[] position, boolean color) {
        super(position, color);
        this.name = "n";
    }

    @Override
    public boolean validateMovement(int[] movement, Piece capturable) {
        return ((movement[0] == position[0] + 2 || movement[0] == position[0] - 2) && (movement[1] == position[1] + 1 || movement[1] == position[1] - 1)
                || (movement[0] == position[0] + 1 || movement[0] == position[0] - 1) && (movement[1] == position[1] + 2 || movement[1] == position[1] - 2));
    }
}

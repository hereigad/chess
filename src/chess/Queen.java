package chess;

public class Queen extends Piece {

    public Queen(int[] position) {
        super(position);
        this.name = "Queen";
    }

    @Override
    public boolean validateMovement(int[] movement, Piece capturable) {
        return movement[0] == position[0] || movement[1] == position[1] || position[0] - position[1] == movement[0] - movement[1] || position[0] + position[1] == movement[0] + movement[1];
    }
}

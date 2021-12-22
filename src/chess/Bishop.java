package chess;

public class Bishop extends Piece {
    public Bishop(int[] position) {
        super(position);
        this.name = "Bishop";
    }

    @Override
    public boolean validateMovement(int[] movement, Piece capturable) {
        return position[0] - position[1] == movement[0] - movement[1] || position[0] + position[1] == movement[0] + movement[1];
    }
}

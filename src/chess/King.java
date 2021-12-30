package chess;

public class King extends Piece {

    public King(int[] position) {
        super(position);
        this.name = "King";
    }

    @Override
    public boolean validateMovement(int[] movement, Piece capturable) {
        return (movement[0] == position[0] || movement[1] == position[1] || position[0] - position[1] == movement[0] - movement[1] || position[0] + position[1] == movement[0] + movement[1]) && (position[0] == movement[0] + 1 || position[0] == movement[0] - 1 || position[1] == movement[1] + 1 || position[1] == movement[1] - 1);
    }
}

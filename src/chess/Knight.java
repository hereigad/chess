package chess;

public class Knight extends Piece {
    public Knight(int[] position, boolean color) {
        super(position, color);
        this.setName("n");
    }

    @Override
    public boolean validateMovement(int[] movement, Piece capturable) {
        boolean capture = true;
        if(capturable != null) {
            capture = this.isColor() != capturable.isColor();
        }
        return (((movement[0] == getPosition()[0] + 2 || movement[0] == getPosition()[0] - 2) && (movement[1] == getPosition()[1] + 1 || movement[1] == getPosition()[1] - 1)
                || (movement[0] == getPosition()[0] + 1 || movement[0] == getPosition()[0] - 1) && (movement[1] == getPosition()[1] + 2 || movement[1] == getPosition()[1] - 2))) && capture;
    }
}

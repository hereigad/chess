package chess;

public class Queen extends Piece {

    public Queen(int[] position, boolean color) {
        super(position, color);
        this.setName("q");
    }

    @Override
    public boolean validateMovement(int[] movement, Piece capturable) {
        boolean capture = true;
        if(capturable != null) {
            capture = this.isColor() != capturable.isColor();
        }
        return (movement[0] == getPosition()[0] || movement[1] == getPosition()[1] || getPosition()[0] - getPosition()[1] == movement[0] - movement[1] || getPosition()[0] + getPosition()[1] == movement[0] + movement[1]) && capture;
    }
}

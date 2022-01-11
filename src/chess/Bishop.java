package chess;

public class Bishop extends Piece {
    public Bishop(int[] position, boolean color) {
        super(position, color);
        this.setName("b");
    }

    @Override
    public boolean validateMovement(int[] movement, Piece capturable) {
        boolean capture = true;
        if(capturable != null) {
            capture = this.isColor() != capturable.isColor();
        }
        return (getPosition()[0] - getPosition()[1] == movement[0] - movement[1] || getPosition()[0] + getPosition()[1] == movement[0] + movement[1]) && capture;
    }
}

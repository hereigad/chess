package chess;

public class Rook extends Piece {
    public Rook(int[] position, boolean color) {
        super(position, color);
        this.setName("r");
    }

    @Override
    public boolean validateMovement(int[] movement, Piece capturable) {
        boolean capture = true;
        if(capturable != null) {
            capture = this.isColor() != capturable.isColor();
        }
        return (movement[0] == getPosition()[0] || movement[1] == getPosition()[1]) && capture;
    }
}

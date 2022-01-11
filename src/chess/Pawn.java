package chess;

public class Pawn extends Piece {
    public Pawn(int[] position, boolean color) {
        super(position, color);
        this.setName("p");
    }

    @Override
    public boolean validateMovement(int[] movement, Piece capturable) {
        boolean capture = true;
        if(capturable != null) {
            capture = this.isColor() != capturable.isColor();
        }
        return ((movement[0] == getPosition()[0] - 1 && movement[1] == getPosition()[1] && capturable == null)
                || (movement[0] == getPosition()[0] - 1 && (movement[1] == getPosition()[1] + 1  || movement[1] == getPosition()[1] - 1) && capturable != null)) && capture;
    }
}

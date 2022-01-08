package chess;

public class Rook extends Piece {
    public Rook(int[] position, boolean color) {
        super(position, color);
        this.name = "r";
    }

    @Override
    public boolean validateMovement(int[] movement, Piece capturable) {
        boolean capture = true;
        if(capturable != null) {
            capture = this.color != capturable.color;
        }
        return (movement[0] == position[0] || movement[1] == position[1]) && capture;
    }
}

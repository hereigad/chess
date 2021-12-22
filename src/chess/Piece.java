package chess;

public abstract class Piece {
    public String name;
    public int[] position;

    public Piece(int[] position) {
        this.position = position;
    }

    public abstract boolean validateMovement(int[] movement, Piece capturable);
}

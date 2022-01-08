package chess;

public abstract class Piece {
    public String name;
    public int[] position;
    public boolean color;

    public Piece(int[] position, boolean color) {
        this.position = position;
        this.color = color;
    }

    public abstract boolean validateMovement(int[] movement, Piece capturable);
}

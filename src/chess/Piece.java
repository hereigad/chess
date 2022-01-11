package chess;

public abstract class Piece {
    private String name;
    private int[] position;
    private boolean color;

    public Piece(int[] position, boolean color) {
        this.setPosition(position);
        this.color = color;
    }

    public abstract boolean validateMovement(int[] movement, Piece capturable);

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isColor() {
        return color;
    }
}

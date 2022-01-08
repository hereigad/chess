package graphics;

import javax.swing.*;

public class Square extends JPanel {
    private int i;
    private int j;

    public Square(int i, int j) {
        super();
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}

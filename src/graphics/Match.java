package graphics;

import chess.Piece;
import game.Game;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Match extends JFrame {
    private Game game;

    private JPanel contentPane;
    private JPanel[][] squares;

    public Match(Game game) {
        this.setContentPane(contentPane);
        this.setTitle("Chess");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.game = game;
        squares = new JPanel[8][8];
        GridBagConstraints c = new GridBagConstraints();
        for(int i = 7; i > -1; i--) {
            for(int j = 0; j < 8; j++) {
                squares[j][i] = new JPanel();
                squares[j][i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                if((j + i) % 2 != 0) {
                    squares[j][i].setBackground(Color.DARK_GRAY);
                }
                squares[j][i].setMinimumSize(new Dimension(70, 70));
                squares[j][i].setPreferredSize(new Dimension(70, 70));
                c.gridx = j;
                c.gridy = i;
                contentPane.add(squares[j][i], c);
            }
        }
        this.updateBoard();
        this.pack();
    }

    private void updateBoard() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Piece p = game.board[7 - i][7 - j];
                if(p == null) {
                    if(squares[i][j].getComponents().length > 0) {
                        squares[i][j].remove(0);
                    }
                } else {
                    char color = 'd';
                    if(p.color) {
                        color = 'l';
                    }
                    String path = "/pieceIcons/Chess_" + p.name + color + "t45.png";
                    URL imageURL = getClass().getResource(path);
                    assert imageURL != null;
                    ImageIcon pieceIcon = new ImageIcon(imageURL);
                    squares[i][j].add(new JLabel(pieceIcon, JLabel.CENTER));
                }
            }
        }
    }
}

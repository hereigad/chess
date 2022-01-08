package graphics;

import chess.Piece;
import game.Game;
import networkLogic.Client;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.Socket;
import java.net.URL;

public class Match extends JFrame {
    private Game game;

    private JPanel contentPane;
    private Square[][] squares;
    private Piece selected;
    private Socket player2;

    public Match(Game game, Socket player2) {
        this.setContentPane(contentPane);
        this.setTitle("Chess");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.game = game;
        this.player2 = player2;
        this.selected = null;
        squares = new Square[8][8];
        GridBagConstraints c = new GridBagConstraints();
        for(int i = 7; i > -1; i--) {
            for(int j = 0; j < 8; j++) {
                squares[j][i] = new Square(i, j);
                squares[j][i].setBorder(new LineBorder(Color.BLACK));
                if((j + i) % 2 != 0) {
                    squares[j][i].setBackground(Color.DARK_GRAY);
                }
                squares[j][i].setMinimumSize(new Dimension(70, 70));
                squares[j][i].setPreferredSize(new Dimension(70, 70));
                c.gridx = j;
                c.gridy = i;
                squares[j][i].addMouseListener(new MouseInputAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        int[] movement = ((Match) SwingUtilities.getWindowAncestor(e.getComponent())).move((Square) e.getComponent());
                        if(movement != null) {
                            Client.sendMovement(player2, movement);
                            movement = Client.receiveMovement(player2);
                        }
                    }
                });
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
                if(((LineBorder) squares[i][j].getBorder()).getLineColor() == Color.GREEN) {
                    squares[i][j].setBorder(new LineBorder(Color.BLACK));
                }
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
                    if(squares[i][j].getComponents().length == 0) {
                        squares[i][j].add(new JLabel(pieceIcon, JLabel.CENTER));
                    }
                }
            }
        }
        this.revalidate();
    }

    private int[] move(Square square) {
        Piece piece = game.board[7 - square.getJ()][7 - square.getI()];
        int[] movement = null;
        if(selected == null) {
            if(piece != null && piece.color == game.player.getSide()) {
                this.selected = piece;
                square.setBorder(new LineBorder(Color.GREEN));
            }
        } else {
            movement = new int[]{7 - square.getJ(), 7 - square.getI()};
            if(this.selected.validateMovement(movement, game.board[movement[0]][movement[1]])) {
                if(piece != null) {
                    game.player.getCaptured().add(piece);
                }
                game.board[selected.position[0]][selected.position[1]] = null;
                selected.position = movement;
                game.board[movement[0]][movement[1]] = selected;
            }
            selected = null;
            updateBoard();
        }
        return movement;
    }

    private void updateRival(int[] movement) {

    }
}

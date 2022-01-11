package graphics;

import chess.Piece;
import game.Game;
import networkLogic.Client;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Arrays;

public class Match extends JFrame {
    private Game game;

    private JPanel contentPane;
    private Square[][] squares;
    private Piece selected;

    public Match(Game game) {
        this.setContentPane(contentPane);
        this.setTitle("Chess");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.game = game;
        this.selected = null;
        this.setGlassPane(new JPanel());
        ((JPanel) this.getGlassPane()).setOpaque(false);
        this.getGlassPane().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        if(!game.getPlayer().getSide()) {
            this.getGlassPane().setVisible(true);
        }
        squares = new Square[8][8];
        GridBagConstraints c = new GridBagConstraints();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                squares[i][j] = new Square(i, j);
                squares[i][j].setBorder(new LineBorder(Color.BLACK));
                if((j + i) % 2 != 0) {
                    squares[i][j].setBackground(Color.DARK_GRAY);
                }
                squares[i][j].setMinimumSize(new Dimension(70, 70));
                squares[i][j].setPreferredSize(new Dimension(70, 70));
                c.gridx = j;
                c.gridy = i;
                squares[i][j].addMouseListener(new MouseInputAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        int[] movement = ((Match) SwingUtilities.getWindowAncestor(e.getComponent())).move((Square) e.getComponent());
                        if(movement != null) {
                            Client.sendMovement(movement);
                        }
                    }
                });
                contentPane.add(squares[i][j], c);
            }
        }
        Thread receive = new Thread(() -> {
            while(true) {
                int[] movement = Client.receiveMovement();
                updateRival(movement);
            }
        });
        receive.start();

        this.updateBoard();
        this.pack();
    }

    private void updateBoard() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Piece p = game.getBoard()[i][j];
                if(((LineBorder) squares[i][j].getBorder()).getLineColor() == Color.GREEN) {
                    squares[i][j].setBorder(new LineBorder(Color.BLACK));
                }
                if(squares[i][j].getComponents().length > 0) {
                    squares[i][j].remove(0);
                }
                if(p != null) {
                    char color = 'd';
                    if(p.isColor()) {
                        color = 'l';
                    }
                    String path = "/pieceIcons/Chess_" + p.getName() + color + "t45.png";
                    URL imageURL = getClass().getResource(path);
                    assert imageURL != null;
                    ImageIcon pieceIcon = new ImageIcon(imageURL);

                    if(squares[i][j].getComponents().length == 0) {
                        squares[i][j].add(new JLabel(pieceIcon, JLabel.CENTER));
                    }
                }
            }
        }
        this.getContentPane().repaint();
        this.getContentPane().revalidate();
    }

    private int[] move(Square square) {
        Piece piece = game.getBoard()[square.getI()][square.getJ()];
        int[] movement = null;
        if(selected == null) {
            if(piece != null && piece.isColor() == game.getPlayer().getSide()) {
                this.selected = piece;
                square.setBorder(new LineBorder(Color.GREEN));
            }
        } else {
            movement = new int[]{square.getI(), square.getJ(), selected.getPosition()[0], selected.getPosition()[1]};
            if(this.selected.validateMovement(movement, game.getBoard()[movement[0]][movement[1]])) {
                this.getGlassPane().setVisible(true);
                if(piece != null) {
                    game.getPlayer().getCaptured().add(piece);
                }
                game.getBoard()[selected.getPosition()[0]][selected.getPosition()[1]] = null;
                selected.setPosition(Arrays.copyOfRange(movement, 0, 2));
                game.getBoard()[movement[0]][movement[1]] = selected;
            }
            selected = null;
            updateBoard();
        }
        return movement;
    }

    private void updateRival(int[] movement) {
        if(movement != null) {
            int[] transformedMovement = transformMovement(movement);
            Piece piece = game.getBoard()[transformedMovement[2]][transformedMovement[3]];
            game.getBoard()[piece.getPosition()[0]][piece.getPosition()[1]] = null;
            piece.setPosition(Arrays.copyOfRange(transformedMovement, 0, 2));
            game.getBoard()[transformedMovement[0]][transformedMovement[1]] = piece;
            this.getGlassPane().setVisible(false);
            updateBoard();
        }
    }

    private int[] transformMovement(int[] movement) {
        for(int i = 0; i < 4; i++) {
            movement[i] = 7 - movement[i];
        }
        return movement;
    }
}

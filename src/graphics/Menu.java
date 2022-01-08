package graphics;

import networkLogic.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.Socket;
import java.util.concurrent.*;

public class Menu extends JFrame {
    private JButton matchmakeButton;
    private JPanel contentPane;
    private Future<Socket> player2;

    public Menu() {
        this.setContentPane(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        matchmakeButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matchmakeButton.setEnabled(false);
                player2 = Client.matchmake();
                System.out.println("Matchmaking");

            }
        });

        this.pack();
    }

    public Future<Socket> getPlayer2() {
        return player2;
    }
}

package graphics;

import networkLogic.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.Socket;
import java.util.concurrent.*;

public class Menu extends JFrame {
    private JButton matchmakeButton;
    private JPanel panel1;
    private Future<Socket> player2;
    private CountDownLatch c;

    public Menu(CountDownLatch c) {
        this.c = c;
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        matchmakeButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

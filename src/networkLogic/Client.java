package networkLogic;

import com.google.gson.Gson;
import game.Game;
import game.Player;
import graphics.Menu;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.AbstractMap;
import java.util.concurrent.*;

public class Client {
    private static CountDownLatch c = new CountDownLatch(1);
    private static String[] ports;
    private static final ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        ports = args;
        Menu menu = new Menu();
        menu.setVisible(true);
        Socket player2Socket = null;
        Game game = null;
        Player localPlayer = null;
        Future<Socket> player2Future2 = pool.submit(Client::acceptMatch);
        try {
            c.await();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        Future<Socket> player2Future1 = menu.getPlayer2();
        while(!player2Future1.isCancelled() && !player2Future2.isCancelled()) {
            if(player2Future1.isDone() && !player2Future1.isCancelled()) {
                System.out.println("El futuro 1 se ha completado");
                try {
                    player2Socket = player2Future1.get();
                    player2Future2.cancel(true);
                    localPlayer = new Player(true);
                } catch(InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            if(player2Future2.isDone() && !player2Future2.isCancelled()) {
                System.out.println("El futuro 2 se ha completado");
                try {
                    player2Socket = player2Future2.get();
                    player2Future1.cancel(true);
                    localPlayer = new Player(false);
                } catch(InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Funciona");
        if(localPlayer != null) {
            game = new Game(localPlayer);
        }
    }

    public static Future<Socket> matchmake() {
        Future<Socket> player2;
        pool.execute(() -> {
            try(Socket server = new Socket("localhost", 7200);
                OutputStream os = new DataOutputStream(server.getOutputStream())) {
                int gamePort = Integer.parseInt(ports[0]);
                int receivePort = Integer.parseInt(ports[2]);
                ((DataOutputStream) os).writeInt(gamePort);
                ((DataOutputStream) os).writeInt(receivePort);
                os.flush();
            } catch(IOException e) {
                e.printStackTrace();
            }
        });
        player2 = pool.submit(() -> {
            Socket rival = null;
            try(ServerSocket ss = new ServerSocket(Integer.parseInt(ports[2]))) {
                try(Socket server2 = ss.accept();
                    InputStream is = new ObjectInputStream(server2.getInputStream())) {
                    AbstractMap.SimpleImmutableEntry<InetAddress, Integer[]> player2pair = (AbstractMap.SimpleImmutableEntry<InetAddress, Integer[]>) ((ObjectInputStream) is).readObject();
                    rival = new Socket(player2pair.getKey().getHostAddress(), player2pair.getValue()[0]);
                    System.out.println("Rival encontrado");
                } catch(IOException e) {
                    e.printStackTrace();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
            return rival;
        });
        c.countDown();
        return player2;
    }

    public static Socket acceptMatch() {
        Socket player2Socket = null;
        try(ServerSocket ss = new ServerSocket(Integer.parseInt(ports[0]))) {
            player2Socket = ss.accept();
            System.out.println("Partida aceptada");
        } catch(IOException e) {
            e.printStackTrace();
        }
        return player2Socket;
    }

    public static void sendMovement(Socket socket, int[] movement) {

    }
}

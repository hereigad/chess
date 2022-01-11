package networkLogic;

import game.Game;
import game.Player;
import graphics.Match;
import graphics.Menu;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Client {
    private static final CountDownLatch c = new CountDownLatch(1);
    private static String[] ports;
    private static final ExecutorService pool = Executors.newCachedThreadPool();
    private static PlayerData player2Data;

    public static void main(String[] args) {
        /* Los puertos se obtienen como parametro de entrada. Era la forma mas comoda de hacerlo teniendo que probar 2 clientes
        * en la misma maquina.
        */
        ports = args;
        Menu menu = new Menu();
        menu.setVisible(true);
        player2Data = null;
        Game game = null;
        Player localPlayer = null;
        try {
            //Esto esta para que a la interfaz le de tiempo a asignar el futuro al campo. Si no el menu.getPlayer2() devuelve null.
            c.await();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        Future<PlayerData> player2Future = menu.getPlayer2();
        try {
            player2Data = player2Future.get();
            System.out.println("El futuro se ha completado");
            localPlayer = new Player(player2Data.getSide());
        } catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if(player2Data != null) {
            System.out.println("Datos de rival recibidos");
        }
        //Prueba de conexion para comprobar que el otro cliente puede conectarse.
        Future<Boolean> connection = pool.submit(Client::acceptMatch);
        pool.execute(Client::tryConnection);
        try {
            if(localPlayer != null && player2Data != null && connection.get()) {
                menu.dispose();
                game = new Game(localPlayer);
                Match match = new Match(game);
                match.setVisible(true);
            }
        } catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static Future<PlayerData> matchmake() {
        Future<PlayerData> player2;
        pool.execute(() -> {
            //Envia los datos de red al servidor para buscar partida
            try(Socket server = new Socket("localhost", 7200);
                OutputStream os = new DataOutputStream(server.getOutputStream())) {
                int gamePort = Integer.parseInt(ports[0]);
                int receivePort = Integer.parseInt(ports[1]);
                ((DataOutputStream) os).writeInt(gamePort);
                ((DataOutputStream) os).writeInt(receivePort);
                os.flush();
            } catch(IOException e) {
                e.printStackTrace();
            }
        });
        player2 = pool.submit(() -> {
            //Espera a que el servidor envie los datos de red del rival
            PlayerData rival = null;
            try(ServerSocket ss = new ServerSocket(Integer.parseInt(ports[1]))) {
                try(Socket server2 = ss.accept();
                    InputStream is = new ObjectInputStream(server2.getInputStream())) {
                    rival = (PlayerData) ((ObjectInputStream) is).readObject();
                    System.out.println("Rival encontrado");
                } catch(IOException e) {
                    e.printStackTrace();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
            c.countDown();
            return rival;
        });
        return player2;
    }

    public static boolean acceptMatch() {
        boolean connection = false;
        try(ServerSocket ss = new ServerSocket(Integer.parseInt(ports[0]));
            Socket player2 = ss.accept()) {
            connection = true;
            System.out.println("Partida aceptada");
        } catch(IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void tryConnection() {
        try(Socket player2 = new Socket(player2Data.getAddress(), player2Data.getPort())) {
            System.out.println("Conexion exitosa");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    //Los dos metodos siguientes son para enviar y recibir jugadas entre clientes
    public static void sendMovement(int[] movement) {
        try(Socket player2Socket = new Socket(player2Data.getAddress(), player2Data.getPort());
            OutputStream os = new DataOutputStream(player2Socket.getOutputStream())) {
            for(int i = 0; i < 4; i++) {
                ((DataOutputStream) os).writeInt(movement[i]);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] receiveMovement() {
        int[] movement = new int[4];
        int i = 0;
        try(ServerSocket local = new ServerSocket(Integer.parseInt(ports[0]));
            Socket player2Socket = local.accept();
            InputStream is = new DataInputStream(player2Socket.getInputStream())) {
            while(i < 4) {
                try {
                    movement[i] = ((DataInputStream) is).readInt();
                    i++;
                } catch(EOFException e) {
                    e.printStackTrace();
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return movement;
    }
}

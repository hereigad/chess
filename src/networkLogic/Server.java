package networkLogic;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.AbstractMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        Queue<AbstractMap.SimpleImmutableEntry<InetAddress, Integer[]>> queue = new ConcurrentLinkedQueue<>();
        ExecutorService pool = Executors.newFixedThreadPool(4);
        try(ServerSocket server = new ServerSocket(7200)) {
            while(true) {
                pool.execute(() -> {
                    try(Socket client = server.accept();
                        InputStream is = new DataInputStream(client.getInputStream())) {
                        int gamePort = ((DataInputStream) is).readInt();
                        int receivePort = ((DataInputStream) is).readInt();
                        queue.add(new AbstractMap.SimpleImmutableEntry<>(client.getInetAddress(), new Integer[]{gamePort, receivePort}));
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                });
                pool.execute(() -> {
                    if(queue.size() % 2 == 0 && queue.size() > 0) {
                        AbstractMap.SimpleImmutableEntry<InetAddress, Integer[]> pair1 = queue.remove();
                        AbstractMap.SimpleImmutableEntry<InetAddress, Integer[]> pair2 = queue.remove();
                        try(Socket player1Socket = new Socket(pair1.getKey().getHostAddress(), pair1.getValue()[1]);
                            Socket player2Socket = new Socket(pair2.getKey().getHostAddress(), pair2.getValue()[1]);
                            OutputStream os1 = new ObjectOutputStream(player1Socket.getOutputStream());
                            OutputStream os2 = new ObjectOutputStream(player2Socket.getOutputStream())) {
                            ((ObjectOutputStream) os1).writeObject(new PlayerData(pair2.getKey(), pair2.getValue()[0], true));
                            ((ObjectOutputStream) os2).writeObject(new PlayerData(pair1.getKey(), pair1.getValue()[0], false));
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }
}

package networkLogic;

import com.google.gson.Gson;

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
                        String player1 = pair1.getKey().getHostAddress();
                        int receivePort = pair1.getValue()[1];
                        AbstractMap.SimpleImmutableEntry<InetAddress, Integer[]> pair2 = queue.remove();
                        try(Socket player = new Socket(player1, receivePort);
                            OutputStream os = new ObjectOutputStream(player.getOutputStream())) {
                            ((ObjectOutputStream) os).writeObject(pair2);
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

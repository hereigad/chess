package networkLogic;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        Queue<InetAddress> queue = new ConcurrentLinkedQueue<>();
        ExecutorService pool = Executors.newCachedThreadPool();
        try(ServerSocket server = new ServerSocket(72000)) {
            while(true) {
                pool.execute(() -> {
                    try(Socket client = server.accept()) {
                        queue.add(client.getInetAddress());
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                });
                pool.execute(() -> {
                    if(queue.size() % 2 == 0) {
                        InetAddress player1 = queue.remove();
                        InetAddress player2 = queue.remove();
                        try(Socket player = new Socket(player1.getHostAddress(), 72000);
                            DataOutputStream os = new DataOutputStream(player.getOutputStream())) {
                            String mensaje = new Gson().toJson(player2);
                            os.writeChars(mensaje);
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

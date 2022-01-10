package networkLogic;

import java.io.Serializable;
import java.net.InetAddress;

public class PlayerData implements Serializable {
    private final InetAddress address;
    private final int port;
    private final boolean side;

    public PlayerData(InetAddress address, int port, boolean side) {
        this.address = address;
        this.port = port;
        this.side = side;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public boolean getSide() {
        return side;
    }
}

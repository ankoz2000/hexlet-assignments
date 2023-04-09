package exercise;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection implements Connection {
    private final String ipAddress;
    private final Integer port;
    private Connection state;

    public TcpConnection(String ipAddress, Integer port) {
        this.ipAddress = ipAddress;
        this.port = port;
        state = new Disconnected(this);
    }

    @Override
    public String getCurrentState() {
        return state.getCurrentState();
    }

    @Override
    public void connect() {
        state.connect();
    }

    @Override
    public void disconnect() {
        state.disconnect();
    }

    @Override
    public void write(String data) {
        state.write(data);
    }

    public void setState(Connection state) {
        this.state = state;
    }
}
// END

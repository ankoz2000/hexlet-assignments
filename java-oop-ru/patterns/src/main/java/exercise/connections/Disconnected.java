package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {
    private final TcpConnection tcpConnection;
    private final String state = "disconnected";

    public Disconnected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public String getCurrentState() {
        return state;
    }

    @Override
    public void connect() {
        tcpConnection.setState(new Connected(tcpConnection));
    }

    @Override
    public void disconnect() {
        if (tcpConnection.getCurrentState().equals(state)) {
            System.out.println("Error");
        }
    }

    @Override
    public void write(String data) {
        System.out.println("Error. Connect to write!");
    }
}
// END

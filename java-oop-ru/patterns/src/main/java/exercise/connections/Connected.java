package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {
    private final TcpConnection tcpConnection;
    private final String state = "connected";

    public Connected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public String getCurrentState() {
        return state;
    }

    @Override
    public void connect() {
        if (tcpConnection.getCurrentState().equals(state)) {
            System.out.println("Error");
        }
    }

    @Override
    public void disconnect() {
        tcpConnection.setState(new Disconnected(tcpConnection));
    }

    @Override
    public void write(String data) {
        System.out.println(data);
    }
}
// END

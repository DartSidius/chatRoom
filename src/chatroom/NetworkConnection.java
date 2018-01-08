package chatroom;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

/**
 *
 * @author vladlyfar
 */
public abstract class NetworkConnection {
    private ConnectionThread connThread = new ConnectionThread();
    private Consumer<Serializable> onReceiveCallback;
    public NetworkConnection(Consumer<Serializable>onReceiveCallback) {
        this.onReceiveCallback = onReceiveCallback;
        connThread.setDaemon(true);
    }
    public void startConnection() throws Exception {
        connThread.start();
    }
    public void send(Serializable data) throws Exception {
        connThread.out.writeObject(data);
    }
    public void closeConnection() throws Exception {
        connThread.clientSocket.close();
    }
    
    protected abstract boolean isServer();
    protected abstract String getIP();
    protected abstract int getPort();
    
    private class ConnectionThread extends Thread {
        private Socket clientSocket;
        private ObjectOutputStream out;
        @Override 
        public void run() {
            try(ServerSocket ss = isServer() ? new ServerSocket(getPort()) : null;
                    Socket clientSocket = isServer() ? ss.accept() : new Socket(getIP(), getPort());
                    ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
                this.clientSocket = clientSocket;
                this.out = out;
                this.clientSocket.setTcpNoDelay(true);
                while(true) {
                    Serializable data = (Serializable) in.readObject();
                    onReceiveCallback.accept(data);
                }
            }   catch (Exception ex) {
                onReceiveCallback.accept("Connection closed");
            }
    }   }
}

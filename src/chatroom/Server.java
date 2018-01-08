/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author vladlyfar
 */
public class Server {
    private ServerSocket serverSocket = new ServerSocket(1234);
    private Socket clientSocket = serverSocket.accept();
    private DataInputStream in = new DataInputStream(clientSocket.getInputStream());
    private DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
    public Server() throws IOException {
        this.serverSocket = serverSocket;
        this.clientSocket = clientSocket;
        this.in = in;
        this.out = out;
    }
    public void sendMessage(String msg) throws IOException {
        this.out.writeUTF(msg);
    }
    public String getMessage() throws IOException {
        return this.in.readUTF();
    }
}

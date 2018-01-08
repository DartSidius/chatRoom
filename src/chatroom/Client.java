/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author vladl
 */
public class Client {
    private Socket clientSocket = new Socket("127.0.0.1", 1234);
    private DataInputStream in = new DataInputStream(clientSocket.getInputStream());
    private DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
    public Client() throws IOException {
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

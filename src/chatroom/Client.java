/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;

/**
 *
 * @author vladl
 */
public class Client extends NetworkConnection {

    private String IP;
    private int port;
    
    public Client(String IP, int port, Consumer<Serializable> onReceiveCallback) {
        super(onReceiveCallback);
        this.IP = IP;
        this.port = port;
    }

    @Override
    protected boolean isServer() {
        return false;
    }

    @Override
    protected String getIP() {
        return this.IP;
    }

    @Override
    protected int getPort() {
        return this.port;
    }
    
}

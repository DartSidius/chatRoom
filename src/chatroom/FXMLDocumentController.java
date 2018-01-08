/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroom;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

/**
 *
 * @author vladlyfar
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextArea message;
    @FXML
    private ListView messageList;
    private final boolean isServer = true;
    
    private void defineChatMember() throws IOException {
        if(!isServer) {
            Client chatMember = new Client();
        } else {
            Server chatMember = new Server();
        }
    }
    private void displayMessages(String msg) {
        messageList.getItems().add(msg);
    } 
    @FXML 
    private void sendMessage() throws IOException {
        defineChatMember();
        String messageContent = isServer ? "Server: " + message.getText() : "Client: " + message.getText();
        displayMessages(messageContent);
        
        Client chatMember = new Client();
        // Server chatMember = new Server();
        
        chatMember.sendMessage(messageContent);
        
        message.clear();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(isServer);
    }    
    
}

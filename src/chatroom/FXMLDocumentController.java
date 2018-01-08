/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroom;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
    private final boolean isServer = false;
    private NetworkConnection connection;
    
    private Server createServer() {
        return new Server(1234, data -> {
            Platform.runLater(() -> {
                messageList.getItems().add(data.toString());
            });
        });
    }
    
    private Client createClient() {
        return new Client("127.0.0.1", 1234, data -> {
            Platform.runLater(() -> {
                messageList.getItems().add(data.toString());
            });
        });
    }
    
    @FXML 
    private void sendMessage() throws IOException, Exception {
        String messageContent = isServer ? "Server: " : "Client: ";
        messageContent += message.getText();
        
        messageList.getItems().add(messageContent);

        message.clear();
        
        connection.send(messageContent);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = isServer ? createServer() : createClient();
        try {
            connection.startConnection();
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}

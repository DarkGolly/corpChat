package chat.chat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import chat.network.TCPConnection;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    private static final String IP_ADDR = "192.168.0.103";
    private static final int  PORT = 8189;
    private TCPConnection connection;

    @FXML
    protected void onHelloButtonClick() {

    }
    @FXML
    void initialize(){

    }
}
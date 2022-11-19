package chat.chat;

import chat.network.TCPConnectionListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import chat.network.TCPConnection;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements TCPConnectionListener {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField ipField;

    @FXML
    private Button setIp;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    private String IP_ADDR = "192.255.107.167"; //"192.168.0.106" 103  "92.255.107.167"
    private static final int  PORT = 8189;
    private TCPConnection connection;
    @FXML
    private TextArea log;
    @FXML
    private TextField name;
    private String nick;
    @FXML
    private Button sendBtn;
    @FXML
    private Button setName;
    @FXML
    private TextField text;
    private String history="";

    @FXML
    void onSendClick(MouseEvent event) {
        String msg = text.getText();
        if (msg.equals("")) return;
        text.setText(null);
        connection.sendString(nick +": "+msg);
    }
    @FXML
    void onSetName(MouseEvent event) {
        nick = name.getText();
        try {
            connection = new TCPConnection(this,IP_ADDR,PORT);
        } catch (IOException e) {
            doInBackground("Connection exception: "+e);
        }
    }
    @FXML
    void onSetIp(MouseEvent event) {
        IP_ADDR = ipField.getText();
    }

    @FXML
    void initialize(){

    }
    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        doInBackground("Connection ready...");
    }
    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        doInBackground(value);
    }
    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        doInBackground("Connection close");
    }
    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {

        doInBackground("Connection exception: "+e);
    }
    @Override
    public void doInBackground(String msg) {
        log.appendText(msg +"\n");
    }
}
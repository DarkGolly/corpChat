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
    private ResourceBundle resources;
    @FXML
    private URL location;
    private static final String IP_ADDR = "192.168.0.106";
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
        log.appendText(msg+"\n");
        connection.sendString(nick +": "+msg);
    }
    @FXML
    void onSetName(MouseEvent event) {
        nick = name.getText();
        try {
            connection = new TCPConnection(this,IP_ADDR,PORT);
        } catch (IOException e) {
            printMsg("Connection exception: "+e);
        }
    }
    @FXML
    void initialize(){

    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        printMsg("Connection ready...");
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        printMsg(value);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        printMsg("Connection close");
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {
        printMsg("Connection exception: "+e);
    }

    private synchronized void printMsg(String msg){
        new Runnable(){
            @Override
            public void run(){
                log.appendText(msg +"\n");
                //log.setText(history + msg +"\n");
                //history+= msg;
            }
        };
    }
}
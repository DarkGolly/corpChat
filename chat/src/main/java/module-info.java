
module chat.chat {
    requires javafx.controls;
    requires javafx.fxml;



    opens chat.chat to javafx.fxml;
    exports chat.chat;
}
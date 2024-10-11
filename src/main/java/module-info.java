module org.david.coinexchange {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens org.david.coinexchange to javafx.fxml;
    exports org.david.coinexchange;
    exports org.david.coinexchange.controller;
    opens org.david.coinexchange.controller to javafx.fxml;

    opens org.david.coinexchange.model to com.google.gson;
}
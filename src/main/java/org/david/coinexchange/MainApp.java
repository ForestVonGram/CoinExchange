package org.david.coinexchange;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.david.coinexchange.model.ConsultaMoneda;
import org.david.coinexchange.model.GenerarJson;
import org.david.coinexchange.model.Moneda;

import java.io.IOException;
import java.util.Scanner;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("coin-exchange-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 450);

        stage.setTitle("ExCoin - cambio de divisa");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

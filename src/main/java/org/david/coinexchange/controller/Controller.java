package org.david.coinexchange.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.david.coinexchange.model.ConsultaMoneda;
import org.david.coinexchange.model.GenerarJson;
import org.david.coinexchange.model.Moneda;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> moneda_base;

    @FXML
    private ComboBox<String> moneda_cambio;

    @FXML
    private TextField valor_cambio;

    @FXML
    private Button conversion;

    @FXML
    private Text resultado;

    private ConsultaMoneda consultaMoneda;

    @FXML
    void initialize() {
        assert moneda_base != null : "fx:id=\"moneda_base\" was not injected: check your FXML file 'coin-exchange-view.fxml'.";
        assert moneda_cambio != null : "fx:id=\"moneda_cambio\" was not injected: check your FXML file 'coin-exchange-view.fxml'.";
        assert resultado != null : "fx:id=\"resultado\" was not injected: check your FXML file 'coin-exchange-view.fxml'.";

        consultaMoneda = new ConsultaMoneda();

        moneda_base.getItems().addAll("USD", "BRL", "ARS", "COP");
        moneda_cambio.getItems().addAll("USD", "BRL", "ARS", "COP");

        conversion.setOnAction(event -> calcularCambio());
    }

    private void calcularCambio() {
        String base = moneda_base.getValue();
        String cambio = moneda_cambio.getValue();
        String valorTexto = valor_cambio.getText();

        if (base != null && cambio != null && !valorTexto.isEmpty()) {
            try {
                double valor = Double.parseDouble(valorTexto);
                Moneda moneda = consultaMoneda.buscarMoneda(base);
                double tasaCambio = moneda.conversion_rates().get(cambio);
                double resultadoCambio = valor * tasaCambio;

                resultado.setText(String.valueOf(resultadoCambio));

                GenerarJson generarJson = new GenerarJson();
                generarJson.guardarJson(moneda, cambio, valor, resultadoCambio);
            }catch (NumberFormatException e) {
                resultado.setText("Error: ingrese un número válido.");
            } catch (RuntimeException | IOException e) {
                resultado.setText("Error: " + e.getMessage());
            }
        } else {
            resultado.setText("Seleccione ambas divisas.");
        }
    }
}

package org.david.coinexchange.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class GenerarJson {
    public void guardarJson (Moneda moneda, String monedaCambio, double valorACambiar, double resultadoCambio) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Map<String, Double> conversionRates = moneda.conversion_rates();
        JsonObject tasaFiltrada = new JsonObject();

        if (conversionRates.containsKey(monedaCambio)) {
            tasaFiltrada.add(monedaCambio, new JsonPrimitive(conversionRates.get(monedaCambio)));
        }

        JsonObject json = new JsonObject();
        json.addProperty("base_code", moneda.base_code());
        json.add("conversion_rates", tasaFiltrada);
        json.addProperty("valorACambiar", valorACambiar);
        json.addProperty("resultado_cambio", resultadoCambio);

        String carpetaDestino = "cambios";
        Path ruta = Paths.get(carpetaDestino);

        if (!Files.exists(ruta)) {
            Files.createDirectories(ruta);
        }

        FileWriter escritura = new FileWriter(carpetaDestino + "/" + moneda.base_code() + "_a_" + monedaCambio + ".json");
        escritura.write(gson.toJson(json));
        escritura.close();
    }
}

package com.javacursos.conversormoneda.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import com.google.gson.Gson;
import com.javacursos.conversormoneda.modelos.MonedaDTO;

public class ConexionApi {
    private String direccion;
    private String apiKey;

    public ConexionApi(String apiKey) {
        this.apiKey = apiKey;
        this.direccion = "https://v6.exchangerate-api.com/v6/" + this.apiKey + "/latest/";

    }

    public MonedaDTO obtenerDatosMoneda(String codigoMoneda) {
        URI direccionUri = URI.create(direccion + codigoMoneda);
        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(direccionUri)
                    .build();

            HttpResponse<String> response = client
                    .send(request, BodyHandlers.ofString());

            int status = response.statusCode();
            String body = response.body().toLowerCase();
            switch (status) {
                case 200 -> {
                    return new Gson().fromJson(response.body(), MonedaDTO.class);
                }
                case 403 -> throw new SecurityException("API key invalida.");
                case 404 -> {
                    if (body.contains("unsupported-code")) {
                        throw new IllegalArgumentException("Codigo de moneda no soportado.");
                    } else {
                        throw new IllegalStateException("Recurso no encontrado. Verifique la URL.");
                    }
                }
                default -> throw new RuntimeException("Error HTTP: " + status);
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Ha ocurrido un error inesperado.");
        }

    }
}

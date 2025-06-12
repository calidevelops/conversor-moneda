package com.javacursos.conversormoneda.service;

import com.javacursos.conversormoneda.api.ConexionApi;
import com.javacursos.conversormoneda.modelos.Moneda;
import com.javacursos.conversormoneda.modelos.MonedaDTO;

public class MonedaService {

    private final ConexionApi conexionApi;

    public MonedaService(String apiKey){
        this.conexionApi = new ConexionApi(apiKey);
    }

    public Double convertirMonto(String codigoBase, String codigoDestino, Double monto){
        MonedaDTO monedaDTO = conexionApi.obtenerDatosMoneda(codigoBase);
        Moneda moneda = new Moneda(monedaDTO);
        return moneda.convertirMonto(codigoDestino, monto);
    }

    



}

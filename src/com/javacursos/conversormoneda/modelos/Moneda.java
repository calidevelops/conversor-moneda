package com.javacursos.conversormoneda.modelos;

import java.util.Map;

public class Moneda {

    private String codigoBase;
    private Map<String, Double> tasasDeConversion;

    public Moneda(MonedaDTO monedaDTO){
        this.codigoBase = monedaDTO.base_code();
        this.tasasDeConversion = monedaDTO.conversion_rates();
    }

    public String getCodigoBase() {
        return codigoBase;
    }

    public Map<String, Double> getTasasDeConversion() {
        return tasasDeConversion;
    }

    public Double convertirMonto(String codigoMoneda, Double monto){
        return monto * obtenerTasaDeConversion(codigoMoneda);
    }
    
    private Double obtenerTasaDeConversion(String codigoMoneda){
        return tasasDeConversion.get(codigoMoneda);
    }
}

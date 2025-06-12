package com.javacursos.conversormoneda.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.javacursos.conversormoneda.service.MonedaService;

public class MonedaController {

    private final MonedaService monedaService;
    private final Scanner scanner;

    public MonedaController(String apiKey) {
        this.monedaService = new MonedaService(apiKey);
        this.scanner = new Scanner(System.in);
    }

    public void iniciarConversion() {

        while (true) {
            System.out.println("""
                    **********************************************************
                    Sea bienvenido/a al Conversor de Moneda =]

                    1) Dolar =>> Peso argentino
                    2) Peso Argentino =>> Dolar
                    3) Dolar =>> Real brasileño
                    4) Real brasileño =>> Dolar
                    5) Dolar =>> Peso colombiano
                    6) Peso colombiano =>> Dolar
                    7) Salir
                    Elija una opción válida:
                    **********************************************************
                    """);
            try {
                int opcion = scanner.nextInt();
                switch (opcion) {
                    case 1 -> convertirMonto("USD", "ARS");
                    case 2 -> convertirMonto("ARS", "USD");
                    case 3 -> convertirMonto("USD", "BRL");
                    case 4 -> convertirMonto("BRL", "USD");
                    case 5 -> convertirMonto("USD", "COP");
                    case 6 -> convertirMonto("COP", "USD");
                    case 7 -> {
                        System.out.println("Finalizando aplicación.");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Opcion invalida. Inténtelo de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese una opcion valida.");
                scanner.nextLine();

            }

        }

    }

    public void convertirMonto(String codigoBase, String codigoDestino) {
        while (true) {
            System.out.println("Ingrese el monto:");
            try {
                double monto = scanner.nextDouble();
                if (monto <= 0) {
                    System.out.println("El monto no pude ser menor o igual a 0.");
                } else {
                    double montoConvertido = monedaService.convertirMonto(codigoBase, codigoDestino, monto);
                    System.out.printf("El valor %.2f [%s] corresponde al valor final de >>>> %.2f [%s] \n",
                            monto, codigoBase, montoConvertido, codigoDestino);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un monto valido.");
                scanner.nextLine();
            }

        }

    }
}

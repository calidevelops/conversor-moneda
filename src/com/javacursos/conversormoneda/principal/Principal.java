package com.javacursos.conversormoneda.principal;


import java.util.Scanner;

import com.javacursos.conversormoneda.controller.MonedaController;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su API key: ");
        String apiKey = scanner.nextLine();
        MonedaController monedaController = new MonedaController(apiKey);
        monedaController.iniciarConversion();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iessevero.aeropuerto.views;

import java.util.Scanner;

/**
 *
 * @author F
 */
public class VistasPrincipal {
    static Scanner sc=new Scanner(System.in);
    public int elegirSistemaAlmacenamiento() {
        int opcion = -1; //opcio -1 indica opcion incorrecta
        while (opcion == -1) {
            System.out.println("Introduzca El tipo de sistema de almacenamiento");
            System.out.println("1.Archivo de texto");
            System.out.println("2.Archivo Binario");
            System.out.println("3.Archivo XML usando DOM");
            System.out.println("4.Archivo XML usando SAX");
            System.out.println("5.Base de datos relacional usando JDBC");
            System.out.println("6.Base de datos relacional usando Hibernate");
            System.out.println("0.Salir");
            
            String entrada = sc.nextLine();
            if (!esEntero(entrada)) {
                opcion = -1;
            } else {
                opcion = Integer.parseInt(entrada);
            }
            if (opcion >= 7 || opcion < 0) {
                System.out.println("Opcion No valida");
                opcion = -1;

            }

        }
        return opcion;
    }
    public int gestionMenusPrincipales() {
        int opcion = -1; //opcio -1 indica opcion incorrecta
        while (opcion == -1) {
            System.out.println("Elige que menu deseas gestionar");
            System.out.println("0.Salir");
            System.out.println("1.Gestionar Vuelos");
            System.out.println("2.Gestionar Reservas");
            System.out.println("3.Informes");

            String entrada = sc.nextLine();
            if (!esEntero(entrada)) {
                opcion = -1;
            } else {
                opcion = Integer.parseInt(entrada);
            }
            if (opcion >= 4 || opcion < 0) {
                System.out.println("Opcion No valida");
                opcion = -1;

            }

        }
        return opcion;
    }//Fin menuPrincipal
    
    private static boolean esEntero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }//Fin esEntero
    
    public void mostrarError(String mensaje) {
        System.out.println(mensaje);
    }//Fin mostrarError
}

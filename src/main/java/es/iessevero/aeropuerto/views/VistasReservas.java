/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iessevero.aeropuerto.views;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import es.iessevero.aeropuerto.clases.Pasajero;
import es.iessevero.aeropuerto.clases.Reserva;
import es.iessevero.aeropuerto.clases.Vuelo;
import es.iessevero.aeropuerto.controller.VuelosController;
import es.iessevero.aeropuerto.dao.aeropuerto.dao.DAOException;

/**
 * @author F
 */
public class VistasReservas {

    static Scanner sc = new Scanner(System.in);
    VuelosController vc = null;

    public VistasReservas() throws DAOException, IOException {

    }

    public int menuPrincipalReserva() throws DAOException, IOException {
        vc = new VuelosController();
        int opcion = -1; //opcio -1 indica opcion incorrecta
        while (opcion == -1) {
            System.out.println("Introduzca la opción a realizar");
            System.out.println("0.Salir");
            System.out.println("1.Nueva Reserva");
            System.out.println("2.Ver Reservas");
            System.out.println("3.Ver reservas de un Vuelo");
            System.out.println("4.Modificar Reserva");
            System.out.println("5.Generar tarjetas de embarque de una reserva");
            System.out.println("6.Cancelar Reserva");

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
    }//Fin menuPrincipal

    public Reserva mostrarInsertarReserva(List<Reserva> lReservas) throws IOException, DAOException {

        List<Pasajero> listaPasajeros = new ArrayList<Pasajero>();

        String numEntero;
        Vuelo v = null;
        double numDec = -1;
        int idReserva = -1;
        boolean repetida = false;

        //IdReserva
        do {
            //Mostrar lista de idReserva
            repetida=false;
            System.out.println("Introduzca la id de reserva. (0 salir)");

            numEntero = sc.nextLine();

            if (!esEntero(numEntero)) {
                System.out.println("Debe introducir un numero entero");
            }
            idReserva = Integer.parseInt(numEntero);
            if (idReserva == 0) {
                //salimos 
                return null;
            }

            if (!idRepetida(lReservas, idReserva)) {
                System.out.println("La id de reserva ya está creada");
                repetida = true;
            }

        } while (!esEntero(numEntero) || idReserva <= 0 || repetida);

        //Fecha Reserva se obtiene la del sistema

        Date f = new Date();
        f.getTime();

        //cancelada
        boolean cancelada = false;

        //----------------------Importe--------------------
        double importe = 0;
        String codVuelo;

        //Codigo vuelo
        do {
            //Mostrar lista de codigo de vuelos y metodo de coprobar que exista
            System.out.println("Introduzca el codigo del vuelo (0 salir)");

            codVuelo = sc.nextLine();
            if (codVuelo.equals("0")) {
                //salimos 
                return null;
            }
            if (codVuelo.length() != 8) {
                // se establece que el codigo debe tener 8 caracteres
                System.out.println("El codigo de vuelo debe tener 8 caracteres");
            }
            v = vc.obtenerVueloParaReserva(codVuelo);

        } while (codVuelo.length() != 8);


        //Creo el acceso a vistas pasajeros
        VistasPasajero vp = new VistasPasajero();
        //Añado el pasajero al ArrayList creado al principio del metodo para insertarlos mas adelante en la reserva
        //listaPasajeros.add(vp.crearPasajero(listaPasajeros,lReservas));
        //Al final de crear pasajeros se pregunta si se desea mas pasajeros, si se dice que si se repite la creacion de pasajeros
        //Al terminar los inserta en reservas
        listaPasajeros=vp.opcionPasajeros(listaPasajeros, lReservas);
        /*while (introducirMasPasajeros()) {
            listaPasajeros.add(vp.crearPasajero(listaPasajeros,lReservas));
        }
*/
        //Insertar pasajeros
        return new Reserva(idReserva, f, v, listaPasajeros);

       //objeto reserva completo
    }


    public String introducirCodigoReservaParaMostrar() {
        System.out.println("Introduce un código de vuelo: ");

        String codVuelo = sc.nextLine();
        return codVuelo;
    }

    public void mostrarReserva(Reserva r) {
        if (r == null) {
            System.out.println("El código de vuelo que has introducido no existe. \n");
        } else {
            String fechaStr;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            fechaStr = sdf.format(r.getFecha_res());
            System.out.println("--------------- Reserva ---------------------------------------------");
            System.out.println("CODIGO   |  FECHA SALIDA    |  CANCELADA   |   COD/VUELO    |   IMPORTE");
            System.out.printf("%-8s |  %-10s      |  %-5s       |   %-8s     |  %8.2f"
                    + "    \n", r.getIdReserva(), fechaStr, r.isCancelada(), r.getVuelo().getCodigo(), r.getImporte());
        }
    }//fin mostrarReserva

    public void mostrarReservas(List<Reserva> reservas) {
        System.out.println("--------------- Reservas ---------------------------------------------");
        System.out.println("CODIGO   |  FECHA SALIDA    |  CANCELADA   |   COD/VUELO    |   IMPORTE");
        String fechaStr;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Reserva r : reservas) {
            fechaStr = sdf.format(r.getFecha_res());
            //como formatear salida de texto en java
            System.out.printf("%-8s |  %-10s      |  %-5s       |   %-8s     |  %8.2f"
                    + "    \n", r.getIdReserva(), fechaStr, r.isCancelada(), r.getVuelo().getCodigo(), r.getImporte());
        }

    }//fin mostrarReservas

    public void mostrarIdPasajeros(List<Pasajero> pasajeros) {
        System.out.println("Id de los pasajeros de la reserva:");
        String salida = "";
        for (Pasajero p : pasajeros) {
            salida += "    " + p.getIdPasajero();
        }
        System.out.println(salida);
    }//fin mostrarIdPasajeros


    private static boolean esEntero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }//Fin esEntero

    private static boolean esDecimal(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }//Fin esDecimal

    private static Date obtenerFecha(String cadena) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        try {
            fecha = sdf.parse(cadena);
            return fecha;
        } catch (ParseException ex) {
            // si no es fecha devolvemos un null
            return null;
        }
    }//Fin obtenerFecha

    public void mostrarError(String mensaje) {
        System.out.println(mensaje);
    }//Fin mostrarError

    /*public boolean introducirMasPasajeros() {

        boolean salir = false;
        boolean masPasajeros= false;
        String op;
        do {
            System.out.println("Deseas introducir más pasajeros? SI=Y, NO=N");
            op = sc.nextLine();
            if (op.equals("Y") || op.equals("y")) {
                salir = true;
                return true;
            } else if (op.equals("N") || op.equals("n")) {
                salir = true;
                return false;
            } else {
                salir = false;
            }
        } while (!salir == false);
        return salir;
    }//fin
*/
    public boolean idRepetida(List<Reserva> reservas, int idReserva) {

        for (Reserva r : reservas) {
            if (idReserva == r.getIdReserva()) {
                return false;
            }
        }
        return true;
    }
}

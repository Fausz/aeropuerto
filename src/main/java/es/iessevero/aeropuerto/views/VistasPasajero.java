/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iessevero.aeropuerto.views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import es.iessevero.aeropuerto.clases.*;

import javax.print.DocFlavor;

/**
 * @author F
 */
public class VistasPasajero {
    static Scanner sc = new Scanner(System.in);

    public Pasajero crearPasajero(List<Pasajero> pasajeros,List<Reserva> reservas) {
        Pasajero p = null;
        double numDec = -1;
        int numEntero = -1;
        int idPasajero = -1;
        String idPass;
        int idReserva=0;

        String solo;
        String silla;
        boolean salir=false;
        boolean vsolo=false;
        boolean usilla=false;
        int maletas = -1;
        boolean idRepetida=false;


        //Id Pasajero
        do {
            idRepetida=false;
            //Mostrar lista de idPasajeros
            System.out.println("Introduzca el id de pasajero. (0 salir)");
            idPass = sc.nextLine();



            if (!esEntero(idPass)) {
                System.out.println("Debe introducir un numero");

            }
                idPasajero = Integer.parseInt(idPass);
            if (idPasajero==0){
                //salimos
                return null;
            }
            if(pasajeros!=null) {
                for(Pasajero pa : pasajeros){
                    if(idPasajero==pa.getIdPasajero()){
                        System.out.println("ID Pasajero repetida, introduce una nueva");
                        idRepetida=true;
                    }
                }
            }
                for(Reserva re: reservas){
                    for(Pasajero pas:re.getPasajeros()) {
                        if (idPasajero == pas.getIdPasajero()) {
                            System.out.println("ID Pasajero repetida, introduce una nueva");
                            idRepetida = true;
                        }
                    }
                }



        } while (!esEntero(idPass) || idPasajero <= 0 || idRepetida);

        //Dni Pasajero
        String dni;

        boolean valido = false;
        do {

            System.out.println("Introduzca el DNI del pasajero sin letras (0 salir)");

            dni = sc.nextLine();
            if (dni.length() != 10) {
                System.err.println("El dni del pasajero ha de tener 10 carácteres");
            }
            if (dni.equals("0")) {
                //salimos 

                return null;
            }


        } while (dni.length() != 10);

        //Nombre pasajero
        String nombre;
        do {
            System.out.println("Introduzca el Nombre del pasajero (0 salir)");

            nombre = sc.nextLine();
            if (nombre.equals("0")) {
                //salimos

                return null;

            }
            if (nombre.length() > 20) {

                System.out.println("El nombre debe tener menos de 50 caracteres");
            }
        } while (nombre.length() > 20);

        //Apellidos Pasajero
        String apellidos;
        do {
            System.out.println("Introduzca los apellidos del pasajero (0 salir)");

            apellidos = sc.nextLine();
            if (nombre.equals("0")) {
                //salimos

                return null;

            }
            if (apellidos.length() > 50) {

                System.out.println("El apellido debe tener menos de 50 caracteres");
            }
        } while (apellidos.length() > 50);

        //Fecha de nacimiento de pasajero
        Date fn = null;
        String fecha;
        do {
            System.out.println("Introduzca la fecha de nacimiento del pasajero (dd/MM/yyyy) 0 para salir");

            fecha = sc.nextLine();
            if (fecha.equals("0")) {
                //salimos

                return null;
            }
            fn = obtenerFecha(fecha);
            if (fn == null) {
                System.out.println("El formato de fecha no es valido");
            }

        } while (fn == null);
        //Numero de maletas de pasajero
        String numMaletas;
        boolean salir2=false;
        do {
            System.out.println("Introduzca el numero de maletas. (0 salir)");

            numMaletas = sc.nextLine();
            if (!esEntero(numMaletas)) {
                System.out.println("Debe introducir un numero");

            } else {
                maletas = Integer.parseInt(numMaletas);
                //salir2=true;
            }
            if (maletas == 0) {
                //salimos
                //salir2=true;
                return null;
            }

        } while (!esEntero(numMaletas) || maletas <= 0 /*|| !salir2*/);

        //Si es niño:
        if (esNinyo(fn)) {
            System.out.println("El pasajero será tomado como un niño ya que es menor de 12 años");
            //Viaja solo?
            do {
                System.out.println("¿Viaja solo el niño? SI=Y, NO=N");
                solo = sc.nextLine();
                if (solo.equals("Y") || solo.equals("y")) {
                    vsolo=true;
                    salir = true;

                } else if (solo.equals("N") || solo.equals("n")) {

                    salir = true;

                } else if ("0".equals(solo)) {

                    salir = true;
                    return null;
                } else {
                    salir = false;
                }
            } while (!salir);

            //Necesita silla?
            do {
                System.out.println("¿Necesita silla el niño? SI=Y, NO=N");
                silla = sc.nextLine();
                if (silla.equals("Y") || silla.equals("y")) {
                    usilla=true;
                    salir = true;

                } else if (silla.equals("N") || silla.equals("n")) {

                    salir = true;

                } else if ("0".equals(silla)) {

                    salir = true;
                    return null;
                } else {
                    salir = false;
                }
            } while (!salir);
            return new Ninyo(idPasajero, dni, nombre, apellidos, fn, maletas, idReserva, vsolo, usilla);
        } else {
            //Si es adulto
            System.out.println("El pasajero será tomado como un adulto ya que es mayor de 12 años");

            Descuentos descuento = this.pedirDescuento();
            System.out.println(descuento);

            if (descuento == null) {
                return null;
            }
            return new Adulto(idPasajero, dni, nombre, apellidos, fn, maletas,idReserva, descuento);
        }
    }//fin crearPasajero

    public static boolean esNinyo(Date fechaNacimiento) {
        Calendar fechaDeCorte = Calendar.getInstance();
        Calendar fechaNacimientoCalendar = Calendar.getInstance();
        fechaNacimientoCalendar.setTime(fechaNacimiento);
        fechaDeCorte.setTime(new Date());
        fechaDeCorte.add(Calendar.YEAR, -12);
        return fechaDeCorte.before(fechaNacimientoCalendar);
    }

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

    public Descuentos pedirDescuento() {
        Descuentos[] descuentos = Descuentos.values();
        List<Descuentos> descuentosAElegir = new ArrayList<>();
        for (Descuentos d : descuentos) {
            if (d.equals(Descuentos.RES_ANT)) {
                continue;
            }
            descuentosAElegir.add(d);
        }
        String respuesta;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("¿Que tipo de descuento tiene?");
            for (int i = 1; i <= descuentosAElegir.size(); i++) {
                System.out.println(i + "-" + descuentosAElegir.get(i - 1));
            }
            respuesta = sc.nextLine();
            if (esEntero(respuesta)) {
                int opcion = Integer.parseInt(respuesta);
                if (opcion == 0) {
                    return null;
                }
                if (opcion == 1 || opcion == 2) {
                    return descuentosAElegir.get(opcion - 1);
                }
            }
        } while (true);
    }//fin pedirDescuento

    public List<Pasajero> opcionPasajeros(List <Pasajero> pasajeros, List<Reserva> reservas){
        pasajeros.add(this.crearPasajero(pasajeros,reservas));
        boolean salir=false;
        String op;
        int opcionPasajero=-1;

        VistasPasajero vp = new VistasPasajero();
        do{
            System.out.println("Selecciona la siguiente opción a realizar: \n" +
                    "0 - No introducir más pasajeros a esta reserva\n" +
                    "1 - Introducir más pasajeros a esta reserva\n" +
                    "2 - Eiminar pasajeros a esta reserva");
            op=sc.nextLine();
            if (!esEntero(op)) {
                System.out.println("Debe introducir un numero entero");
            }
            opcionPasajero = Integer.parseInt(op);
            if (opcionPasajero == 0) {
                //salimos
                salir=true;
            }else if(opcionPasajero==1){
                pasajeros.add(vp.crearPasajero(pasajeros,reservas));
                salir=false;
            }else{
                pasajeros=eliminarPasajero(pasajeros);
                salir=false;
            }

        }while(!salir);
        return pasajeros;
    }
    /*System.out.println("--------------- Pasajeros ---------------------------------------------");
       System.out.println("ID   |  DNI    |  NOMBRE   |   APELLIDOS    |   FECHA_NAC    |    MALETAS    |    ID_RESERVA");
       */
    public List<Pasajero> eliminarPasajero(List<Pasajero> pasajeros) {
        String idPas;
        int id;
        boolean salir=false;
        do {
            for(Pasajero p : pasajeros){
                System.out.println(p.getIdPasajero()+" : "+p.getNombre());
            }
            System.out.println("Introduce la id del pasajero que desea eliminar");
            idPas = sc.nextLine();
            if (!esEntero(idPas)) {
                System.out.println("Debe introducir un numero entero");
            }
            id=Integer.parseInt(idPas);
            if(id==0){
                salir=true;
            }

            for(Pasajero p : pasajeros){
                if(p.getIdPasajero()==id){


                    pasajeros.remove(p);
                    salir=true;

                }
            }
        }while(!salir);
        return pasajeros;
    }

}

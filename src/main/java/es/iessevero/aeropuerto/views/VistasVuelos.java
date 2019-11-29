/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iessevero.aeropuerto.views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import es.iessevero.aeropuerto.clases.Vuelo;

/**
 *
 * @author F
 */
public class VistasVuelos {
    
    static Scanner sc = new Scanner(System.in);
    
    
    
    public int menuPrincipalVuelo() {
        int opcion = -1; //opcio -1 indica opcion incorrecta
        while (opcion == -1) {
            System.out.println("Introduzca la opción a realizar");
            System.out.println("0.Salir");
            System.out.println("1.Crear Vuelo");
            System.out.println("2.Modificar Vuelo");
            System.out.println("3.Eliminar Vuelo");
            System.out.println("4.Obtener un Vuelo");
            System.out.println("5.Obtener todos los vuelos");
            
            
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
    
    public static String obtenerCodigoVueloModificar(ArrayList<String> cod){
        System.out.println(cod);
        System.out.println("Introduce el código de vuelo para modificar");
        
        String codVuelo=sc.nextLine();
        return codVuelo;
    }//Fin obtenerCodigoVueloModificar
    
    public static int menuModificarVuelo(){
        int opcion = -1; //opcio -1 indica opcion incorrecta
        while (opcion == -1) {
            System.out.println("Elige opción del menú para modificar vuelo");
            System.out.println("0.Salir");
            System.out.println("1.Origen");
            System.out.println("2.Destino");
            System.out.println("3.Precio Persona");
            System.out.println("4.Fecha Vuelo");
            System.out.println("5.Plazas disponibles");
            
            
             
            String entrada = sc.nextLine();
            if (!esEntero(entrada)) {
                opcion = -1;
            } else {
                opcion = Integer.parseInt(entrada);
            }
            if (opcion >= 6 || opcion < 0) {
                System.out.println("Opcion No valida");
                opcion = -1;

            }

        }
        return opcion;
    }//Fin menuModificarVuelo
    
    
    public static String pedirModificacionOrigenVuelo(){
        String entrada;
        boolean salir=false;
        do {
            System.out.println("Introduzca aeropuerto de origen (0 salir)");
            
            entrada = sc.nextLine();
            if (entrada.equals("0")) {
                //salimos 
                salir=true;
            }
            if (entrada.length() >5) {
                // se establece que el aeropuerto debe tener 3 caracteres
                System.out.println("El origen debe tener 5 caracteres");
            }
            
        } while (entrada.length() >5||salir);
        return entrada;
    }//Fin pedirModificacionOrigenVuelo
    
    public static String pedirModificacionDestinoVuelo(){
        String entrada;
        boolean salir=false;
        do {
            //destino
            System.out.println("Introduzca el aeropuerto del destino (0 salir)");
            
            entrada = sc.nextLine();
            if (entrada.equals("0")) {
                //salimos 
                salir = true;
            }
            if (entrada.length() >5) {
                // se establece que el destino debe tener 8 caracteres
                System.out.println("El destino debe tener como maximo 5 caracteres");
            }
            
        } while (entrada.length() >5);
        return entrada;
    }//Fin pedirModificacionDestinoVuelo
    
    public static double pedirModificacionPrecioPersonaVuelo(){
        String entrada;
        double numDec=-1;
        boolean salir=false;
        do {
            
            System.out.println("Introduzca el nuevo precio. (0 salir)");
            
            entrada = sc.nextLine();
            if (numDec == 0) {
                //salimos 
                salir=true;
            }
            if (!esDecimal(entrada)) {
                System.out.println("El precio debe ser un número");;
            } else {
                numDec = Double.parseDouble(entrada);
            }
            
            
        } while (!esDecimal(entrada)||numDec<=0||salir);
        return numDec;
    }//Fin pedirModificacionPrecioPersonaVuelo
    
    public static Date pedirModificacionFechaVuelo(){
        String entrada;
        Date f = null;
        boolean salir=false;
        do {
            System.out.println("Introduzca la fecha de salida del vuelo (dd/MM/yyyy) 0 para salir");
            
            entrada = sc.nextLine();
            if (entrada.equals("0")) {
                //salimos 
                salir=true;
            }
            f = obtenerFecha(entrada);
            if (f == null) {
                System.out.println("El formato de fecha no es valido");
            }
           
        } while (f == null||salir);
        return f;
    }//Fin pedirModificacionFechaVuelo
    
    public static int pedirModificacionPlazasDisponibles(){
        String entrada;
        boolean salir=false;
        int numEntero=-1;
        do {
            System.out.println("Introduzca el numero de plazas. (0 salir)");
            
            entrada = sc.nextLine();
            if (!esEntero(entrada)) {
                System.out.println("Debe introducir un numero");;
            } else {
                numEntero = Integer.parseInt(entrada);
            }
            if (numEntero == 0) {
                //salimos 
                salir=true;
            }

        } while (!esEntero(entrada)||numEntero<=0||salir);
        return numEntero;
    }//Fin pedirModificacionPlazasDisponibles
    
    public static String obtenerVueloEliminar(ArrayList<String> cod){
        System.out.println(cod);
        System.out.println("Introduce el código de vuelo para eliminar");
        String codVuelo=sc.nextLine();
        return codVuelo;
    }//Fin obtenerVueloEliminar
    
    public void mostrarVuelos(List<Vuelo> vuelos) {
        System.out.println("--------------- Vuelos -------------");
        System.out.println("CODIGO   |  ORIGEN    |  DESTINO   |    P/PERSONA | FECHA       |        PLAZAS_DISP   TERMINAL   PUERTA");
        String fechaStr;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Vuelo v : vuelos) {
            fechaStr = sdf.format(v.getFecha_vuelo());
            //como formatear salida de texto en java
            System.out.printf("%-8s |  %-7s   |   %-7s  |  %8.2f    | %-10s  |        %-12d  %-8d   %-3d\n", v.getCodigo(), v.getOrigen(), v.getDestino(),v.getPrecio_persona(),fechaStr, v.getPlazas_disponibles(), v.getTerminal(), v.getPuerta());
        }
    }//Fin mostrarVuelos
    
    public String introducirCodigoMostrarVuelo(ArrayList<String> cod){

            System.out.println(cod);

        System.out.println("Introduce un código de vuelo: ");
        
        String codVuelo=sc.nextLine();
        return codVuelo;
    }//Fin introducirCodigoMostrarVuelo
    
    public void mostrarVuelo(Vuelo v){
        System.out.println("--------------- Vuelos ----------------------------------------------------------------------------------");
        System.out.println("CODIGO   |  ORIGEN    |  DESTINO   |    P/PERSONA | FECHA       |        PLAZAS_DISP  |  TERMINAL  |   PUERTA");
        String fechaStr;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        fechaStr = sdf.format(v.getFecha_vuelo());
        System.out.printf("%-8s |  %-7s   |   %-7s  |  %8.2f    | %-10s  |        %-12d | %-8d   |   %-3d\n", v.getCodigo(), v.getOrigen(), v.getDestino(),v.getPrecio_persona(),fechaStr, v.getPlazas_disponibles(), v.getTerminal(), v.getPuerta());
        
    }//Fin mostrarVuelo
    
    public void mostrarError(String mensaje) {
        System.out.println(mensaje);
    }//Fin mostrarError
    
    public Vuelo mostrarInsertarVuelo(){
        Vuelo v=new Vuelo();
        String entrada;
        double numDec=-1;
        int numEntero=-1;
        //codigo del vuelo
        do {
            System.out.println("Introduzca el codigo del vuelo (0 salir)");
            
            
            entrada = sc.nextLine();
            if (entrada.equals("0")) {
                //salimos 
                return null;
            }
            if (entrada.length() != 8) {
                // se establece que el codigo debe tener 8 caracteres
                System.out.println("El codigo de vuelo debe tener 8 caracteres");
            }
            v.setCodigo(entrada);
        } while (entrada.length() != 8);
        //origen
        do {
            System.out.println("Introduzca aeropuerto de origen (0 salir)");
           
            entrada = sc.nextLine();
            if (entrada.equals("0")) {
                //salimos 
                return null;
            }
            if (entrada.length() >5) {
                // se establece que el aeropuerto debe tener 3 caracteres
                System.out.println("El origen debe tener 5 caracteres");
            }
            v.setOrigen(entrada);
        } while (entrada.length() >5);
        
        do {
            //destino
            System.out.println("Introduzca el aeropuerto del destino (0 salir)");
            
            entrada = sc.nextLine();
            if (entrada.equals("0")) {
                //salimos 
                return null;
            }
            if (entrada.length() >5) {
                // se establece que el destino debe tener 8 caracteres
                System.out.println("El destino debe tener como maximo 5 caracteres");
            }
            v.setDestino(entrada);
        } while (entrada.length() >5);
        //precio de vuelo
        do {
            System.out.println("Introduzca el precioScanner. (0 salir)");
            
            entrada = sc.nextLine();
            
            if (!esDecimal(entrada)) {
                System.out.println("El precio debe ser un número");;
            } else {
                numDec = Double.parseDouble(entrada);
            }
            if (numDec == 0) {
                //salimos 
                return null;
            } else {
                v.setPrecio_persona(numDec);
            }

        } while (!esDecimal(entrada)||numDec<=0);
        
        //fecha de salida
        Date f = null;
        do {
            System.out.println("Introduzca la fecha de salida del vuelo (dd/MM/yyyy) 0 para salir");
            
            entrada = sc.nextLine();
            if (entrada.equals("0")) {
                //salimos 
                return null;
            }
            f = obtenerFecha(entrada);
            if (f == null) {
                System.out.println("El formato de fecha no es valido");
            }
            v.setFecha_vuelo(f);
        } while (f == null);
        
        //numero de plazas
        do {
            System.out.println("Introduzca el numero de plazas. (0 salir)");
            
            entrada = sc.nextLine();
            if (!esEntero(entrada)) {
                System.out.println("Debe introducir un numero");;
            } else {
                numEntero = Integer.parseInt(entrada);
            }
            if (numEntero == 0) {
                //salimos 
                return null;
            } else {
                v.setPlazas_disponibles(numEntero);
            }

        } while (!esEntero(entrada)||numEntero<=0);
        
        return v;
    }//fin mostrarInsertarVuelo
   
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
    }
    //Fin esDecimal
    
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
    
   
    
    
}

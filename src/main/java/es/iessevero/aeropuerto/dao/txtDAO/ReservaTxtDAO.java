/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iessevero.aeropuerto.dao.txtDAO;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import es.iessevero.aeropuerto.clases.*;
import es.iessevero.aeropuerto.dao.aeropuerto.dao.DAOException;
import es.iessevero.aeropuerto.dao.aeropuerto.dao.IReservaDAO;

/**
 * @author F
 */
public class ReservaTxtDAO implements IReservaDAO {

    private String archivoReservas = null;
    private VueloTxtDAO vdao = null;//creo un objeto de tipoVueloTxtDAO vacio
    private String archivoPasajeros = null;

    public ReservaTxtDAO() throws IOException, DAOException {
        //Metodo para volvar los datos al txt
        try {
            Properties pro = new Properties();
            pro.load(this.getClass().getResourceAsStream("/configuracion.properties"));
            this.archivoReservas = pro.getProperty("reservasTxt");
            this.archivoPasajeros = pro.getProperty("pasajerosTxt");
            vdao = new VueloTxtDAO();

        } catch (IOException e) {
            throw new DAOException("Ha habido un problema al obtener el archivo de configuracion", e);
        }
    }


    @Override
    public void modificarReserva(Reserva r) throws DAOException {
        /**
         * Modificar una reserva en el dao
         *
         * @param r: reserva modificad
         * @throws DAOException
         */
        List<Reserva> lista_reservas = this.volcarTxtEnReservas();
        int reservaModificar = -1;
        for (Reserva rFor : lista_reservas) {  // comprueba todas las reservas del txt
            if (r.getIdReserva() == rFor.getIdReserva()) {   // busca la reserva a modificar
                reservaModificar = lista_reservas.indexOf(rFor);    // posicion (indice) de la reserva
            }
        }

        // eliminamos los pasajeros de la reserva

        //OBTENGO PASAJEROS A ELIMINAR
        /*List <Pasajero> pasajeroEliminar=*/this.eliminarPasajerosReserva(reservaModificar);
        //vuelos.remove(vBorrar);
        //this.volcarVuelosEnTxt(vuelos);


        // guardamos los nuevos pasajeros

        /*for (Pasajero p : r.getPasajeros()) {
            idaoPasajero.nuevoPasajero(p);
        }*/

        lista_reservas.set(reservaModificar, r);    // en la posicion de reservaModificar inserta la nueva
        this.volcarReservasEnTxt(lista_reservas);   // guarda la lista nueva
    }
    private /*List <Pasajero>*/ void eliminarPasajerosReserva(int idReserva) throws DAOException {
        List<Pasajero> pasajeros=this.volcarTxtEnPasajeros();
        List<Pasajero> pasajerosEliminar=new ArrayList<>() ;

        for(Pasajero p : pasajeros) {
            if (idReserva != p.getIdReserva()){
                pasajerosEliminar.add(p);
            }
        }
        pasajeros.remove(pasajerosEliminar);
        //this.volcarPasajerosEnTxt(pasajeros);
        //return pasajerosNuevo;
    }
    private List<Reserva> volcarTxtEnReservas() throws DAOException {
        /**
         * Metodo para obtener los datos del txt y guardarlos en un objeto
         */
        List<Reserva> reservas = new ArrayList<Reserva>();
        File archivo = null;//archivo fisico
        FileReader frR = null;//clase que accede al archivo fisico
        BufferedReader brR = null;//lee el archivo

        try {

            archivo = new File(archivoReservas);//variable clase Vuelo
            frR = new FileReader(archivo);
            brR = new BufferedReader(frR);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
            Vuelo v;
            String linea;
            while ((linea = brR.readLine()) != null) {//si hay linea nula
                Reserva r = null;
                String[] str = linea.split("#");//devuelve un array de strings  separando por #
                int idReserva = Integer.parseInt(str[0]);//primer elemento
                Date d = sdf.parse(str[1]);
                boolean cancelada = Boolean.parseBoolean(str[2]);
                String codVuelo = str[3];
                double importe = Double.parseDouble(str[4]);
                //asigno al objeto vuelo el valor del metodo de obtener vuelo
                v = vdao.obtenerVuelo(codVuelo);
                List<Pasajero> pasajeros = new ArrayList<Pasajero>();
                try {
                    BufferedReader brP = new BufferedReader(new FileReader(archivoPasajeros));
                    String pasajero;
                    Pasajero p = null;
                    // PASAJEROS
                                    /*IPasajeroDAO idao = ServicioPrincipal.getServicio().getIdaoPasajero();
                                    ArrayList<Pasajero> lista_pasajeros = idao.obtenerPasajerosReserva(r.getId());
                                    */
                    while ((pasajero = brP.readLine()) != null) {
                        String[] datosPasajero = pasajero.split("#");
                        int idReservaPasajero = Integer.parseInt(datosPasajero[6]);
                        if (idReserva == idReservaPasajero) {

                            int id = Integer.parseInt(datosPasajero[0]);

                            String dni = datosPasajero[1];

                            String nombre = datosPasajero[2];

                            String apellido = datosPasajero[3];

                            Date fechaNacimiento = sdf.parse(datosPasajero[4]);

                            int numeroMaletas = Integer.parseInt(datosPasajero[5]);
                            //String
                            if (datosPasajero[7].equals("N")) {
                                boolean solo = Boolean.parseBoolean(datosPasajero[8]);
                                boolean silleta = Boolean.parseBoolean(datosPasajero[9]);
                                pasajeros.add(new Ninyo(id, dni, nombre, apellido, fechaNacimiento, numeroMaletas,idReservaPasajero, solo, silleta));
                            } else {
                                Descuentos de;
                                if ("N".equals(datosPasajero[8])) {
                                    de = Descuentos.NINGUNO;

                                }else if("I".equals(datosPasajero[8])){
                                    de=Descuentos.RESIDENTE_ISLA;
                                }else{
                                    de=Descuentos.RES_ANT;
                                }
                                pasajeros.add(new Adulto(id, dni, nombre, apellido, fechaNacimiento, numeroMaletas,idReservaPasajero, de));
                            }
                        }
                    }
                } catch (Exception e) {
                    throw new DAOException(
                            "Ha habido un problema al obtener los pasajeros de la reserva: " + idReserva, (IOException) e);
                }



                reservas.add(new Reserva(idReserva, d, cancelada, v, importe, pasajeros));

            }

            return reservas;

        } catch (Exception e) {
            throw new DAOException(
                    "Ha habido un problema al obtener las reservas desde el archivo de texto:",
                    e);
        } finally {
            try {
                if (null != frR) {
                    frR.close();
                }
            } catch (Exception e2) {
                throw new DAOException(
                        "Ha habido un problema al cerrar el archivo de texto",
                        e2);
            }
        }

    }
    private List<Pasajero> volcarTxtEnPasajeros() throws DAOException {
        List<Pasajero> pasajeros = new ArrayList<Pasajero>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
            BufferedReader brP = new BufferedReader(new FileReader(archivoPasajeros));
            String pasajero;
            Pasajero p = null;
            // PASAJEROS
                                    /*IPasajeroDAO idao = ServicioPrincipal.getServicio().getIdaoPasajero();
                                    ArrayList<Pasajero> lista_pasajeros = idao.obtenerPasajerosReserva(r.getId());
                                    */
            while ((pasajero = brP.readLine()) != null) {
                String[] datosPasajero = pasajero.split("#");
                int idReservaPasajero = Integer.parseInt(datosPasajero[6]);

                    int id = Integer.parseInt(datosPasajero[0]);

                    String dni = datosPasajero[1];

                    String nombre = datosPasajero[2];

                    String apellido = datosPasajero[3];

                    Date fechaNacimiento = sdf.parse(datosPasajero[4]);

                    int numeroMaletas = Integer.parseInt(datosPasajero[5]);
                    //String
                    if (datosPasajero[7].equals("N")) {
                        boolean solo = Boolean.parseBoolean(datosPasajero[8]);
                        boolean silleta = Boolean.parseBoolean(datosPasajero[9]);
                        pasajeros.add(new Ninyo(id, dni, nombre, apellido, fechaNacimiento, numeroMaletas,idReservaPasajero, solo, silleta));
                    } else {
                        Descuentos de;
                        if ("N".equals(datosPasajero[8])) {
                            de = Descuentos.NINGUNO;

                        } else if ("I".equals(datosPasajero[8])) {
                            de = Descuentos.RESIDENTE_ISLA;
                        } else {
                            de = Descuentos.RES_ANT;
                        }
                        pasajeros.add(new Adulto(id, dni, nombre, apellido, fechaNacimiento, numeroMaletas,idReservaPasajero, de));
                    }

            }
        } catch (Exception e) {
            throw new DAOException(
                    "Ha habido un problema al obtener los pasajeros de la reserva: " , (IOException) e);
        }
        return pasajeros;
    }
    private void volcarReservasEnTxt(List<Reserva> reservas) throws DAOException {
        /*FileWriter ficheroR = null;
        PrintWriter pwR = null;
        FileWriter ficheroP = null;
        PrintWriter pwP = null;*/
        try (PrintWriter pwR = new PrintWriter(new FileWriter(archivoReservas, false))) {
            try (PrintWriter pwP = new PrintWriter(new FileWriter(archivoPasajeros, false))) {
                //try {
                //fichero = new FileWriter(archivoReservas);
                //pw = new PrintWriter(fichero);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");

                for (Reserva r : reservas) {
                    String codVuelo = r.getVuelo().getCodigo();
                    String fechaStr = sdf.format(r.getFecha_res());
                    String cadenaReserva = r.getIdReserva() + "#" + fechaStr + "#"
                            + r.isCancelada() +
                            "#"+codVuelo+"#" + r.getImporte();
                    //Escribo la cadena en el archivo
                    pwR.println(cadenaReserva);

                    for(Pasajero p : r.getPasajeros()){
                        String fechaNacStr = sdf.format(p.getFecha_nac());
                        String cadenaPasajero= p.getIdPasajero()+ "#" +p.getDni()+ "#" +
                            p.getNombre()+ "#" +p.getApellidos()+ "#" + fechaNacStr + "#" +
                            p.getNum_maletas()+ "#" + r.getIdReserva();
                        if (p instanceof Ninyo) {
                            cadenaPasajero +="#N"+"#" + ((Ninyo) p).isSolo() + "#" + ((Ninyo) p).isSilleta();
                        } else {
                            cadenaPasajero +="#A" +"#";
                            if(((Adulto) p).getDescuento()==Descuentos.NINGUNO){
                                cadenaPasajero+="N";
                            }else if(((Adulto) p).getDescuento()==Descuentos.RESIDENTE_ISLA){
                                cadenaPasajero+="I";
                            }else{
                                cadenaPasajero+="PP";
                            }

                        }
                        pwP.println(cadenaPasajero);
                    }
                }
            } catch (Exception e) {
                throw new DAOException(
                        "Ha habido un problema al guardar las reservas en el archivo de texto:",
                        e);
            }
            //-----------------------------
        } catch (Exception e) {
            throw new DAOException(
                    "Ha habido un problema al guardar las reservas en el archivo de texto:",
                    e);

        }
    }

    /*public void volcarReservasEnTxt(List<Reserva> reservas) throws IOException, DAOException{
        try {
            PrintWriter pwR = new PrintWriter(new FileWriter(reservas, false));
                
        try {
            PrintWriter pwP = new PrintWriter(new FileWriter(pasajeros, false))
                }) {
                
                    for (Reserva r : reservas) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        String fechaStr = sdf.format(r.getFecha_res());
                        String reserva = r.getIdReserva() + "#" + fechaStr + "#" + r.isCancelada()
                                + "#" + r.getVuelo().getCodigo() + "#" + r.getImporte();

                        pwR.println(reserva);
                    
                        for (Pasajero p : r.getPasajeros()) {
                            String fechaPasajero = sdf.format(p.getFecha_nac());
                            String pasajero = r.getIdReserva() + "#" + p.getIdPasajero() + "#" + p.getDni() + "#"
                                    + p.getNombre() + "#" + p.getApellidos() + "#"
                                    + fechaPasajero + "#" + p.getNum_maletas();
                            
                            pwP.println(pasajero);
                        }
                        }
        }catch(Exception e) {
                    throw new DAOException(
                            "Ha habido un problema al guardar los pasajeros en el archivo de texto:", (IOException) e);
                 
        }
        
        }
    }*/
    public void crearReserva(Reserva r) throws DAOException {
        List<Reserva> reservas = this.obtenerTodasReservas();
        reservas.add(r);
        this.volcarReservasEnTxt(reservas);
    }



    public Reserva obtenerReserva(String codVuelo) throws DAOException {
        List<Reserva> reservas = this.obtenerTodasReservas();

        for (Reserva r : reservas) {
            if (r.getVuelo().getCodigo().equals(codVuelo)) {
                return r;
            }
        }
        return null;
    }

    public boolean eliminarReserva(int id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Reserva> obtenerTodasReservas() throws DAOException {
        return this.volcarTxtEnReservas();
    }

    public List<Reserva> obtenerTodasReservas(Date d) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Reserva> obtenerTodasReservas(String idVuelo) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Reserva obtenerReservaPorCodigo(int codReserva) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}

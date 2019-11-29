/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iessevero.aeropuerto.dao.txtDAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import es.iessevero.aeropuerto.dao.aeropuerto.dao.DAOException;
import es.iessevero.aeropuerto.dao.aeropuerto.dao.IVueloDAO;
import es.iessevero.aeropuerto.clases.Vuelo;

/**
 *
 * @author F
 */
public class VueloTxtDAO implements IVueloDAO {
    //creo la variable string donde se guardará los vuelos para mostrar

    private String archivoVuelos = null;

    public VueloTxtDAO() throws IOException, DAOException {
        //Metodo para volvar los datos al txt
        try {
            Properties pro = new Properties();
            pro.load(this.getClass().getResourceAsStream("/configuracion.properties"));
            this.archivoVuelos = pro.getProperty("vuelosTxt");

        } catch (IOException e) {
            throw new DAOException("Ha habido un problema al obtener el archivo de configuracion", e);
        }
    }

    @Override
    public void crearVuelo(Vuelo v) throws DAOException {
        List<Vuelo> vuelos = this.volcarTxtEnVuelos();
        vuelos.add(v);
        this.volcarVuelosEnTxt(vuelos);
    }

    @Override
    public void modificarVuelo(Vuelo v) throws DAOException {
        this.eliminarVuelo(v.getCodigo());
        this.crearVuelo(v);
    }

    @Override
    public void eliminarVuelo(String codigo) throws DAOException {
        //recorro lista
        Vuelo vBorrar=null;
        //equals
        //selecciono y remuevo
        List<Vuelo> vuelos = this.obtenerTodosVuelos();
        for (Vuelo v : vuelos) {
            if (v.getCodigo().equals(codigo)) {
                vBorrar = v;
            }

        }
        
        vuelos.remove(vBorrar);
        this.volcarVuelosEnTxt(vuelos);

    }

    @Override
    public Vuelo obtenerVuelo(String codigo) throws DAOException {
        List<Vuelo> vuelos = this.obtenerTodosVuelos();
        for (Vuelo v : vuelos) {
            if (v.getCodigo().equals(codigo)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public List<Vuelo> obtenerTodosVuelos() throws DAOException {
        return this.volcarTxtEnVuelos();
    }

    private void volcarVuelosEnTxt(List<Vuelo> vuelos) throws DAOException {
        FileWriter fichero = null;
        PrintWriter pw = null;

        try {
            fichero = new FileWriter(archivoVuelos);
            pw = new PrintWriter(fichero);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");

            for (Vuelo v : vuelos) {
                String fechaStr = sdf.format(v.getFecha_vuelo());
                String cadena = v.getCodigo() + "#" + v.getOrigen() + "#"
                        + v.getDestino() + "#" + v.getPrecio_persona()
                        + "#" + fechaStr + "#" + v.getPlazas_disponibles() + "#"
                        + v.getTerminal() + "#" + v.getPuerta();;
                pw.println(cadena);

            }

        } catch (Exception e) {
            throw new DAOException(
                    "Ha habido un problema al guardar los vuelos en el archivo de texto:",
                    e);
        } finally {
            try { // Nuevamente aprovechamos el finally para

                // asegurarnos que se cierra el fichero. if (null != fichero)
                fichero.close();
            } catch (Exception e2) {
                throw new DAOException(
                        "Ha habido un problema al cerrar el archivo de texto",
                        e2);
            }
        }
    }

    private List<Vuelo> volcarTxtEnVuelos() throws DAOException {
        /**
         * Metodo para obtener los datos del txt y guardarlos en un objeto
         */
        List<Vuelo> vuelos = new ArrayList<Vuelo>();
        File archivo = null;//archivo fisico
        FileReader fr = null;//clase que accede al archivo fisico
        BufferedReader br = null;//lee el archivo

        try {

            archivo = new File(archivoVuelos);//variable clase Vuelo 
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
            String linea;
            while ((linea = br.readLine()) != null) {//si hay linea nula

                String[] str = linea.split("#");//devuelve un array de strings  separando por #
                String codVuelo = str[0];//primer elemento
                String origen = str[1];
                String destino = str[2];
                double precio_persona = Double.parseDouble(str[3]);
                Date d = sdf.parse(str[4]);
                int plazas = Integer.parseInt(str[5]);
                int terminal = Integer.parseInt(str[6]);
                int puerta = Integer.parseInt(str[7]);
                Vuelo v = new Vuelo(codVuelo, origen, destino, precio_persona, d, plazas, terminal, puerta);

                v.setTerminal(terminal);
                v.setTerminal(puerta);
                vuelos.add(v);

            }

            return vuelos;

        } catch (Exception e) {
            throw new DAOException(
                    "Ha habido un problema al obtener los vuelos desde el archivo de texto:",
                    e);
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                throw new DAOException(
                        "Ha habido un problema al cerrar el archivo de texto",
                        e2);
            }
        }

    }

    public void pedirTerminalPuerta(String codigo) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

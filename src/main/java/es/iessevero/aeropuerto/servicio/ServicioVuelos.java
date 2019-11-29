/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iessevero.aeropuerto.servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.iessevero.aeropuerto.dao.aeropuerto.dao.DAOException;
import es.iessevero.aeropuerto.dao.aeropuerto.dao.IVueloDAO;
import es.iessevero.aeropuerto.dao.txtDAO.VueloTxtDAO;
import es.iessevero.aeropuerto.clases.Vuelo;

/**
 *
 * @author F
 */

    public class ServicioVuelos {
        //idao es donde se selecciona donde elegir el metodo de guardado
        private static IVueloDAO idao=null;
        /*public ServicioVuelos() throws DAOException, IOException{
            //idao=new VueloTxtDAO();
    }*/
        public void elegirSistemaAlmacenamiento(int opcion) throws DAOException, IOException {

        if (opcion == 1) {
            idao = new VueloTxtDAO();
        }
        /*if (opcion == 2) {

            idao = new FutbolAleatorioDAO();

        }
        if (opcion == 3) {

            idao = new FutbolXMLDAO();

        }
        if (opcion == 4) {

            idao = new FutbolXMLSaxDAO();

        }
        if (opcion == 5) {

            idao = new FutbolJDBCDAO();

        }
        if (opcion == 6) {

            idao = new FutbolHibernate();

        }*/

    }
    //Para obtener los vuelos con el tipo elegido
    public List<Vuelo> obtenerVuelos() throws DAOException{
        List<Vuelo> vuelos = idao.obtenerTodosVuelos();
        return vuelos;
    }
    public void eliminarVuelo(String codVuelo) throws DAOException{
        idao.eliminarVuelo(codVuelo);
    }
    public void modificarVuelo(String codVuelo) throws DAOException{
        Vuelo v=null;
        v=idao.obtenerVuelo(codVuelo);
        idao.modificarVuelo(v);
    }
    public void modificarVueloOrigen(String codigoVuelo, String origen)throws DAOException,ServicioException {
        Vuelo v=this.obtenerVuelo(codigoVuelo);
        v.setOrigen(origen);
        idao.modificarVuelo(v);
    }
    
    public void modificarVueloDestino(String codigoVuelo, String destino)throws DAOException,ServicioException {
        Vuelo v=this.obtenerVuelo(codigoVuelo);
        v.setDestino(destino);
        idao.modificarVuelo(v);
    }
    
    public void modificarVueloPrecio(String codigoVuelo, Double precio)throws DAOException,ServicioException {
            Vuelo v=this.obtenerVuelo(codigoVuelo);
            v.setPrecio_persona(precio);
            idao.modificarVuelo(v);
        }

    public void modificarVueloFecha(String codigoVuelo, Date fecha)throws DAOException,ServicioException {
       Vuelo v=this.obtenerVuelo(codigoVuelo);
        v.setFecha_vuelo(fecha);
        idao.modificarVuelo(v);
    }

    public void modificarVueloPlazas(String codigoVuelo, Integer plazas)throws DAOException,ServicioException {
        Vuelo v=this.obtenerVuelo(codigoVuelo);
        v.setPlazas_disponibles(plazas);
        idao.modificarVuelo(v);
    }

    public void modificarVueloTerminal(String codigoVuelo, Integer terminal)throws DAOException,ServicioException {
        Vuelo v=this.obtenerVuelo(codigoVuelo);
        v.setTerminal(terminal);
        idao.modificarVuelo(v);
    }

    public void modificarVueloPuerta(String codigoVuelo,Integer puerta)throws DAOException,ServicioException{
        Vuelo v=this.obtenerVuelo(codigoVuelo);
        v.setPuerta(puerta);
        idao.modificarVuelo(v);
    }
    public void crearVuelo(Vuelo v) throws DAOException,ServicioException, IOException{
        /**
         * crear vuelo mediante el tipo de guardado
         */
        
        Vuelo vPrueba=idao.obtenerVuelo(v.getCodigo());
        if(vPrueba!=null){
            throw new ServicioException("Ya existe un vuelo con ese código");
        }
        idao.crearVuelo(v);
    }
    public Vuelo obtenerVuelo(String codVuelo) throws DAOException{
        Vuelo v = null;
        v=idao.obtenerVuelo(codVuelo);
        return v;
       
    }
    public ArrayList<String> obtenerCodVuelos() throws DAOException {


            List<Vuelo> vuelos = idao.obtenerTodosVuelos();
        ArrayList<String> cod = new ArrayList<String>();
            for(Vuelo v: vuelos){
                cod.add(v.getCodigo());
            }
            return cod;
    }
    
}

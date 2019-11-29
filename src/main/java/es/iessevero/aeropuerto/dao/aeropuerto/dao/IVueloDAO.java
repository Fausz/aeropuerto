/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iessevero.aeropuerto.dao.aeropuerto.dao;

import java.util.List;
import es.iessevero.aeropuerto.clases.Vuelo;

/**
 *
 * @author F
 */
public interface IVueloDAO {
    public void crearVuelo(Vuelo v) throws DAOException;
    public void modificarVuelo(Vuelo v) throws DAOException;
    public void eliminarVuelo(String codigo) throws DAOException;
    public Vuelo obtenerVuelo(String codigo) throws DAOException;
    public List<Vuelo>obtenerTodosVuelos() throws DAOException;
    public void pedirTerminalPuerta(String codigo)throws DAOException;
}

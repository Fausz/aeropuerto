/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iessevero.aeropuerto.dao.aeropuerto.dao;

import java.util.Date;
import java.util.List;

import es.iessevero.aeropuerto.clases.Reserva;

/**
 *
 * @author F
 */
public interface IReservaDAO {
    void crearReserva(Reserva r)throws DAOException;
    void modificarReserva(Reserva r)throws DAOException;
    public Reserva obtenerReserva(String codVuelo)throws DAOException;
    boolean eliminarReserva(int id)throws DAOException;
    List<Reserva> obtenerTodasReservas() throws DAOException;
    List <Reserva> obtenerTodasReservas(Date d)throws DAOException;
    List<Reserva> obtenerTodasReservas(String idVuelo)throws DAOException ;

    public Reserva obtenerReservaPorCodigo(int codReserva);
    
}

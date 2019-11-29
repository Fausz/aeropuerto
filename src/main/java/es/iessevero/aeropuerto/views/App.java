/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iessevero.aeropuerto.views;

import java.io.IOException;

import es.iessevero.aeropuerto.controller.PrincipalController;
import es.iessevero.aeropuerto.dao.aeropuerto.dao.DAOException;
import es.iessevero.aeropuerto.servicio.ServicioException;

/**
 *
 * @author F
 */
public class App {
    public static void main(String []args) throws DAOException, IOException, ServicioException {
        PrincipalController pc= new PrincipalController();
        pc.iniciar_aplicacion();
    }
}

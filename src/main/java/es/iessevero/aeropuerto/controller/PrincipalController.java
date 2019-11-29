/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iessevero.aeropuerto.controller;

import java.io.IOException;
import java.util.Scanner;

import es.iessevero.aeropuerto.dao.aeropuerto.dao.DAOException;
import es.iessevero.aeropuerto.servicio.ServicioException;

import es.iessevero.aeropuerto.servicio.ServicioReservas;
import es.iessevero.aeropuerto.servicio.ServicioVuelos;
import es.iessevero.aeropuerto.views.VistasPrincipal;

/**
 *
 * @author F
 */
public class PrincipalController {
    static Scanner sc=new Scanner(System.in);
    VistasPrincipal vp=null;
    VuelosController vc=null;
    ReservasController rc=null;
    
    private static final boolean modo_depuracion=true;
    
    
    public void PrincipalController() throws DAOException, IOException{
        
        
    }
    public void iniciar_aplicacion() throws IOException, ServicioException {
        vp=new VistasPrincipal();
        int sistemaAl = vp.elegirSistemaAlmacenamiento();
        if (sistemaAl == 0) {
            //salimos de la aplicacion
            return;

        } else {
            //acceso a una clase Singleton
            //como se ve no hace falta una instancia concreta
            // Esta clase controlador y la clase vistas también la podróamos haber
            //hecho así
            try {
                ServicioVuelos sv=new ServicioVuelos();
                ServicioReservas sr=new ServicioReservas();
                sv.elegirSistemaAlmacenamiento(sistemaAl);
                sr.elegirSistemaAlmacenamiento(sistemaAl);
                //this.iniciar_menu_vuelos();
                this.iniciar_menu_principal();

            } catch (DAOException e) {
                vp.mostrarError("Error al iniciar sistema de almacenamiento \n" + e.getMessage());
                if (modo_depuracion) {
                    //esto de aquí abajo solo para depuración
                    e.printStackTrace();
                }
            }

        }

    }
    
    public void iniciar_menu_principal() throws IOException, ServicioException, DAOException{
        int opcion=-1;
        vc=new VuelosController();
        rc=new ReservasController();
        try{
            while (opcion != 0) {
            opcion=vp.gestionMenusPrincipales();
            switch(opcion){
                case 0:
                  this.iniciar_aplicacion();
                    break;
                case 1:
                    vc.iniciar_menu_vuelos();
                    break;
                case 2:
                    rc.iniciar_menu_reservas();
                    break;
                /*case 3:
                    this.iniciar_menu_informes();
                    break;
               */
               }
            }
        
        }catch(DAOException e){
            //si da un error se muestra en vistas
            vp.mostrarError(e.getMessage());
            if(modo_depuracion){
                e.printStackTrace();//la lista de errores
            }
            //muestra para depurar
            
        }
    }
}

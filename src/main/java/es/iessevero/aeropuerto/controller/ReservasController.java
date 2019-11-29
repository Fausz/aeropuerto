/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iessevero.aeropuerto.controller;

import java.io.IOException;
import java.util.List;

import es.iessevero.aeropuerto.dao.aeropuerto.dao.DAOException;
import es.iessevero.aeropuerto.servicio.ServicioException;
import es.iessevero.aeropuerto.views.VistasReservas;
import es.iessevero.aeropuerto.clases.Reserva;
import es.iessevero.aeropuerto.servicio.ServicioReservas;
/**
 *
 * @author F
 */
public class ReservasController {
    
        VistasReservas vr=null;
        //ReservasController rc=null;
        VuelosController vc=null;
        ServicioReservas sr=null;
        PrincipalController pc=null;
        private static final boolean modo_depuracion=true;

        public ReservasController() throws DAOException, IOException{
            
            //rc=new ReservasController();
        
    }
    public void iniciar_menu_reservas()throws IOException, ServicioException, DAOException{
        vr=new VistasReservas();
        vc= new VuelosController();
        sr=new ServicioReservas();
        pc=new PrincipalController();
        int opcion=-1;
        
        try{
            while (opcion != 0) {
            opcion=vr.menuPrincipalReserva();
            switch(opcion){
                case 0:
                  pc.iniciar_menu_principal();
                    break;
                case 1:
                    this.crear_reserva();
                    break;
                case 2:
                    this.mostrar_reservas();
                   break;
                case 3:
                     this.mostrar_reserva();
                    break;
                case 4:
                    // this.modificarReserva(vv.obteneridReservaModificar());
                    
                    break;
                    case 5:
                    //this.generar_tarjeta_embarque();
                    break;
                case 6:
                    //this.cancelar_reserva();
                    break;
               }
            }
        
        }catch(Exception e){
            //si da un error se muestra en vistas
            vr.mostrarError(e.getMessage());
            /*if(modo_depuracion){
                dao.printStackTrace();//la lista de errores
            }*/
            //muestra para depurar
            
        }
    }//Fin iniciar_menu_reservas
    private void crear_reserva() throws IOException, DAOException{
        List<Reserva> reservas= sr.obtenerReservas();
        Reserva r= vr.mostrarInsertarReserva(reservas);
        if(r==null){//si es null es que el usuario quiere salir
            return;
        }
        try {
            sr.crearReserva(r);
            //no se puede imprimir la puerta hasta tener el vuelo
        } catch (DAOException ex) {
            vr.mostrarError(ex.getMessage());
            if(modo_depuracion){
                ex.printStackTrace();
            }
        
        }
    }
    private void mostrar_reserva() throws DAOException{
        String codVuelo=vr.introducirCodigoReservaParaMostrar();
        Reserva r;
         r= sr.obtenerReservaPorCodigo(codVuelo);
         vr.mostrarReserva(r);
    }
    private void mostrar_reservas(){
        try{
            List<Reserva> reservas= sr.obtenerReservas();
            vr.mostrarReservas(reservas);
        }catch(DAOException e){
            //si da un error se muestra en vistas
            vr.mostrarError(e.getMessage());
            if(modo_depuracion){
                e.printStackTrace();//la lista de errores
            }
            //muestra para depurar
            
        }
    }
    
}

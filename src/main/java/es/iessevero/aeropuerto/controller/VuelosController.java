/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iessevero.aeropuerto.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.iessevero.aeropuerto.dao.aeropuerto.dao.DAOException;
import es.iessevero.aeropuerto.clases.Vuelo;
import es.iessevero.aeropuerto.servicio.ServicioException;
import es.iessevero.aeropuerto.servicio.ServicioVuelos;
import es.iessevero.aeropuerto.views.VistasPrincipal;
import es.iessevero.aeropuerto.views.VistasVuelos;

/**
 *
 * @author F
 */
public class VuelosController {
    ServicioVuelos sv=null;
    VistasVuelos vv=null;
    ReservasController rc=null;
    VistasPrincipal vp=null;
    
    private static final boolean modo_depuracion=true;
    
    public VuelosController() throws DAOException, IOException{
        sv=new ServicioVuelos();
        vv=new VistasVuelos();
        rc=new ReservasController();
        vp=new VistasPrincipal();
        
    }
    
   
    public void iniciar_menu_vuelos() throws IOException, ServicioException{
        //metodo para crear vuelos
        
        
        int opcion=-1;
        
        try{
            while (opcion != 0) {
            opcion=vv.menuPrincipalVuelo();
            switch(opcion){
                
                case 1:
                    this.crear_vuelo();
                    break;
                case 2:
                    ArrayList<String> cod =sv.obtenerCodVuelos();
                    this.modificarVuelo(vv.obtenerCodigoVueloModificar(cod));
                    break;
                case 3:
                    this.eliminar_vuelo();
                    break;
                case 4:
                    this.obtener_vuelo();
                    break;
                case 5:
                    this.mostrar_vuelos();
                    break;
               }
            }
        
        }catch(DAOException e){
            //si da un error se muestra en vistas
            vv.mostrarError(e.getMessage());
            if(modo_depuracion){
                e.printStackTrace();//la lista de errores
            }
            //muestra para depurar
            
        }
    }
    
    private void modificarVuelo(String codigoVuelo) throws ServicioException, DAOException {

        if (codigoVuelo != null) {
            try {
                Integer opcion;
                opcion = vv.menuModificarVuelo();
                if (opcion != null) {
                    switch (opcion) {
                        case 1:
                            String origen = vv.pedirModificacionOrigenVuelo();
                            if (origen != null) {
                                sv.modificarVueloOrigen(codigoVuelo, origen);
                            }
                            break;
                        case 2:
                            String destino = vv.pedirModificacionDestinoVuelo();
                            if (destino != null) {
                                sv.modificarVueloDestino(codigoVuelo, destino);
                            }
                            break;
                        case 3:
                            Double precio = vv.pedirModificacionPrecioPersonaVuelo();
                            if (precio != null) {
                                sv.modificarVueloPrecio(codigoVuelo, precio);
                            }
                            break;
                        case 4:
                            Date fecha = vv.pedirModificacionFechaVuelo();
                            if (fecha != null) {
                                sv.modificarVueloFecha(codigoVuelo, fecha);
                            }
                            break;
                        case 5:
                            Integer plazas = vv.pedirModificacionPlazasDisponibles();
                            if (plazas != null) {
                                sv.modificarVueloPlazas(codigoVuelo, plazas);
                            }
                            break;
                        /*case 6:
                            Integer terminal = vv.pedirModificacionTerminal();
                            if (terminal != null) {
                                sv.modificarVueloTerminal(codigoVuelo, terminal);
                            }
                            break;
                        case 7:
                            Integer puerta = vv.pedirModificacionPuerta();
                            if (puerta != null) {
                                sv.modificarVueloPuerta(codigoVuelo, puerta);
                            }
                            break;*/
                    }

                }
            } catch (DAOException dao) {
                vv.mostrarError("Error al intentar obtener los datos: " + dao.getMessage());
            } catch (ServicioException se) {
                vv.mostrarError("Error al modificar un vuelo: " + se.getMessage());
            }
        }
    }
    
    
    
    private void eliminar_vuelo() throws DAOException{
        ArrayList<String> cod =sv.obtenerCodVuelos();
        String codVuelo=vv.obtenerVueloEliminar(cod);
        sv.eliminarVuelo(codVuelo);
    }
    private void crear_vuelo()throws DAOException, IOException{
        /**
         * hacer que la vista nos devuelva un objeto vuelo completo
         */
        Vuelo v=vv.mostrarInsertarVuelo();
        if(v==null){//si es null es que el usuario quiere salir
            return;
        }
        try {
            sv.crearVuelo(v);
            //no se puede imprimir la puerta hasta tener el vuelo
        } catch (DAOException ex) {
            vv.mostrarError(ex.getMessage());
            if(modo_depuracion){
                ex.printStackTrace();
            }
        } catch (ServicioException ex) {
            vv.mostrarError(ex.getMessage());
            if(modo_depuracion){
                ex.printStackTrace();
            }
        }
    }
    private void mostrar_vuelos(){
        try{
            List<Vuelo> vuelos= sv.obtenerVuelos();
            vv.mostrarVuelos(vuelos);
        }catch(DAOException e){
            //si da un error se muestra en vistas
            vv.mostrarError(e.getMessage());
            if(modo_depuracion){
                e.printStackTrace();//la lista de errores
            }
            //muestra para depurar
            
        }
        
    }
    private void obtener_vuelo() throws DAOException{
        ArrayList<String> cod =sv.obtenerCodVuelos();
        String codVuelo=vv.introducirCodigoMostrarVuelo(cod);
        Vuelo v;
         v= sv.obtenerVuelo(codVuelo);
         vv.mostrarVuelo(v);
        
    }
    
    public Vuelo obtenerVueloParaReserva(String codVuelo) throws DAOException{
        Vuelo v=sv.obtenerVuelo(codVuelo);
        return v;
    }
}

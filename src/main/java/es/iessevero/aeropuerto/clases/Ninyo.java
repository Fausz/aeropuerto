/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iessevero.aeropuerto.clases;

import java.util.Date;

/**
 *
 * @author F
 */
public class Ninyo extends Pasajero{
    private boolean solo;
    private boolean silleta;
    

    public Ninyo(int idPasajero, String dni, String nombre, String apellidos, Date fecha_nac, int num_maletas, int idReserva,boolean solo, boolean silleta){
        super(idPasajero, dni, nombre, apellidos, fecha_nac, num_maletas,idReserva);
        this.solo=solo;
        this.silleta=silleta;
    }

    public boolean isSilleta() {
        return silleta;
    }

    public void setSilleta(boolean silleta) {
        this.silleta = silleta;
    }

    public boolean isSolo() {
        return solo;
    }

    public void setSolo(boolean solo) {
        this.solo = solo;
    }
    
}

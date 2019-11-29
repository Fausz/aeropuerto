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
public class Adulto extends Pasajero{
    
    private Descuentos descuento;

    public Adulto(int idPasajero, String dni, String nombre, String apellidos, Date fecha_nac, int num_maletas, int idReserva, Descuentos descuento){
        super(idPasajero, dni, nombre, apellidos, fecha_nac, num_maletas, idReserva);

        this.descuento=descuento;
    }

    public Descuentos getDescuento() {
        return descuento;
    }

    public void setDescuento(Descuentos descuento) {

        this.descuento = descuento;
    }
    
}

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
public class Orden_Pago {
    
    private int id;
    private Date fecha_orden;
    private String dni_ordente;
    private String num_tarjeta;
    private double importe;
    private Reserva r;

    public Orden_Pago() {
    }

    public Orden_Pago(int id, Date fecha_orden, String dni_ordente, String num_tarjeta, double importe, Reserva r) {
        this.id = id;
        this.fecha_orden = fecha_orden;
        this.dni_ordente = dni_ordente;
        this.num_tarjeta = num_tarjeta;
        this.importe = importe;
        this.r = r;
    }

    public String getDni_ordente() {
        return dni_ordente;
    }

    public void setDni_ordente(String dni_ordente) {
        this.dni_ordente = dni_ordente;
    }

    public Date getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(Date fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getNum_tarjeta() {
        return num_tarjeta;
    }

    public void setNum_tarjeta(String num_tarjeta) {
        this.num_tarjeta = num_tarjeta;
    }

    public Reserva getR() {
        return r;
    }

    public void setR(Reserva r) {
        this.r = r;
    }
    
    
}

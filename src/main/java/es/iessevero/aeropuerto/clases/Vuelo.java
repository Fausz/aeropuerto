/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iessevero.aeropuerto.clases;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author F
 */
public class Vuelo implements Comparable<Vuelo>,Serializable{
    private String codVuelo;
    private String origen;
    private String destino;
    private double precio_persona;
    private Date fecha_vuelo;
    private int plazas_disponibles;
    private int terminal;
    private int puerta;

    public Vuelo(String codVuelo, String origen, String destino, double precio_persona, Date fecha_vuelo, int plazas_disponibles, int terminal, int puerta) {
        this.codVuelo = codVuelo;
        this.origen = origen;
        this.destino = destino;
        this.precio_persona = precio_persona;
        this.fecha_vuelo = fecha_vuelo;
        this.plazas_disponibles = plazas_disponibles;
        this.terminal = terminal;
        this.puerta = puerta;
    }
    public Vuelo(){}

    
    public String getCodigo() {
        return codVuelo;
    }

    public void setCodigo(String codVuelo) {
        this.codVuelo = codVuelo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFecha_vuelo() {
        return fecha_vuelo;
    }

    public void setFecha_vuelo(Date fecha_vuelo) {
        this.fecha_vuelo = fecha_vuelo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public int getPlazas_disponibles() {
        return plazas_disponibles;
    }

    public void setPlazas_disponibles(int plazas_disponibles) {
        this.plazas_disponibles = plazas_disponibles;
    }

    public double getPrecio_persona() {
        return precio_persona;
    }

    public void setPrecio_persona(double precio_persona) {
        this.precio_persona = precio_persona;
    }

    public int getPuerta() {
        return puerta;
    }

    public void setPuerta(int puerta) {
        this.puerta = puerta;
    }

    public int getTerminal() {
        return terminal;
    }

    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }

    @Override
    public int compareTo(Vuelo v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

   
    
}

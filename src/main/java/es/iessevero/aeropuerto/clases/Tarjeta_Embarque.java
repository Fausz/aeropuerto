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
public class Tarjeta_Embarque {
    
    private int idTarjeta;
    private String vuelo;
    private String origen;
    private String destino;
    private Date fecha_vuelo;
    private int terminal;
    private int puerta;

    public Tarjeta_Embarque(int idTarjeta, String vuelo, String origen, String destino, Date fecha_vuelo, int terminal, int puerta) {
        this.idTarjeta = idTarjeta;
        this.vuelo = vuelo;
        this.origen = origen;
        this.destino = destino;
        this.fecha_vuelo = fecha_vuelo;
        this.terminal = terminal;
        this.puerta = puerta;
    }

    public Tarjeta_Embarque() {
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

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
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

    public String getVuelo() {
        return vuelo;
    }

    public void setVuelo(String vuelo) {
        this.vuelo = vuelo;
    }
    
    
}

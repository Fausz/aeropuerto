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
public abstract class Pasajero {
    private int idPasajero;
    private String dni;
    private String nombre;
    private String apellidos;
    private Date fecha_nac;
    private int num_maletas;
    private int idReserva;
    //private Tarjeta_Embarque t;

    public Pasajero(int idPasajero, String dni, String nombre, String apellidos, Date fecha_nac, int num_maletas,int idReserva/*, Tarjeta_Embarque t*/) {
        this.idPasajero = idPasajero;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_nac = fecha_nac;
        this.num_maletas = num_maletas;
        this.idReserva=idReserva;
        //this.t = t;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNum_maletas() {
        return num_maletas;
    }

    public void setNum_maletas(int num_maletas) {
        this.num_maletas = num_maletas;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    /*public Tarjeta_Embarque getT() {
            return t;
        }

        public void setT(Tarjeta_Embarque t) {
            this.t = t;
        }
    */
    public int size() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    
}

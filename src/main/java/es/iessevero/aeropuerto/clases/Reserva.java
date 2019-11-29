/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iessevero.aeropuerto.clases;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author F
 */
public class Reserva {
    /**
     * Variables reserva
     */
    private int idReserva;
    private Date fecha_res;//actual o introducir
    private boolean cancelada;//false por defecto
    private Vuelo vuelo ;
    private double importe;
    private List <Pasajero> pasajeros;

    public Reserva(int idReserva, Date fecha_res, boolean cancelada,Vuelo v, double importe, List <Pasajero> p) {
        this.idReserva = idReserva;
        this.fecha_res = fecha_res;
        this.cancelada = cancelada;
        this.importe = importe;
        this.vuelo=v;
        this.pasajeros=p;

    }
    public Reserva(int idReserva, Date fecha_res,Vuelo v, List <Pasajero> p) {
        this(idReserva,fecha_res,false,v,0,p);
        this.setImporte(calcularImporte());

    }

    public Reserva(){
        
    }
    public List <Pasajero> getPasajeros() {
        
        return pasajeros;
    }

    public void setPasajeros(List<Pasajero> p) {
        this.pasajeros = p;
    }


    
    /**
     * Getters y Setters
     * @return 
     */
    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
    

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public Date getFecha_res() {
        return fecha_res;
    }

    public void setFecha_res(Date fecha_res) {
        this.fecha_res = fecha_res;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int id) {
        this.idReserva = idReserva;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public double calcularImporte() {
        double importe = 0;

        Configuracion configuracion = new Configuracion("/configuracion.properties");
        double importePersona = vuelo.getPrecio_persona();
        for (Pasajero p : this.pasajeros) {
            importePersona = vuelo.getPrecio_persona();
            if (p instanceof Ninyo) {
                if (((Ninyo) p).isSolo()) {
                    importePersona += configuracion.getCosteNinyoSolo();
                }
                if (((Ninyo) p).isSilleta()) {
                    importePersona += configuracion.getCosteSilleta();
                }
                importe += importePersona * (1 - (configuracion.getDescuentoNinyo() / 100));
            } else if (p instanceof Adulto) {
                if (((Adulto) p).getDescuento().equals(Descuentos.RESIDENTE_ISLA)) {
                    importe += importePersona * (1 - (configuracion.getDescuentoResidenteIsla() / 100));
                } else if (this.tieneDescuentoPorAnterioridad()) {
                    importe += importePersona * (1 - (configuracion.getDescuentoReservaConAnterioridad() / 100));
                } else {
                    importe += importePersona;
                }
            }
        }
        return importe;
    }

    private boolean tieneDescuentoPorAnterioridad() {
        Calendar fechaDeCorte = Calendar.getInstance();
        Calendar fechaVuelo = Calendar.getInstance();
        fechaVuelo.setTime(this.getVuelo().getFecha_vuelo());
        fechaDeCorte.setTime(new Date());
        fechaDeCorte.add(Calendar.MONTH, -2);
        return fechaDeCorte.before(fechaVuelo);
    }

    /*public void generarTarjetas() {
        for (Pasajero p : pasajeros) {
            p.setTarjeta(new TarjetaEmbarque(p.getId(), vuelo.getCodigo(), vuelo.getOrigen(), vuelo.getDestino(), vuelo.getFechaVuelo(), vuelo.getTerminal(), vuelo.getPuerta()));
        }
    }

    public double calcularCancelacion() {
        Configuracion configuracion = new Configuracion("/configuracion.properties");
        if(cancelado15Dias(this.getFecha())){
            return importe*(configuracion.getGastosCancelacionAntesDe15Dias()/100);

        }else if(cancelado1Mes(this.getFecha())){
            return importe*(configuracion.getGastosCancelacionAntesDeUnMes()/100);
        }else{
            return 0;
        }
    }

    private boolean cancelado15Dias(Date fechaReserva){
        Calendar fechaReservaCalendario=Calendar.getInstance();
        fechaReservaCalendario.setTime(fechaReserva);
        Calendar fechaCorte=Calendar.getInstance();
        fechaCorte.setTime(new Date());
        fechaCorte.add(Calendar.DAY_OF_MONTH, -15);
        return fechaCorte.before(fechaReservaCalendario);
    }

    private boolean cancelado1Mes(Date fechaReserva) {
        Calendar fechaReservaCalendario=Calendar.getInstance();
        fechaReservaCalendario.setTime(fechaReserva);
        Calendar fechaCorte=Calendar.getInstance();
        fechaCorte.setTime(new Date());
        fechaCorte.add(Calendar.MONTH, -1);
        return fechaCorte.before(fechaReservaCalendario);
    }
    */

    /*public void calcular_importe(){
        
    }*/
    /*public void generar_tarjetas(){
            
    }*/
    
    //public void generar_orden(String dni, ,int importe )

}

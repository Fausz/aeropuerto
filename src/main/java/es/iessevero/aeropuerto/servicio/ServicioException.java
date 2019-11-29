/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iessevero.aeropuerto.servicio;

/**
 *
 * @author F
 */
public class ServicioException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3249803612006121332L;
	public ServicioException(String msg, Exception e) {
        super(msg,e);
    }
	public ServicioException(String msg) {
        super(msg);
    }


}

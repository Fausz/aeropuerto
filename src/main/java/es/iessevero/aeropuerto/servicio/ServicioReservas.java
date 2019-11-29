package es.iessevero.aeropuerto.servicio;

import java.io.IOException;
import java.util.List;

import es.iessevero.aeropuerto.dao.aeropuerto.dao.DAOException;
import es.iessevero.aeropuerto.dao.aeropuerto.dao.IVueloDAO;
import es.iessevero.aeropuerto.dao.txtDAO.VueloTxtDAO;
import es.iessevero.aeropuerto.dao.aeropuerto.dao.IReservaDAO;
import es.iessevero.aeropuerto.dao.txtDAO.ReservaTxtDAO;
import es.iessevero.aeropuerto.clases.Reserva;

/**
 *
 * @author F
 */
public class ServicioReservas {
    private static IReservaDAO iReserva=null;
    private static IVueloDAO iVuelo=null;
    public ServicioReservas() throws DAOException, IOException{
        
    }
    public void elegirSistemaAlmacenamiento(int opcion) throws DAOException, IOException {

        if (opcion == 1) {
            
            iVuelo=new VueloTxtDAO();
            iReserva=new ReservaTxtDAO();
        }
        /*if (opcion == 2) {

            idao = new FutbolAleatorioDAO();

        }
        if (opcion == 3) {

            idao = new FutbolXMLDAO();

        }
        if (opcion == 4) {

            idao = new FutbolXMLSaxDAO();

        }
        if (opcion == 5) {

            idao = new FutbolJDBCDAO();

        }
        if (opcion == 6) {

            idao = new FutbolHibernate();

        }*/

    }
    public List<Reserva> obtenerReservas() throws DAOException{
        return iReserva.obtenerTodasReservas();
    }
    public Reserva obtenerReservaPorCodigo(String codVuelo) throws DAOException{
        Reserva r = null;
        r=iReserva.obtenerReserva(codVuelo);
        return r;
    }
    public void crearReserva(Reserva r) throws DAOException{
        int numPlazas = r.getVuelo().getPlazas_disponibles();
        int numPersonas=r.getPasajeros().size();
        if(numPlazas >numPersonas /*&& !idReserva*/){
            iReserva.crearReserva(r);
        }
        /*boolean idReserva=idReservaValido(r);

        
        //Reserva rComprobacion=idao.obtenerReserva(r.getVuelo().getCodigo());
        //rComprobacion.getVuelo().getPlazas_disponibles();
        //for(int i=0;r.getVuelo().getPlazas_disponibles()i++)*/
    }//fin crearReserva

    /*
    public boolean idReservaValido(Reserva r) throws DAOException{
        List <Reserva> reservas=iReserva.obtenerTodasReservas();
        boolean repetido=false;
        for(Reserva re : reservas){
            if(re.getIdReserva()==r.getIdReserva()){
                return true;
            }else{
                return false;
            }
        }
        return repetido;
    }*/
}

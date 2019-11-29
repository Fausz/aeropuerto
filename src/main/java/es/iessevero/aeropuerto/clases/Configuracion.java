package es.iessevero.aeropuerto.clases;

import java.io.IOException;
import java.util.Properties;

public class Configuracion {
    public static Properties propiedades=new Properties();

    public Configuracion(String direccionArchivoPropiedades)throws IllegalArgumentException{
        try {
            propiedades.load(this.getClass().getResourceAsStream(direccionArchivoPropiedades));
        } catch (IOException ex) {
            throw new IllegalArgumentException("Error al intentar acceder a la ruta: "+direccionArchivoPropiedades);
        }
    }

    public String getTxtVuelos(){
        return propiedades.getProperty("vuelosTxt");
    }

    public String getTxtReservas(){
        return propiedades.getProperty("reservasTxt");
    }

    public String getTxtPasajeros(){
        return propiedades.getProperty("pasajerosTxt");
    }

    public String getTxtTarjetasEmbarque() {
        return propiedades.getProperty("tarjetasTxt");
    }

    public String getTxtOrdenesPago() {
        return propiedades.getProperty("ordenesTxt");
    }

    public Integer getGastosCancelacionAntesDeUnMes(){
        return Integer.parseInt(propiedades.getProperty("GastosCancelacionAntesDeUnMes"));
    }

    public Integer getGastosCancelacionAntesDe15Dias(){
        return Integer.parseInt(propiedades.getProperty("GastosCancelacionAntesDe15Dias"));
    }

    public Integer getCosteMaleta(){
        return Integer.parseInt(propiedades.getProperty("CosteMaleta"));
    }

    public Integer getCosteSilleta(){
        return Integer.parseInt(propiedades.getProperty("CosteSilleta"));
    }

    public Integer getCosteNinyoSolo(){
        return Integer.parseInt(propiedades.getProperty("CosteNinyoSolo"));
    }

    public Integer getDescuentoNinyo(){
        return Integer.parseInt(propiedades.getProperty("DescuentoNinyo"));
    }

    public Integer getDescuentoResidenteIsla(){
        return Integer.parseInt(propiedades.getProperty("DescuentoResidenteIsla"));
    }

    public Integer getDescuentoReservaConAnterioridad(){
        return Integer.parseInt(propiedades.getProperty("DescuentoReservaConAnterioridad"));
    }
}

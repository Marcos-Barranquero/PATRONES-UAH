package BuilderPortatiles;

import ClasesBase.Portatil;
import Comando.PortatilExecuter;

/**
 * Builder de portátiles. 
 * Crea el modelo que debe tener un builder de un portátil concreto,
 * asegurándose de proveer de los métodos que le dan los valores
 * al portátil. 
 * @author marco
 */
public abstract class PortatilBuilder {

    protected Portatil portatil;
    protected final PortatilExecuter pe = new PortatilExecuter();

    /**
     * Setea la ID del portátil leyendo la última ID de la bbdd e 
     * incrementándola en uno. 
     */
    public void darValoresPorDefecto() {
        // incremento en 1 el id del portátil pa crear uno nuevo
        int idPortatil = pe.getMaxIdPortatil() + 1;
        portatil.setIdPortatil(idPortatil);
        portatil.setPrestado(false);
    }

    /**
     * Constructor por defecto. 
     */
    public PortatilBuilder() {
    }

    /**
     * Devuelve el portátil asociado.
     * @return -> portátil construido. 
     */
    public Portatil getPortatil() {
        return portatil;
    }

    /**
     * Crea un portátil dándole los valores propios
     * de ese builder. 
     */
    public void crearPortatil() {
        portatil = new Portatil();
        darMarca();
        darPulgadas();
        darEspecificaciones();
    }

    /**
     * Asigna marca al portátil asociado. 
     */
    public abstract void darMarca();

    /**
     * Asigna pulgadas al portátil asociado. 
     */
    public abstract void darPulgadas();

    /**
     * Asigna especificaciones al portátil asociado. 
     */
    public abstract void darEspecificaciones();
    

}

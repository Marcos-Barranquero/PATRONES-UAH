package Factory;

import BuilderPortatiles.AsusPortatilBuilder;
import BuilderPortatiles.MacbookPortatilBuilder;
import BuilderPortatiles.PortatilBuilder;
import ClasesBase.Portatil;
import java.util.ArrayList;

/**
 * Factoría dedicada a crear portátiles.
 *
 * @author Edu
 */
public class PortatilFactory implements AbstractFactory {

    private static PortatilFactory instanciaUnica;
    private final ArrayList<String> tipos = new ArrayList<>();
    private PortatilBuilder pb;

    /**
     * Actualiza los tipos de portátiles que se pueden crear.
     */
    private PortatilFactory() {
        tipos.add("Macbook");
        tipos.add("AsusThinkpad");
    }

    /**
     * Devuelve la instancia única de la factoría.
     *
     * @return
     */
    public static PortatilFactory getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new PortatilFactory();
        }

        return instanciaUnica;
    }

    /**
     * Crea un portátil.
     *
     * @param parametros -> tipo de portátil a crear.
     * @return -> nuevo objeto de tipo Portatil
     */
    @Override
    public Portatil crear(ArrayList parametros) {
        Portatil devuelto = null;

        if (tipos.contains((String) parametros.get(0))) {
            switch ((String) parametros.get(0)) {
                case "Macbook":
                    pb = new MacbookPortatilBuilder();
                    pb.crearPortatil();
                    devuelto = pb.getPortatil();
                    break;
                case "AsusThinkpad":
                    pb = new AsusPortatilBuilder();
                    pb.crearPortatil();
                    devuelto = pb.getPortatil();
                    break;
            }
        }

        return devuelto;
    }

    /**
     * Devuelve el array de tipos de portátiles que se pueden crear.
     *
     * @return
     */
    public ArrayList<String> getTipos() {
        return tipos;
    }
}

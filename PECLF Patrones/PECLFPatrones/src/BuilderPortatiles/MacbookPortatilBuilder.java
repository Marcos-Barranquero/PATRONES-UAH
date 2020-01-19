package BuilderPortatiles;


/**
 * Builder de portátiles de Apple. 
 * @author marco
 */
public class MacbookPortatilBuilder extends PortatilBuilder {
    
    @Override
    public void darMarca() {
        portatil.setMarca("Apple");
    }

    @Override
    public void darPulgadas() {
        portatil.setPulgadas(13);
    }

    @Override
    public void darEspecificaciones() {
        portatil.setEspecificaciones("Procesador: i5-6500U; Gráfica: Intel HD Graphics 630; SSD 240GB; Pantalla 720p.");
    }
    
}

package BuilderPortatiles;

/**
 * Builder de portátiles asus. 
 * @author marco
 */
public class AsusPortatilBuilder extends PortatilBuilder {
    
    @Override
    public void darMarca() {
        portatil.setMarca("Asus");
    }

    @Override
    public void darPulgadas() {
        portatil.setPulgadas(15);
    }

    @Override
    public void darEspecificaciones() {
        portatil.setEspecificaciones("Procesador: i7-8700U; Gráfica: Nvidia MX 250; SSD 512GB; Pantalla 1080p.");
    }

    
}

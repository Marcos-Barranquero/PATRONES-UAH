package Iterator;

import ClasesBase.Libro;

/**
 *
 * Iterador concreto para trabajar sobre ColeccionLibros
 * 
 * @author Edu
 */
public class IteradorLibros implements AbstractIterator{

    private final ColeccionLibros coleccion;
    private final int longitud;
    private int puntero = -1;

    public IteradorLibros(ColeccionLibros coleccion) {
        this.coleccion = coleccion;
        this.longitud = coleccion.getLibros().size() - 1;
    }
    
    @Override
    public boolean siguiente() {
        boolean haySiguiente = false;
        
        if(puntero < longitud) {
            puntero++;
            haySiguiente = true;
        }
        
        return haySiguiente;
    }

    @Override
    public void reiniciarIterador() {
        this.puntero = -1;
    }

    @Override
    public Libro getElemento() {
        return coleccion.getLibro(puntero);
    }  
}

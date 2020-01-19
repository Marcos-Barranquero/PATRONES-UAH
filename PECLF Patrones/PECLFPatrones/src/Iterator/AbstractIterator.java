package Iterator;

/**
 * Iterador Abstracto que debe ser implementado por los iteradores concretos
 * 
 * @author Edu
 * @param <T> -> Tipo de datos que manejará el iterador
 */
public interface AbstractIterator<T> {
    
    /**
     * Comprueba si hay un elemento siguiente en la colección y, si es así,
     * avanza al elemento siguiente.
     * @return 
     */
    public boolean siguiente();
    
    /**
     * Pone el puntero del iterador a su posición inicial (-1)
     */
    public void reiniciarIterador();
    
    /**
     * Devuelve el elemento al que esté apuntando el puntero en ese momento 
     * @return Objeto del tipo con el que trabaje el iterador
     */
    public T getElemento();    
}

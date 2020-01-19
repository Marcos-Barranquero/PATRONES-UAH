package Factory;

import java.util.ArrayList;

/**
 *
 * Para instanciar un objeto que implemente esta interfaz, se debe instanciar
 * con el tipo del objeto implementador. Se fuerza a hacer de esta forma
 * para que las fábricas concretas puedan ser Singleton .
 * 
 * @author Edu 
 * @param <T> Tipo que devuelve la factoría concreta
 * 
 */
public interface AbstractFactory<T> {
    
    /**
     * Crea una instancia del objeto para el que se haya instanciado la fábrica
     * 
     * @param parametros parámetros necesarios para instanciar la clase 
     * correctamente
     * @return objeto de la clase correspondiente
     */
    public T crear(ArrayList<Object> parametros);

}

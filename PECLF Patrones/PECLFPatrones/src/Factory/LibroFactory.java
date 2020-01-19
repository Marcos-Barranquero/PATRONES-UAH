package Factory;

import ClasesBase.Libro;
import java.util.ArrayList;

/**
 * Factoría usada para crear libros. 
 * @author Edu
 */
public class LibroFactory implements AbstractFactory {

    private static LibroFactory instanciaUnica;
    
    private LibroFactory() { }
    
    /**
     * Devuelve la instancia única de la factoría de libros.
     * @return 
     */
    public static LibroFactory getInstancia() {
        if(instanciaUnica == null){
            instanciaUnica = new LibroFactory();
        }
        
        return instanciaUnica;
    }
    
    /**
     * Crea un libro. 
     * @param parametros -> args del libro. 
     * @return -> libro creado. 
     */
    @Override
    public Libro crear(ArrayList parametros) {
        return new Libro((Integer)parametros.get(0), (Integer)parametros.get(1),(String)parametros.get(2), (String)parametros.get(3), (String)parametros.get(4), (Boolean)parametros.get(5));
    }
    
}

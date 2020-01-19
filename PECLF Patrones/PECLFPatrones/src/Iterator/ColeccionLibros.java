package Iterator;

import ClasesBase.Libro;
import java.util.ArrayList;

/**
 *
 * Clase envoltorio de ArrayList para poder tener un iterador propio.
 * Almacena objetos de tipo libro.
 * 
 * @author Edu
 */
public class ColeccionLibros {
    private ArrayList<Libro> libros;

    /**
     * Constructor que recibe la longitud con la que instanciar el ArrayList
     * @param longitud 
     */
    public ColeccionLibros(int longitud) {
        libros = new ArrayList<>(longitud);
    }
    
    /**
     * Constructor básico, inicializa el ArrayList con la longitud por defecto
     */
    public ColeccionLibros() {
        libros = new ArrayList<>();
    }

    /**
     * Devuelve todos los libros de la base de datos en forma de ArrayList
     * @return ArrayList de libros con todos los libros de la colección
     */
    public ArrayList<Libro> getLibros() {
        return this.libros;
    }
    
    /**
     * Sobreescribe el ArrayList que almacena los libros de la colección con el
     * nuevo ArrayList
     * @param libros ArrayList de libros con el que sobreescribir
     */
    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }
    
    /**
     * Recupera un libro en concreto en base a un puntero
     * @param puntero int que determina que posición de la colección recuperar
     * @return Objeto de tipo libro
     */
    public Libro getLibro(int puntero) {
        return this.libros.get(puntero);
    }
    
    /**
     * Inserta un objeto de tipo Libro en la colección
     * @param libro Libro a insertar
     */
    public void insertarLibro (Libro libro) {
        this.libros.add(libro);
    }
    
    /**
     * Elimina un libro de la colección en base al objeto libro que se pase
     * @param libro libro a eliminar
     */
    public void eliminarLibro(Libro libro) {
        this.libros.remove(libro);
    }
    
    /**
     * Instancia un Iterador para esta colección y lo devuelve
     * @return Iterador para la colección de libros
     */
    public IteradorLibros getIterador() {
        return new IteradorLibros(this);
    }
    
    /**
     * Comprueba si la colección está vacía
     * @return true si está vacía, false si no lo está
     */
    public boolean estaVacio() {
        return libros.isEmpty();
    }
    
    
}

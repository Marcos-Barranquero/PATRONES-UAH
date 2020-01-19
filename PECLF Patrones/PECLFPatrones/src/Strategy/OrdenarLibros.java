package Strategy;


import Iterator.ColeccionLibros;

/**
 *
 * Ordenador abstracto que debe ser implementado, ordena los libros haciendo
 * uso del algoritmo de Quicksort. Es capaz de hacerlo en orden descentente y
 * ascendente
 * 
 * @author Edu
 */
public interface OrdenarLibros {
    /**
     * Ordena la colección de libros que recibe en orden descendente
     * @param libros Colección de libros a ordenar
     * @return colección de libros ordenada en orden descendente
     */
    public ColeccionLibros ordenar(ColeccionLibros libros);
    
    /**
     * Ordena la colección de libros que recibe en orden ascendente
     * @param libros Colección de libros a ordenar
     * @return colección de libros ordenada en orden ascendente
     */
    public ColeccionLibros reverso(ColeccionLibros libros);
}

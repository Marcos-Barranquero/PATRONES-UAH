package Strategy;

import ClasesBase.Libro;
import Iterator.ColeccionLibros;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Edu
 */
public class OrdenarLibrosAutor implements OrdenarLibros{
    @Override
    public ColeccionLibros ordenar(ColeccionLibros libros) {
        ArrayList<Libro> aux = libros.getLibros();
        libros.setLibros(quickSort(aux));
        return libros;
    }
    
    /**
     * Método privado que ordena el ArrayList de libros que recibe usando el
     * algoritmo de ordenación Quick Sort, haciendo uso de recursión
     * 
     * @param libros ArrayList de libros a ordenar
     * @return ArrayList de libros ordenados
     */
    private ArrayList<Libro> quickSort(ArrayList<Libro> libros) {
        if (libros.isEmpty()) {
            return libros;
        }

        ArrayList<Libro> ordenado;
        ArrayList<Libro> menores = new ArrayList<>();
        ArrayList<Libro> mayores = new ArrayList<>();
        Libro pivot = libros.remove(0);
        
        for (Libro libro : libros) {
            if (libro.getAutor().compareTo(pivot.getAutor()) < 0) {
                menores.add(libro);
            } else {
                mayores.add(libro);
            }
        }
        menores = quickSort(menores);
        mayores = quickSort(mayores);
        menores.add(pivot);
        menores.addAll(mayores);
        ordenado = menores;

        return ordenado;
    }
    
    @Override
    public ColeccionLibros reverso(ColeccionLibros libros) {
        ArrayList<Libro> aux = quickSort(libros.getLibros());
        Collections.reverse(aux);
        libros.setLibros(aux);
        return libros;
    }
}

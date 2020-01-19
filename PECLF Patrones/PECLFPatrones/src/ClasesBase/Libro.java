package ClasesBase;

/**
 *
 * @author marco
 */
public class Libro {
    private int idLibro, edicion;
    private String nombre, isbn, autor;
    private boolean prestado;

    public Libro(int idLibro, int edicion, String nombre, String isbn, String autor, boolean prestado) {
        this.idLibro = idLibro;
        this.edicion = edicion;
        this.nombre = nombre;
        this.isbn = isbn;
        this.autor = autor;
        this.prestado = prestado;
    }

    // Getters, setters, etc.

    public int getIdLibro() {
        return idLibro;
    }

    public int getEdicion() {
        return edicion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAutor() {
        return autor;
    }

    public boolean estaPrestado() {
        return prestado;
    }

    @Override
    public String toString() {
        return "Libro{" + "idLibro=" + idLibro + ", edicion=" + edicion + ", nombre=" + nombre + ", isbn=" + isbn + ", autor=" + autor + ", prestado=" + prestado + '}';
    }
    
    
    
    
    
    
    
    
    
}

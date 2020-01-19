package Comando;

import BaseDatos.BaseDatos;
import ClasesBase.Libro;
import Factory.LibroFactory;
import Iterator.ColeccionLibros;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Lanzador de comandos en la BBDD relacionados con los préstamos
 * de libros. 
 * @author marco
 */
public class PrestamoLibrosExecuter {

    private final BaseDatos bbdd = BaseDatos.getInstancia();
    private final LibroFactory libroFactory = LibroFactory.getInstancia();

    /**
     * Crea un préstamo de libro en la BBDD. 
     * @param idLibro -> libro prestado. 
     * @param fechaMaximaDevolucion -> fecha máxima que tiene el usuario para devolverlo. 
     * @param dniUsuario -> dni del usuario al que se presta el libro.  
     */
    public void crearPrestamoLibro(int idLibro, Date fechaMaximaDevolucion, String dniUsuario) {
        String query = "INSERT INTO TIENE_PRESTADO_LIBRO VALUES ("
                + "'" + fechaMaximaDevolucion.toString() + "', "
                + "'" + dniUsuario + "', "
                + "" + idLibro + ")";

        bbdd.lanzarQuery(query);

        marcarLibroComoPrestado(idLibro);
    }

    /**
     * Dado un id de libro, lo marca como prestado en la bbdd. 
     * @param idLibro -> id del libro a marcar como prestado.  
     */
    private void marcarLibroComoPrestado(int idLibro) {
        String query = "update libro set prestado = true where id_libro = " + idLibro;
        bbdd.lanzarQuery(query);
    }

    /**
     * Elimina el préstamo de un libro al ser devuelto, en la BBDD. 
     * @param idLibro -> id del libro devuelto
     * @param dniUsuario -> usuario qeu devuelve el libro.  
     */
    public void devolverLibro(int idLibro, String dniUsuario) {
        String query = "DELETE FROM TIENE_PRESTADO_LIBRO"
                + " where dni_usuario = '" + dniUsuario + "' "
                + " and id_libro = " + idLibro;

        bbdd.lanzarQuery(query);
        marcarLibroComoDevuelto(idLibro);
        
    }

    /**
     * Marca un libro como devuelto. 
     * @param idLibro -> id del libro devuelto.  
     */
    private void marcarLibroComoDevuelto(int idLibro) {
        String query = "update libro set prestado = false where id_libro = " + idLibro;
        bbdd.lanzarQuery(query);
    }

    /**
     * Recupera todos los libros préstado para un usuario
     *
     * @param dni dni del usuario sobre el que se recuperan los libros
     * @return ColeccionLibros con todos los libros que el usuario tiene
     * prestado
     */
    public ColeccionLibros recuperarLibrosPrestados(String dni) {
        ColeccionLibros libros = new ColeccionLibros();
        
        String query = "SELECT LIBRO.* FROM LIBRO INNER JOIN TIENE_PRESTADO_LIBRO "
                + "ON TIENE_PRESTADO_LIBRO.ID_LIBRO = LIBRO.ID_LIBRO "
                + "WHERE TIENE_PRESTADO_LIBRO.DNI_USUARIO='"+dni+"'";
        
        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        try {
            while(resultado.next()) {
                int idLibro = resultado.getInt("ID_LIBRO");
                String nombre = resultado.getString("NOMBRE");
                String isbn = resultado.getString("ISBN");
                String autor = resultado.getString("AUTOR");
                int edicion = resultado.getInt("EDICION");
                boolean prestado = resultado.getBoolean("PRESTADO");
                
                ArrayList<Object> atributosLibro = new ArrayList<>();
                atributosLibro.add(idLibro);
                atributosLibro.add(edicion);
                atributosLibro.add(nombre);
                atributosLibro.add(isbn);
                atributosLibro.add(autor);
                atributosLibro.add(prestado);
                
                Libro libro = libroFactory.crear(atributosLibro);
                libros.insertarLibro(libro);
            }

        } catch (Exception e) {
            System.out.println("Error al recuperar libros prestados en préstamo de libro: ");
            System.out.println("Query: " + query);
            e.printStackTrace();
        }

        return libros;
    }

    
    /**
     * Dado un préstamo conformado por el id del libro prestado
     * y el usuario que tiene ese libro, devuelve la fecha máxima
     * que tenía para devolverlo. 
     * @param idLibro -> id del libro prestado. 
     * @param dni -> dni del usuario que tiene el libro prestado. 
     * @return 
     */
    public Date getFechaMaximaDevolucion(int idLibro, String dni) {
        Date fechaMaxima = null;
        String query = "SELECT fecha_maxima_devolucion FROM TIENE_PRESTADO_LIBRO "
                + "where dni_usuario = '" + dni + "' "
                + "and id_libro="+idLibro;
        
        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        try {
            if (resultado.next()) {
                fechaMaxima = resultado.getDate("fecha_maxima_devolucion");
            }

            resultado.close();
            consulta.close();

        } catch (Exception e) {
            System.out.println("Error al recuperar la fecha máxma de devolución para un libro: ");
            System.out.println("Query: " + query);
            e.printStackTrace();
        }
        return fechaMaxima;
    }
}

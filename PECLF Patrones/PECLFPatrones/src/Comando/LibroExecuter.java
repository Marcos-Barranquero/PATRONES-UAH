package Comando;

import BaseDatos.BaseDatos;
import ClasesBase.Libro;
import Factory.LibroFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Lanzador de comandos sobre la BBDD relacionados con los libros. 
 * @author marco
 */
public class LibroExecuter {

    private final BaseDatos bbdd = BaseDatos.getInstancia();
    private final LibroFactory libroFactory = LibroFactory.getInstancia();

    public LibroExecuter() {    }

    /**
     * Inserta un libro en la base de datos. 
     * @param libro -> libro a insertar. 
     */
    public void insertar(Libro libro) {

        String query = "INSERT INTO LIBRO VALUES("
                + libro.getIdLibro() + ", "
                + "'" + libro.getNombre() + "', "
                + "'" + libro.getIsbn() + "', "
                + "'" + libro.getAutor() + "', "
                + libro.getEdicion() + ", "
                + "'" + libro.estaPrestado() + "')";

        bbdd.lanzarQuery(query);

    }

    /**
     * Recupera un libro de la BBDD.
     * @param idLibro -> id del libro a recuperar. 
     * @return -> el libro devuelto.  
     */
    public Libro recuperar(int idLibro) {
        String query = "SELECT * FROM LIBRO WHERE (id_libro = " + idLibro + ")";
        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        Libro devuelto = null;
        try {
            if (resultado.next()) {
                String nombre = resultado.getString("nombre");
                String isbn = resultado.getString("isbn");
                String autor = resultado.getString("autor");

                // ints
                int edicion = resultado.getInt("edicion");
                int idLibroDevuelto = resultado.getInt("id_libro");

                // bools
                boolean estaPrestado = resultado.getBoolean("prestado");
                
                ArrayList<Object> atributosLibro = new ArrayList<>();
                atributosLibro.add(idLibroDevuelto);
                atributosLibro.add(edicion);
                atributosLibro.add(nombre);
                atributosLibro.add(isbn);
                atributosLibro.add(autor);
                atributosLibro.add(estaPrestado);
                

                devuelto = libroFactory.crear(atributosLibro);
            }
            resultado.close();
            consulta.close();
        } catch (Exception e) {
            System.out.println("Error al recuperar libro: ");
            System.out.println("Query: " + query);
            e.printStackTrace();
        }

        return devuelto;
    }

    /**
     * Recupera todos los libros con un ISBN concreto.
     *
     * @param isbn
     * @return
     */
    public ArrayList<Libro> recuperarLibros(String isbn) {
        ArrayList<Libro> devueltos = new ArrayList<>();
        String query = "SELECT id_libro FROM LIBRO WHERE isbn = '" + isbn + "'";
        ArrayList<Integer> idLibros = new ArrayList<>();

        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        try {

            while (resultado.next()) {
                idLibros.add(Integer.valueOf(resultado.getString("id_libro")));
            }

            resultado.close();
            consulta.close();

        } catch (Exception e) {
            System.out.println("Error al recuperar libros por ISBN: ");
            System.out.println("Query: " + query);
            e.printStackTrace();
        }

        for (int idLibro : idLibros) {
            devueltos.add(recuperar(idLibro));
        }

        return devueltos;
    }

    /**
     * Recupera todos los libros.
     *
     * @return
     */
    public ArrayList<Libro> recuperarLibros() {
        ArrayList<Libro> devueltos = new ArrayList<>();
        String query = "SELECT * FROM LIBRO";

        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        try {
            Libro libro;
            while (resultado.next()) {
                ArrayList<Object> atributosLibro = new ArrayList<>();
                atributosLibro.add(resultado.getInt(1));
                atributosLibro.add(resultado.getInt(5));
                atributosLibro.add(resultado.getString(2));
                atributosLibro.add(resultado.getString(3));
                atributosLibro.add(resultado.getString(4));
                atributosLibro.add(resultado.getBoolean(6));
                libro = libroFactory.crear(atributosLibro);
                devueltos.add(libro);
            }

            resultado.close();
            consulta.close();

        } catch (Exception e) {
            System.out.println("Error al recuperar libros por ISBN: ");
            System.out.println("Query: " + query);
            e.printStackTrace();
        }

        return devueltos;
    }

    /**
     * Devuelve la id más alta de libro. Usado a la hora de dar de alta un
     * libro.
     *
     * @return
     */
    public int getMaxIdLibro() {
        int id = 0;
        String query = "SELECT MAX(id_libro) AS MAXIMO FROM LIBRO";

        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        try {
            while (resultado.next()) {
                id = resultado.getInt("MAXIMO");
            }

            resultado.close();
            consulta.close();

        } catch (Exception e) {
            System.out.println("Error al recuperar maxima id en libros: ");
            System.out.println("Query: " + query);
            e.printStackTrace();
        }

        return id;
    }

    /**
     * Devuelve un arraylist con los libros que tiene en préstamo un usuario.
     *
     * @param dniUsuario
     * @return
     */
    public ArrayList<Libro> getLibrosPrestados(String dniUsuario) {
        ArrayList<Libro> prestados = new ArrayList<>();
        String query = "SELECT * FROM TIENE_PRESTADO_LIBRO where dni_usuario = '" + dniUsuario+"'";
        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        try {
            while (resultado.next()) {
                int idLibroPrestado = resultado.getInt("id_libro");
                prestados.add(recuperar(idLibroPrestado));
            }

        } catch (SQLException ex) {
            System.out.println("Error recuperando libros prestados: ");
            System.out.println("Query: " + query);
            ex.printStackTrace();
        }
        return prestados;
    }

}

package Comando;

import BaseDatos.BaseDatos;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

/**
 * Lanzador de comandos relacionados con el préstamo de portátiles
 * sobre la BBDD.  
 * @author marco
 */
public class PrestamoPortatilExecuter {

    private final BaseDatos bbdd = BaseDatos.getInstancia();

    /**
     * Crea un préstamo de un portátil en la BBDD. 
     * @param idPortatil -> id del portátil prestado. 
     * @param horaMaximaDevolucion -> hora límite para devovlerlo. 
     * @param dniUsuario -> dni del usuario al que se presta dicho portátil. 
     */
    public void crearPrestamoPortatil(int idPortatil, Time horaMaximaDevolucion, String dniUsuario) {

        String query = "INSERT INTO TIENE_PRESTADO_PORTATIL VALUES ("
                + "'" + horaMaximaDevolucion.toString() + "', "
                + "'" + dniUsuario + "', "
                + "" + idPortatil + ")";

        bbdd.lanzarQuery(query);

        marcarPortatilComoPrestado(idPortatil);
    }


    /**
     * Marca un portátil como prestado en la BBDD. 
     * @param idPortatil -> id del portátil prestado.  
     */
    private void marcarPortatilComoPrestado(int idPortatil) {
        String query = "update portatil set prestado = true where id_portatil = " + idPortatil;
        bbdd.lanzarQuery(query);
    }

    /**
     * Devuelve un portátil, es decir, elimina el préstamo. 
     * @param idPortatil -> id del portátil prestado.
     * @param dniUsuario -> dni del usuario al que se prestó el portátil. 
     */
    public void devolverPortatil(int idPortatil, String dniUsuario) {
        String query = "DELETE FROM TIENE_PRESTADO_PORTATIL"
                + " where dni_usuario = '" + dniUsuario + "' "
                + " and id_portatil = " + idPortatil;

        bbdd.lanzarQuery(query);
        marcarPortatilComoDevuelto(idPortatil);
    }

    /**
     * Actualiza el estado de un portátil a disponible en la BBDD.
     * @param idPortatil -> portátil devuelto.  
     */
    private void marcarPortatilComoDevuelto(int idPortatil) {
        String query = "update portatil set prestado = false where id_portatil = " + idPortatil;
        bbdd.lanzarQuery(query);
    }

    /**
     * Dado un dni de un usuario, devuelve TRUE si ya tiene un portátil.  
     * @param dniUsuario -> dni del usuario a consultar.
     * @return -> true si tiene un portátil, false si no.  
     */
    public boolean tienePortatil(String dniUsuario) {
        boolean yaTienePortatil = false;
        String query = "SELECT * FROM TIENE_PRESTADO_PORTATIL where dni_usuario = '" + dniUsuario + "'";

        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        try {
            while (resultado.next()) {
                yaTienePortatil = true;
            }

            resultado.close();
            consulta.close();

        } catch (Exception e) {
            System.out.println("Error al recuperar si el usuario tiene ya un portátil: ");
            System.out.println("Query: " + query);
            e.printStackTrace();
        }
        return yaTienePortatil;
    }

    /**
     * Devuelve la ID máxima de portátil regsitrada en la BBDD. 
     * @param dni
     * @return 
     */
    public int getIdPortatilPrestado(String dni) {
        int idPortatilPrestado = 0;
        String query = "SELECT id_portatil FROM TIENE_PRESTADO_PORTATIL where dni_usuario = '" + dni + "'";

        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        try {
            while (resultado.next()) {
                idPortatilPrestado = resultado.getInt("id_portatil");
            }

            resultado.close();
            consulta.close();

        } catch (Exception e) {
            System.out.println("Error al recuperar si el usuario tiene ya un portátil: ");
            System.out.println("Query: " + query);
            e.printStackTrace();
        }
        return idPortatilPrestado;
    }

    /**
     * Devuelve la hora tope que tiene un usuario para devolver el portátil. 
     * @param dni -> dni del usuario que tenga el portátil.  
     * @return 
     */
    public Time getHoraMaximaDevolucion(String dni) {
        Time horaMaxima = null;
        String query = "SELECT hora_maxima_devolucion FROM TIENE_PRESTADO_PORTATIL where dni_usuario = '" + dni + "'";
        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        try {
            while (resultado.next()) {
                horaMaxima = resultado.getTime("hora_maxima_devolucion");
            }

            resultado.close();
            consulta.close();

        } catch (Exception e) {
            System.out.println("Error al recuperar si el usuario tiene ya un portátil: ");
            System.out.println("Query: " + query);
            e.printStackTrace();
        }
        return horaMaxima;
    }

    /**
     * Dado una id de portátil, devuele el dni del usuario 
     * que lo tenga prestado. 
     * @param idPortatil -> id del portátil prestado.
     * @return -> dni del usuario con ese portátil. 
     */
    public String getDniPortatilPrestado(int idPortatil) {
        String dni = null;
        String query = "SELECT dni_usuario FROM TIENE_PRESTADO_PORTATIL where id_portatil = " + idPortatil + "";
        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        try {
            while (resultado.next()) {
                dni = resultado.getString("dni_usuario");
            }

            resultado.close();
            consulta.close();

        } catch (Exception e) {
            System.out.println("Error al recuperar dni de usuario dado el portátil que tiene prestado: ");
            System.out.println("Query: " + query);
            e.printStackTrace();
        }
        return dni;
    }
}

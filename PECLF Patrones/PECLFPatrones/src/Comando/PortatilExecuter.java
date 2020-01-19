package Comando;

import BaseDatos.BaseDatos;
import ClasesBase.Portatil;
import Factory.PortatilFactory;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Lanzador de comandos sobre la BBDD relacionados con los portátiles. 
 * @author marco
 */
public class PortatilExecuter {

    private final BaseDatos bbdd = BaseDatos.getInstancia();
    private final PortatilFactory portatilFactory = PortatilFactory.getInstancia();

    /**
     * Constructor por defecto.. 
     */
    public PortatilExecuter() {}

    /**
     * Inserta un portátil sobre la BBDD
     * @param portatil -> portátil a insertar.  
     */
    public void insertar(Portatil portatil) {

        String query = "INSERT INTO PORTATIL VALUES("
                + portatil.getIdPortatil() + ", "
                + portatil.isPrestado() + ", '"
                + portatil.getMarca() + "', "
                + portatil.getPulgadas() + ",' "
                + portatil.getEspecificaciones() + "')";

        bbdd.lanzarQuery(query);

    }

    /**
     * Recupera un portátil de la BBDD. 
     * @param idPortatil -> id del portátil a recuperar. 
     * @return 
     */
    public Portatil recuperar(int idPortatil) {
        String query = "SELECT * FROM PORTATIL WHERE (id_portatil = " + idPortatil + ")";

        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        Portatil devuelto = null;

        try {

            if (resultado.next()) {
                boolean prestado = resultado.getBoolean("prestado");
                String marca = resultado.getString("marca");
                int pulgadas = resultado.getInt("pulgadas");

                String especificaciones = resultado.getString("especificaciones");

                // Si he recuperado exitosamente creo portátil
                devuelto = new Portatil();
                devuelto.setIdPortatil(idPortatil);
                devuelto.setPrestado(prestado);
                devuelto.setMarca(marca);
                devuelto.setPulgadas(pulgadas);
                devuelto.setEspecificaciones(especificaciones);
            }
            
            resultado.close();
            consulta.close();
        } catch (Exception e) {
            System.out.println("Error al recuperar usuario: ");
            e.printStackTrace();
        }
        return devuelto;
    }

    /**
     * Devuelve la id más alta de portátil registrada en la BBDD.
     * Usado para establecer la ID de portátiles nuevos. 
     * @return 
     */
    public int getMaxIdPortatil() {
        // por si no hay ningún portátil aún
        int maxId = 1;

        String query = "SELECT MAX(id_portatil) AS ULTIMA_ID FROM PORTATIL";

        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);
        try {
            resultado = consulta.executeQuery(query);

            while (resultado.next()) {
                maxId = resultado.getInt("ULTIMA_ID");
            }

            resultado.close();
            consulta.close();

        } catch (Exception e) {
            System.out.println("Error al recuperar idPortátil: ");
            e.printStackTrace();
        }
        return maxId;
    }

    /**
     * Devuelve el portátil que tiene en préstamo un usuario, si es que tiene.
     *
     * @param dniUsuario
     * @return
     */
    public Portatil getPortatilPrestado(String dniUsuario) {
        Portatil prestado = null;
        String query = "SELECT * FROM TIENE_PRESTADO_PORTATIL where dni_usuario = '" + dniUsuario + "'";
        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        try {
            while (resultado.next()) {
                int idPortatilPrestado = resultado.getInt("id_portatil");
                prestado = recuperar(idPortatilPrestado);
            }

        } catch (Exception ex) {
            System.out.println("Error recuperando portátil prestado: ");
            System.out.println("Query: " + query);
            ex.printStackTrace();
        }
        return prestado;
    }
    
    
    /**
     * Devuelve el número de portátiles disponibles de la marca Asus
     * 
     * @return número de portátiles disponibles de la marca Asus
     */
    public int getNumeroDisponibleAsus() {
        int numeroPortatiles = 0;
        String query = "SELECT COUNT(*) AS NUM_PORTATILES FROM PORTATIL where marca = 'Asus' and prestado=false";
        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        try {
            if (resultado.next()) {
                numeroPortatiles = resultado.getInt("NUM_PORTATILES");
            }

        } catch (Exception ex) {
            System.out.println("Error recuperando portátil prestado: ");
            System.out.println("Query: " + query);
            ex.printStackTrace();
        }
        return numeroPortatiles;
    }
    
    /**
     * Devuelve el número de portátiles disponibles de la marca Apple
     * 
     * @return número de portátiles disponibles de la marca Apple
     */
    public int getNumeroDisponibleApple() {
        int numeroPortatiles = 0;
        String query = "SELECT COUNT(*) AS NUM_PORTATILES FROM PORTATIL where marca = 'Apple' and prestado=false";
        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        try {
            if (resultado.next()) {
                numeroPortatiles = resultado.getInt("NUM_PORTATILES");
            }

        } catch (Exception ex) {
            System.out.println("Error recuperando portátil prestado: ");
            System.out.println("Query: " + query);
            ex.printStackTrace();
        }
        return numeroPortatiles;
    }
    
    /**
     * Devuelve un arraylist de todos los portátiles no prestados. 
     * @return 
     */
    public ArrayList<Portatil> getDisponibles() {
        ArrayList<Portatil> disponibles = new ArrayList<>();
        String query = "SELECT * FROM PORTATIL where prestado=false";
        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        try {
            while (resultado.next()) {
                Portatil devuelto = new Portatil();
                
                // cojo datos
                boolean prestado = resultado.getBoolean("prestado");
                String marca = resultado.getString("marca");
                int pulgadas = resultado.getInt("pulgadas");
                int idPortatil = resultado.getInt("id_portatil");
                String especificaciones = resultado.getString("especificaciones");
                

                // los añado
                devuelto.setIdPortatil(idPortatil);
                devuelto.setPrestado(prestado);
                devuelto.setMarca(marca);
                devuelto.setPulgadas(pulgadas);
                devuelto.setEspecificaciones(especificaciones);
                
                // y lo añado al arraylist
            }

        } catch (Exception ex) {
            System.out.println("Error recuperando portátiles prestados: ");
            System.out.println("Query: " + query);
            ex.printStackTrace();
        }
        return disponibles;

    }
}

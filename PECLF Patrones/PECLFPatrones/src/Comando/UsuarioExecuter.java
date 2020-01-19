package Comando;

import BaseDatos.BaseDatos;
import ClasesBase.Administrador;
import ClasesBase.Persona;
import ClasesBase.Usuario;
import Factory.UsuarioFactory;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Lanzador de comandos relacionados con la creación y actualización de 
 * usuarios sobre la BBDD.  
 * @author marco
 */
public class UsuarioExecuter {

    private final BaseDatos bbdd = BaseDatos.getInstancia();
    private final UsuarioFactory usuarioFactory = UsuarioFactory.getInstancia();

    public UsuarioExecuter() {
    }

    /**
     * Inserta un usuario en la BBDD.
     *
     * @param usuario
     */
    public void insertarUsuario(Usuario usuario) {
        String telefono = String.valueOf(usuario.getTelefono());
        String edad = Integer.toString(usuario.getEdad());

        // Bools
        String esAdmin = "false"; // por ser usuario
        String castigado = Boolean.toString(usuario.estaCastigado());

        String fecha = "null";
        if (Boolean.valueOf(castigado)) {
            fecha = "'" + usuario.getFecha().toString() + "'";
        }

        String query = "INSERT INTO USUARIO VALUES("
                + "'" + usuario.getNombre() + "', "
                + "'" + usuario.getMail() + "', "
                + "'" + usuario.getPass() + "', "
                + "'" + usuario.getDni() + "', "
                + "" + edad + ", "
                + "" + telefono + ", "
                + "" + esAdmin + ", "
                + "" + castigado + ", "
                + "" + fecha + ")";

        bbdd.lanzarQuery(query);

    }

    /**
     * Inserta un admin en la BBDD.
     *
     * @param administrador
     */
    public void insertarAdministrador(Administrador administrador) {
        // Ints
        String telefono = String.valueOf(administrador.getTelefono());
        String edad = Integer.toString(administrador.getEdad());

        // Bools
        String esAdmin = "true";
        String castigado = "false";

        // Fecha
        String fecha = "null";
        System.out.println(fecha);

        String query = "INSERT INTO USUARIO VALUES("
                + "'" + administrador.getNombre() + "', "
                + "'" + administrador.getMail() + "', "
                + "'" + administrador.getPass() + "', "
                + "'" + administrador.getDni() + "', "
                + "" + edad + ", "
                + "" + telefono + ", "
                + "" + esAdmin + ", "
                + "" + castigado + ", "
                + "" + fecha + ")";

        bbdd.lanzarQuery(query);

    }

    /**
     * Dado el dni de un usuario, devuelve ese usuario o admin.
     *
     * @param dni -> dni del usuario o administrador.
     * @return -> el usuario o admin con ese dni.
     */
    public Persona recuperar(String dni) {
        String query = "SELECT * FROM USUARIO WHERE dni = '" + dni + "'";
        levantarCastigoSiProcede(dni);
        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        Persona devuelto = null;

        try {
            if (resultado.next()) {
                String nombre = resultado.getString("nombre");
                String pass = resultado.getString("pass");
                String correo = resultado.getString("mail");

                // ints
                int telefono = resultado.getInt("telefono");
                int edad = resultado.getInt("edad");

                // booleans
                boolean esAdmin = resultado.getBoolean("esadmin");
                boolean estaCastigado = resultado.getBoolean("esta_castigado");

                // fecha
                Date fecha = resultado.getDate("fecha_fin_castigo");

                ArrayList<Object> atributosUsuario = new ArrayList<>();
                atributosUsuario.add(nombre);
                atributosUsuario.add(correo);
                atributosUsuario.add(pass);
                atributosUsuario.add(dni);
                atributosUsuario.add(edad);
                atributosUsuario.add(telefono);
                atributosUsuario.add(esAdmin);
                atributosUsuario.add(estaCastigado);
                atributosUsuario.add(fecha);
                
                
                devuelto = usuarioFactory.crear(atributosUsuario);
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
     * Devuelve TRUE si el usuario denotado por el dni 
     * y la password existe en la BBDD. 
     * @param dni -> dni de usuario
     * @param password -> contraseña del usuario
     * @return -> true si el usuario es correcto, si no false.  
     */
    public boolean loginCorrecto(String dni, String password) {
        boolean esCorrecto = false;
        String query = "SELECT * FROM USUARIO WHERE dni = '" + dni + "' and pass = '" + password + "'";
        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        try {
            // Si hay resultado, entonces es correcto
            if (resultado.next()) {
                esCorrecto = true;
            }

            resultado.close();
            consulta.close();
        } catch (Exception e) {
            System.out.println("Error al verificar si existe usuario: ");
            e.printStackTrace();
        }

        return esCorrecto;
    }

    /**
     * Devuelve true si el usuario dado por el dni es un administrador. 
     * @param dni -> dni del presunto admin.  
     * @return 
     */
    public boolean esAdmin(String dni) {
        String query = "SELECT esadmin FROM USUARIO WHERE dni = '" + dni + "'";
        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        boolean esAdmin = false;

        try {
            if (resultado.next()) {
                esAdmin = resultado.getBoolean("esadmin");
            }
        } catch (SQLException ex) {
            System.out.println("Error leyendo si es admin: ");
            System.out.println("Query: " + query);
            ex.printStackTrace();
        }

        return esAdmin;
    }

    /**
     * Dado un dni, elimina al usuario con ese dni de la BBDD. 
     * @param dni 
     */
    public void eliminarUsuario(String dni) {
        String query = "DELETE FROM USUARIO WHERE DNI = '" + dni + "'";
        bbdd.lanzarQuery(query);
    }

    /**
     * Castiga a un usuario hasta la fecha dada.
     * @param dni -> dni del usuario a castigar- 
     * @param fechaFinCastigo -> fecha tope de castigo. 
     */
    public void castigarUsuario(String dni, Date fechaFinCastigo) {
        if (!esAdmin(dni)) {
            String query = "UPDATE USUARIO SET esta_castigado = true "
                    + ", fecha_fin_castigo = '" + fechaFinCastigo.toString() + "' "
                    + "WHERE dni='"+dni+"'";
            bbdd.lanzarQuery(query);
        }
    }

    private Date getFechaCastigo(String dni) {
        String query = "SELECT fecha_fin_castigo FROM USUARIO WHERE dni = '" + dni + "'";
        Statement consulta = bbdd.prepararConsulta();
        ResultSet resultado = bbdd.lanzarQuery(consulta, query);

        Date fechaFinCastigo = null;

        try {
            if (resultado.next()) {
                fechaFinCastigo = resultado.getDate("fecha_fin_castigo");
            }
        } catch (SQLException ex) {
            System.out.println("Error leyendo fecha fin de castigo: ");
            System.out.println("Query: " + query);
            ex.printStackTrace();
        }

        return fechaFinCastigo;
    }

    /**
     * Si ya ha pasado la fecha de castigo, levanta el castigo
     * del usuario dado en la BBDD. 
     * @param dni -> dni del usuario al que levantar el castigo si
     * ya ha pasado la fecha.  
     */
    public void levantarCastigoSiProcede(String dni) {
        Date fechaFinCastigo = getFechaCastigo(dni);
        if (fechaFinCastigo != null) {
            LocalDate finCastigo = fechaFinCastigo.toLocalDate();
            LocalDate hoy = LocalDate.now();

            int diferencia = (int) ChronoUnit.DAYS.between(hoy, finCastigo);
            // Si ya se ha pasado la fecha de castigo, se le levanta
            if (diferencia <= 0) {
                String query = "UPDATE USUARIO SET fecha_fin_castigo = null, esta_castigado = false where dni = '" + dni + "'";
                bbdd.lanzarQuery(query);
            }
        }

    }

}

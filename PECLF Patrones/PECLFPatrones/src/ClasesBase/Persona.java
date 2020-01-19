package ClasesBase;

/**
 *
 * Abstracción de una persona, que contiene valores compartidos entre el
 * administrador y el usuario.
 *
 * @author marco
 */
public abstract class Persona {

    protected String nombre, mail, pass, dni;
    protected int edad, telefono;

    public Persona(String nombre, String mail, String pass, String dni, int edad, int telefono) {
        this.nombre = nombre;
        this.mail = mail;
        this.pass = pass;
        this.dni = dni;
        this.edad = edad;
        this.telefono = telefono;
    }

}

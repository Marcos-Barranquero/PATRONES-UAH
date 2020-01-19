package ClasesBase;

/**
 * Administrador de la aplicación que puede gestionar altas,
 * bajas, préstamos y devoluciones. 
 * @author marco
 */
public class Administrador extends Persona {
    
    /**
     * Crea un administrador. 
     * @param nombre -> nombre del admin 
     * @param mail -> mail del admin
     * @param pass -> contraseña del admin
     * @param dni -> dni del admin
     * @param edad -> edad del admin
     * @param telefono  -> tlf. del admin
     */
    public Administrador(String nombre, String mail, String pass, String dni, int edad, int telefono) {
        super(nombre, mail, pass, dni, edad, telefono);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    
    
    
    
}

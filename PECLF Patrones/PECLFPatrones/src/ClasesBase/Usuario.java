package ClasesBase;

import java.sql.Date;

/**
 * Abstracción de un usuario de la aplicación.
 * @author marco
 */
public class Usuario extends Persona {
    
    private Date fechaFinCastigo;
    
    
    private boolean castigado;
    

    /**
     * Crea un usuario. Debe usarse con la factoría de usuarios.
     * @param nombre
     * @param mail
     * @param pass
     * @param dni
     * @param edad
     * @param telefono
     * @param castigado
     * @param fecha 
     */
    public Usuario(String nombre, String mail, String pass, String dni, int edad, int telefono, boolean castigado, Date fecha) {
        super(nombre, mail, pass, dni, edad, telefono);
        this.fechaFinCastigo = fecha;
        this.castigado = castigado;
    }
    
    
    // Getters, setters, etc. 

    public Date getFecha() {
        return fechaFinCastigo;
    }

    public void setFecha(Date fecha) {
        this.fechaFinCastigo = fecha;
    }

    public boolean estaCastigado() {
        return castigado;
    }

    public void setCastigado(boolean castigado) {
        this.castigado = castigado;
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

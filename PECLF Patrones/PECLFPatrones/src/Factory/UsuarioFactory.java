package Factory;

import ClasesBase.Administrador;
import ClasesBase.Persona;
import ClasesBase.Usuario;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Factoría de creación de usuarios.
 * @author Edu
 */
public class UsuarioFactory implements AbstractFactory {

    private static UsuarioFactory instanciaUnica;
    
    private UsuarioFactory() { }
    
    /**
     * Devuelve la instancia única de creación de usuarios.
     * @return 
     */
    public static UsuarioFactory getInstancia() {
        if(instanciaUnica == null){
            instanciaUnica = new UsuarioFactory();
        }
        
        return instanciaUnica;
    }
    
    /**
     * Crea un usuario o administrador, en función de los argumentos.
     * @param parametros -> args. de creación de usuario.
     * @return -> nuevo objeto de tipo Persona
     */
    @Override
    public Persona crear(ArrayList parametros) {
        if ((Boolean) parametros.get(6)) {
            return new Administrador((String) parametros.get(0), (String) parametros.get(1), (String) parametros.get(2), (String) parametros.get(3), (Integer) parametros.get(4), (Integer) parametros.get(5));
        } else {
            return new Usuario((String) parametros.get(0), (String) parametros.get(1), (String) parametros.get(2), (String) parametros.get(3), (Integer) parametros.get(4), (Integer) parametros.get(5), (Boolean) parametros.get(7), (Date) parametros.get(8));
        }
    }

}

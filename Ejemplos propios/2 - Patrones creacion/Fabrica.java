
// Responsable de devolver la instancia del objeto que queramos.
// El cliente solo interactúa con la fábrica.
public class FabricaDeConexiones
{
    // NEcesario poner una interfaz para hacer flexible el código
    public Conexion getConexion(String nombre)
    {
        Conexion conexion;
        if(nombre.equals("MySQL"))        {conexion = new ConexionMySQL();}
        else if(nombre.equals("Postgres"))        {conexion = new ConexionPostgres();}
        else conexion = new ConexionVacia();
        return conexion;
    }
}

public interface Conexion
{
    void conectar();
    void desconectar();
}

public class ConexionVacia implements Conexion
{
    public void conectar()
    {
        System.out.println("No se ha conectado a nada. ");
    }
    public void desconectar()
    {
        System.out.println("No se ha conectado a nada. ");
    }
}

public class ConexionMySQL implements Conexion
{
    public void conectar(){
        System.out.println("Me conecto a MySQL.");
    }
    public void desconectar()
    {
        System.out.println("Me desconecto de MySQL.");
    }
}

public class ConexionPostgres implements Conexion
{
    public void conectar(){
        System.out.println("Me conecto a Postgres.");
    }
    public void desconectar()
    {
        System.out.println("Me desconecto de Postgres.");
    }
}
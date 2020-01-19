import java.util.ArrayList;


/**
 * El patrón command permite tirar comandos sobre un objeto o clase. Además, los puede ir guardando en orden, de forma
 * que podemos deshacer lo hecho luego invirtiendo las operaciones. (Eso es el arraylist de ejecutorFichero). Tenemos:
 * 1) Una clase sobre la que se van a tirar comandos. 2) Una interfaz comandos pa que todos los comandos compartan
 * estructura. 3) Implementaciones de los comandos. 4) Una clase que ejecuta comandos y almacena el historial.
 */

/* clase sobre la que se tiran comandos */
public class Fichero
{
    private String nombre;

    public Fichero(String nombre)
    {
        this.nombre = nombre;
    }

    public String abrir()
    {
        return "Abriendo fichero " + nombre;
    }

    public String cerrar()
    {
        return "Cerrando fichero " + nombre;
    }

    public String escribir()
    {
        return "Escribiendo fichero " + nombre;
    }
}

/* Interfaz de comando */
public interface OperacionFichero
{
    String ejecutar();
}

/* Operaciones de fichero: abrir, cerrar, escribir */
public class AbrirFichero implements OperacionFichero
{
    private Fichero fichero;

    public AbrirFichero(Fichero fichero)
    {
        this.fichero = fichero;
    }

    @Override
    public String ejecutar()
    {
        return fichero.abrir();
    }
}

public class CerrarFichero implements OperacionFichero
{
    private Fichero fichero;

    public CerrarFichero(Fichero fichero)
    {
        this.fichero = fichero;
    }

    @Override
    public String ejecutar()
    {
        return fichero.cerrar();
    }
}

public class EscribirFichero implements OperacionFichero
{
    private Fichero fichero;

    public EscribirFichero(Fichero fichero)
    {
        this.fichero = fichero;
    }

    @Override
    public String ejecutar()
    {
        return fichero.escribir();
    }
}

/* Clase que ejecuta comandos y los almacena en un arraylist. */
public class EjecutorFichero
{
    // Historial de comandos
    private ArrayList<OperacionFichero> operaciones = new ArrayList<>();

    public String ejecutarOperacion(OperacionFichero op)
    {
        // Añado al historial
        operaciones.add(op);
        // Ejecuto operacion
        return op.ejecutar();
    }
}

// Cliente que ejecuta comandos a través del ejecutor de comandos. 
public class Cliente
{
    public static void main(String[] args)
    {
        EjecutorFichero ef = new EjecutorFichero();

        ef.ejecutarOperacion(new AbrirFichero(new Fichero("fichero1.txt")));
        ef.ejecutarOperacion(new EscribirFichero(new Fichero("fichero1.txt")));
        ef.ejecutarOperacion(new CerrarFichero(new Fichero("fichero1.txt")));
    }
}
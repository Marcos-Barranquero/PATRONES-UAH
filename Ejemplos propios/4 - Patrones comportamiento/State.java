/**
 * Patrón State: sirve para cambiar la funcionalidad según un estado, en tiempo de ejecución. Necesitamos:
 * 1) Interfaz estado que defina la funcionalidad del estado.
 * 2) Implementaciones de varios estados con funcionalidad distinta.
 * 3) Clase que tenga estado y algún método que delegue la funcionalidad en ese estado. 
 */

 /* En el ejemplo, el menú del the witcher 3 varía en función del estado de tu partida. */ 

 /* Interfaz del estado del juego */ 
public interface EstadoJuego
{
    void ejecutarMenu();
}

/* Estado implementado */
public class JuegoNuevo implements EstadoJuego
{
    @Override
    public void ejecutarMenu()
    {
        System.out.println("Menú: ");
        System.out.println("1) Nueva partida");
        System.out.println("2) Configuración");
        System.out.println("3) Salir");
    }
}

/* Estado imlementado */ 
public class JuegoEmpezado implements EstadoJuego
{
    @Override
    public void ejecutarMenu()
    {
        System.out.println("Menú: ");
        System.out.println("1) Nueva partida");
        System.out.println("2) Continuar partida");
        System.out.println("3) Configuración");
        System.out.println("4) Salir");
    }
}

/* Estado imlementado */
public class JuegoFinalizado implements EstadoJuego
{
    @Override
    public void ejecutarMenu()
    {
        System.out.println("Menú: ");
        System.out.println("1) Nueva partida");
        System.out.println("2) Nueva partida plus");
        System.out.println("3) Configuración");
        System.out.println("4) Salir");
    }
}

/* Clase que tiene estado y delega alguna funcionalidad en el */
public class TheWitcher3
{
    private EstadoJuego estado;

    public TheWitcher3()
    {
        this.estado = new JuegoNuevo();
    }

    public void empezarPartida()
    {
        this.estado = new JuegoEmpezado();
    }

    public void finPartida()
    {
        this.estado = new JuegoFinalizado();
    }

    public void menu()
    {
        estado.ejecutarMenu();
    }
}

/* Cliente */ 
public class Juego
{
    public static void main(String[] args)
    {
        TheWitcher3 miPartida = new TheWitcher3();

        miPartida.menu();

        miPartida.empezarPartida();

        miPartida.menu();
    }
}
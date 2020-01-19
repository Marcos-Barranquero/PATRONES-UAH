import java.util.ArrayList;

/**
 * El patrón iterator proporciona una interfaz para recorrer una colección.
 * El iterador debe implementar las funciones getActual, getSiguiente, ver si hay más, y adicionalmente
 * lo que se quiera.
 * Debemos tener:
 * 1) Una colección iterable. (ordenada).
 * 2) Un "agregador" que contiene la colección.
 * 3) Un iterador que podemos generar desde el agregador, que tiene acceso a la
 * colección del agregador y que implementa las funciones antes descritas.
 */

/* Interfaz del agregador */
public interface Iterador
{
    Object getElemento();

    boolean hayMas();

    Object getPrimero();

    Object getSiguiente();
}

/* Elemento de la coleccción */
public class Universitario
{
    private String nombre, carrera;

    public Universitario(String nombre, String carrera)
    {
        this.nombre = nombre;
        this.carrera = carrera;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getCarrera()
    {
        return carrera;
    }

    public void setCarrera(String carrera)
    {
        this.carrera = carrera;
    }
}

/* Implementación del iterador */
public class IteradorUniversitario implements Iterador
{

    AgregadorUniversitarios agregadorUniversitarios;
    private int indice = 0;

    public IteradorUniversitario(AgregadorUniversitarios agregadorUniversitarios)
    {
        this.agregadorUniversitarios = agregadorUniversitarios;
    }

    @Override
    public Object getElemento()
    {
        return agregadorUniversitarios.universitarios.get(indice);
    }

    @Override
    public boolean hayMas()
    {
        boolean hayMas = true;
        if(agregadorUniversitarios.universitarios.size() == indice-1)
        {
            hayMas = false;
        }
        return hayMas;
    }

    @Override
    public Object getPrimero()
    {
        indice=0;
        return agregadorUniversitarios.universitarios.get(indice);
    }

    @Override
    public Object getSiguiente()
    {
        indice+=1;
        return agregadorUniversitarios.universitarios.get(indice);
    }
}

/* Agregador con la colección */
public class AgregadorUniversitarios
{
    public ArrayList<Universitario> universitarios;

    public AgregadorUniversitarios(ArrayList<Universitario> universitarios)
    {
        this.universitarios = universitarios;
    }

    public Iterador crearIterador()
    {
        return new IteradorUniversitario(this);
    }
}

/* Cliente que ejecuta el iterador */ 
public class Cliente
{
    public static void main(String[] args)
    {
        Universitario u1 = new Universitario("Marcos","GII");
        Universitario u2 = new Universitario("Edu","GII");

        ArrayList<Universitario> universitarios = new ArrayList<>();
        universitarios.add(u1);
        universitarios.add(u2);

        AgregadorUniversitarios au = new AgregadorUniversitarios(universitarios);

        Iterador iterador = au.crearIterador();

        while (iterador.hayMas())
        {
            System.out.println(iterador.getElemento().toString());
            iterador.getSiguiente();
        }

    }
}

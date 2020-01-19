/**
 * El patrón interfaz consiste en implementar una interfaz. (Que inesperado, ¿eh?).
 */


public interface Interfaz
{
    // La interfaz define la estructura mínima de una clase que la implemente

    public void f();

    public void g();
}

public class Clase implements Interfaz
{
    // La clase implementa los métodos de la interfaz

    @Override
    public void f()
    {
        System.out.println("Ejecuto f().");
    }

    @Override
    public void g()
    {
        System.out.println("Ejecuto g().");

    }
}




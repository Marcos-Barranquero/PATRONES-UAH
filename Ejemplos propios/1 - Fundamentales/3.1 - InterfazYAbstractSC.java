/**
 * Podemos combinar las interfaces y las superClases Abstractas. ¿A que no
 * se te había ocurrido?
 */

public interface Interfaz
{
    // La interfaz define la estructura mínima de una clase que la implemente

    public void f();

    public void g();
}


public abstract class SuperClaseAbstracta implements Interfaz
{
    // Implemento por completo las operaciones comunes
    public void f()    {        System.out.println("Hago algo concreto.");    }

    // Defino las operaciones concretas como abstractas
    public abstract void g();
}

public class ClaseConcreta extends SuperClaseAbstracta
{

    // Implemento las operaciones dinámicas
    @Override
    public void g()    {        System.out.println("Implemento mi versón de la operación dinámica. ");    }
}


/**
 * Realmente esto es una estafa, no es un patrón, sino la combinación de los dos
 * anteriores.
 */

public interface Interfaz
{
    public void f();

    public void g();
}

public class Delegada implements Interfaz
{
    // Contiene la funcionalidad
    public void f()
    {
        System.out.println("Ejecuto f()");
    }

    public void g()
    {
        System.out.println("Ejecuto g()");
    }
}

public class Delegadora
{
    // Delega parte de su funcionalidad en otra clase
    private Delegada delegada = new Delegada();
    public void f()
    {
        // en vez de ejecutar por mi misma el método,
        // delego la funcionalidad en mi clase delegada:
        delegada.f();
    }

    public void g()
    {
        delegada.g();
    }
}

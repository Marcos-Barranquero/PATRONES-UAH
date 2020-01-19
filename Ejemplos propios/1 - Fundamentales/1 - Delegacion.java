/**
 * El patrón delegación consiste en que una clase A delega parte de su funcionalidad a una clase B.
 * - La clase delegada contiene la funcionalidad.
 * - La clase delegadora llama a la funcionalidad de la delegada.
 */

public class Delegada
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

public class EjemploRapido
{
}

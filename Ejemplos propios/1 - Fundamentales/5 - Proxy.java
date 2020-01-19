/**
 * La gracia de este patrón es utilizar una clase intermedia llamada proxy para no tener que pasar
 * toda la clase con la que queremos interactuar. Tiene 3 componentes:
 * 1) Interfaz: sirve pa comunicar el proxy y la implementación.
 * 2) Implementación: contiene la funcionalidad y es la clase sobre la que se delega.
 * 3) Proxy: clase delegadora con la que interactúa el mundo exterior.
 */

// Interfaz de envío de cartas
public interface EnviarCarta
{
    void enviar();
}

// Con este bicho solo interactua el Proxy, NO el usuario final.
public class EnviarCartaImplementado implements EnviarCarta
{
    @Override
    // Funcionalidad a ser delegada
    public void enviar()
    {
        System.out.println("Carta enviada. ");
    }
}

// Con este bicho interactua el usuario final.
public class EnviarCartaProxy implements EnviarCarta
{
    private static EnviarCarta enviarCarta;

    public EnviarCartaProxy()
    {
        enviarCarta = new EnviarCartaImplementado();
    }

    // Delegamos en la implementación
    @Override
    public void enviar()
    {
        enviarCarta.enviar();
    }
}
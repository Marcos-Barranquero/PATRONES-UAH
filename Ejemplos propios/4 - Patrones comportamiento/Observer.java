/**
 * El patrón observer añade una clase observadora a otra.
 * La clase observada contiene a la observadora, y la notificará cuando cambie su estado.
 * La clase observadora implementa un método actualizar que mira el estado de la clase observada.
 * Vease que Observador contiene a Sujeto y Sujeto contiene a Observador. (O un arraylist si hay varios).
 * Necesitamos:
 * 1) Abstract class observador que contiene a la clase observada y un método actualizar abstracto.
 * 2) La implementación de la clase observadora con su método actualziar que lee la clase observada.
 * 3) Una clase sujeto que notifique al observador cuando se llame a los métodos que cambien su estado.
 */


/* Observador abstracto */
public abstract class Observador
{
    protected Sujeto sujeto;
    public abstract void actualizar();

}
/* Observador implementado */
public class ObservadorImplementado extends Observador
{
    public ObservadorImplementado(Sujeto sujeto)
    {
        this.sujeto = sujeto;
        sujeto.agregarObservador(this);
    }

    @Override
    public void actualizar()
    {
        boolean baila = this.sujeto.getEstado();
        if(baila)
        {
            System.out.println("Ya está el bachatas bailando.");
        }
        else
        {
            System.out.println("El bachatas deja de bailar. ");
        }
    }
}

/* Sujeto observado */
public class Sujeto
{
    private Observador observador;

    private boolean baila;

    public void agregarObservador(Observador observador)
    {
        this.observador = observador;
        this.baila = false;
    }

    public void bailar()
    {
        System.out.println("Estoy bailando. ");
        this.baila = true;
        notificarObservador();
    }

    public void dejarDeBailar()
    {
        System.out.println("Ya no bailo. ");
        this.baila = false;
        notificarObservador();
    }

    public void notificarObservador()
    {
        observador.actualizar();
    }

    public boolean getEstado()
    {
        return this.baila;
    }
}

public class ArquitecturaWeb
{
    public static void main(String[] args)
    {
        Sujeto bachatas = new Sujeto();

        Observador pegatinas = new ObservadorImplementado(bachatas);

        bachatas.bailar();
    }
}
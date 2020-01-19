/**
 * Este patrón utiliza una clase abstracta pa organizar el código. Ya está,
 * no hay más. ¿Decepcionado?
 */


public abstract class AbstractSuperclass
{
    // Implemento por completo las operaciones comunes
    public void operacionComun()
    {
        System.out.println("Hago algo concreto.");
    }

    // Defino las operaciones concretas como abstractas
    public abstract void operacionDinamica();
}

public class ClaseConcreta extends AbstractSuperclass
{

    // Implemento las operaciones dinámicas
    @Override
    public void operacionDinamica()
    {
        System.out.println("Implemento mi versón de la operación dinámica. ");
    }
}


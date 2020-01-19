/**
 * El patrón visitor es útil cuando tenemos varias clases diferentes relacionadas
 * y tenemos que ejecutar operaciones sobre estas de forma estandarizada.
 * Creamos una clase Visitor que "visita" cada una de las clases aplicando
 * la funcionalidad necesaria según la clase que sea. (Sobrecarga de métodos).
 * Tenemos:
 * 1) (Opcional) Interfaz de las clases relacionadas
 * 2) Implementaciones de las clases relacionadas. Cada una de ellas tendrá un método
 * aceptar(Visitor) que recibe al visitor y se pasa a si misma al visitor pa que haga
 * lo que tenga que hacer.
 * 3) Visitor con un método para cada tipo de clase a visitar.
 */

/* Interfaz de clases comunes, opcional */
public interface Departamento
{
    // Método para aceptar al visitor
    void aceptar(Visitor visitor);
}

/* Clase relacionada nº 1 */
public class DepartamentoFinanzas implements Departamento
{
    private int notaDepartamento;

    public DepartamentoFinanzas(int notaDepartamento)
    {
        this.notaDepartamento = notaDepartamento;
    }

    @Override
    public void aceptar(Visitor visitor)
    {
        visitor.visitar(this);
    }

    public int getNotaDepartamento()
    {
        return notaDepartamento;
    }
}

/* Clase relacionada nº 2 */
public class DepartamentoVentas implements Departamento
{
    private int mediaDepartamento;

    public DepartamentoVentas(int mediaDepartamento)
    {
        this.mediaDepartamento = mediaDepartamento;
    }

    @Override
    public void aceptar(Visitor visitor)
    {
        visitor.visitar(this);
    }

    public int getMediaDepartamento()
    {
        return mediaDepartamento;
    }
}

/* Visitor con un método distinto para cada clase relacionada */
public class Visitor
{
    public String visitar(DepartamentoFinanzas departamentoFinanzas)
    {
        return Integer.toString(departamentoFinanzas.getNotaDepartamento());
    }

    public String visitar(DepartamentoVentas departamentoVentas)
    {
        return Integer.toString(departamentoVentas.getMediaDepartamento());
    }
}




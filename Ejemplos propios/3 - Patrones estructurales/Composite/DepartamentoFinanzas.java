// Hoja
public class DepartamentoFinanzas implements Departamento
{
    private Integer id;
    private String nombre;

    @Override
    public void imprimeNombre()
    {
        System.out.println("Este es el dpto. de finanzas");
    }

}

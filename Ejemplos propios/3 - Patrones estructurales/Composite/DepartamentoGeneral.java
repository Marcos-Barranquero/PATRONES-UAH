import java.util.ArrayList;

// Componente compuesto
public class DepartamentoGeneral implements Departamento
{
    private Integer id;
    private String nombre;
    private ArrayList<Departamento> departamentosHijos;

    public DepartamentoGeneral(Integer id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
        departamentosHijos = new ArrayList<>();
    }

    @Override
    public void imprimeNombre()
    {
        for ( Departamento departamento: departamentosHijos)
        {
            departamento.imprimeNombre();
        }
    }




}

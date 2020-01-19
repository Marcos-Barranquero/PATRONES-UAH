package EjemploEmpleados;

import java.util.ArrayList;

public class Jefe extends Trabajador
{
    private ArrayList<Trabajador> subordinados;

    public Jefe(ArrayList<Trabajador> subordinados)
    {
        this.subordinados = new ArrayList<>();
    }


    @Override
    public void addSubordinado(Trabajador trabajador)
    {
        subordinados.add(trabajador);
    }

    @Override
    public void eliminarSubordinado(Trabajador trabajador)
    {
        subordinados.remove(trabajador);
    }
}

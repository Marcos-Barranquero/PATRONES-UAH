package EjemploEmpleados;

public abstract class Trabajador
{
    private String nombre;

    public abstract void addSubordinado(Trabajador trabajador);
    public abstract void eliminarSubordinado(Trabajador trabajador);


}

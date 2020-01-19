import java.util.ArrayList;
/**
 * Este patrón utiliza interfaces vacías pa poder clasificar las clases.
 * Si una clase implementa esa interfaz, pues esa clase es de ese tipo de interfaz.
 * Y si no, pues no. Jajaja.
 */

// Esta interfaz se le asigna a los profesores de mierda.
public interface ProfesorDeMierda {}

public class FicusMan implements ProfesorDeMierda
{
    // FicusMan es un profesor de mierda.
    public boolean darClase(){return false}
}

public class RafaelRicoLopez
{
    // Rico no implementa profesor de mierda, por lo que NO es un profesor de mierda.
}

public class Distinguidor
{
    private static FicusMan f1 = new FicusMan();
    private static RafaelRicoLopez r1 = new RafaelRicoLopez();
    private static ArrayList<Object> profesores = new ArrayList<>();

    public static void main(String[] args)
    {
        profesores.add(f1);profesores.add(r1);
        for(Object p: profesores)
        {
            if(p instanceof ProfesorDeMierda)
            {
                System.out.println(p.toString() + " es un profesor de mierda. ");
            }
        }
    }
}

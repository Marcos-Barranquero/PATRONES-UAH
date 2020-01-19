package FormasYColores;

public class Cuadrado extends Forma
{

    public Cuadrado(Color color)
    {
        super(color);
    }

    @Override
    public String pintar()
    {
        return "Cuadrado" + super.getColor().pintarColor();
    }
}

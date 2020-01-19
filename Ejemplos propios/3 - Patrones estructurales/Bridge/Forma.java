package FormasYColores;

public abstract class Forma
{
    private Color color;

    public Forma(Color color)
    {
        this.color = color;
    }

    public abstract String pintar();

    public Color getColor()
    {
        return color;
    }
}


package FormasYColores;

public class PruebaFormasYColores
{
    public static void main(String[] args)
    {
        Forma cuadrado = new Cuadrado(new Azul());
        System.out.println(cuadrado.pintar());
    }
}

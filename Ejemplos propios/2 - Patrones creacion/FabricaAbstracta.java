/**
 * Fábrica de fábrias. Para un objeto, hay que indicar qué fábrica instancia un objeto.
 */
public interface FabricaAbstracta
{
    Forma getForma(String forma);
    Color getColor(String color);
}

// Productos de la fábrica
public interface Forma{/*...*/}

public interface Color{/*...*/}

// Implementaciones

public class Cuadrado implements Forma{/*...*/}

public class Circulo implements Forma{/*...*/}

public class Azul implements Color{/*...*/}

public class Rojo implements Color{/*...*/}


// Fábricas
public class FabricaColor implements FabricaAbstracta
{
    @Override
    public Forma getForma(String forma)
    {
        return null;
    }

    @Override
    public Color getColor(String color)
    {
        if(color.equals("Azul")) return new Azul();
        else if(color.equals("Rojo")) return new Rojo();
        return null;
    }
}

public class FabricaForma implements FabricaAbstracta
{

    @Override
    public Forma getForma(String forma)
    {
        if(forma.equals("Cuadrado")) return new Cuadrado();
        else if(forma.equals("Circulo"))return new Circulo();
        return null;
    }


    @Override
    public Color getColor(String color)
    {
        return null;
    }
}

// Fábrica de fábricas
public class ProductorFabrica
{
    public static FabricaAbstracta getFabrica(String tipo)
    {
        if(tipo.equals("Forma")) return new FabricaForma();
        else if(tipo.equals("Color")) return new FabricaColor();
        return null;
    }
}

// Programa principal.
public class Main
{
    public static void main(String[] args)
    {
        // Como ProductorFabrica tiene un método estático no hace falta instanciarlo
        FabricaAbstracta fabricaColor = ProductorFabrica.getFabrica("Color");
        FabricaAbstracta fabricaForma = ProductorFabrica.getFabrica("Forma");

        Color color = fabricaColor.getColor("Azul");
        Forma forma = fabricaForma.getForma("Cuadrado");
    }
}
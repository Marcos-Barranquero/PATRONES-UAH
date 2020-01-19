import Builders.Pizza;
import Builders.PizzaBuilder;

public class Cocinero
{
    private PizzaBuilder builder;

    public Cocinero(PizzaBuilder builder)
    {
        this.builder = builder;
    }

    public Pizza getPizza()
    {
        return builder.getPizza();
    }

    public void crearPizza()
    {
        builder.crearPizza();
        builder.crearIngredientes();
        builder.crearMasa();
    }

    public void setBuilder(PizzaBuilder builder)
    {
        this.builder = builder;
    }
}

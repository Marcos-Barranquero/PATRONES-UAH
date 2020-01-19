package Builders;

public abstract class PizzaBuilder
{
    protected Pizza pizza;

    public Pizza getPizza()
    {
        return pizza;
    }

    public void crearPizza()
    {
        pizza = new Pizza();
    }

    public abstract void darNombre();
    public abstract void crearMasa();
    public abstract void crearIngredientes();
}

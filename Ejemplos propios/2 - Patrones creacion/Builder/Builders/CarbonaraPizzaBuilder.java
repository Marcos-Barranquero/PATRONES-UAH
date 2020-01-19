package Builders;

public class CarbonaraPizzaBuilder extends PizzaBuilder
{

    @Override
    public void darNombre()
    {
        pizza.setNombre("Carbonara");
    }

    @Override
    public void crearMasa()
    {
        pizza.setMasa("Gorda");
    }

    @Override
    public void crearIngredientes()
    {
        pizza.setIngredientres("Aceitunas");
    }
}

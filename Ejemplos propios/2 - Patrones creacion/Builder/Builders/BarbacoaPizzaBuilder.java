package Builders;

public class BarbacoaPizzaBuilder extends PizzaBuilder
{

    @Override
    public void darNombre()
    {
        pizza.setNombre("Barbacoa");
    }

    @Override
    public void crearMasa()
    {
        pizza.setMasa("Fina");
    }

    @Override
    public void crearIngredientes()
    {
        pizza.setIngredientres("Bacon");

    }
}

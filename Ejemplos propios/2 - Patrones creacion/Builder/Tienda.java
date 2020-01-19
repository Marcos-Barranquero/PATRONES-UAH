import Builders.BarbacoaPizzaBuilder;
import Builders.CarbonaraPizzaBuilder;
import Builders.Pizza;
import Builders.PizzaBuilder;

public class Tienda
{
    public static void main(String[] args)
    {
        PizzaBuilder builderBarbacoa = new BarbacoaPizzaBuilder();
        Cocinero cocinero = new Cocinero(builderBarbacoa);

        // El cocinero crea la pizza
        cocinero.crearPizza();

        // El cliente la recoge
        Pizza pizzaRecogida = cocinero.getPizza();

        // Ahora queremos quecree pizza de Carbonara
        PizzaBuilder builderCarbonara = new CarbonaraPizzaBuilder();
        cocinero.setBuilder(builderCarbonara);

        // El cocinero crea la pizza
        cocinero.crearPizza();

        // Y el cliente la recoge
        Pizza pizzaRecogida2 = cocinero.getPizza();


    }
}

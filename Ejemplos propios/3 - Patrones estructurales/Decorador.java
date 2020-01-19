/**
 * El patrón decorador permite aumentar la funcionalidad de un objeto sin tener que recurrir a la herencia.
 * Necesitamos:
 * 1) Interfaz del objeto
 * 2) Implementación del objeto base (que implementa la interfaz)
 * 3) Clase abstracta decorador que contiene un elemento interfaz
 * 4) Decoradores que heredan de la clase abstracta y que implementan el objeto
 */

 /* Interfaz */ 
public interface ArbolNavidad {
    String decorar();
}

/* Árbol navidad base */
public class ArbolNavidadBase implements ArbolNavidad {
    @Override
    public String decorar() {
        return "Arbolito de navidad";
    }
}

/* Decorador abstracto */
public abstract class DecoradorArbol {
    private ArbolNavidad arbolNavidad;

    public DecoradorArbol(ArbolNavidad arbolNavidad) {this.arbolNavidad = arbolNavidad;}

    public String decorar() {return arbolNavidad.decorar();}
}

/* Decorador concreto: luces */
public class LucesNavidad extends DecoradorArbol implements ArbolNavidad {

    public LucesNavidad(ArbolNavidad arbolNavidad) { super(arbolNavidad); }

    private String decoradorLuces() { return " con luces navideñas"; }

    @Override
    public String decorar() {return super.decorar() + decoradorLuces();}
}

/* Decorador concreto: estrellita */
public class EstrellaNavidad extends DecoradorArbol implements ArbolNavidad {
    public EstrellaNavidad(ArbolNavidad arbolNavidad) { super(arbolNavidad); }

    public String decorarEstrella() { return " con estrellita en la punta"; }

    @Override
    public String decorar() { return super.decorar() + decorarEstrella(); }
}

/* Prueba */
public class PruebaArbolNavidad {

    public static void main(String[] args) {
        /* Arbol decorado con luces y estrella */
        ArbolNavidad arbol1 = new EstrellaNavidad(new LucesNavidad(new ArbolNavidadBase()));
        System.out.println(arbol1.decorar());
        // Arbolito de navidad con luces navideñas con estrellita en la punta
    }
}

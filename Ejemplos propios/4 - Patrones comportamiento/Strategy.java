import java.util.Arrays;
import java.util.Scanner;

/**
 * El patrón estrategia ofrece varias estrategias distintas para resolver un
 * problema y que no se sabe cual de ellas se va a elegir hasta tiempo de
 * ejecución. Cuenta con: 1) Abstract class que engloba las estrategias. 2)
 * Estrategias concreta que heredan de esa clase abstracta. 3) Un cliente que
 * elige estrategia.
 */

/* Clase abstracta que engloba las estrategias */
public abstract class Ordenar {
    public abstract int[] ordenarArray(int[] array);

}

/* Implementación concreta de estrategia */
public class OrdenarQuickSort extends Ordenar {
    @Override
    public int[] ordenarArray(int[] array) {
        Arrays.sort(array);
        return array;
    }
}

/* Implementación concreta de estrategia */
public class OrdenarBurbuja extends Ordenar {
    @Override
    public int[] ordenarArray(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (array[j] > array[j + 1]) {
                    // swap arr[j+1] and arr[i]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
        return array;
    }
}

/* Cliente */
public class PruebaOrdenar {
    public static void main(String[] args) {
        int[] array = { 3, 5, 2, 1, 4 };
        int[] arrayOrdenado = new int[5];
        // Eligo método de ordenación en tpo. de ejecución!

        System.out.println("Q -> quicksort; B -> burbuja. ¿Qué algoritmo quieres?");
        String algoritmo = (new Scanner(System.in)).nextLine();

        if (algoritmo.equals("Q")) {
            arrayOrdenado = new OrdenarQuickSort().ordenarArray(array);
        } else if (algoritmo.equals("B")) {
            arrayOrdenado = new OrdenarBurbuja().ordenarArray(array);
        }
    }
}
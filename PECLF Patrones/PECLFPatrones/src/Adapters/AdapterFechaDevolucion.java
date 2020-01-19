package Adapters;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Adapter que coge fecha de préstamo (para libros) y genera la fecha máxima de devolución.
 * Es un Adaptador no basado en interfaz. 
 * @author marco
 */
public class AdapterFechaDevolucion {
    private int dia, mes, ano;

    /**
     * Dado un dia, mes, año en el que se toma prestado un libro, 
     * calcula la fecha máxima de devolución de ese libro. 
     * @param dia -> dia del préstamo.
     * @param mes -> mes del préstamo.
     * @param ano -> ano del préstamo.
     */
    public AdapterFechaDevolucion(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }
    
    /**
     * Devuelve la fecha de devolución máxima. 
     * @return -> fecha máxima de devolución. 
     */
    public Date getDateDevolucion()
    {
        LocalDate fechaRecogida = LocalDate.of(ano, mes, dia);
        
        // Se da un mes de tiempo de préstamo
        LocalDate fechaMaximaDevolucion = fechaRecogida.plusMonths(1);
        
        // Se parsea a date
        Date fechaDevolucion = Date.valueOf(fechaMaximaDevolucion);
        
        return fechaDevolucion;
    }
    
}

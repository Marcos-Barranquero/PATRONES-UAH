package Adapters;

import java.sql.Time;
import java.time.LocalTime;

/**
 * Adapter que coge hora de préstamo (para portátiles) y genera la hora máxima
 * de devolución. Es un Adaptador no basado en interfaz.
 *
 * @author marco
 */
public class AdapterHoraDevolucion {

    private int hora, minutos;

    /**
     * Dado una hora y minutos en el que se toma prestado un portátil, calcula
     * la hora máxima de devolución de ese portátil.
     *
     * @param hora
     * @param minutos
     */
    public AdapterHoraDevolucion(int hora, int minutos) {
        this.hora = hora;
        this.minutos = minutos;
    }

    /**
     * Devuelve la hora máxima de devolución de ese portátil.
     *
     * @return -> hora maxima de devolución.
     */
    public Time getTimeDevolucion() {
        // la hora máxima de devolución de portátiles son las 20:30
        // El tiempo máximo de préstamo son 5 horas.
        LocalTime horaMaximaDevolucion = LocalTime.of(20, 30);

        // Creo hora de recogida
        LocalTime horaRecogida = LocalTime.of(hora, minutos);

        LocalTime horaDevolucion = null;

        LocalTime horaApertura = LocalTime.of(8, 0);

        // Si no se pasa de la hora máxima, le pongo la devolución
        // en 5 horas desde que lo ha cogido. Si se pasa, le pongo
        // que debe devolverlo a las 20:30. 
        if (horaRecogida.plusHours(5).isBefore(horaMaximaDevolucion) && horaRecogida.plusHours(5).isAfter(horaApertura)) {
            horaDevolucion = horaRecogida.plusHours(5);
        } else {
            horaDevolucion = horaMaximaDevolucion;
        }

        // Adapto el tipado para la BBDD
        Time timeDevolucion = Time.valueOf(horaDevolucion);

        return timeDevolucion;
    }

}

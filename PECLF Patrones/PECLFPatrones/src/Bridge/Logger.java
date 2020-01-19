package Bridge;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase dedicada a Loguear toda la actividad en la aplicación. 
 * @author Edu
 */
@FunctionalInterface
public interface Logger {

    /**
     * Dado un mensaje, escribe la fecha y hora, el nivel de logeo configurado
     * para ese momento, y el mensaje que se le pasa como parámetro.
     * 
     * El mensaje se mostrará tanto por pantalla, como escrito en el fichero de log
     * correspondiente al día en el que se genera el mensaje en la carpeta de logs 
     * creada en el directorio de la aplicación.
     * 
     * @param mensaje -> mensaje que se desea logear en el sistema
     */
    public void log(String mensaje);

    /**
     * Logea los eventos con la etiqueta de info
     *
     * @return -> Logger de tipo Info
     */
    public static Logger Info() {
        return mensaje -> {
            // Saco el mensaje por pantalla
            System.out.println("INFO [" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + "]: " + mensaje);

            // Si no existe el directorio de logs, lo creo
            new File("./logs").mkdir();
            // Escribo el mensaje al archivo de log correspondiente
            try (FileWriter fw = new FileWriter("./logs/" + DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now()) + ".log", true)) {
                fw.write("INFO [" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + "]: " + mensaje + "\r\n");
            } catch (IOException ex) {
                System.out.println("Error al escribir en el archivo de log: " + ex.getMessage());
            }
        };
    }

    /**
     * Logea los eventos con la etiqueta de warning
     *
     * @return -> Logger de tipo Warning
     */
    public static Logger Warning() {
        return mensaje -> {
            // Saco el mensaje por pantalla
            System.out.println("WARNING [" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + "]: " + mensaje);

            // Si no existe el directorio de logs, lo creo
            new File("./logs").mkdir();
            // Escribo el mensaje al archivo de log correspondiente
            try (FileWriter fw = new FileWriter("./logs/" + DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now()) + ".log", true)) {
                fw.write("WARNING [" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + "]: " + mensaje + "\r\n");
            } catch (IOException ex) {
                System.out.println("Error al escribir en el archivo de log: " + ex.getMessage());
            }
        };
    }

    /**
     * Logea los eventos con la etiqueta de error
     *
     * @return -> Logger de tipo Error
     */
    public static Logger Error() {
        return mensaje -> {
            // Saco el mensaje por pantalla
            System.out.println("ERROR [" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + "]: " + mensaje);

            // Si no existe el directorio de logs, lo creo
            new File("./logs").mkdir();
            // Escribo el mensaje al archivo de log correspondiente
            try (FileWriter fw = new FileWriter("./logs/" + DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now()) + ".log", true)) {
                fw.write("ERROR [" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + "]: " + mensaje + "\r\n");
            } catch (IOException ex) {
                System.out.println("Error al escribir en el archivo de log: " + ex.getMessage());
            }
        };
    }
}

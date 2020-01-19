package Main;

import BaseDatos.BaseDatos;
import Bridge.Logger;
import GUI.Inicio;

/**
 *
 * Clase principal de la aplicación, lanza la aplicación
 * 
 * @author Edu
 */
public class Main {

    /**
     * Main de la aplicación, establece el "look and feel" de la interfaz Swing
     * a Nimbus, inicializa el log, lanza la ventana de interfaz gráfica
     * principal, y lanza un hilo que esté atento a cuando se intente cerrar
     * la aplicación para que quede reflejado en el log el cierre del log y se
     * desconecte la base de datos.
     * @param args -> argumentos recibidos al llamar al programa
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        Logger.Info().log("Sesión de log iniciada");
        new Inicio().setVisible(true);
         
        // Se añade un hilo que esté esperando a que se intente cerrar la aplicación
        // para cerrar la conexión con la base de datos y la sesión de log
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                BaseDatos.getInstancia().cerrarConexion();
                Logger.Info().log("Sesión de log cerrada");
            }
        });
    }
}

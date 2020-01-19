package GUI;

import ClasesBase.Administrador;
import ClasesBase.Portatil;
import ClasesBase.Usuario;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 * Interfaz de usuario para administradores que permite la gestión del préstamo
 * de portátiles
 *
 * @author Edu
 */
public class PrestarPortatil extends javax.swing.JFrame {

    private final Administrador admin;
    private final Fachada fachada = Fachada.getInstancia();

    public PrestarPortatil(Administrador admin) {
        this.admin = admin;
        initComponents();
        cerrarCampos();
    }

    /**
     * Marca los campos de usuario que no pueden ser editados como tal
     */
    private void cerrarCampos() {
        tfPulgadas.setEditable(false);
        tfMarca.setEditable(false);
        textoCaracteristicas.setEditable(false);
    }

    /**
     * Carga los datos del portátil en base a su ID
     *
     * @param idPortatil -> ID del portátil al que pertenecen los datos
     */
    private void cargarCampos(String idPortatil) {
        int idPortatilInt = -1;
        boolean correcto = true;
        Portatil portatil = null;

        try {
            idPortatilInt = Integer.valueOf(idPortatil);
            if (idPortatilInt < 1) {
                correcto = false;
                mostrarDialogError("Escribe un ID adecuado (mayor que 0). ");
            }

        } catch (Exception e) {
            correcto = false;
            mostrarDialogError("Escribe una ID numérica..");
        }

        if (correcto) {
            portatil = fachada.recuperarPortatil(idPortatilInt);
        }

        if (correcto && portatil == null) {
            correcto = false;
            mostrarDialogError("No hay ningún portátil con esa ID.");
        }

        if (correcto) {
            tfPulgadas.setText(String.valueOf(portatil.getPulgadas()));
            tfMarca.setText(portatil.getMarca());
            String caracteristicas = portatil.getEspecificaciones().replace(";", "\n");
            textoCaracteristicas.setText(caracteristicas);
        }
    }

    /**
     * Método que intenta prestar un portátil al usuario, comprueba todo tipo de
     * errores en el préstamo, como si el portátil ya está prestado, si el
     * usuario está castigado, etc.
     *
     * @param dni -> DNI del usuario al que se le prestará el portátil
     * @param idPortatil -> ID del portátil a prestar
     */
    private void prestar(String dni, String idPortatil) {
        int idPortatilInt = -1;
        boolean correcto = true;
        Portatil portatil = null;

        if (!(LocalTime.now().isAfter(LocalTime.of(8, 0))
                && LocalTime.now().isBefore(LocalTime.of(20, 30)))) {
            correcto = false;
            mostrarDialogError("No se pueden prestar portátiles más tarde de las 20:30 o antes de las 8:00. ");
        }

        try {
            idPortatilInt = Integer.valueOf(idPortatil);
            if (idPortatilInt < 1) {
                correcto = false;
                mostrarDialogError("Escribe un ID adecuado (mayor que 0). ");
            }

        } catch (Exception e) {
            correcto = false;
            mostrarDialogError("Escribe una ID numéricas.");
        }

        if (correcto) {
            portatil = fachada.recuperarPortatil(idPortatilInt);
        }

        if (correcto && portatil == null) {
            correcto = false;
            mostrarDialogError("No hay ningún portátil con esa ID.");
        }

        if (correcto && !dniCorrecto(dni)) {
            correcto = false;
            mostrarDialogError("El DNI es incorrecto. ");
        }
        if (correcto && fachada.recuperarUsuario(dni) == null) {
            correcto = false;
            mostrarDialogError("No existe ningún usuario con ese DNI en la BBDD. ¿Seguro que está registrado?");
        }

        if (correcto && portatil.isPrestado()) {

            correcto = false;
            mostrarDialogError("Ese portátil ya está prestado. (¿Cómo es que lo tienes si está prestado?)");
        }

        if (correcto && fachada.getPortatilPrestado(dni) != null) {

            correcto = false;
            mostrarDialogError("El usuario ya tiene un portátil.");
        }

        if (correcto && fachada.esAdmin(dni)) {
            correcto = false;
            mostrarDialogError("No se pueden prestar portátiles a un administrador.");
        }

        if (correcto && ((Usuario) fachada.recuperarUsuario(dni)).estaCastigado()) {
            correcto = false;
            mostrarDialogError("El usuario está castigado. ");
        }

        if (correcto) {
            fachada.prestarPortatil(dni, idPortatilInt);

            tfIdPortatil.setText("");
            tfDNI.setText("");
            tfPulgadas.setText("");
            tfMarca.setText("");
            textoCaracteristicas.setText("");

            JOptionPane.showMessageDialog(
                    this,
                    "Portátil " + idPortatilInt + " prestado a " + dni,
                    "Préstamo realizado. ",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * Método auxiliar para mostrar JOptionPane con mensajes de error
     *
     * @param texto -> texto que queremos que saque el JOptionPane
     */
    public void mostrarDialogError(String texto) {
        String mensajeError = "Error: \n" + texto;
        JOptionPane.showMessageDialog(
                this,
                mensajeError,
                "Error en préstamo de portátil.",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Comprobación de si el DNI que ha insertado el usuario está en un formato
     * adecuado.
     *
     * @param dni -> DNI a comprobar
     * @return -> true si es válido, false si no lo es
     */
    public boolean dniCorrecto(String dni) {
        boolean correcto = false;

        Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");

        Matcher matcher = pattern.matcher(dni);

        if (matcher.matches()) {
            String letra = matcher.group(2);
            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

            int index = Integer.parseInt(matcher.group(1));
            index = index % 23;
            String reference = letras.substring(index, index + 1);

            if (reference.equalsIgnoreCase(letra)) {
                correcto = true;
            } else {
                correcto = false;
            }

        } else {
            correcto = false;
        }

        return correcto;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bAtras = new javax.swing.JButton();
        lAlta = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tfPulgadas = new javax.swing.JTextField();
        lPulgadas = new javax.swing.JLabel();
        bPrestar = new javax.swing.JButton();
        lUsuario1 = new javax.swing.JLabel();
        tfDNI = new javax.swing.JTextField();
        lUsuario2 = new javax.swing.JLabel();
        bCargarDatos = new javax.swing.JButton();
        tfIdPortatil = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        textoCaracteristicas = new javax.swing.JTextArea();
        tfMarca = new javax.swing.JTextField();
        lPulgadas1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Biblioteca - Préstamo de portátil");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        bAtras.setText("Atrás");
        bAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAtrasActionPerformed(evt);
            }
        });

        lAlta.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lAlta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lAlta.setText("Biblioteca - Préstamo portátil");

        jLabel1.setText("DNI: ");

        lPulgadas.setText("Pulgadas: ");

        bPrestar.setText("Prestar");
        bPrestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPrestarActionPerformed(evt);
            }
        });

        lUsuario1.setText("Características:");

        lUsuario2.setText("ID Portátil: ");

        bCargarDatos.setText("Cargar datos");
        bCargarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCargarDatosActionPerformed(evt);
            }
        });

        textoCaracteristicas.setColumns(20);
        textoCaracteristicas.setRows(5);
        jScrollPane1.setViewportView(textoCaracteristicas);

        lPulgadas1.setText("Marca: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tfIdPortatil, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(bCargarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel1)
                        .addGap(5, 5, 5)
                        .addComponent(tfDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lPulgadas, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(tfPulgadas, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lPulgadas1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(tfMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addComponent(bPrestar, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bAtras)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(51, Short.MAX_VALUE))
            .addComponent(lAlta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfIdPortatil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bCargarDatos)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(tfDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lPulgadas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPulgadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lPulgadas1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(lUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bPrestar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bAtras)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que gestiona el evento de pulsar el botón de préstamo, recoge los
     * datos de los TextField y se los pasa el método de préstamo
     *
     * @param evt -> evento de pulsar el botón
     */
    private void bPrestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPrestarActionPerformed
        String dni = tfDNI.getText();
        String idPortatil = tfIdPortatil.getText();

        prestar(dni, idPortatil);
    }//GEN-LAST:event_bPrestarActionPerformed

    /**
     * Método que gestiona el evento de pulsar el botón de carga de datos,
     * recoge los datos de los TextField y se los pasa el método de cargar
     * campos
     *
     * @param evt -> evento de pulsar el botón
     */
    private void bCargarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCargarDatosActionPerformed
        String id = tfIdPortatil.getText();
        cargarCampos(id);
    }//GEN-LAST:event_bCargarDatosActionPerformed

    /**
     * Método que gestiona el evento de pulsar el botón de atrás, devuelve al
     * usuario al panel de administración
     *
     * @param evt -> evento de pulsar el botón
     */
    private void bAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAtrasActionPerformed
        new PanelAdmin(admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bAtrasActionPerformed

    /**
     * Evento de cierre de ventana, notifica al log del cierre de sesión del
     * usuario
     *
     * @param evt -> evento del cierre de ventana
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        fachada.logInfoExterna("Cierre de sesión de administrador con DNI " + this.admin.getDni());
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAtras;
    private javax.swing.JButton bCargarDatos;
    private javax.swing.JButton bPrestar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lAlta;
    private javax.swing.JLabel lPulgadas;
    private javax.swing.JLabel lPulgadas1;
    private javax.swing.JLabel lUsuario1;
    private javax.swing.JLabel lUsuario2;
    private javax.swing.JTextArea textoCaracteristicas;
    private javax.swing.JTextField tfDNI;
    private javax.swing.JTextField tfIdPortatil;
    private javax.swing.JTextField tfMarca;
    private javax.swing.JTextField tfPulgadas;
    // End of variables declaration//GEN-END:variables
}

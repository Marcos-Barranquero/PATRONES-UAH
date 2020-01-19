package GUI;

import ClasesBase.Administrador;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * Interfaz gráfica de usuario para administradores encargada de gestionar 
 * el alta de usuarios en la aplicación
 * 
 * @author marco
 */
public class AltaUsuario extends javax.swing.JFrame {

    private final Administrador admin;
    Fachada fachada = Fachada.getInstancia();

    public AltaUsuario(Administrador admin) {
        this.admin = admin;
        initComponents();
    }

    /**
     * Método encargado de verificar los campos de input del usuario
     *
     * @return -> true si los campos son correctos, false si hay algún error
     */
    private boolean verificarCampos() {
        String usuario = tfUsuario.getText();
        String email = tfEmail.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String dni = tfDNI.getText();
        String edad = tfEdad.getText();
        String telefono = tfTelefono.getText();

        int edadInt;
        int telefonoInt;

        boolean correcto = true;

        if (usuario.equals("")) {
            correcto = false;
            mostrarDialogError("Rellena el campo usuario. ");
        }

        if (email.equals("") || !(email.contains("@"))) {
            correcto = false;
            mostrarDialogError("Escribe un email correcto.");
        }

        if (password.equals("") || password.length() < 8) {
            correcto = false;
            mostrarDialogError("Escribe una contraseña correcta, de al menos 8 caracteres. ");
        }

        if (!dniCorrecto(dni)) {
            correcto = false;
            mostrarDialogError("Escribe un DNI correcto. ");
        }

        try {
            edadInt = Integer.valueOf(edad);
            if (edadInt < 18) {
                correcto = false;
                mostrarDialogError("Debes de ser mayor de edad. ");
            }

        } catch (Exception e) {
            correcto = false;
            mostrarDialogError("Escribe una edad correcta.");
        }

        try {
            telefonoInt = Integer.valueOf(telefono);
            if (telefonoInt < 600000000) {
                correcto = false;
                mostrarDialogError("Escribe un teléfono adecuado. ");
            }

        } catch (Exception e) {
            correcto = false;
            mostrarDialogError("Escribe una teléfono adecuado.");
        }

        // Verifico que no es DNI duplicado:
        if (fachada.recuperarUsuario(dni) != null && correcto) {
            mostrarDialogError("El DNI ya existe en la BBDD. ");
            correcto = false;
        }

        return correcto;
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

            correcto = reference.equalsIgnoreCase(letra);
        } else {
            correcto = false;
        }

        return correcto;
    }

    /**
     * Método auxiliar para mostrar JOptionPane con mensajes de error
     *
     * @param texto -> texto que queremos que saque el JOptionPane
     */
    public void mostrarDialogError(String texto) {
        String mensajeError = "El registro no es válido.\n" + texto;
        JOptionPane.showMessageDialog(
                this,
                mensajeError,
                "Error en el registro",
                JOptionPane.ERROR_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lUsuario1 = new javax.swing.JLabel();
        tfDNI = new javax.swing.JTextField();
        lUsuario2 = new javax.swing.JLabel();
        tfEdad = new javax.swing.JTextField();
        lAlta = new javax.swing.JLabel();
        lUsuario3 = new javax.swing.JLabel();
        tfUsuario = new javax.swing.JTextField();
        tfTelefono = new javax.swing.JTextField();
        pfPassword = new javax.swing.JPasswordField();
        lUsuario4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lUsuario = new javax.swing.JLabel();
        bAtras = new javax.swing.JButton();
        tfEmail = new javax.swing.JTextField();
        bAlta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Biblioteca - Alta de usuario");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lUsuario1.setText("Email: ");

        lUsuario2.setText("DNI:");

        lAlta.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lAlta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lAlta.setText("Biblioteca - Alta usuario");

        lUsuario3.setText("Edad:");

        lUsuario4.setText("Teléfono:");

        jLabel2.setText("Contraseña: ");

        lUsuario.setText("Usuario: ");

        bAtras.setText("Atrás");
        bAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAtrasActionPerformed(evt);
            }
        });

        bAlta.setText("Dar de alta");
        bAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAltaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(115, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(tfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(tfDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lUsuario3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(tfEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lUsuario4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(tfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(115, 115, 115))
            .addComponent(lAlta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lUsuario3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lUsuario4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(bAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(bAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Botón de atrás, crea un nuevo panel de admin y se deshace de esta ventana
     *
     * @param evt -> evento de pulsar botón
     */
    private void bAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAtrasActionPerformed
        new PanelAdmin(admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bAtrasActionPerformed

    /**
     * Botón de alta, intenta dar de alta a un usuario en base al input
     *
     * @param evt -> evento de pulsar el botón
     */
    private void bAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAltaActionPerformed
        String usuario = tfUsuario.getText();
        String email = tfEmail.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String dni = tfDNI.getText();
        String edad = tfEdad.getText();
        String telefono = tfTelefono.getText();

        if (verificarCampos()) {
            fachada.registroUsuario(usuario, password, email, dni, edad, telefono);
            JOptionPane.showMessageDialog(
                    this,
                    "Usuario dado de alta correctamente.",
                    "Registro exitoso.",
                    JOptionPane.INFORMATION_MESSAGE);

            tfUsuario.setText("");
            tfEmail.setText("");
            pfPassword.setText("");
            tfDNI.setText("");
            tfEdad.setText("");
            tfTelefono.setText("");
        }
    }//GEN-LAST:event_bAltaActionPerformed

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
    private javax.swing.JButton bAlta;
    private javax.swing.JButton bAtras;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lAlta;
    private javax.swing.JLabel lUsuario;
    private javax.swing.JLabel lUsuario1;
    private javax.swing.JLabel lUsuario2;
    private javax.swing.JLabel lUsuario3;
    private javax.swing.JLabel lUsuario4;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JTextField tfDNI;
    private javax.swing.JTextField tfEdad;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfTelefono;
    private javax.swing.JTextField tfUsuario;
    // End of variables declaration//GEN-END:variables
}

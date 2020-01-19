package GUI;

import ClasesBase.Administrador;
import Factory.PortatilFactory;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * Interfaz gráfica para gestionar el alta de portátiles en la aplicación
 * 
 * @author marco
 */
public class AltaPortatil extends javax.swing.JFrame {

    private final Administrador admin;
    private final PortatilFactory pf = PortatilFactory.getInstancia();
    Fachada fachada = Fachada.getInstancia();

    public AltaPortatil(Administrador admin) {
        this.admin = admin;
        initComponents();
        llenarComboBoxPortatiles();
    }

    /**
     * Rellena el comboBox de la interfaz en base a los tipos de portátiles
     * que haya en la biblioteca
     */
    public void llenarComboBoxPortatiles() {
        ArrayList<String> tipos = pf.getTipos();
        cbPortatiles.setModel(new DefaultComboBoxModel<>(tipos.toArray(new String[0])));
    }

    /**
     * Verifica el input que haga el administrador
     * @return -> si todos los campos son correctos, devolverá true; 
     * si no lo son, devolverá false
     */
    private boolean verificarCampos() {
        String unidades = tfUnidades.getText();

        int nUnidades;

        boolean correcto = true;
        try {
            nUnidades = Integer.valueOf(unidades);
            if (nUnidades < 1) {
                correcto = false;
                mostrarDialogError("Introduce un numero de unidades a dar de alta en numeros. ");
            }

        } catch (Exception e) {
            correcto = false;
            mostrarDialogError("Introduce un numero de unidades a dar de alta en numeros. ");
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

        lAlta = new javax.swing.JLabel();
        lUsuario = new javax.swing.JLabel();
        bAtras = new javax.swing.JButton();
        bAltaLibro = new javax.swing.JButton();
        lUsuario3 = new javax.swing.JLabel();
        tfUnidades = new javax.swing.JTextField();
        cbPortatiles = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Biblioteca - Alta de portátil");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lAlta.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lAlta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lAlta.setText("Biblioteca - Registrar portátil");

        lUsuario.setText("Tipo:");

        bAtras.setText("Atrás");
        bAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAtrasActionPerformed(evt);
            }
        });

        bAltaLibro.setText("Dar de alta");
        bAltaLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAltaLibroActionPerformed(evt);
            }
        });

        lUsuario3.setText("Nº de unidades:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(130, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bAltaLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bAtras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(lUsuario3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tfUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbPortatiles, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(122, 122, 122))
            .addComponent(lAlta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbPortatiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lUsuario3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bAltaLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(bAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que gestiona la pulsación del botón de atrás.
     * Crea un nuevo Panel de usuario y elimina esta ventana.
     * 
     * @param evt -> evento de pulsar el botón
     */
    private void bAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAtrasActionPerformed
        new PanelAdmin(admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bAtrasActionPerformed

    
    /**
     * Botón de alta, intenta dar de alta los portátiles en base al input
     *
     * @param evt -> evento de pulsar el botón
     */
    private void bAltaLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAltaLibroActionPerformed
        if (verificarCampos()) {
            int nUnidades = Integer.valueOf(tfUnidades.getText());
            String tipo = cbPortatiles.getSelectedItem().toString();

            for (int i = 0; i < nUnidades; i++) {
                fachada.crearPortatil(tipo);
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Portátiles añadidos correctamente.",
                    "Registro exitoso.",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_bAltaLibroActionPerformed

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
    private javax.swing.JButton bAltaLibro;
    private javax.swing.JButton bAtras;
    private javax.swing.JComboBox<String> cbPortatiles;
    private javax.swing.JLabel lAlta;
    private javax.swing.JLabel lUsuario;
    private javax.swing.JLabel lUsuario3;
    private javax.swing.JTextField tfUnidades;
    // End of variables declaration//GEN-END:variables
}

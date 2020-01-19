package GUI;

import ClasesBase.Usuario;

/**
 *
 * Interfaz gráfica que representa el panel de usuario
 * 
 * @author Edu
 */
public class PanelUsuario extends javax.swing.JFrame {
    private final Usuario usuario;
    private final Fachada fachada = Fachada.getInstancia();
    
    /**
     * Creates new form PanelUsuario
     * @param usuario
     */
    public PanelUsuario(Usuario usuario) {
        this.usuario = usuario;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        bLogOut = new javax.swing.JButton();
        bDevolverLibros = new javax.swing.JButton();
        bConsultarLibros = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bConsultarPortatiles = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Biblioteca - Panel de usuario");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Panel de usuario");

        bLogOut.setText("Cerrar Sesión");
        bLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLogOutActionPerformed(evt);
            }
        });

        bDevolverLibros.setText("Devolver Libros");
        bDevolverLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDevolverLibrosActionPerformed(evt);
            }
        });

        bConsultarLibros.setText("Consultar Libros");
        bConsultarLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConsultarLibrosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Libros");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Portátiles");

        bConsultarPortatiles.setText("Consultar Disponibilidad");
        bConsultarPortatiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConsultarPortatilesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bDevolverLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bConsultarLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bConsultarPortatiles)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(bLogOut)
                                .addGap(25, 25, 25)))))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bConsultarLibros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bDevolverLibros)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bConsultarPortatiles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(bLogOut)
                .addGap(32, 32, 32))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que gestiona la pulsación del botón de logout.
     * Redirige al usuario a la ventana de Inicio y logea el cierre de sesión.
     * 
     * @param evt -> evento de pulsar el botón
     */
    private void bLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLogOutActionPerformed
        fachada.logInfoExterna("Cierre de sesión de usuario con DNI " + this.usuario.getDni());
        new Inicio().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bLogOutActionPerformed

    /**
     * Método que gestiona la pulsación del botón de consulta de libros.
     * Redirige al usuario a la ventana de CosnultarLibros.
     * 
     * @param evt -> evento de pulsar el botón
     */
    private void bConsultarLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConsultarLibrosActionPerformed
        new ConsultarLibros(this.usuario).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bConsultarLibrosActionPerformed

    /**
     * Método que gestiona la pulsación del botón de consulta de portátiles.
     * Redirige al usuario a la ventana de ConsultarPortatiles.
     * 
     * @param evt -> evento de pulsar el botón
     */
    private void bConsultarPortatilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConsultarPortatilesActionPerformed
        new DisponibilidadPortatiles(this.usuario).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bConsultarPortatilesActionPerformed

    /**
     * Método que gestiona la pulsación del botón de devolver libros.
     * Redirige al usuario a la ventana de DevolverLibros.
     * 
     * @param evt -> evento de pulsar el botón
     */
    private void bDevolverLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDevolverLibrosActionPerformed
        new DevolverLibros(this.usuario).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bDevolverLibrosActionPerformed

    /**
     * Evento de cierre de ventana, notifica al log del cierre de sesión del
     * usuario
     *
     * @param evt -> evento del cierre de ventana
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        fachada.logInfoExterna("Cierre de sesión de usuario con DNI " + this.usuario.getDni());
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bConsultarLibros;
    private javax.swing.JButton bConsultarPortatiles;
    private javax.swing.JButton bDevolverLibros;
    private javax.swing.JButton bLogOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}

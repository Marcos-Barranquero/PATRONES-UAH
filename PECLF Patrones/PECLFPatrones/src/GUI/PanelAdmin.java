package GUI;

import ClasesBase.Administrador;

/**
 * Interfaz gráfica para gestionar el panel de administración
 * @author marco
 */
public class PanelAdmin extends javax.swing.JFrame {

    private final Administrador admin;
    private final Fachada fachada = Fachada.getInstancia();

    /**
     * Creates new form PanelAdmin
     * @param admin
     */
    public PanelAdmin(Administrador admin) {
        this.admin = admin;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bAlta = new javax.swing.JButton();
        bBaja = new javax.swing.JButton();
        bPrestarLibro = new javax.swing.JButton();
        bDevolverLibro = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bSalir = new javax.swing.JButton();
        bPrestarPortatil = new javax.swing.JButton();
        bDevolverPortatil = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        bAltaLibro = new javax.swing.JButton();
        bCrearPortatil = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Biblioteca - Panel de administración");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        bAlta.setText("Alta Usuario");
        bAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAltaActionPerformed(evt);
            }
        });

        bBaja.setText("Baja Usuario");
        bBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBajaActionPerformed(evt);
            }
        });

        bPrestarLibro.setText("Prestar libro");
        bPrestarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPrestarLibroActionPerformed(evt);
            }
        });

        bDevolverLibro.setText("Devolver libro");
        bDevolverLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDevolverLibroActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Panel de administrador");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Gestión usuarios");

        bSalir.setText("Salir");
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });

        bPrestarPortatil.setText("Prestar portátil");
        bPrestarPortatil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPrestarPortatilActionPerformed(evt);
            }
        });

        bDevolverPortatil.setText("Devolver portátil");
        bDevolverPortatil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDevolverPortatilActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("Gestión portátiles");

        bAltaLibro.setText("Crear libro");
        bAltaLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAltaLibroActionPerformed(evt);
            }
        });

        bCrearPortatil.setText("Crear portátil");
        bCrearPortatil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCrearPortatilActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setText("Gestión libros");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(300, 300, 300)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(bAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(30, 30, 30)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(bPrestarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bDevolverLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bAltaLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(50, 50, 50)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(bPrestarPortatil, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bDevolverPortatil, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bCrearPortatil, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(314, 314, 314)
                        .addComponent(bSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(302, 302, 302)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(336, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(bAlta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bBaja))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bPrestarLibro)
                                .addGap(8, 8, 8)
                                .addComponent(bDevolverLibro))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bPrestarPortatil)
                                .addGap(8, 8, 8)
                                .addComponent(bDevolverPortatil)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bAltaLibro)
                    .addComponent(bCrearPortatil))
                .addGap(35, 35, 35)
                .addComponent(bSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(138, 138, 138)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(201, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Redirige al usuario a la ventana de alta de usuarios
     *
     * @param evt -> evento de pulsar el botón
     */
    private void bAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAltaActionPerformed
        new AltaUsuario(admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bAltaActionPerformed

    /**
     * Redirige al usuario a la ventana de baja de usuarios
     *
     * @param evt -> evento de pulsar el botón
    */
    private void bBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBajaActionPerformed
        // TODO add your handling code here:
        new BajaUsuario(admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bBajaActionPerformed
    
    /**
     * Redirige al usuario a la ventana de préstamo de libros
     *
     * @param evt -> evento de pulsar el botón
    */
    private void bPrestarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPrestarLibroActionPerformed
        // TODO add your handling code here:
        new PrestarLibro(admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bPrestarLibroActionPerformed

    /**
     * Redirige al usuario a la ventana de préstamo de portátiles
     *
     * @param evt -> evento de pulsar el botón
    */
    private void bPrestarPortatilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPrestarPortatilActionPerformed
        new PrestarPortatil(admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bPrestarPortatilActionPerformed

    /**
     * Cierra la sesión actual del administrador y lo redirige a la ventana
     * de inicio, registrando el cierre de sesión en el log
     *
     * @param evt -> evento de pulsar el botón
    */
    private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
        fachada.logInfoExterna("Cierre de sesión de administrador con DNI " + this.admin.getDni());
        new Inicio().setVisible(true);
        this.dispose();

    }//GEN-LAST:event_bSalirActionPerformed

    /**
     * Redirige al usuario a la ventana de alta de libros
     *
     * @param evt -> evento de pulsar el botón
    */
    private void bAltaLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAltaLibroActionPerformed
        new AltaLibro(admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bAltaLibroActionPerformed

    /**
     * Redirige al usuario a la ventana de alta de portátiles
     *
     * @param evt -> evento de pulsar el botón
    */
    private void bCrearPortatilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCrearPortatilActionPerformed
        // TODO add your handling code here:
        new AltaPortatil(admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bCrearPortatilActionPerformed

    /**
     * Redirige al usuario a la ventana de devolución de portátiles
     *
     * @param evt -> evento de pulsar el botón
    */
    private void bDevolverPortatilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDevolverPortatilActionPerformed
        new DevolverPortatil(admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bDevolverPortatilActionPerformed

    /**
     * Evento de cierre de ventana, notifica al log del cierre de sesión del
     * usuario
     *
     * @param evt -> evento del cierre de ventana
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        fachada.logInfoExterna("Cierre de sesión de administrador con DNI " + this.admin.getDni());
    }//GEN-LAST:event_formWindowClosing

    
    /**
     * Redirige al usuario a la ventana de devolución de libros
     *
     * @param evt -> evento de pulsar el botón
    */
    private void bDevolverLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDevolverLibroActionPerformed
        new DevolverLibrosAdmin(admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bDevolverLibroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAlta;
    private javax.swing.JButton bAltaLibro;
    private javax.swing.JButton bBaja;
    private javax.swing.JButton bCrearPortatil;
    private javax.swing.JButton bDevolverLibro;
    private javax.swing.JButton bDevolverPortatil;
    private javax.swing.JButton bPrestarLibro;
    private javax.swing.JButton bPrestarPortatil;
    private javax.swing.JButton bSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
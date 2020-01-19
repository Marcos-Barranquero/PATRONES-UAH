package GUI;

import ClasesBase.Administrador;
import javax.swing.JOptionPane;

/**
 *
 * Interfaz de usuario para administrador que permite al usuario dar de alta un
 * libro nuevo
 *
 * @author marco
 */
public class AltaLibro extends javax.swing.JFrame {

    private final Administrador admin;
    Fachada fachada = Fachada.getInstancia();

    public AltaLibro(Administrador admin) {
        this.admin = admin;
        initComponents();
    }

    /**
     * Método que verifica los campos de input que ha introducido el usuario.
     *
     * @return Si alguno de los campos no es correcto, devuelve false. Si todos
     * son correctos, devuelve true.
     */
    private boolean verificarCampos() {
        String nombre = tfNombre.getText();
        String autor = tfAutor.getText();
        String ISBN = tfISBN.getText();
        String edicion = tfEdicion.getText();
        String unidades = tfUnidades.getText();

        int edicionInt;
        int nUnidades;

        boolean correcto = true;

        if (nombre.equals("")) {
            correcto = false;
            mostrarDialogError("Rellena el campo nombre. ");
        }

        if (autor.equals("")) {
            correcto = false;
            mostrarDialogError("Rellena el campo autor.");
        }

        if (!isbnValido(ISBN) || ISBN.equals("")) {
            correcto = false;
            mostrarDialogError("Escribe un ISBN válido. ");
        }

        try {
            edicionInt = Integer.valueOf(edicion);
            if (edicionInt < 1) {
                correcto = false;
                mostrarDialogError("Introduce una edición correcta. En numeros. ");
            }

        } catch (Exception e) {
            correcto = false;
            mostrarDialogError("Introduce una edición correcta. En numeros. ");
        }

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
     * Comprueba que el ISBN es válido antes de insertar un libro
     *
     * @param isbn -> ISBN insertado en el TextField
     * @return -> true si es válido, false si no lo es
     */
    public boolean isbnValido(String isbn) {
        if (isbn == null) {
            return false;
        }

        // elimina guiones
        isbn = isbn.replaceAll("-", "");

        //must be a 13 digit ISBN
        if (isbn.length() != 13) {
            return false;
        }

        try {
            int tot = 0;
            for (int i = 0; i < 12; i++) {
                int digit = Integer.parseInt(isbn.substring(i, i + 1));
                tot += (i % 2 == 0) ? digit * 1 : digit * 3;
            }

            //checksum must be 0-9. If calculated as 10 then = 0
            int checksum = 10 - (tot % 10);
            if (checksum == 10) {
                checksum = 0;
            }

            return checksum == Integer.parseInt(isbn.substring(12));
        } catch (NumberFormatException nfe) {
            //to catch invalid ISBNs that have non-numeric characters in them
            return false;
        }
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
        tfEdicion = new javax.swing.JTextField();
        lUsuario2 = new javax.swing.JLabel();
        lAlta = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lUsuario = new javax.swing.JLabel();
        bAtras = new javax.swing.JButton();
        tfAutor = new javax.swing.JTextField();
        bAltaLibro = new javax.swing.JButton();
        tfISBN = new javax.swing.JTextField();
        lUsuario3 = new javax.swing.JLabel();
        tfUnidades = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Biblioteca - Alta de libros");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lUsuario1.setText("Autor:");

        lUsuario2.setText("Edicion:");

        lAlta.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lAlta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lAlta.setText("Biblioteca - Alta libro");

        jLabel2.setText("ISBN: ");

        lUsuario.setText("Nombre:");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(120, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(tfAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(tfEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bAltaLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bAtras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lUsuario3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(120, 120, 120))
            .addComponent(lAlta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lUsuario3))
                .addGap(18, 18, 18)
                .addComponent(bAltaLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(bAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
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
     * Botón de alta, intenta dar de alta a un libro en base al input Si los
     * campos han sido verificados correctamente, lo inserta en la base de
     * datos.
     *
     * @param evt -> evento de pulsar el botón
     */
    private void bAltaLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAltaLibroActionPerformed
        if (verificarCampos()) {
            String nombre = tfNombre.getText();
            String autor = tfAutor.getText();
            String ISBN = tfISBN.getText();
            int edicionInt = Integer.valueOf(tfEdicion.getText());
            int nUnidades = Integer.valueOf(tfUnidades.getText());

            for (int i = 0; i < nUnidades; i++) {
                fachada.crearLibro(nombre, ISBN, autor, edicionInt);
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Libros dados de alta correctamente.",
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lAlta;
    private javax.swing.JLabel lUsuario;
    private javax.swing.JLabel lUsuario1;
    private javax.swing.JLabel lUsuario2;
    private javax.swing.JLabel lUsuario3;
    private javax.swing.JTextField tfAutor;
    private javax.swing.JTextField tfEdicion;
    private javax.swing.JTextField tfISBN;
    private javax.swing.JTextField tfNombre;
    private javax.swing.JTextField tfUnidades;
    // End of variables declaration//GEN-END:variables
}

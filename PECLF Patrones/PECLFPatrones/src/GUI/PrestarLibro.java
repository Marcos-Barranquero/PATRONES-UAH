/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ClasesBase.Administrador;
import ClasesBase.Libro;
import ClasesBase.Usuario;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author marco
 */
public class PrestarLibro extends javax.swing.JFrame {

    private final Administrador admin;
    private final Fachada fachada = Fachada.getInstancia();

    public PrestarLibro(Administrador admin) {
        this.admin = admin;
        initComponents();
        cerrarCampos();
    }

    /**
     * Marca los campos de usuario que no pueden ser editados como tal
    */
    private void cerrarCampos() {
        tfNombre.setEditable(false);
        tfISBN.setEditable(false);
        tfAutor.setEditable(false);
        tfEdicion.setEditable(false);
    }

    /**
     * Carga los datos del libro en base a su ID
     * @param idPortatil -> ID del libro al que pertenecen los datos
     */
    private void cargarCampos(String idLibro) {
        int idlibroInt = -1;
        boolean correcto = true;
        Libro libro = null;

        try {
            idlibroInt = Integer.valueOf(idLibro);
            if (idlibroInt < 1) {
                correcto = false;
                mostrarDialogError("Escribe un ID adecuado (mayor que 0). ");
            }

        } catch (Exception e) {
            correcto = false;
            mostrarDialogError("Escribe una ID numérica.");
        }

        if (correcto) {
            libro = fachada.recuperarLibro(idlibroInt);
        }

        if (correcto && libro == null) {
            correcto = false;
            mostrarDialogError("No hay ningún libro con esa ID.");
        }

        if (correcto) {

            tfNombre.setText(libro.getNombre());
            tfISBN.setText(libro.getIsbn());
            tfAutor.setText(libro.getAutor());
            tfEdicion.setText(String.valueOf(libro.getEdicion()));
        }
    }

    /**
     * Método que intenta prestar un libro al usuario, comprueba todo tipo
     * de errores en el préstamo, como si el libro ya está prestado, si el
     * usuario está castigado, etc.
     * 
     * @param dni -> DNI del usuario al que se le prestará el portátil
     * @param idPortatil -> ID del libro a prestar
     */
    private void prestar(String dni, String idLibro) {
        int idLibroInt = -1;
        boolean correcto = true;
        Libro libro = null;

        try {
            idLibroInt = Integer.valueOf(idLibro);
            if (idLibroInt < 1) {
                correcto = false;
                mostrarDialogError("Escribe un ID adecuado (mayor que 0). ");
            }

        } catch (Exception e) {
            correcto = false;
            mostrarDialogError("Escribe una ID numéricas.");
        }

        if (correcto) {
            libro = fachada.recuperarLibro(idLibroInt);
        }

        if (correcto && libro == null) {
            correcto = false;
            mostrarDialogError("No hay ningún libro con esa ID.");
        }

        if (correcto && !dniCorrecto(dni)) {
            correcto = false;
            mostrarDialogError("El DNI es incorrecto. ");
        }
        if (correcto && fachada.recuperarUsuario(dni) == null) {
            correcto = false;
            mostrarDialogError("No existe ningún usuario con ese DNI en la BBDD. ¿Seguro que está registrado?");
        }

        if (correcto && libro.estaPrestado()) {

            correcto = false;
            mostrarDialogError("Error. Ese libro ya está prestado. (¿Cómo es que lo tienes si está prestado?)");
        }

        if (correcto && fachada.esAdmin(dni)) {
            correcto = false;
            mostrarDialogError("Error. No se pueden prestar libros a un administrador.");
        }

        if (correcto && ((Usuario) fachada.recuperarUsuario(dni)).estaCastigado()) {
            correcto = false;
            mostrarDialogError("Error. El usuario está castigado. ");
        }

        if (correcto && fachada.getLibrosPrestados(dni).size() >= fachada.MAXIMO_LIBROS) {
            correcto = false;
            mostrarDialogError("Error. El usuario tiene ya el máximo de libros establecido. ");
        }

        if (correcto) {

            fachada.prestarLibro(dni, idLibroInt);

            tfNombre.setText("");
            tfDNI.setText("");
            tfISBN.setText("");
            tfEdicion.setText("");
            tfAutor.setText("");

            JOptionPane.showMessageDialog(
                    this,
                    "Libro " + idLibroInt + " prestado a " + dni,
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
                "Error en préstamo de libro.",
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfDNI = new javax.swing.JTextField();
        lUsuario2 = new javax.swing.JLabel();
        bCargarDatos = new javax.swing.JButton();
        tfLibro = new javax.swing.JTextField();
        bAtras = new javax.swing.JButton();
        lAlta = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tfISBN = new javax.swing.JTextField();
        tfNombre = new javax.swing.JTextField();
        lPulgadas1 = new javax.swing.JLabel();
        lPulgadas = new javax.swing.JLabel();
        bPrestar = new javax.swing.JButton();
        tfAutor = new javax.swing.JTextField();
        lPulgadas2 = new javax.swing.JLabel();
        tfEdicion = new javax.swing.JTextField();
        lPulgadas3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Biblioteca - Préstamo de libro");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lUsuario2.setText("ID Libro: ");

        bCargarDatos.setText("Cargar datos");
        bCargarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCargarDatosActionPerformed(evt);
            }
        });

        bAtras.setText("Atrás");
        bAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAtrasActionPerformed(evt);
            }
        });

        lAlta.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lAlta.setText("Biblioteca - Préstamo libro");

        jLabel1.setText("DNI: ");

        lPulgadas1.setText("ISBN: ");

        lPulgadas.setText("Nombre:");

        bPrestar.setText("Prestar");
        bPrestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPrestarActionPerformed(evt);
            }
        });

        lPulgadas2.setText("Autor: ");

        lPulgadas3.setText("Edición: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(lAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tfLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(bCargarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(jLabel1)
                                .addGap(5, 5, 5)
                                .addComponent(tfDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lPulgadas, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lPulgadas1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(tfISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lPulgadas2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(tfAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lPulgadas3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(tfEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(40, 40, 40)
                                .addComponent(bPrestar, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(bAtras)))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lPulgadas1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lPulgadas2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(bPrestar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lPulgadas3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(bAtras)
                .addGap(26, 26, 26))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * Botón de cargar datos. Recupera el texto del TextField y llama a
     * cargarCampos con la ID del libro.
     *
     * @param evt -> evento de pulsar botón
     */
    private void bCargarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCargarDatosActionPerformed
        String id = tfLibro.getText();
        cargarCampos(id);

    }//GEN-LAST:event_bCargarDatosActionPerformed

    
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
     * Método que gestiona la pulsación del botón de prestar.
     * Recupera los datos de lo TextField y llama a prestar
     * 
     * @param evt -> evento de pulsar botón
     */
    private void bPrestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPrestarActionPerformed
        String dni = tfDNI.getText();
        String idLibro = tfLibro.getText();

        prestar(dni, idLibro);
    }//GEN-LAST:event_bPrestarActionPerformed

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
    private javax.swing.JLabel lAlta;
    private javax.swing.JLabel lPulgadas;
    private javax.swing.JLabel lPulgadas1;
    private javax.swing.JLabel lPulgadas2;
    private javax.swing.JLabel lPulgadas3;
    private javax.swing.JLabel lUsuario2;
    private javax.swing.JTextField tfAutor;
    private javax.swing.JTextField tfDNI;
    private javax.swing.JTextField tfEdicion;
    private javax.swing.JTextField tfISBN;
    private javax.swing.JTextField tfLibro;
    private javax.swing.JTextField tfNombre;
    // End of variables declaration//GEN-END:variables
}

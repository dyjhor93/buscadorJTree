package buscadorjtree;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
public class Vista extends javax.swing.JFrame {
    public Vista() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtRuta = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        arbol = new javax.swing.JTree();
        buscar = new javax.swing.JTextField();
        criterio = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtRuta.setText("Ruta");

        jButton1.setText("Seleccionar carpeta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        arbol.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(arbol);

        criterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todo", "Tamaño exacto del archivo", "Archivos cuyo tamaño es inferior", "Archivos cuyo tamaño es superior", "Extension de archivo", "Extension de archivo diferente" }));

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Buscar");

        jLabel2.setText("Criterio");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(txtRuta, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jScrollPane1)
                    .add(jButton1)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(buscar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 127, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel1))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jLabel2)
                                .add(0, 0, Short.MAX_VALUE))
                            .add(layout.createSequentialGroup()
                                .add(criterio, 0, 258, Short.MAX_VALUE)
                                .add(18, 18, 18)
                                .add(jButton2)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(txtRuta)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel1)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(buscar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(criterio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton2))
                .add(18, 18, 18)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void cargar(){
            File fileRoot = new File(txtRuta.getText().toString());
            DefaultMutableTreeNode root = new DefaultMutableTreeNode(fileRoot.getAbsolutePath());
            addChilds(root, txtRuta.getText().toString());
            DefaultTreeModel treeModel = new DefaultTreeModel(root);
            arbol.setModel(treeModel);
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        int result = f.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = f.getSelectedFile().toString();
            txtRuta.setText(path);
            cargar();
        } else if (result == JFileChooser.CANCEL_OPTION) {
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(txtRuta.getText()=="Ruta"){
            JOptionPane.showMessageDialog(null, "Seleccione una carpeta");
        }else{
            cargar();
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    
    protected File[] getListFiles(String Path) {
        File file = new File(Path);
        return file.listFiles();
    }

    private void addChilds(DefaultMutableTreeNode rootNode, String path) {
        File[] files = this.getListFiles(path);
        System.out.println(criterio.getSelectedIndex());
        for(File file:files) {
            if(file.isDirectory()) {
                DefaultMutableTreeNode subDirectory = new DefaultMutableTreeNode(file.getName());
                addChilds(subDirectory, file.getAbsolutePath());
                rootNode.add(subDirectory);
            } else {
                long tam = 0;
                String ext = "";
                switch(criterio.getSelectedIndex()){
                    
                    case 0:
                        rootNode.add(new DefaultMutableTreeNode(file.getName()+" - "+file.length()+" bytes"));
                        break;
                    case 1:
                        tam = Long.parseLong(buscar.getText());
                        if(tam == file.length())
                            rootNode.add(new DefaultMutableTreeNode(file.getName()+" - "+file.length()+" bytes"));
                        break;
                    case 2: 
                        tam = Long.parseLong(buscar.getText());
                        if(file.length() < tam)
                            rootNode.add(new DefaultMutableTreeNode(file.getName()+" - "+file.length()+" bytes"));
                        break;
                    case 3: 
                        tam = Long.parseLong(buscar.getText());
                        if(file.length() > tam)
                            rootNode.add(new DefaultMutableTreeNode(file.getName()+" - "+file.length()+" bytes"));
                        break;
                    case 4: 
                        tam = file.getName().lastIndexOf(".");
                        ext = file.getName().substring((int)tam + 1);
                        if(ext.equals(buscar.getText())){
                            rootNode.add(new DefaultMutableTreeNode(file.getName()+" - "+file.length()+" bytes"));
                        }
                        break;
                    case 5: 
                        tam = file.getName().lastIndexOf(".");
                        ext = file.getName().substring((int)tam + 1);
                        if(!ext.equals(buscar.getText())){
                            rootNode.add(new DefaultMutableTreeNode(file.getName()+" - "+file.length()+" bytes"));
                        }
                        break;
                }
                
            }
        }
    }
    
    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
////
    java.awt.EventQueue.invokeLater(() -> {new Vista().setVisible(true);});

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arbol;
    private javax.swing.JTextField buscar;
    private javax.swing.JComboBox<String> criterio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txtRuta;
    // End of variables declaration//GEN-END:variables
}
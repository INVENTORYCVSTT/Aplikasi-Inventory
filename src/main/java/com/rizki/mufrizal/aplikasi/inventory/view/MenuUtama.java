package com.rizki.mufrizal.aplikasi.inventory.view;

/**
 *
 * @author Rizki Mufrizal <mufrizalrizki@gmail.com>
 */
public class MenuUtama extends javax.swing.JFrame {

    private final BarangView barangView;
    private final PenjualanView penjualanView;
    private final PembelianView pembelianView;

    public MenuUtama() {
        initComponents();
        barangView = BarangView.getInstanceBarangView();
        penjualanView = PenjualanView.getInstancePenjualanView();
        pembelianView = PembelianView.getInstancePembelianView();

        desktopPane.add(barangView);
        desktopPane.add(penjualanView);
        desktopPane.add(pembelianView);

        barangView.setVisible(false);
        penjualanView.setVisible(false);
        pembelianView.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menu = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        exit = new javax.swing.JMenuItem();
        edit = new javax.swing.JMenu();
        menuBarang = new javax.swing.JMenuItem();
        menuPenjualan = new javax.swing.JMenuItem();
        menuPembelian = new javax.swing.JMenuItem();
        help = new javax.swing.JMenu();
        about = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        file.setText("File");

        exit.setText("Exit");
        file.add(exit);

        menu.add(file);

        edit.setText("Edit");

        menuBarang.setText("Data Barang");
        menuBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBarangActionPerformed(evt);
            }
        });
        edit.add(menuBarang);

        menuPenjualan.setText("Data Penjualan");
        menuPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPenjualanActionPerformed(evt);
            }
        });
        edit.add(menuPenjualan);

        menuPembelian.setText("Data Pembelian");
        menuPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPembelianActionPerformed(evt);
            }
        });
        edit.add(menuPembelian);

        menu.add(edit);

        help.setText("Help");

        about.setText("About");
        help.add(about);

        menu.add(help);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBarangActionPerformed
        if (barangView.isVisible()) {
            System.out.println("udh ada broe");
        } else {
            desktopPane.remove(barangView);
            desktopPane.add(barangView);
            barangView.setVisible(true);
        }
    }//GEN-LAST:event_menuBarangActionPerformed

    private void menuPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPenjualanActionPerformed
        if (penjualanView.isVisible()) {
            System.out.println("udh ada broe");
        } else {
            desktopPane.remove(penjualanView);
            desktopPane.add(penjualanView);
            penjualanView.setVisible(true);
        }
    }//GEN-LAST:event_menuPenjualanActionPerformed

    private void menuPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPembelianActionPerformed
        if (pembelianView.isVisible()) {
            System.out.println("udh ada broe");
        } else {
            desktopPane.remove(pembelianView);
            desktopPane.add(pembelianView);
            pembelianView.setVisible(true);
        }
    }//GEN-LAST:event_menuPembelianActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu edit;
    private javax.swing.JMenuItem exit;
    private javax.swing.JMenu file;
    private javax.swing.JMenu help;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem menuBarang;
    private javax.swing.JMenuItem menuPembelian;
    private javax.swing.JMenuItem menuPenjualan;
    // End of variables declaration//GEN-END:variables
}

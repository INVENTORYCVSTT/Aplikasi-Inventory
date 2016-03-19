package com.rizki.mufrizal.aplikasi.inventory.view;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Rizki Mufrizal <mufrizalrizki@gmail.com>
 */
public class PembelianView extends javax.swing.JInternalFrame {

    private final Dimension dimension;

    private static PembelianView pembelianView;

    public static PembelianView getInstancePembelianView() {
        if (pembelianView == null) {
            pembelianView = new PembelianView();
        }
        return pembelianView;
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public PembelianView() {
        initComponents();
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dimension.width / 2) - (getSize().width / 2), (dimension.height / 2) - (getSize().height / 2));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setClosable(true);
        setIconifiable(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

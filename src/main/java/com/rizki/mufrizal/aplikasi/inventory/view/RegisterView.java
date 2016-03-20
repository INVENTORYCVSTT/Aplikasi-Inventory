package com.rizki.mufrizal.aplikasi.inventory.view;

import com.rizki.mufrizal.aplikasi.inventory.controller.UserController;
import com.rizki.mufrizal.aplikasi.inventory.domain.User;
import java.util.concurrent.ExecutionException;
import javax.swing.GroupLayout;
import javax.swing.SwingWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Rizki Mufrizal <mufrizalrizki@gmail.com>
 */
public class RegisterView extends javax.swing.JDialog {

    private final GroupLayout groupLayout;
    private WorkerRegister workerRegister;
    private final UserController userController = new UserController(this);
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterView.class);

    /**
     * Creates new form RegisterView
     *
     * @param parent
     * @param modal
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public RegisterView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        progressBar.setVisible(Boolean.FALSE);
        groupLayout = (GroupLayout) getContentPane().getLayout();
        groupLayout.setHonorsVisibility(progressBar, Boolean.FALSE);
        email.requestFocusInWindow();

        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        error = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        progressBar = new javax.swing.JProgressBar();
        register = new javax.swing.JButton();
        login = new javax.swing.JButton();

        error.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        error.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        jLabel1.setText("Registrasi");

        jLabel2.setText("Email");

        jLabel3.setText("Password");

        register.setText("Register");
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });

        login.setText("Login");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(register)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(login)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                            .addComponent(password)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jLabel1)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(register)
                    .addComponent(login))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
        if (workerRegister != null && !workerRegister.isDone()) {
            workerRegister.cancel(Boolean.TRUE);
            workerRegister = null;
        }

        try {
            groupLayout.replace(error, progressBar);
        } catch (java.lang.IllegalArgumentException ae) {
        }

        workerRegister = new WorkerRegister();
        workerRegister.execute();

        register.setEnabled(Boolean.FALSE);
        login.setEnabled(Boolean.FALSE);
        getProgressBar().setVisible(Boolean.TRUE);
        getProgressBar().setIndeterminate(Boolean.TRUE);
    }//GEN-LAST:event_registerActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        userController.login();
    }//GEN-LAST:event_loginActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField email;
    private javax.swing.JLabel error;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton login;
    private javax.swing.JPasswordField password;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton register;
    // End of variables declaration//GEN-END:variables
    /**
     * @return the email
     */
    public javax.swing.JTextField getEmail() {
        return email;
    }

    /**
     * @return the error
     */
    public javax.swing.JLabel getError() {
        return error;
    }

    /**
     * @return the password
     */
    public javax.swing.JPasswordField getPassword() {
        return password;
    }

    /**
     * @return the progressBar
     */
    public javax.swing.JProgressBar getProgressBar() {
        return progressBar;
    }

    private class WorkerRegister extends SwingWorker<User, Void> {

        @Override
        protected void done() {
            try {
                if (get() != null) {
                    groupLayout.removeLayoutComponent(getProgressBar());
                    groupLayout.removeLayoutComponent(getError());
                } else {
                    groupLayout.replace(getProgressBar(), getError());
                }
                register.setEnabled(Boolean.TRUE);
                login.setEnabled(Boolean.TRUE);
                getProgressBar().setIndeterminate(Boolean.FALSE);
                getProgressBar().setVisible(Boolean.FALSE);
            } catch (InterruptedException | ExecutionException ex) {
                LOGGER.error("error di : {}", ex);
            }
        }

        @Override
        protected User doInBackground() throws Exception {
            Thread.sleep(1000);
            return userController.registerUser();
        }
    }

}

/**
 * Copyright (C) 2016 Rizki Mufrizal (https://rizkimufrizal.github.io/) (mufrizalrizki@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
public class LoginView extends javax.swing.JDialog {

    private final GroupLayout groupLayout;
    private WorkerLogin workerLogin;
    private final UserController userController = new UserController(this);
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginView.class);

    /**
     * Creates new form LoginView
     *
     * @param parent
     * @param modal
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public LoginView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        progressBar.setVisible(Boolean.FALSE);
        groupLayout = (GroupLayout) getContentPane().getLayout();
        groupLayout.setHonorsVisibility(progressBar, Boolean.FALSE);
        email.requestFocusInWindow();

        setLocationRelativeTo(null);
    }

    private void closeDialog() {
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        error = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        cancel = new javax.swing.JButton();
        login = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();
        progressBar = new javax.swing.JProgressBar();
        register = new javax.swing.JButton();
        gambarGembok = new javax.swing.JLabel();
        gambarUser = new javax.swing.JLabel();

        error.setText("Username Dan Password Anda Salah");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        jLabel1.setText("Silahkan Login");

        jLabel2.setText("Email");

        jLabel3.setText("Password");

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        login.setText("Login");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        register.setText("Register");
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });

        gambarGembok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gembok.png"))); // NOI18N

        gambarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(register)
                                .addGap(76, 76, 76)
                                .addComponent(login)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cancel))
                            .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(gambarGembok)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addGap(12, 12, 12)
                        .addComponent(gambarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(password)
                            .addComponent(email))
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(gambarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gambarGembok))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(28, 28, 28)))
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
                    .addComponent(login)
                    .addComponent(cancel)
                    .addComponent(register))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        if (workerLogin != null && !workerLogin.isDone()) {
            workerLogin.cancel(Boolean.TRUE);
            workerLogin = null;
        }

        try {
            groupLayout.replace(error, progressBar);
        } catch (java.lang.IllegalArgumentException ae) {
        }

        workerLogin = new WorkerLogin();
        workerLogin.execute();

        login.setEnabled(Boolean.FALSE);
        register.setEnabled(Boolean.FALSE);
        getProgressBar().setVisible(Boolean.TRUE);
        getProgressBar().setIndeterminate(Boolean.TRUE);
    }//GEN-LAST:event_loginActionPerformed

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
        userController.register();
    }//GEN-LAST:event_registerActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        System.exit(0);
    }//GEN-LAST:event_cancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JTextField email;
    private javax.swing.JLabel error;
    private javax.swing.JLabel gambarGembok;
    private javax.swing.JLabel gambarUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton login;
    private javax.swing.JPasswordField password;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton register;
    // End of variables declaration//GEN-END:variables
    /**
     * @return the error
     */
    public javax.swing.JLabel getError() {
        return error;
    }
// End of variables declaration                   

    /**
     * @return the email
     */
    public javax.swing.JTextField getEmail() {
        return email;
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

    private class WorkerLogin extends SwingWorker<User, Void> {

        @Override
        protected void done() {
            try {
                if (get() != null) {
                    closeDialog();
                } else {
                    groupLayout.replace(getProgressBar(), getError());
                }
                login.setEnabled(Boolean.TRUE);
                register.setEnabled(Boolean.TRUE);
                getProgressBar().setIndeterminate(Boolean.FALSE);
                getProgressBar().setVisible(Boolean.FALSE);
            } catch (InterruptedException | ExecutionException ex) {
                LOGGER.error("error di : {}", ex);
            }
        }

        @Override
        protected User doInBackground() throws Exception {
            Thread.sleep(1000);
            return userController.loginUser();
        }
    }
}

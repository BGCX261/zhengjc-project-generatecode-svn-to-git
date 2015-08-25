/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.frame;

import cn.com.photop.sap.contactme.common.CommonSession;
import cn.com.photop.sap.contactme.model.User;
import cn.com.photop.sap.contactme.service.GetUserService;
import cn.com.photop.sap.contactme.util.StringUtil;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author Jiancheng.Zheng
 */
public class LogonJFrame extends javax.swing.JFrame {

    private JFrame mainJFrame;

    /**
     * Creates new form LogonJFrame
     */
    public LogonJFrame(JFrame jFrame) {
        if (jFrame instanceof MainJFrame) {
            this.mainJFrame = (MainJFrame) jFrame;
        } else {
            this.mainJFrame = (Main4TXTDataJFrame) jFrame;
        }
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     *
     */
    public LogonJFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("用户名");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("密    码");

        jButton1.setText("登录");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("取消");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jButton1)
                        .addGap(41, 41, 41)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(jPasswordField1))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //获取用户名
        String message = "";
        String name = jTextField1.getText().trim();
        if (StringUtil.isNull(name)) {
            message = "请输入用户名";
            javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null,
                    message, "提示", javax.swing.JOptionPane.DEFAULT_OPTION);
            return;
        }
        String pwd = jPasswordField1.getText().trim();
        if (StringUtil.isNull(pwd)) {
            message = "请输入密码";
            javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null,
                    message, "提示", javax.swing.JOptionPane.DEFAULT_OPTION);
            return;
        }
        User user = new User();
        user.setName(name);
        user.setPassword(pwd);

        if (GetUserService.LogonUser(user)) {
            this.setVisible(false);
            this.dispose();
            //发出提示消息
            message = "登录成功";
            javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null,
                    message, "提示", javax.swing.JOptionPane.DEFAULT_OPTION);
            String title = "欢迎您，" + user.getName();
            this.mainJFrame.setTitle(title);

            if (this.mainJFrame instanceof MainJFrame) {
                ((MainJFrame) this.mainJFrame).setJMenuItem4Text("退出管理");
//                ((MainJFrame) this.mainJFrame).getJMenuItem7().setVisible(true);
//                ((MainJFrame) this.mainJFrame).getJMenuItem8().setVisible(true);
//                ((MainJFrame) this.mainJFrame).getJMenuItem10().setVisible(true);
                ((MainJFrame) this.mainJFrame).getJMenu5().setVisible(true);
                ((MainJFrame) this.mainJFrame).getJMenu6().setVisible(true);
                ((MainJFrame) this.mainJFrame).getJMenu7().setVisible(true);
                if (CommonSession.user.getRole().equalsIgnoreCase("SUPERUSER")) {
                    ((MainJFrame) this.mainJFrame).getjMenuItem2().setVisible(true);
                    ((MainJFrame) this.mainJFrame).getjMenuItem11().setVisible(true);
                    ((MainJFrame) this.mainJFrame).getjMenuItem12().setVisible(true);
                    ((MainJFrame) this.mainJFrame).getjMenuItem13().setVisible(true);
                    ((MainJFrame) this.mainJFrame).getjMenuItem14().setVisible( true );
                }
            } else {
                ((Main4TXTDataJFrame) this.mainJFrame).setJMenuItem4Text("退出管理");
                ((Main4TXTDataJFrame) this.mainJFrame).getJMenuItem7().setVisible(true);
                ((Main4TXTDataJFrame) this.mainJFrame).getJMenuItem8().setVisible(true);
                ((Main4TXTDataJFrame) this.mainJFrame).getJMenuItem10().setVisible(true);
//                ((MainJFrame) this.mainJFrame).getJMenu5().setVisible(true);
//                ((MainJFrame) this.mainJFrame).getJMenu6().setVisible(true);
//                ((MainJFrame) this.mainJFrame).getJMenu7().setVisible(true);
//                 
            }
        } else {
            message = "登录失败，请重新登录";
            javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null,
                    message, "提示", javax.swing.JOptionPane.DEFAULT_OPTION);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;


                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LogonJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogonJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogonJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogonJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new LogonJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            /*
             * *关闭当前窗体
             *
             * 如果省略此句，那么就禁用了关闭按钮
             *
             */

            this.dispose();
        } else {
            super.processWindowEvent(e);
        }
    }
}

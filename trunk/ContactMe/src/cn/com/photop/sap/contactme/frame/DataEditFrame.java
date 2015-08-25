/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.frame;

import cn.com.photop.sap.contactme.model.ContactInfo;
import cn.com.photop.sap.contactme.model.User;
import cn.com.photop.sap.contactme.service.ImageService;
import cn.com.photop.sap.contactme.service.impl.ContactInfoService;
import cn.com.photop.sap.contactme.service.impl.UserService;
import cn.com.photop.sap.contactme.util.RegExUtil;
import cn.com.photop.sap.contactme.util.StringUtil;
import java.awt.Desktop;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Jiancheng.Zheng
 */
public class DataEditFrame extends javax.swing.JFrame {

    private ContactInfo ci;
    private JTable jTable;
    private List<ContactInfo> listContactInfo;

    /**
     * Creates new form DataDetailFrame
     */
    public DataEditFrame(ContactInfo ci) {
        this.ci = ci;

        initComponents();
        this.setLocationRelativeTo(null);

        jTextField1.setText(this.ci.getCompany());
        jTextField2.setText(this.ci.getDepartment());
        jTextField8.setText(this.ci.getEmail());
        jTextField6.setText(this.ci.getFax());
        jTextField5.setText(this.ci.getInside());
        jTextField7.setText(this.ci.getMobile());
        jTextField3.setText(this.ci.getName());
        jTextField9.setText(this.ci.getRemark());
        jTextField4.setText(this.ci.getTel());
//        this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING) );
    }

    public DataEditFrame(ContactInfo ci, JTable jTable, List<ContactInfo> listContactInfo) {
        this.ci = ci;
        this.jTable = jTable;
        this.listContactInfo = listContactInfo;
        initComponents();
        this.setLocationRelativeTo(null);

        jTextField1.setText(this.ci.getCompany());
        jTextField2.setText(this.ci.getDepartment());
        jTextField8.setText(this.ci.getEmail());
        jTextField6.setText(this.ci.getFax());
        jTextField5.setText(this.ci.getInside());
        jTextField7.setText(this.ci.getMobile());
        jTextField3.setText(this.ci.getName());
        jTextField9.setText(this.ci.getRemark());
        jTextField4.setText(this.ci.getTel());
//        this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING) );


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton1.setText("保存");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("公司：");

        jLabel2.setText("部门：");

        jLabel3.setText("姓名：");

        jLabel4.setText("电话：");

        jLabel5.setText("内线：");

        jLabel6.setText("传真：");

        jLabel7.setText("手机：");

        jLabel8.setText("邮箱：");

        jLabel9.setText("备注：");

        jTextField1.setEditable(false);

        jTextField2.setEditable(false);

        jTextField3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField3MouseClicked(evt);
            }
        });
        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });

        jTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4MouseClicked(evt);
            }
        });
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });

        jTextField5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField5MouseClicked(evt);
            }
        });
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField5FocusLost(evt);
            }
        });

        jTextField6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField6MouseClicked(evt);
            }
        });
        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField6FocusLost(evt);
            }
        });

        jTextField7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField7MouseClicked(evt);
            }
        });
        jTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField7FocusLost(evt);
            }
        });

        jTextField8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField8MouseClicked(evt);
            }
        });
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField8FocusLost(evt);
            }
        });

        jTextField9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField9MouseClicked(evt);
            }
        });
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField9FocusLost(evt);
            }
        });

        jLabel10.setText("     ");

        jLabel11.setText("     ");

        jLabel12.setText("     ");

        jLabel13.setText("     ");

        jLabel14.setText("     ");

        jLabel15.setText("     ");

        jLabel16.setText("     ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setText("取消");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jButton1)
                        .addGap(33, 33, 33)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 保存
     *
     * @param evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int row = jTable.getSelectedRow();

        jTable.setValueAt(jTextField3.getText(), row, 3);
        jTable.setValueAt(jTextField4.getText(), row, 4);
        jTable.setValueAt(jTextField5.getText(), row, 5);
        jTable.setValueAt(jTextField6.getText(), row, 6);
        jTable.setValueAt(jTextField7.getText(), row, 7);
        jTable.setValueAt(jTextField8.getText(), row, 8);
        jTable.setValueAt(jTextField9.getText(), row, 9);



        this.ci.setEmail(jTextField8.getText());
        this.ci.setFax(jTextField6.getText());
        this.ci.setInside(jTextField5.getText());
        this.ci.setMobile(jTextField7.getText());
        this.ci.setName(jTextField3.getText());
        this.ci.setRemark(jTextField9.getText());
        this.ci.setTel(jTextField4.getText());
        ContactInfoService contactInfoService = new ContactInfoService();
        contactInfoService.update(this.ci);
        //刷新系统list数据
        //新增系统中的数据
        for (int i = 0; i < this.listContactInfo.size(); i++) {
            if (this.listContactInfo.get(i).getId().equals(this.ci.getId())) {
                this.listContactInfo.remove(i);
            }
        }
        this.listContactInfo.add(this.ci);

        setVisible(false);
        dispose();



    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * 取消按钮
     *
     * @param evt
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MouseClicked
        jLabel10.setIcon(ImageService.getImageIcon("onFocus"));
    }//GEN-LAST:event_jTextField3MouseClicked

    private void jTextField4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseClicked
        jLabel11.setIcon(ImageService.getImageIcon("onFocus"));
    }//GEN-LAST:event_jTextField4MouseClicked

    private void jTextField5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField5MouseClicked
        jLabel12.setIcon(ImageService.getImageIcon("onFocus"));
    }//GEN-LAST:event_jTextField5MouseClicked

    private void jTextField6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField6MouseClicked
        jLabel13.setIcon(ImageService.getImageIcon("onFocus"));
    }//GEN-LAST:event_jTextField6MouseClicked

    private void jTextField7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField7MouseClicked
        jLabel14.setIcon(ImageService.getImageIcon("onFocus"));
    }//GEN-LAST:event_jTextField7MouseClicked

    private void jTextField8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField8MouseClicked
        jLabel15.setIcon(ImageService.getImageIcon("onFocus"));
    }//GEN-LAST:event_jTextField8MouseClicked

    private void jTextField9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField9MouseClicked
        jLabel16.setIcon(ImageService.getImageIcon("onFocus"));
    }//GEN-LAST:event_jTextField9MouseClicked

    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost
         String message = "姓名为空";
        if (StringUtil.isNotNull(jTextField3.getText())) {
            message = "";
        }
        judgeTextContent(jTextField3, jLabel10, message, true);
    }//GEN-LAST:event_jTextField3FocusLost

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
        //判断是否正确
        String message = "电话为空";
        if ( StringUtil.isNotNull( jTextField4.getText() )) {
            message = RegExUtil.ereg( RegExUtil.regexData.tel, jTextField4.getText().trim() )?"":"电话号码输入不正确";
        }
        judgeTextContent(jTextField4, jLabel11, message, false);
    }//GEN-LAST:event_jTextField4FocusLost

    private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusLost
        //判断是否正确
        String message = "内线为空";
        if ( StringUtil.isNotNull( jTextField5.getText() )) {
            message = RegExUtil.ereg( RegExUtil.regexData.intege1, jTextField5.getText().trim() )?"":"内线输入不正确";
        }
        judgeTextContent(jTextField5, jLabel12, message, false);
    }//GEN-LAST:event_jTextField5FocusLost

    private void jTextField6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusLost
        //判断是否正确
        String message = "传真为空";
        if ( StringUtil.isNotNull( jTextField6.getText() )) {
            message = RegExUtil.ereg( RegExUtil.regexData.tel, jTextField6.getText().trim() )?"":"传真输入不正确";
        }
        judgeTextContent(jTextField6, jLabel13, message, false);
    }//GEN-LAST:event_jTextField6FocusLost

    private void jTextField7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusLost
        //判断是否正确
        String message = "手机为空";
        if ( StringUtil.isNotNull( jTextField7.getText() )) {
            message = RegExUtil.ereg( RegExUtil.regexData.mobile, jTextField7.getText().trim() )?"":"手机输入不正确";
        }
        judgeTextContent(jTextField7, jLabel14, message, false);
    }//GEN-LAST:event_jTextField7FocusLost

    private void jTextField8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusLost
        //判断是否正确
        String message = "邮箱为空";
        if ( StringUtil.isNotNull( jTextField8.getText() )) {
            message = RegExUtil.ereg( RegExUtil.regexData.email, jTextField8.getText().trim() )?"":"邮箱输入不正确";
        }
        judgeTextContent(jTextField8, jLabel15, message, false);
    }//GEN-LAST:event_jTextField8FocusLost

    private void jTextField9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusLost
        //判断是否正确
        String message = "备注为空";
        if ( StringUtil.isNotNull( jTextField9.getText() )) {
            message = "";
        }
        judgeTextContent(jTextField9, jLabel16, message, false);
    }//GEN-LAST:event_jTextField9FocusLost

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
            java.util.logging.Logger.getLogger(DataEditFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataEditFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataEditFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataEditFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ContactInfo ci = new ContactInfo();
                new DataEditFrame(ci).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
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

    /**
     * 判断填写的内容是否正确
     *
     * @param jtextField
     * @param jLabel
     * @param tip
     */
    private void judgeTextContent(JTextField jtextField, JLabel jLabel, String tip, Boolean obligatory) {
        if (StringUtil.isNotNull(jtextField.getText())) {
            if ( StringUtil.isNull(tip) ) {
               jLabel.setIcon(ImageService.getImageIcon("onCorrect"));
                jLabel.setToolTipText(""); 
            }else{
                jLabel.setIcon(ImageService.getImageIcon("onError"));
                jLabel.setToolTipText( tip ); 
            }
            
        } else {
            jLabel.setIcon(ImageService.getImageIcon(obligatory ? "onError" : "onShow"));
            jLabel.setToolTipText(tip);
        }
    }
}

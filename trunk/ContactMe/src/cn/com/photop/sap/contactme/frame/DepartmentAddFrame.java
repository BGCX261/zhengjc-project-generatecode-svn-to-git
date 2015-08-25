/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.frame;

import cn.com.photop.sap.contactme.common.CommonConstant;
import cn.com.photop.sap.contactme.common.CommonSession;
import cn.com.photop.sap.contactme.model.Company;
import cn.com.photop.sap.contactme.model.ContactInfo;
import cn.com.photop.sap.contactme.model.Department;
import cn.com.photop.sap.contactme.model.User;
import cn.com.photop.sap.contactme.service.impl.ContactInfoService;
import cn.com.photop.sap.contactme.service.impl.DepartmentService;
import cn.com.photop.sap.contactme.service.impl.UserService;
import cn.com.photop.sap.contactme.util.StringUtil;
import java.awt.Desktop;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author Jiancheng.Zheng
 */
public class DepartmentAddFrame extends javax.swing.JFrame {

    private MainJFrame mainJFrame;
    //公司的集合
    private List<Company> listCompany;
    //部门的集合 key 是公司
    private Map<String, List<Department>> mapDepartment;

    /**
     * Creates new form DataDetailFrame
     */
    public DepartmentAddFrame(MainJFrame mainJFrame) {
        this.mainJFrame = mainJFrame;
        listCompany = this.mainJFrame.getListCompany();
        mapDepartment = this.mainJFrame.getMapDepartment();
        initComponents();
        this.setLocationRelativeTo(null);

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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("公司：");

        jLabel2.setText("部门：");

        List<String> companyStrList = new ArrayList<String>();
        companyStrList.add(0, CommonSession.getGroupName());
        for (int i = 0; i < listCompany.size(); i++) {
            companyStrList.add( listCompany.get(i).getCompany() );
        }
        final int size = companyStrList.size() + 1;
        String[] jModel = (String[])companyStrList.toArray( new String[size] );
        jComboBox1.setModel( new javax.swing.DefaultComboBoxModel( jModel ) );
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("保存");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, 207, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jButton1)
                .addGap(41, 41, 41)
                .addComponent(jButton2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 保存
     *
     * @param evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String message = "";
        String name = (String) jComboBox1.getSelectedItem();
        if (CommonSession.getGroupName().equals(name)) {
            message = "请选择公司";
            javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null,
                    message, "提示", javax.swing.JOptionPane.DEFAULT_OPTION);
            return;
        }

        String selectedItem = (String) jComboBox1.getSelectedItem();
        List<Department> departmentList = mapDepartment.get(selectedItem);

        String dname = (String) jTextField1.getText().trim();
        if (StringUtil.isNull(dname)) {
            message = "请输入部门";
            javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null,
                    message, "提示", javax.swing.JOptionPane.DEFAULT_OPTION);
            return;
        }
        for (Department d : departmentList) {
            if( d.getDepartment().equalsIgnoreCase(dname) ){
                message = "部门已经存在";
                javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null,
                        message, "提示", javax.swing.JOptionPane.DEFAULT_OPTION);
                return;
            }
        }

        Department department = new Department();
//        String companyId = "";
//        //根据公司名称获取公司ID
//        for ( Company c:listCompany ) {
//            if( c.getCompany().equalsIgnoreCase( name ) ){
//                companyId = c.getId();
//                break;
//            }
//        }
//        
//        department.setCompany( companyId );
        department.setCompany(name);
        department.setDepartment(dname);
        department.setStatus("1");
        DepartmentService departmentService = new DepartmentService();
        departmentService.save(department);
        this.mainJFrame.getMapDepartment().get(name).add(department);
        this.mainJFrame.initJComboBox2();
        this.mainJFrame.getMapContactInfo().get(name).put(dname, new ArrayList<ContactInfo>());
        this.mainJFrame.initJTree1();
        
        setVisible(false);
        dispose();
        message = "部门新增成功";
        javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null,
                message, "提示", javax.swing.JOptionPane.DEFAULT_OPTION);
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

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
    }//GEN-LAST:event_jComboBox1ItemStateChanged

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
            java.util.logging.Logger.getLogger(DepartmentAddFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DepartmentAddFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DepartmentAddFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DepartmentAddFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new DepartmentAddFrame(new MainJFrame()).setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DepartmentAddFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DepartmentAddFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(DepartmentAddFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
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

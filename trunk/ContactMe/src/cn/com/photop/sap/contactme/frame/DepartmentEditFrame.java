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
import cn.com.photop.sap.contactme.service.GetMDBContactInfoService;
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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author Jiancheng.Zheng
 */
public class DepartmentEditFrame extends javax.swing.JFrame {

    private MainJFrame mainJFrame;
    //公司的集合
    private List<Company> listCompany;
    //所有人员的List
    private List<ContactInfo> listContactInfo;
    //部门的集合 key 是公司
    private Map<String, List<Department>> mapDepartment;

    /**
     * Creates new form DataDetailFrame
     */
    public DepartmentEditFrame(MainJFrame mainJFrame) {
        this.mainJFrame = mainJFrame;
        listCompany = this.mainJFrame.getListCompany();
        mapDepartment = this.mainJFrame.getMapDepartment();
        listContactInfo = this.mainJFrame.getListContactInfo();
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
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("公司");

        jLabel2.setText("新名称");

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

        jLabel3.setText("部门");

        jComboBox2.insertItemAt(CommonConstant.CONSTANT_DEFAULT_DEPARTMENT, 0);
        jComboBox2.setSelectedIndex(0);
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, 0, 207, Short.MAX_VALUE)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jButton1.setText("修改");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jButton1)
                        .addGap(41, 41, 41)
                        .addComponent(jButton2)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(20, Short.MAX_VALUE))
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

        String changeDepartmentName = (String) jComboBox2.getSelectedItem();
        if (CommonConstant.CONSTANT_DEFAULT_DEPARTMENT.equals(changeDepartmentName)) {
            message = "请选择要修改的部门";
            javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null,
                    message, "提示", javax.swing.JOptionPane.DEFAULT_OPTION);
            return;
        }

        String selectedItem = (String) jComboBox1.getSelectedItem();
        List<Department> departmentList = mapDepartment.get(selectedItem);

        String dname = (String) jTextField1.getText().trim();
        if (StringUtil.isNull(dname)) {
            message = "请输入部门新名称";
            javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null,
                    message, "提示", javax.swing.JOptionPane.DEFAULT_OPTION);
            return;
        }
        for (Department d : departmentList) {
            if (d.getDepartment().equalsIgnoreCase(dname)) {
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
        
        for (Department d : departmentList){
            if ( changeDepartmentName.equalsIgnoreCase( d.getDepartment() ) ) {
                department = d;
                break;
            }
        }
        
//        department.setCompany(name);
        //就部门新名称 dname
        department.setDepartment(dname);
//        department.setStatus("1");
        DepartmentService departmentService = new DepartmentService();
        departmentService.update(department);
        //修改部门对应的人员信息
        ContactInfoService contactInfoService = new ContactInfoService();
        // 更新ContactInfo的信息
        String sql = " update contactInfo set [department] = '" + dname + "' where [company] = '" + name + "' and [department] = '" + changeDepartmentName + "'";
        contactInfoService.update(sql);

//        List<Department> dList = this.mainJFrame.getMapDepartment().get(name);
//        for (int i = 0; i < dList.size(); i++) {
//            if (dList.get(i).getCompany().equalsIgnoreCase(name)
//                    && dList.get(i).getDepartment().equalsIgnoreCase(changeDepartmentName)) {
//                dList.remove(i);
//                break;
//            }
//        }
//        this.mainJFrame.getMapDepartment().get(name).add(department);
//        this.mainJFrame.initJComboBox2();
//        List<ContactInfo> ciList = new ArrayList<ContactInfo>();
//        ciList = this.mainJFrame.getMapContactInfo().get(name).get(changeDepartmentName);
//        //更新部门新数据
//        for (int i = 0; i < ciList.size(); i++) {
//            ciList.get(i).setDepartment(dname);
//        }
//        for (int i = 0; i < listContactInfo.size(); i++) {
//            if (listContactInfo.get(i).getCompany().equalsIgnoreCase(name)
//               && listContactInfo.get(i).getDepartment().equalsIgnoreCase(changeDepartmentName)) {
//                listContactInfo.get(i).setDepartment(dname);
//            }
//        }
//
//        this.mainJFrame.getMapContactInfo().get(name).put(dname, ciList);
//        this.mainJFrame.getMapContactInfo().get(name).remove(changeDepartmentName);
//        this.mainJFrame.initJTree1();

        
//        mainJFrame.freshData();
        
        try {
           GetMDBContactInfoService gmdbcis1 = new GetMDBContactInfoService();
           mainJFrame.setMapContactInfo( gmdbcis1.getMapContactInfo() );
            //所有人员的List
            mainJFrame.setListContactInfo( gmdbcis1.getListContactInfo() );
            //公司的集合
            mainJFrame.setListCompany( gmdbcis1.getListCompany() )  ;
            //部门的集合 key 是公司
            mainJFrame.setMapDepartment( gmdbcis1.getMapDepartment() );

            mainJFrame.initJComboBox1();
            mainJFrame.initJComboBox2();
            mainJFrame.initJTree1();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DepartmentEditFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DepartmentEditFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DepartmentEditFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        
        setVisible(false);
        dispose();
        
        message = "部门修改成功";
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
        String selectedItem = (String) jComboBox1.getSelectedItem();
        if (CommonSession.getGroupName().equals(selectedItem)) {
            jComboBox2.removeAllItems();
            jComboBox2.insertItemAt(CommonConstant.CONSTANT_DEFAULT_DEPARTMENT, 0);
            jComboBox2.setSelectedIndex(0);
            return;
        }
        //部门的集合 key 是公司
        List<Department> listDepartment = mapDepartment.get(selectedItem);
//        listDepartment.add(0, "All");
        List<String> departmentStrList = new ArrayList<String>();
        for (int i = 0; i < listDepartment.size(); i++) {
            departmentStrList.add(listDepartment.get(i).getDepartment());
        }
        int size = departmentStrList.size();
        if (size > 0) {
            String[] jModel = (String[]) departmentStrList.toArray(new String[size]);
            jComboBox2.removeAllItems();
            jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(jModel));
            jComboBox2.insertItemAt(CommonConstant.CONSTANT_DEFAULT_DEPARTMENT, 0);
            jComboBox2.setSelectedIndex(0);
        } else {
            jComboBox2.removeAllItems();
            jComboBox2.insertItemAt(CommonConstant.CONSTANT_DEFAULT_DEPARTMENT, 0);
            jComboBox2.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

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
            java.util.logging.Logger.getLogger(DepartmentEditFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DepartmentEditFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DepartmentEditFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DepartmentEditFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new DepartmentEditFrame(new MainJFrame()).setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DepartmentEditFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DepartmentEditFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(DepartmentEditFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
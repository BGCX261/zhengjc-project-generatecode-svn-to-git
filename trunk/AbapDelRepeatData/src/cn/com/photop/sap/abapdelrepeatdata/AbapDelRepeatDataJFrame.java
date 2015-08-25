/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.abapdelrepeatdata;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jiancheng.zheng
 */
public class AbapDelRepeatDataJFrame extends javax.swing.JFrame {

    private Set<String> vectorStr;

    /**
     * Creates new form AbapDelRepeatDataJFrame
     */
    public AbapDelRepeatDataJFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        vectorStr = new HashSet<String>();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("打开文件");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButton1)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jButton1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         vectorStr = new HashSet<String>();
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("打开");
        FileNameExtensionFilter txt = new FileNameExtensionFilter("TXT Files (.txt)", "txt");
        FileNameExtensionFilter csv = new FileNameExtensionFilter("CSV Files (.csv)", "csv");
        chooser.setFileFilter(txt);
        chooser.setFileFilter(csv);
        int rtn = chooser.showOpenDialog(this);
        String message = "";
        if (rtn == JFileChooser.APPROVE_OPTION) {
//            Image i = Toolkit.getDefaultToolkit().getImage(chooser.getSelectedFile().getPath());
            //显示出来
//            System.out.println( "文件地址："+chooser.getSelectedFile().getPath() ); 
//            String message = new CodeGenerate().doGenerate(chooser.getSelectedFile().getPath());
            String filePath = chooser.getSelectedFile().getPath();
            File file = new File(filePath);
            String fileName = filePath.substring(filePath.lastIndexOf("\\"), filePath.length() - 4);
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(
                        new FileInputStream(file)));
                for (String line = br.readLine(); line != null; line = br.readLine()) {
                    if (0 != line.length()) {
                        vectorStr.add(line);
                    }
                }
                if (vectorStr != null && vectorStr.size() != 0) {
                    File generate_File = new File(".\\" + fileName);
                    if (!generate_File.exists()) {
                        generate_File.mkdirs();
                    }
                    File generateFile = new File(".\\" + fileName + "\\" + fileName + ".csv");
                    FileWriter fileWriter = new FileWriter(generateFile);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    for (String content : vectorStr) {
                        content = content + "\r\n";
                        fileWriter.write(content);
                    }

                    fileWriter.close();
                }
                br.close();
                message = "删除重复数据成功";
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AbapDelRepeatDataJFrame.class.getName()).log(Level.SEVERE, null, ex);
                message = "删除重复数据异常1";
            } catch (IOException e) {
                Logger.getLogger(AbapDelRepeatDataJFrame.class.getName()).log(Level.SEVERE, null, e);
                message = "删除重复数据异常2";
            } catch (Exception e) {
                Logger.getLogger(AbapDelRepeatDataJFrame.class.getName()).log(Level.SEVERE, null, e);
                message = "删除重复数据异常3";
            }
            int res = javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null,
                    message, "提示", javax.swing.JOptionPane.DEFAULT_OPTION);
             if (res == JOptionPane.YES_OPTION) {
                 System.exit(0);
             }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AbapDelRepeatDataJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AbapDelRepeatDataJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AbapDelRepeatDataJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AbapDelRepeatDataJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new AbapDelRepeatDataJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
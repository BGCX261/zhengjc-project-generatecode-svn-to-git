/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photop.sap.gc;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 1.设置打开或保存文件时弹出的对话框的文件类型选择。 2.外观选择。
 *
 * @author EX-QINCIDONG001
 *
 */
public class FileSaveExtensionChooser extends JFrame {

    private JButton btn = new JButton("保存文件");
    private JComboBox laf;
    private Vector<SupportedLaF> supportedLookAndFeel = new Vector<SupportedLaF>();

    static class SupportedLaF {

        String name;
        LookAndFeel laf;

        SupportedLaF(String name, LookAndFeel laf) {
            this.name = name;
            this.laf = laf;
        }

        public String toString() {
            return name;
        }
    }

    public FileSaveExtensionChooser() {
        setSize(300, 200);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        add(btn);
        add(new JLabel("选择外观:"));
        setLookAndFeel();
        add(laf);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                String msg = "Hello World!";
                JFileChooser jfc = new JFileChooser();
                FileNameExtensionFilter fnef = new FileNameExtensionFilter("TXT and INI Files (.txt, .ini)", "txt", "ini");
                FileNameExtensionFilter txt = new FileNameExtensionFilter("TXT Files (.txt)", "txt");
                FileNameExtensionFilter ini = new FileNameExtensionFilter("INI Files (.ini)", "ini");

                jfc.addChoosableFileFilter(fnef);
                jfc.addChoosableFileFilter(txt);
                jfc.addChoosableFileFilter(ini);
                jfc.setDialogType(JFileChooser.SAVE_DIALOG);
                int res = jfc.showSaveDialog(FileSaveExtensionChooser.this);
                if (res == JFileChooser.APPROVE_OPTION) {
                    File file = jfc.getSelectedFile();
//                    System.out.println(file.getAbsolutePath());


                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                        bw.write(msg);
                        bw.flush();
                        bw.close();
                        System.out.println("File Save Finished!");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });

        laf.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                SupportedLaF slaf = (SupportedLaF) laf.getSelectedItem();
                try {
                    UIManager.setLookAndFeel(slaf.laf);
                    System.out.println("Set LookAndFeel To " + slaf.name);
                } catch (UnsupportedLookAndFeelException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        this.setLocationRelativeTo(null);
        pack();
    }

    public void setLookAndFeel() {
        UIManager.LookAndFeelInfo[] installedLafs = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo lafInfo : installedLafs) {
            try {
                Class lnfClass = Class.forName(lafInfo.getClassName());
                LookAndFeel laf = (LookAndFeel) (lnfClass.newInstance());
                if (laf.isSupportedLookAndFeel()) {
                    String name = lafInfo.getName();
                    supportedLookAndFeel.add(new SupportedLaF(name, laf));
                }
            } catch (Exception e) {
                continue;
            }
        }

        laf = new JComboBox(supportedLookAndFeel);
        laf.setSelectedIndex(0);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        FileSaveExtensionChooser t2 = new FileSaveExtensionChooser();
    }
}

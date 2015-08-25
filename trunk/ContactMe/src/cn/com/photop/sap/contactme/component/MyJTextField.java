/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**   
* @project ContactMe
* @Title: MyJTextField
* @Package cn.com.photop.sap.contactme.Component 
* @author Jiancheng.Zheng
* @email dahaha@189.cn
* @date 2014-5-12 15:44:54 
* @Copyright photop.cn.Co.,Ltd
* @All right reserved
* @version V1.0   
* @Description: TODO(用一句话描述该文件做什么) 
*
*/
package cn.com.photop.sap.contactme.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;


/** 
* @ClassName: MyJTextField
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Jiancheng.Zheng
* @email dahaha@189.cn
* @date 2014-5-12 15:44:54 
* 
*  
*/
public class MyJTextField extends JTextField {
private static final long serialVersionUID = 1L;   
    private JPopupMenu jPopupMenu = new JPopupMenu();   
    private JMenuItem copy = new JMenuItem("复制");   
    private JMenuItem paste = new JMenuItem("粘贴");   
    private JMenuItem cut = new JMenuItem("剪切");   
       
    MyJTextField myself = this;   
       
    public MyJTextField(){   
        this.addMouseListener(new MouseAdapter(){   
            public void mousePressed(MouseEvent arg0) {   
                if(arg0.getButton()==3){   
                    if(myself.isEnabled()){//如果当前组件处于不可用状态，则不弹出右键菜单   
                       
                        jPopupMenu.removeAll();   
                        if(!myself.isEditable()){   
                            jPopupMenu.add(copy);   
                        }else{   
                            jPopupMenu.add(copy);   
                            jPopupMenu.add(paste);   
                            jPopupMenu.add(cut);   
                        }   
                        jPopupMenu.show(arg0.getComponent(), arg0.getX(), arg0.getY());        
                    }      
                }   
            }   
        });   
           
        jPopupMenu.add(copy);   
        jPopupMenu.add(paste);   
        jPopupMenu.add(cut);   
           
//        copy.setIcon(ImageIcons.copy_gif);   
        copy.addActionListener(new ActionListener() {   
            public void actionPerformed(ActionEvent arg0) {   
                myself.copy();   
                System.out.println("copy");
            }   
        });   
//        paste.setIcon(ImageIcons.paste_png);   
        paste.addActionListener(new ActionListener(){   
            public void actionPerformed(ActionEvent arg0) {   
                myself.paste();        
                System.out.println("paste");
            }          
        });   
//        cut.setIcon(ImageIcons.cut_png);   
        cut.addActionListener(new ActionListener(){   
            public void actionPerformed(ActionEvent arg0) {   
                myself.cut(); 
                System.out.println("cut");
            }          
        });       
    } 
}

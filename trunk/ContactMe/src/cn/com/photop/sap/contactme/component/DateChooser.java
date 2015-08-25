/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: DateChooser @Package
 * cn.com.photop.sap.contactme.component
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-21 15:22:43
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @ClassName: DateChooser 
 * @Description: 日期选择组件
 *
 * @author Jiancheng.Zheng 
 * @email dahaha@189.cn
 * @date 2014-5-21 15:22:43
 * 
*
 */
public class DateChooser extends JPanel implements ActionListener, ChangeListener {

    private static final long serialVersionUID = 1L;
    int startYear = 1980; //默认【最小】显示年份 
    int lastYear = 2050; //默认【最大】显示年份 
    int width = 220; //界面宽度 
    int height = 180; //界面高度 
    Color backGroundColor = Color.gray; //底色 
    //月历表格配色----------------// 
    Color palletTableColor = Color.white; //日历表底色 
    Color todayBackColor = Color.orange; //今天背景色 
    Color weekFontColor = Color.blue; //星期文字色 
    Color dateFontColor = Color.black; //日期文字色 
    Color weekendFontColor = Color.red; //周末文字色 
    //控制条配色------------------// 
    Color controlLineColor = Color.pink; //控制条底色 
    Color controlTextColor = Color.white; //控制条标签文字色 
    Color rbFontColor = Color.white; //RoundBox文字色 
    Color rbBorderColor = Color.red; //RoundBox边框色 
    Color rbButtonColor = Color.pink; //RoundBox按钮色 
    Color rbBtFontColor = Color.red; //RoundBox按钮文字色 
    JDialog dialog; //用于显示日期选择控件
    JSpinner yearSpin; //调节年份的JSpinner
    JSpinner monthSpin; //调节月份
    JSpinner hourSpin; //调节小时
    JSpinner minuteSpin;//调节分钟
    JButton[][] daysButton = new JButton[6][7];//用于显示当前月份每一天所对应的星期的按钮数组
    JFormattedTextField jFormattedTextField;//显示当前选择日期的有格式输入框 
    Calendar c = getCalendar();
    Calendar cal = Calendar.getInstance();
    int currentDay = cal.get(Calendar.DAY_OF_MONTH);

    public DateChooser(JFormattedTextField jftf) {
        jFormattedTextField = jftf;
        //设置布局及边框
        setLayout(new BorderLayout());
        setBorder(new LineBorder(backGroundColor, 2));
        setBackground(backGroundColor);

        //初始化及添加子面板
        JPanel topYearAndMonth = createYearAndMonthPanal();
        add(topYearAndMonth, BorderLayout.NORTH);
        JPanel centerWeekAndDay = createWeekAndDayPanal();
        add(centerWeekAndDay, BorderLayout.CENTER);
    }

    /**
     *
     * 功能描述:用于创建年份及月份显示面板
     *
     * @return 用于创建年份及月份显示面板JPanel对象
     */
    private JPanel createYearAndMonthPanal() {
        int currentYear = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH) + 1;
//        int currentHour = c.get(Calendar.HOUR_OF_DAY);
//        int currentMinute = c.get(Calendar.MINUTE);

        JPanel result = new JPanel();
        result.setLayout(new FlowLayout());
        result.setBackground(controlLineColor);

        yearSpin = new JSpinner(new SpinnerNumberModel(currentYear, startYear,
                lastYear, 1));
        yearSpin.setPreferredSize(new Dimension(48, 20));
        yearSpin.setName("Year");
        yearSpin.setEditor(new JSpinner.NumberEditor(yearSpin, "####"));
        yearSpin.addChangeListener(this);
        result.add(yearSpin);

        JLabel yearLabel = new JLabel("年");
        yearLabel.setForeground(controlTextColor);
        result.add(yearLabel);

        monthSpin = new JSpinner(new SpinnerNumberModel(currentMonth, 1, 12, 1));
        monthSpin.setPreferredSize(new Dimension(35, 20));
        monthSpin.setName("Month");
        monthSpin.addChangeListener(this);
        result.add(monthSpin);

        JLabel monthLabel = new JLabel("月");
        monthLabel.setForeground(controlTextColor);
        result.add(monthLabel);

//        hourSpin = new JSpinner(new SpinnerNumberModel(currentHour, 0, 23, 1));
//        hourSpin.setPreferredSize(new Dimension(35, 20));
//        hourSpin.setName("Hour");
//        hourSpin.addChangeListener(this);
//        result.add(hourSpin);

//        JLabel hourLabel = new JLabel("时");
//        hourLabel.setForeground(controlTextColor);
//        result.add(hourLabel);
// 
//        minuteSpin = new JSpinner(new SpinnerNumberModel(currentMinute, 0, 59,
//                1));
//        minuteSpin.setPreferredSize(new Dimension(35, 20));
//        minuteSpin.setName("Minute");
//        minuteSpin.addChangeListener(this);
//        result.add(minuteSpin);
// 
//        JLabel minuteLabel = new JLabel("分");
//        minuteLabel.setForeground(controlTextColor);
//        result.add(minuteLabel);

        return result;
    }

    /**
     *
     * 功能描述:用于创建当前月份中每一天按对应星期排列所在面板
     *
     * @return
     */
    private JPanel createWeekAndDayPanal() {
        String colname[] = {"日", "一", "二", "三", "四", "五", "六"};
        JPanel result = new JPanel();
        //设置固定字体，以免调用环境改变影响界面美观 
        result.setFont(new Font("宋体", Font.PLAIN, 12));
        result.setLayout(new GridLayout(7, 7));
        result.setBackground(Color.white);
        JLabel cell; //显示星期

        for (int i = 0; i < 7; i++) {
            cell = new JLabel(colname[i]);
            cell.setHorizontalAlignment(JLabel.CENTER);
            if (i == 0 || i == 6) {
                cell.setForeground(weekendFontColor);
            } else {
                cell.setForeground(weekFontColor);
            }
            result.add(cell);
        }

        int actionCommandId = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                JButton numberButton = new JButton();
                numberButton.setBorder(null);
                numberButton.setHorizontalAlignment(SwingConstants.CENTER);
                numberButton.setActionCommand(String.valueOf(actionCommandId));
                numberButton.addActionListener(this);
                numberButton.setBackground(palletTableColor);
                numberButton.setForeground(dateFontColor);
                if (j == 0 || j == 6) {
                    numberButton.setForeground(weekendFontColor);
                } else {
                    numberButton.setForeground(dateFontColor);
                }
                daysButton[i][j] = numberButton;
                numberButton.addMouseListener(new MouseAdapter() {

                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2) { //鼠标双击再次时
                            closeAndSetDate();
                        }
                    }
                });
                result.add(numberButton);
                actionCommandId++;
            }
        }

        return result;
    }

    private JDialog createDialog(Frame owner) {
        JDialog result = new JDialog(owner, "日期时间选择", true);
        result.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        result.getContentPane().add(this, BorderLayout.CENTER);
        result.pack();
        result.setSize(width, height);
        return result;
    }

    public void showDateChooser(Point position) {
        Object tmpobj = SwingUtilities.getWindowAncestor(jFormattedTextField);
        if (tmpobj.getClass().isInstance(new JDialog()) || tmpobj.getClass().getSuperclass().isInstance(new JDialog())) {
            JDialog ownerdialog = (JDialog) SwingUtilities.getWindowAncestor(jFormattedTextField);
            Frame owner = (Frame) SwingUtilities.getWindowAncestor(ownerdialog);
            if (dialog == null || dialog.getOwner() != owner) {
                dialog = createDialog(owner);
            }
            dialog.setLocation(getAppropriateLocation(owner, position));
        } else if (tmpobj.getClass().isInstance(new JFrame()) || tmpobj.getClass().getSuperclass().isInstance(new JFrame())) {
            JFrame ownerFrame = (JFrame) SwingUtilities.getWindowAncestor(jFormattedTextField);
            if (dialog == null || dialog.getOwner() != ownerFrame) {
                dialog = createDialog(ownerFrame);
            }
            dialog.setLocation(getAppropriateLocation(ownerFrame, position));
        }

        flushWeekAndDay();
        dialog.setVisible(true);
    }

    Point getAppropriateLocation(Frame owner, Point position) {
        Point result = new Point(position);
        Point p = owner.getLocation();
        int offsetX = (position.x + width) - (p.x + owner.getWidth());
        int offsetY = (position.y + height) - (p.y + owner.getHeight());

        if (offsetX > 0) {
            result.x -= offsetX;
        }

        if (offsetY > 0) {
            result.y -= offsetY;
        }

        return result;

    }

    public void closeAndSetDate() {
        setDate(c.getTime());
        dialog.dispose();
    }

    private Calendar getCalendar() {
        Calendar result = Calendar.getInstance();
        result.setTime(getDate());
        return result;
    }

    private int getSelectedYear() {
        return ((Integer) yearSpin.getValue()).intValue();
    }

    private int getSelectedMonth() {
        return ((Integer) monthSpin.getValue()).intValue();
    }

//    private int getSelectedHour() {
//        return ((Integer) hourSpin.getValue()).intValue();
//    }
// 
//    private int getSelectedMinute() {
//        return ((Integer) minuteSpin.getValue()).intValue();
//    }
    private void dayColorUpdate(boolean isOldDay) {
        int day = c.get(Calendar.DAY_OF_MONTH);
//        System.out.println(day+"-----day-----");
        c.set(Calendar.DAY_OF_MONTH, currentDay);
//        System.out.println("当前日期day:"+c.get(Calendar.DATE));
        int actionCommandId = day - 2 + c.get(Calendar.DAY_OF_WEEK);
        int i = actionCommandId / 7;
        int j = actionCommandId % 7;
        if (isOldDay) {
            daysButton[i][j].setForeground(dateFontColor);
        } else {
            daysButton[i][j].setForeground(todayBackColor);
        }
    }

    private void flushWeekAndDay() {
        c.set(Calendar.DAY_OF_MONTH, currentDay);
        int maxDayNo = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int dayNo = 2 - c.get(Calendar.DAY_OF_WEEK);
//        System.out.println("某月日期值范围："+dayNo+"----"+maxDayNo);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                String s = "";
                if (dayNo >= 1 && dayNo <= maxDayNo) {
                    s = String.valueOf(dayNo);
                }
                daysButton[i][j].setText(s);
                dayNo++;
            }
        }
        dayColorUpdate(false);
    }

    public void stateChanged(ChangeEvent e) {
        JSpinner source = (JSpinner) e.getSource();

//        if (source.getName().equals("Minute")) {
//            c.set(Calendar.MINUTE, getSelectedMinute());
//             
//            return;
//        }
//        if (source.getName().equals("Hour")) {
//            c.set(Calendar.HOUR_OF_DAY, getSelectedHour());
//            
//            return;
//        }
        dayColorUpdate(true);
        if (source.getName().equals("Year")) {
            c.set(Calendar.YEAR, getSelectedYear());
        }
        if (source.getName().equals("Month")) {
            c.set(Calendar.MONTH, getSelectedMonth() - 1);
        }

        flushWeekAndDay();
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source.getText().length() == 0) {
            return;
        }
        dayColorUpdate(true);
        source.setForeground(todayBackColor);
        int newDay = Integer.parseInt(source.getText());
        c.set(Calendar.DAY_OF_MONTH, newDay);

    }

    public void setDate(Date date) {
        jFormattedTextField.setText(getDefaultDateFormat().format(date));
    }

    public Date getDate() {

        try {
            String dateString = jFormattedTextField.getText();
//            System.out.println(dateString+"---------");
            return getDefaultDateFormat().parse(dateString);
        } catch (ParseException e) {
            return getNowDate();
        } catch (Exception ee) {
            return getNowDate();
        }
    }

    private static Date getNowDate() {
        return Calendar.getInstance().getTime();
    }

    private static SimpleDateFormat getDefaultDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    /**
     * @Title: main(这里用一句话描述这个方法的作用) @Description: TODO(这里用一句话描述这个方法的作用)
     *
     * @param args 设定文件
     * @return void 返回类型
     * @throws
     *
     */
    public static void main(String[] args) {
        JFrame jf;
        final JFormattedTextField tfDate;
        JLabel lb;
        jf = new JFrame("日期组件");
        jf.setLayout(null);
        jf.setBounds(10, 10, 300, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lb = new JLabel("选择日期：");
        lb.setBounds(15, 60, 80, 30);


        tfDate = new JFormattedTextField();
        tfDate.setBounds(100, 60, 120, 23);
        tfDate.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent mouseevent) { // 添加文本框单击事件
                DateChooser mDateChooser = new DateChooser(tfDate);
                // 设置DateChooser弹出位置
                Point p = tfDate.getLocationOnScreen();
                p.y = p.y + 30;
                mDateChooser.showDateChooser(p);
                tfDate.requestFocusInWindow();
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });
        jf.add(tfDate);
        jf.add(lb);
        jf.setVisible(true);


    }
}

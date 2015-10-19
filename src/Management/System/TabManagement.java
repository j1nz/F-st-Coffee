/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management.System;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.accessibility.AccessibleRole;
import javax.swing.BorderFactory;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabManagement extends JFrame {

    String tempUser = null;
    String tempLevel = null;
    
    private Border lineBorder = BorderFactory.createLineBorder(Color.blue);
    private Border raisedBevel = BorderFactory.createRaisedBevelBorder();
    private Border loweredBevel = BorderFactory.createLoweredBevelBorder();
    
    private Border panelBorder = BorderFactory
            .createEmptyBorder(20, 20, 20, 20);

    public TabManagement() {
        
        setDisplay();
        prepareGUI();
    }
    
    public TabManagement(String user, String level){
        tempUser = user;
        tempLevel = level;
        
        prepareGUI();
        setDisplay();
    }

    /**
     * set display for JFrame
     */
    private void setDisplay() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(985, 660);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Add TabbedPane vào JFrame
     */
    private void prepareGUI() {
        add(createTabbedPane());
    }

    // Hàm tạo TabPane
    private JTabbedPane createTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();

        /* create three JPanel, which is content of tabs */
        JPanel pn_deal = createPane1();
        JPanel pn_cash = createJPanel("content of panel 2");
        JPanel pn_report = createJPanel("content of panel 3");
        JPanel pn_config = createPaneConfig();

        /* Thêm tab*/
        tabbedPane.addTab("DEAL", null, pn_deal, "Thực hiện giao dịch");
        tabbedPane.addTab("CASH", null, pn_cash, "Thay đổi giá thực đơn");
        tabbedPane.addTab("REPORT", null, pn_report, "Báo cáo nhanh, ghi chú lại quá trình vận hành");
        tabbedPane.addTab("CONFIG", null, pn_config, "Cấu hình lại shop khi có thay đổi");

        
        tabbedPane.addChangeListener(changeListener);
        
        return tabbedPane;
    }

    /**
     * create JPanel 1 contain a JTextArea
     */
    private JPanel createPaneConfig() {
        JPanel panel = new JPanel();
        
        TabConfig showMn = new TabConfig(tempUser, tempLevel);
        panel.add(showMn, new BorderLayout().NORTH);
        
        return panel;
    }

    private JPanel createPane1() {
        JPanel panel = new JPanel();
        //panel.add(new JScrollPane(createTextArea(10, 40)));
        return panel;
    }

    /**
     * create a JPanel contain a JLabel
     */
    private JPanel createJPanel(String text) {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        JLabel lb = new JLabel(text);
        lb.setHorizontalAlignment(JLabel.CENTER);
        panel.add(lb);
        return panel;
    }

    /**
     * create a JTextArea with rows and columns, two method setWrapStyleWord and
     * setLineWrap make text can down line when text too long
     */
    private JTextArea createTextArea(int row, int col) {
        JTextArea ta = new JTextArea(row, col);
        ta.setWrapStyleWord(true);
        ta.setLineWrap(true);
        ta.setForeground(Color.BLUE);
        return ta;
    }

    ChangeListener changeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            
        }
    };
    public static void main(String[] args) {
        new TabManagement();
    }
}

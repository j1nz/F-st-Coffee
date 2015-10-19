/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Management.Fogot_Password;
import Management.System.TabConfig;
import Management.System.TabManagement;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author thanglt
 */
public class Login_System extends JFrame {

    JFrame mainFrame = new JFrame();

    public Login_System() {
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        //Panel Control
        JPanel controlpanel = new JPanel();
        mainFrame.add(controlpanel);
        
        //Khởi tạo panel để chứa các Element(button, textfield...)
        JPanel pn_logo = new JPanel(); //chứa logo
        JPanel pn_showImagesUser = new JPanel(); // chứa ảnh đại diện
        JPanel pn_enter = new JPanel(new GridLayout(2, 2, 10, 10)); //chứa ô nhập vào
        //pn_enter.setLayout(new BoxLayout(pn_enter, BoxLayout.Y_AXIS));
        pn_enter.setBounds(400, 300, 300, 150);
        
        JPanel pn_submit = new JPanel(); //chứa nút submit
        pn_submit.setSize(200, 50);
        pn_submit.setBounds(400, 550, 100, 30);
        
        //add
        controlpanel.add(pn_logo);
        controlpanel.add(pn_showImagesUser);
        controlpanel.add(pn_enter);
        controlpanel.add(pn_submit);
        
        //nút submit
        JButton bt_submit = new JButton("Submit");
        pn_submit.add(bt_submit, new BoxLayout(pn_submit, BoxLayout.Y_AXIS));
        
        //khởi tạo các element
        JLabel lb_username = new JLabel("User Name: "); 
        JLabel lb_password = new JLabel("Password: "); 
        JTextField tf_username = new JTextField(12); //Nhập vào user name
        JPasswordField tf_password = new JPasswordField(); // nhập pass
        
        //
        pn_enter.add(lb_username, BorderLayout.WEST);
        pn_enter.add(tf_username);
        pn_enter.add(lb_password, BorderLayout.WEST);
        pn_enter.add(tf_password);
        
        //Sự kiện khi nhấn Button tên:  "SUBMIT"
        bt_submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                TabManagement tmng = new TabManagement();

                setVisible(false);
                tmng.setVisible(true);

            }
        });
    }

    private JPanel createPanelLogin() {
        JPanel pn_control = new JPanel();
        //controlpanel.setSize(mainFrame.getSize());
        

        return pn_control;
    }

    // Hàm kiểm tra user nhập vào đúng hay ko
    public void checkAccount() {
        try {
            if (rb_fogotPass.isSelected() == true) {
                Fogot_Password fogot = new Fogot_Password();
                setVisible(false);
                fogot.show();
            } else if (tf_username.getText().equals("")) {
                lb_error.setText("Please enter your USERNAME");
            } else if (tf_password.getText().equals("")) {
                lb_error.setText("Please enter your PASSWORD");
            } else {

                preparedStatement = cn.prepareStatement("SELECT * FROM user_manager WHERE username = ? AND password = ?");
                preparedStatement.setString(1, tf_username.getText());
                preparedStatement.setString(2, ps.MD5(tf_password.getText()));

                rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    TabManagement tmng = new TabManagement();
                    TabConfig tcf = new TabConfig(rs.getString("username"), rs.getString("type"));
                    
                    setVisible(false);
                    tmng.setVisible(true);
                } else {
                    lb_error.setText("Please check your account again!!!");
                    tf_password.setText("");
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
    public static void main(String[] args) {
        new Login_System();
    }
}

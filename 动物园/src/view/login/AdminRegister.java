package view.login;

import dao.Register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;


public class AdminRegister extends JPanel {


    public AdminRegister() {
        init();
    }

    void init() {


        JFrame frame = new JFrame("注册用户账号");
        frame.setLayout(null);

        JLabel nameStr = new JLabel("用户名:");
        nameStr.setBounds(250, 150, 100, 25);
        frame.add(nameStr);

        JLabel accountNumberStr = new JLabel("账号:");
        accountNumberStr.setBounds(250, 200, 100, 25);
        frame.add(accountNumberStr);

        JLabel telephoneStr = new JLabel("电话号码:");
        telephoneStr.setBounds(250, 250, 100, 25);
        frame.add(telephoneStr);

        JLabel passwordStr = new JLabel("密码:");
        passwordStr.setBounds(250, 300, 100, 25);
        frame.add(passwordStr);

        JLabel confrimStr = new JLabel("确认密码:");
        confrimStr.setBounds(250, 350, 100, 30);
        frame.add(confrimStr);


        JTextField userName = new JTextField();
        userName.setBounds(320, 150, 150, 25);
        frame.add(userName);

        JTextField Account = new JTextField();
        Account.setBounds(320, 200, 150, 25);
        frame.add(Account);

        JTextField Telephone = new JTextField();
        Telephone.setBounds(320, 250, 150, 25);
        frame.add(Telephone);

        JPasswordField password = new JPasswordField();
        password.setBounds(320, 300, 150, 25);
        frame.add(password);

        JPasswordField confrimPassword = new JPasswordField();
        confrimPassword.setBounds(320, 350, 150, 25);
        frame.add(confrimPassword);


        JButton buttonregister = new JButton("注册");
        buttonregister.setBounds(270, 400, 70, 25);
        frame.add(buttonregister);


        frame.setBounds(400, 100, 800, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JButton out=new JButton("返回");
        out.setBounds(400, 400, 70, 25);
        frame.add(out);


        out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Login_Register login_register = new Login_Register();
            }
        });



        //为注册按钮增加监听器
        buttonregister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = userName.getText();
                String accountNumber = Account.getText();
                String passwd = new String(password.getPassword());
                String confrimpasswd = new String(confrimPassword.getPassword());
                String telephone = Telephone.getText();


                //创建Register类
                Register register = new Register();
                register.setAccountNumber(accountNumber);
                register.setName(name);
                register.setPassword(passwd);
                register.setconfirmpasswd(confrimpasswd);
                register.setTelephone(telephone);


                //如果注册成功，返回登录界面
                try {
                    if (register.JudgeRegister()) {

                        frame.setVisible(false);

                        Login_Register login_register = new Login_Register();

                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    //e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }

        });
    }

}

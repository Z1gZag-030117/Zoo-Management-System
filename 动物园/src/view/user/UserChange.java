package view.user;

/*
     用户修改信息界面
 */

import view.breeder.BreederManage;
import view.login.Login_Register;
import dao.Register;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class UserChange extends JFrame {

    public UserChange(String accountOld) {



        JFrame frame = new JFrame("修改用户信息");
        frame.setLayout(null);

        JLabel nameStr = new JLabel("用户名:");
        nameStr.setBounds(250, 150, 100, 25);
        frame.add(nameStr);

       JLabel accountStr=new JLabel("账号");
       accountStr.setBounds(250,200,100,25);
       frame.add(accountStr);


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

        JTextField accountNumber = new JTextField();
        accountNumber.setBounds(320, 200, 150, 25);
        frame.add(accountNumber);

        JTextField Telephone = new JTextField();
        Telephone.setBounds(320, 250, 150, 25);
        frame.add(Telephone);

        JPasswordField password = new JPasswordField();
        password.setBounds(320, 300, 150, 25);
        frame.add(password);

        JPasswordField confrimPassword = new JPasswordField();
        confrimPassword.setBounds(320, 350, 150, 25);
        frame.add(confrimPassword);


        JButton buttonregister = new JButton("确认修改");
        buttonregister.setBounds(270, 400, 100, 25);
        frame.add(buttonregister);

        JButton out=new JButton("返回");
        out.setBounds(390,400,70,25);
        frame.add(out);


        frame.setBounds(400, 100, 800, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserDask userDask=new UserDask(accountNumber.getText());
                frame.setVisible(false);
            }
        });


        buttonregister.addActionListener(new ActionListener() {
            Register register = new Register();
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = accountNumber.getText();
                String name= userName.getText();
                String telephone= Telephone.getText();
                String psd= new String(password.getPassword());
                String cpsd= new String(confrimPassword.getPassword());

                if(name.equals("")){
                    JOptionPane.showMessageDialog(null, "用户名不能为空！", "用户名", JOptionPane.ERROR_MESSAGE);
                }
                if (account.equals("")){
                    JOptionPane.showMessageDialog(null, "账号不能为空！", "账号为空", JOptionPane.ERROR_MESSAGE);
                }
                if (telephone.length()!=11){
                    JOptionPane.showMessageDialog(null,"电话号码应该为11位","电话号码输入错误",JOptionPane.ERROR_MESSAGE);
                }
                if (!(psd.equals(cpsd))){
                    JOptionPane.showMessageDialog(null, "两次输入的密码不一致!", "密码不一致", JOptionPane.ERROR_MESSAGE);
                }
                if (psd.length()<4){
                    JOptionPane.showMessageDialog(null,"密码设置错误,码不能小于4位","密码不能小于4位",JOptionPane.ERROR_MESSAGE);
                }

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Shanghai","root","123456");
                    String sql = "update user set account = ?, username = ? , password = ?, telephone = ? where account= ?";
                    PreparedStatement preparedStatement=connection.prepareStatement(sql);
                    preparedStatement.setString(1,account);
                    preparedStatement.setString(2,name);
                    preparedStatement.setString(3,psd);
                    preparedStatement.setString(4,telephone);
                    preparedStatement.setString(5,accountOld);
                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "修改信息成功", "修改信息成功", JOptionPane.NO_OPTION);
                    frame.setVisible(false);
                    UserDask userDask=new UserDask(account);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }
}
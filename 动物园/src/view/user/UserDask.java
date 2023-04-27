package view.user;

import view.login.Login_Register;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


/*
     用户主界面
 */
public class UserDask extends JFrame {

    String telephone;
    String name;



    public UserDask(String account){


        JFrame userdask = new JFrame("用户界面");
        userdask.setLayout(null);


        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Shanghai","root","123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            while(resultSet.next() ){
                if(resultSet.getString("account").equals(account)){
                    name=resultSet.getString("username");
                    telephone =resultSet.getString("telephone");
                }
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        JLabel label=new JLabel("账号：");
        label.setBounds(100,100,50,25);
        userdask.add(label);

        JLabel label1=new JLabel(account);
        label1.setBounds(150,100,80,25);
        userdask.add(label1);

        JLabel label2=new JLabel("用户名：");
        label2.setBounds(90,120,80,25);
        userdask.add(label2);

        JLabel label3=new JLabel(name);
        label3.setBounds(150,120,80,25);
        userdask.add(label3);

        JLabel label4=new JLabel("电话号码:");
        label4.setBounds(80,140,100,25);
        userdask.add(label4);

        JLabel label5=new JLabel(telephone);
        label5.setBounds(150,140,150,25);
        userdask.add(label5);

        JButton buttonchange =new JButton("修改用户信息");
        buttonchange.setBounds(300,250,110,25);
        userdask.add(buttonchange);

        JButton buttonvisit =new JButton("参观");
        buttonvisit.setBounds(300,300,110,25);
        userdask.add(buttonvisit);

        JButton buttonout =new JButton("退出登录");
        buttonout.setBounds(300,350,110,25);
        userdask.add(buttonout);

        userdask.setBounds(400, 100, 800, 640);
        userdask.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userdask.setVisible(true);

        //进入修改页面
        buttonchange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userdask.setVisible(false);
                UserChange userchange=new UserChange(account);
            }
        });

        //进入参观界面
        buttonvisit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userdask.setVisible(false);
                UserVisit userVisit=new UserVisit(account);
            }
        });


        //退出登录
        buttonout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userdask.setVisible(false);
                Login_Register login_register = new Login_Register();
            }
        });}



//    public static void main(String[] args) {
//        UserDask userDask=new UserDask();
//    }
}

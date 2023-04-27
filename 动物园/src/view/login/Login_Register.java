package view.login;

import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import po.Admin;
import servlce.Login;
import view.breeder.BreederDask;
import view.principal.PrincipalDask;
import view.user.UserDask;
import java.awt.Image;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import javax.swing.*;


public class Login_Register extends JPanel {
//    String account;
    String name;
    int ID;
    int identity;
    String useraccount;

    BufferedImage bufferedImage;
    int x=0,y=0,bgwidth,bgheight;


    public Login_Register() {
        init();
    }

    //登录界面初始化
    public void init() {

        JFrame frame = new JFrame("动物园登录");
        frame.setLayout(null);
        ImageIcon image=new ImageIcon("zoo.png");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(Color.cyan);

//        try {
//            bufferedImage=ImageIO.read(new File("C:\\Users\\zhu zhe\\Desktop\\，\\java\\工作室\\mysql connect\\zoo.png"));
//            bgwidth=bufferedImage.getWidth();
//            bgheight=bufferedImage.getHeight();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        JLabel Label = new JLabel("欢迎来到动物园！");
        Label.setFont(new Font("华文行楷",Font.PLAIN,40));
        Label.setBounds(235,70,1500,50);
        frame.add(Label);


        JLabel nameStr = new JLabel("账号:");
        nameStr.setBounds(250, 200, 100, 25);
        frame.add(nameStr);


        JLabel passwordStr = new JLabel("密码:");
        passwordStr.setBounds(250, 250, 100, 25);
        frame.add(passwordStr);


        JTextField accountNumber = new JTextField();
        accountNumber.setBounds(300, 200, 150, 25);
        frame.add(accountNumber);


        JPasswordField password = new JPasswordField();
        password.setBounds(300, 250, 150, 25);
        frame.add(password);

        JButton buttonlogin = new JButton("登录");
        buttonlogin.setBounds(275, 300, 70, 25);
        frame.add(buttonlogin);

        //所有人都是用户，都存在用户数据库

        //院长从用户中指定人成为饲养员，并将饲养员数据传入饲养员数据库
        //登录时判断用户是饲养员还是一般用户
        //饲养员登录进入饲养员界面
        //用户登录进入用户界面
        //用户可以原则修改信息或者成为参观者
        //用户选择成为参观者，数据传入参观者数据库，并且进入参观者界面

        JButton buttonregisterUser = new JButton("注册用户");
        buttonregisterUser.setBounds(375, 300, 90, 25);
        frame.add(buttonregisterUser);


        frame.setBounds(400, 100, 800, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        //为登录按钮添加监听器
        buttonlogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String account = accountNumber.getText();
                String passwd = new String(password.getPassword());
                useraccount=accountNumber.getText();
                //创建一个Admin用户，把输入框中的用户名密码和提出来
                Admin admin = new Admin();
                admin.setAccountNumber(account);
                admin.setPassword(passwd);
                admin.getID();

                //登录
                Login login = new Login();
                login.setAdmin(admin);


                if (login.JudgeAdmin() == 0) {
                    //弹出账号或密码错误的窗口
                    JOptionPane.showMessageDialog(null, "账号或密码错误", "账号或密码错误", JOptionPane.WARNING_MESSAGE);
                    //清除密码框中的信息
                    password.setText("");
                    //清除账号框中的信息
                    accountNumber.setText("");

                    //System.out.println("登陆失败");
                } else {
                    //弹出登录成功的窗口
                    JOptionPane.showMessageDialog(null, "登陆成功", "登陆成功", JOptionPane.NO_OPTION);
                    //点击确定后会跳转到主窗口
                    frame.setVisible(false);

                    //注册驱动
                    try {
                        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                        //获取链接
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Shanghai","root","123456");
                        //执行sql
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("select * from user ");

                        //判断用户身份
                        while (resultSet.next()) {

                            identity = resultSet.getInt("identity");
                            //System.out.println(identity);
                            String accountnumber = resultSet.getString("account");
                            //System.out.println(accountnumber);
                            name = resultSet.getString("username");
                            //System.out.println(name);
                            ID = resultSet.getInt("ID");
                            //System.out.println(ID);
                            if (accountnumber.equals(admin.getAccountNumber())) {
                                //System.out.println(identity);
                                if (identity == 0) {
                                    frame.setVisible(false);
                                    UserDask userDask = new UserDask(useraccount);//进入用户界面
                                    break;

                                } else if (identity == 1) {
                                    frame.setVisible(false);
                                    BreederDask breederDask = new BreederDask();//进入饲养员界面
                                    break;
                                } else {
                                    frame.setVisible(false);
                                    PrincipalDask principalDask = new PrincipalDask();//进入院长界面
                                    break;
                                }
                                //System.out.println(ID);

                            }

                        }


                        //关闭链接
                        resultSet.close();
                        statement.close();
                        connection.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                }
                  //return identity;
            }

        });



        //为注册按钮添加监听器
        buttonregisterUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //注册页面
                frame.setVisible(false);
                AdminRegister ar = new AdminRegister();
            }
        });
    }


    public static void main(String[] args) throws Exception {
        //主程序
        //登录窗口
        Login_Register login_register = new Login_Register();

    }



    @Override
    public void paint(Graphics g) {
        g.drawImage(bufferedImage,x,y,bgwidth,bgheight,null);
    }
}

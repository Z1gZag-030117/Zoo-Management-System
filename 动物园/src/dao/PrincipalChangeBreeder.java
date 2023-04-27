package dao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrincipalChangeBreeder {
    String name;
    String account;
    String password;
    String confirmpassword;
    String telephone;



    //链接数据库
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/mydata?serverTimezone=UTC&characterEncoding=utf-8";
    private String user = "root";
    private String sqlpassword = "123456";


    void changeName(String name) {
        this.name = name;
    }
    void changeAccount(String account) { this.account = account; }
    void changePassword(String password) {
        this.password = password;
    }
    void changeConfirmpasswd(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
    void changeTelephone(String telephone) {this.telephone=telephone;}



    //判断修改账号是否符合规则
    boolean JudgeChange() throws SQLException, ClassNotFoundException {

        if(this.name.equals("")) {
            JOptionPane.showMessageDialog(null, "用户名不能为空！", "用户名", JOptionPane.ERROR_MESSAGE);
            return false;
        }

//        if(this.account.equals("")) {
//            JOptionPane.showMessageDialog(null, "账号不能为空！", "账号为空", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }

        if(this.password.equals("")) {
            JOptionPane.showMessageDialog(null, "密码不能为空！", "密码为空", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!this.password.equals(this.confirmpassword)) {
            JOptionPane.showMessageDialog(null, "两次输入的密码不一致!", "密码不一致", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(this.telephone.length()!=11){
            JOptionPane.showMessageDialog(null,"电话号码应该为11位","电话号码输入错误",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(this.password.length()<4){
            JOptionPane.showMessageDialog(null,"密码设置错误","密码不能小于4位",JOptionPane.ERROR_MESSAGE);
        }

        //符合规则，弹出修改成功的窗口，并将账号添加数据库
        JOptionPane.showMessageDialog(null, "修改成功");
        changeAdmin();
        return true;
    }

    //向数据库修改账户

    void changeAdmin() throws ClassNotFoundException, SQLException {
        String sql="insert into user set username=?,password=?,telephone=?,account=?";
        Class.forName(driver);
        try {
            Connection conn = DriverManager.getConnection(url, user, sqlpassword);
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, this.name);
            ps.setString(2, this.password);
            ps.setString(3,this.telephone);
            ps.setString(4,this.account);


            ps.executeUpdate();
            ps.close();
            conn.close();

        }catch(SQLException ex) {
            System.out.println("修改用户失败！");
        }

    }
}

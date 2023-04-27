package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Register {
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


    public void setName(String name) { this.name = name; }
    public void setAccountNumber(String accountNumber) { this.account = accountNumber; }
    public void setPassword(String password) { this.password = password; }
    public void setconfirmpasswd(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
    public void setTelephone(String telephone) {this.telephone=telephone;}



    //判断注册的账号是否符合规则
    public boolean JudgeRegister() throws SQLException, ClassNotFoundException {

        if(this.name.equals("")) {
            JOptionPane.showMessageDialog(null, "用户名不能为空！", "用户名", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(this.account.equals("")) {
            JOptionPane.showMessageDialog(null, "账号不能为空！", "账号为空", JOptionPane.ERROR_MESSAGE);
            return false;
        }

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
            JOptionPane.showMessageDialog(null,"密码设置错误,码不能小于4位","密码不能小于4位",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //符合规则，弹出注册成功的窗口，并将账号添加数据库
        JOptionPane.showMessageDialog(null, "注册成功");
        addAdmin();
        return true;
    }

    //向数据库添加Admin账户
    void addAdmin() throws ClassNotFoundException, SQLException {
        String sql="insert into user (account, username, password,telephone) values (?,?,?,?)";
        Class.forName(driver);
        try {
            Connection conn = DriverManager.getConnection(url, user, sqlpassword);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, this.account);
            ps.setString(2, this.name);
            ps.setString(3, this.password);
            ps.setString(4,this.telephone);


            ps.executeUpdate();
            ps.close();
            conn.close();

        }catch(SQLException ex) {
            System.out.println("添加用户失败！");
        }

    }

}

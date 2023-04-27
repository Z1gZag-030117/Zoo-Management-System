package dao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserAddComment {
    String text;
    String account;
    String park1;


    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/mydata?serverTimezone=UTC&characterEncoding=utf-8";
    private String user = "root";
    private String sqlpassword = "123456";

    public void setText(String text){this.text=text;}
    public void setAccount(String account){this.account=account;}
    public void setPark1(String park){this.park1=park;}


    public boolean JudgeAdd() throws SQLException, ClassNotFoundException{

        if (this.text.equals("写下你的评论...")){
            JOptionPane.showMessageDialog(null, "请写下你的评论", "请写下你的评论", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        JOptionPane.showMessageDialog(null, "评论成功");
        addcomment();
        return true;
    }



    void addcomment() throws ClassNotFoundException ,SQLException{
        String sql="insert into comment (content,userID,park) values (?,?,?)";
        Class.forName(driver);
        try{
            Connection conn = DriverManager.getConnection(url, user, sqlpassword);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,this.text);
            ps.setString(2,this.account);
            ps.setString(3,this.park1);


            ps.executeUpdate();
            ps.close();
            conn.close();

        }catch (SQLException ex){
            System.out.println("添加园区失败！");

        }
    }
}

package dao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BreederChangeAnimal {
    String animalname;
    String name;
    String park;
    String variety;

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/mydata?serverTimezone=UTC&characterEncoding=utf-8";
    private String user = "root";
    private String sqlpassword = "123456";

    public void setAnimalName(String animalname){this.animalname=animalname;}

    public void setName(String name) {
        this.name = name;
    }

    public void setPark(String park) {
        this.park= park;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public boolean JudgeAddAnimal() throws SQLException, ClassNotFoundException {

        if (this.name.equals("")) {
            JOptionPane.showMessageDialog(null, "动物名不能为空！", "动物名", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (this.variety.equals("")) {
            JOptionPane.showMessageDialog(null, "动物种类不能为空！", "种类", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //符合规则，弹出注册成功的窗口，并将账号添加数据库
        JOptionPane.showMessageDialog(null, "修改动物成功");
        changeAnimal();
        return true;
    }

    //将动物添加到数据库
    void changeAnimal() throws ClassNotFoundException, SQLException {
        String sql = "update animal set animal=?,variety=? where animalname=?";
        Class.forName(driver);
        try {
            Connection conn = DriverManager.getConnection(url, user, sqlpassword);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, this.name);
            ps.setString(2, this.variety);
            ps.setString(3,this.animalname);
            ps.executeUpdate();
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("添加动物失败！");
        }


    }
}

package dao;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrincipalAddPark {
    String parkname;
    String parkID;
    String parkbreeder;
    String introduction;

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/mydata?serverTimezone=UTC&characterEncoding=utf-8";
    private String user = "root";
    private String sqlpassword = "123456";

    public void setParkname(String name){this.parkname=name;}
    public void setParkID(String ID){this.parkID=ID;}
    public void setParkbreeder(String breeder){this.parkbreeder=breeder;}
    public void setIntroduction(String introduction){this.introduction=introduction;}

    public boolean JudgeAdd() throws SQLException, ClassNotFoundException{

        if (this.parkname.equals("")){
            JOptionPane.showMessageDialog(null, "园区名不能为空！", "园区名", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(this.parkID.equals("")) {
            JOptionPane.showMessageDialog(null, "园区账号不能为空！", "账号为空", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(this.parkbreeder.equals("")) {
            JOptionPane.showMessageDialog(null, "饲养员账号不能为空！", "账号为空", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        JOptionPane.showMessageDialog(null, "添加成功");
        addpark();
        return true;
    }



    void addpark() throws ClassNotFoundException ,SQLException{
        String sql="insert into park (parkID,introduction,breedername,name) values (?,?,?,?)";
        Class.forName(driver);
        try{
            Connection conn = DriverManager.getConnection(url, user, sqlpassword);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,this.parkID);
            ps.setString(2,this.introduction);
            ps.setString(3,this.parkbreeder);
            ps.setString(4,this.parkname);

            ps.executeUpdate();
            ps.close();
            conn.close();

        }catch (SQLException ex){
            System.out.println("添加园区失败！");

        }
    }

}

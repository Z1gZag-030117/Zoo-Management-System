package servlce;

import javax.swing.*;
import java.sql.*;

public class getMYSQL {

        public int getNUM(){
            int num=0;
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Shanghai","root","123456");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from animal ");
                while (resultSet.next()) {
                    num++;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return num;
        }

}

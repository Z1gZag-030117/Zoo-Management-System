package view.principal;

import view.breeder.BreederManage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ParkDelete extends JPanel {

    ParkDelete(){
        init();
    }


    void init(){
        JFrame parkdelete=new JFrame("园区删除");
        parkdelete.setLayout(null);

        JLabel parkID=new JLabel("需要删除的园区ID:");
        parkID.setBounds(150,250,150,25);
        parkdelete.add(parkID);

//        JTextField parkIDText=new JTextField();
//        parkIDText.setBounds(320,250,200,25);
//        parkdelete.add(parkIDText);


        JComboBox<String> comboBox=new JComboBox<>();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Shanghai","root","123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from park ");
            while(resultSet.next()){
                String pname = resultSet.getString("name");
                comboBox.addItem(pname);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        comboBox.setBounds(320,250,150,25);
        parkdelete.add(comboBox);



        JButton button=new JButton("确定删除该园区");
        button.setBounds(300,300,150,25);
        parkdelete.add(button);

        JButton buttonout=new JButton("返回");
        buttonout.setBounds(300,500,100,25);
        parkdelete.add(buttonout);

        parkdelete.setBounds(400, 100, 800, 640);
        parkdelete.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parkdelete.setVisible(true);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String parkID = parkIDText.getText();
                String parkname= (String) comboBox.getSelectedItem();

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Shanghai","root","123456");
                    String sql = "delete from park where name=?";
                    PreparedStatement preparedStatement= connection.prepareStatement(sql);
                    preparedStatement.setString(1,parkname);
                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "删除园区成功", "删除园区成功", JOptionPane.NO_OPTION);
                    ParkManage parkManage=new ParkManage();
                    parkdelete.setVisible(false);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });





        buttonout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parkdelete.setVisible(false);
                PrincipalDask principalDask=new PrincipalDask();
            }
        });


    }



}

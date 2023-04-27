package view.principal;

import dao.PrincipalAddPark;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ParkAdd extends JPanel {


    ParkAdd() {
        init();
    }

    void init() {


        JFrame frame = new JFrame("添加园区");
        frame.setLayout(null);

        JLabel parkName = new JLabel("园区名:");
        parkName.setBounds(250, 150, 100, 25);
        frame.add(parkName);

        JLabel parkID = new JLabel("园区ID:");
        parkID.setBounds(250, 200, 100, 25);
        frame.add(parkID);

        JLabel parkIntroduction = new JLabel("园区简介：");
        parkIntroduction.setBounds(250, 300, 100, 25);
        frame.add(parkIntroduction);

        JLabel parkBreeder = new JLabel("园区饲养员账号:");
        parkBreeder.setBounds(200, 250, 100, 25);
        frame.add(parkBreeder);


        JTextField parkname = new JTextField();
        parkname.setBounds(320, 150, 150, 25);
        frame.add(parkname);

        JTextField parkid = new JTextField();
        parkid.setBounds(320, 200, 150, 25);
        frame.add(parkid);

//        JTextField parkbreeder = new JTextField();
//        parkbreeder.setBounds(320, 250, 150, 25);
//        frame.add(parkbreeder);

        JComboBox parkbreeder=new JComboBox();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Shanghai","root","123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            //String identity=resultSet.getString(identity);
            while(resultSet.next() ){
                if(resultSet.getString("identity").equals("1")){
                    String name=resultSet.getString("username");
                    parkbreeder.addItem(name);
                }

            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        parkbreeder.setBounds(300,250,200,25);
        frame.add(parkbreeder);


        JTextArea parkintroduction = new JTextArea();
        parkintroduction.setBounds(320, 300, 150, 75);
        frame.add(parkintroduction);


        JButton buttonAdd = new JButton("添加");
        buttonAdd.setBounds(320, 380, 70, 25);
        frame.add(buttonAdd);

        JButton buttonout=new JButton("返回");
        buttonout.setBounds(300,500,100,25);
        frame.add(buttonout);


        frame.setBounds(400, 100, 800, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = parkname.getText();
                String ID = parkid.getText();
                String breedername = (String) parkbreeder.getSelectedItem();
                String introduction = parkintroduction.getText();

                PrincipalAddPark add = new PrincipalAddPark();
                add.setParkID(ID);
                add.setIntroduction(introduction);
                add.setParkbreeder(breedername);
                add.setParkname(name);

                PrincipalAddPark a = new PrincipalAddPark();
                a.setParkID(ID);
                a.setIntroduction(introduction);
                a.setParkbreeder(breedername);
                a.setParkname(name);

                try {
                    if (add.JudgeAdd()) {

                        frame.setVisible(false);
                        ParkManage parkManage = new ParkManage();


                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    //e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }


            }
        });

        buttonout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ParkManage parkManage=new ParkManage();
            }
        });


    }
}

package test;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;

public class ItemTest extends JFrame implements ItemListener {

    JComboBox comboBoxPark=new JComboBox();   //一级选项，存放省份
    JComboBox comboBoxAnimal=new JComboBox();     //二级选项，存放城市名

        public  ItemTest(){

            comboBoxPark.setBounds(320,150,150,25);
            this.add(comboBoxPark);

            comboBoxAnimal.setBounds(320,200,150,25);
            this.add(comboBoxAnimal);

            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata", "root", "123456");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from park ");
                while(resultSet.next()){
                    String pname = resultSet.getString("name");
                    comboBoxPark.addItem(pname);
                }
            }catch (
                    SQLException throwables) {
                throwables.printStackTrace();
            }


        }

        public void itemStateChanged(ItemEvent e){
            JComboBox com =(JComboBox)e.getSource();
            String str = com.getSelectedItem().toString();
            String[] park = getAnimalByPark(str);
            comboBoxAnimal.removeAllItems();
            for(int i=0;i<park.length;i++)
            comboBoxAnimal.addItem(park[i]);

        }



        //获取与省份匹配的城市
        private String[] getAnimalByPark(String str) {
            String animal = new String();
            String[] animalname=new String[20];
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata", "root", "123456");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from animal ");
                for (int i=0;resultSet.next();i++){
                    if(resultSet.getString("park").equals(str)){
                        String name=resultSet.getString("animalname");
                        animalname[i]=name;
                     }
                }

            }catch (SQLException throwables) {
                throwables.printStackTrace();
            }
                return animalname;
        }
    }










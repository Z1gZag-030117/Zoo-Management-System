package view.breeder;

import dao.BreederChangeAnimal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/*
修改该饲养员管理的动物信息
 */
public class ChangeAnimal extends JFrame {

    public ChangeAnimal(){changeanimal();}

    String park;
    String name;

    public void changeanimal(){
        JFrame frame=new JFrame("修改动物信息界面");
        frame.setLayout(null);

        JLabel Park=new JLabel("选择需要修改的动物:");
        Park.setBounds(130,150,160,25);
        frame.add(Park);

//        JLabel animal=new JLabel("选择需要修改的动物:");
//        animal.setBounds(140,200,160,25);
//        frame.add(animal);
//
//        JLabel nameNeed=new JLabel("选择需要修改的动物名称:");
//        nameNeed.setBounds(230,200,150,25);
//        frame.add(nameNeed);


        JComboBox<String> comboBoxPark=new JComboBox<>();
        SelectFromPark(comboBoxPark);
        comboBoxPark.setBounds(320,150,150,25);
        frame.add(comboBoxPark);
        park= (String) comboBoxPark.getSelectedItem();



//        JComboBox<String> comboBoxAnimal=new JComboBox<>();
//        try {
//            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Shanghai","root","123456");
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from animal ");
//            System.out.println(park);
//            while(resultSet.next()){
//                if(resultSet.getString("park")==park){
//                    String animalname=resultSet.getString("animalname");
//                    System.out.println(animalname);
//                    comboBoxAnimal.addItem(animalname);
//                }
//            }
//        }catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        comboBoxAnimal.setBounds(320,200,150,25);
//        frame.add(comboBoxAnimal);



//        JComboBox<String> comboBoxParkChange=new JComboBox<>();
//        SelectFromPark(comboBoxParkChange);
//        comboBoxParkChange.setBounds(320,150,150,25);
//        frame.add(comboBoxParkChange);



        JLabel namechange=new JLabel("修改动物名:");
        namechange.setBounds(210,250,100,25);
        frame.add(namechange);

        JTextField Name=new JTextField();
        Name.setBounds(300,250,100,25);
        frame.add(Name);

        JLabel varietychange =new JLabel("修改动物种类:");
        varietychange.setBounds(200,300,100,25);
        frame.add(varietychange);

        JTextField Vaiety=new JTextField();
        Vaiety.setBounds(300,300,100,25);
        frame.add(Vaiety);

        JButton button=new JButton("确认修改");
        button.setBounds(200,500,100,25);
        frame.add(button);

        JButton out=new JButton("返回");
        out.setBounds(370,500,70,25);
        frame.add(out);

        frame.setBounds(400, 100, 800, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BreederDask breederDask=new BreederDask();
            }
        });


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String animalname= (String) comboBoxPark.getSelectedItem();
                String name=Name.getText();
                String variety=Vaiety.getText();
                BreederChangeAnimal change=new BreederChangeAnimal();
                change.setAnimalName(animalname);
                change.setName(name);
                change.setVariety(variety);
                try {
                    if (change.JudgeAddAnimal()) {
                        frame.setVisible(false);
                        BreederDask breederDask=new BreederDask();
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


    }

    private void SelectFromPark(JComboBox<String> comboBoxPark) {
        try {
            name = (String) comboBoxPark.getSelectedItem();
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Shanghai","root","123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from animal ");
            while (resultSet.next()) {
                String pname = resultSet.getString("animalname");
                comboBoxPark.addItem(pname);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ChangeAnimal changeAnimal=new ChangeAnimal();
    }
}

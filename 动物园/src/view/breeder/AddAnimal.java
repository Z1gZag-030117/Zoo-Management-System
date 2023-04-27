package view.breeder;

import dao.PrincipalAddBreeder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/*
添加该饲养员管理的动物
 */
public class AddAnimal extends JFrame {

    public AddAnimal() {
        init();
    }

    void init() {


        JFrame frame = new JFrame("添加动物");
        frame.setLayout(null);

        JLabel animalname = new JLabel("动物名:");
        animalname.setBounds(250, 150, 100, 25);
        frame.add(animalname);

        JLabel parkID = new JLabel("所属园区编号:");
        parkID.setBounds(230, 200, 100, 25);
        frame.add(parkID);

        JLabel animalvariety = new JLabel("种类:");
        animalvariety.setBounds(250, 250, 100, 25);
        frame.add(animalvariety);

        JTextField Name=new JTextField();
        Name.setBounds(320,150,150,25);
        frame.add(Name);


        JComboBox<String> comboBox=new JComboBox<>();
        SelectFromPark(comboBox);
        comboBox.setBounds(320,200,150,25);
        frame.add(comboBox);


        JTextField Variety = new JTextField();
        Variety.setBounds(320, 250, 150, 25);
        frame.add(Variety);


        JButton buttonAdd = new JButton("添加");
        buttonAdd.setBounds(350, 400, 70, 25);
        frame.add(buttonAdd);

        JButton buttonout=new JButton("返回");
        buttonout.setBounds(350,450,70,24);
        frame.add(buttonout);


        frame.setBounds(400, 100, 800, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);



        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = Name.getText();
                String park = (String) comboBox.getSelectedItem();
                String variety =Variety.getText();

                //System.out.println(name+park+variety);

                PrincipalAddBreeder add=new PrincipalAddBreeder();
                add.setName(name);
                add.setVariety(variety);
                add.setParkID(park);

                try {
                    if (add.JudgeAddAnimal()) {

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

        buttonout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BreederDask breederDask=new BreederDask();
            }
        });


    }

    private void SelectFromPark(JComboBox<String> comboBox) {
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
    }
}

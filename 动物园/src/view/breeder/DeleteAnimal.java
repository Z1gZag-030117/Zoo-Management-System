package view.breeder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/*
删除该饲养员管理的动物
 */
public class DeleteAnimal extends JFrame {

    public DeleteAnimal(){deleteanimal();}

    public void deleteanimal(){


        JFrame frame=new JFrame("修改动物信息界面");
        frame.setLayout(null);

        JLabel nameNeed=new JLabel("选择需要删除的动物名称:");
        nameNeed.setBounds(160,200,150,25);
        frame.add(nameNeed);

/*
    下拉框无法做到联动
 */

        JComboBox<String> comboBoxAnimal=new JComboBox<>();
        SelectFromAnimal(comboBoxAnimal);
        comboBoxAnimal.setBounds(320,200,150,25);
        frame.add(comboBoxAnimal);

//        JButton button1=new JButton("查询");
//        button1.setBounds(600,150,70,25);
//        frame.add(button1);

        JButton buttonout=new JButton("返回");
        buttonout.setBounds(320,300,70,25);
        frame.add(buttonout);

        JButton button=new JButton("确认删除");
        button.setBounds(320,400,110,25);
        frame.add(button);



        frame.setBounds(400, 100, 800, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);



        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                try {
                    //String name= (String) comboBoxAnimal.getSelectedItem();
                    String name= (String) comboBoxAnimal.getSelectedItem();
                    System.out.println(name);
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Shanghai","root","123456");
                    String sql = "delete from animal where animalname=?";
                    PreparedStatement preparedStatement=connection.prepareStatement(sql);
                    preparedStatement.setString(1,name);
                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "删除动物成功", "删除动物成功", JOptionPane.NO_OPTION);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                frame.setVisible(false);
                BreederDask breederDask=new BreederDask();

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

    private void SelectFromAnimal(JComboBox<String> comboBoxAnimal) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Shanghai","root","123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from animal ");
            while(resultSet.next()){
                //if(resultSet.getString("park").equals(park)){
                    String name=resultSet.getString("animalname");
                    comboBoxAnimal.addItem(name);
                //}
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

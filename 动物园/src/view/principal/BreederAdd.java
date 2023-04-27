package view.principal;


import view.breeder.BreederManage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BreederAdd extends JFrame {
    //String accountNumber;
    public BreederAdd() {
        BreederAdd();
    }

    private void BreederAdd() {

        JFrame frame=new JFrame("添加饲养员");
        frame.setLayout(null);



        JLabel label=new JLabel("选择想要添加的饲养员:");
        label.setBounds(150,300,300,25);
        frame.add(label);

//        JTextField field=new JTextField();
//        field.setBounds(300,300,200,25);
//        frame.add(field);


        JComboBox<String> comboBox=new JComboBox<>();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Shanghai","root","123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            //String identity=resultSet.getString(identity);
            while(resultSet.next() ){
                String identity=resultSet.getString("identity");
                if (identity.equals("0"))
                {
                    //String account = resultSet.getString("account");
                    String name=resultSet.getString("username");
                    comboBox.addItem(name);
                }

            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        comboBox.setBounds(300,300,200,25);
        frame.add(comboBox);



        JButton button=new JButton("确定添加该饲养员");
        button.setBounds(300,430,150,25);
        frame.add(button);

        JButton buttonout=new JButton("返回");
        buttonout.setBounds(300,500,100,25);
        frame.add(buttonout);


        frame.setBounds(400, 100, 800, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //String name = (String) comboBox.getSelectedItem();
                //System.out.println(name);
//                String account=field.getText();

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                try {
                    String name= (String) comboBox.getSelectedItem();
//                    System.out.println(name);
                   // String account= (String) comboBox.getSelectedItem();
                   // System.out.println(account+name);
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Shanghai","root","123456");
                    String sql = "update user set identity ='1' where username=?";
                    PreparedStatement preparedStatement=connection.prepareStatement(sql);
                    preparedStatement.setString(1,name);
                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "添加饲养员成功", "添加饲养员成功", JOptionPane.NO_OPTION);
                    frame.setVisible(false);
                    BreederManage breederManage=new BreederManage();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }





            }
        });

        buttonout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BreederManage breederManage=new BreederManage();
            }
        });





    }
}

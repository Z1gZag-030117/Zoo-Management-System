package view.principal;

import view.breeder.BreederManage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BreederDelete extends JFrame {

    public BreederDelete() {
        BreederDelete();
    }

    private void BreederDelete() {
        JFrame frame=new JFrame("删除饲养员");
        frame.setLayout(null);

        JLabel label=new JLabel("输入删除饲养员的账号:");
        label.setBounds(150,300,300,25);
        frame.add(label);



        JComboBox<String> comboBox=new JComboBox<>();
        SelectFromUser(comboBox);
        comboBox.setBounds(320,300,150,25);
        frame.add(comboBox);



        JButton button=new JButton("确定删除该饲养员");
        button.setBounds(300,430,100,25);
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

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                try {
                    String name= (String) comboBox.getSelectedItem();
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Shanghai","root","123456");
                    String sql = "update user set identity ='0' where = username=?";
                    PreparedStatement preparedStatement=connection.prepareStatement(sql);
                    preparedStatement.setString(1,name);
                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "删除饲养员成功", "删除饲养员成功", JOptionPane.NO_OPTION);
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

    private void SelectFromUser(JComboBox<String> comboBox) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Shanghai","root","123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user ");

            while(resultSet.next() ){
                String identity=resultSet.getString("identity");
                if(identity.equals("1")){
                    String name = resultSet.getString("username");
                    comboBox.addItem(name);
                }
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BreederDelete breederDelete=new BreederDelete();
    }

}

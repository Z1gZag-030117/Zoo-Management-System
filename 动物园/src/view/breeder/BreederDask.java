package view.breeder;

import view.login.Login_Register;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BreederDask extends JFrame {

    public BreederDask(){breederdask();}

    public  void breederdask(){

        JFrame breederdask = new JFrame("动物饲养员界面");
        breederdask.setLayout(null);

        JButton animaladd =new JButton("添加动物");
        animaladd.setBounds(300,250,110,25);
        breederdask.add(animaladd);

        JButton animaldelete =new JButton("删除动物");
        animaldelete.setBounds(300,300,110,25);
        breederdask.add(animaldelete);

        JButton animaldesc =new JButton("查询动物信息");
        animaldesc.setBounds(300,350,110,25);
        breederdask.add(animaldesc);

        JButton animalchange =new JButton("修改动物信息");
        animalchange.setBounds(300,400,110,25);
        breederdask.add(animalchange);

        JButton buttonout =new JButton("退出登录");
        buttonout.setBounds(300,450,110,25);
       breederdask.add(buttonout);


        breederdask.setBounds(400, 100, 800, 640);
        breederdask.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        breederdask.setVisible(true);

        //退出登录
        buttonout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                breederdask.setVisible(false);
                Login_Register login_register = new Login_Register();
            }
        });

        animaladd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                breederdask.setVisible(false);
                AddAnimal addAnimal=new AddAnimal();
            }
        });

        animalchange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                breederdask.setVisible(false);
                ChangeAnimal changeAnimal=new ChangeAnimal();
            }
        });

        animaldelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                breederdask.setVisible(false);
                DeleteAnimal deleteAnimal=new DeleteAnimal();
            }
        });

        animaldesc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                breederdask.setVisible(false);
                QueryAnimal queryAnimla=new QueryAnimal();
            }
        });

    }
}

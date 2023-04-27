package view.principal;

import view.login.Login_Register;
import view.breeder.BreederManage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalDask extends JFrame {

    public PrincipalDask() {
        principaldask();
    }

    public void principaldask() {

        JFrame principaldask = new JFrame("院长");
        principaldask.setLayout(null);



        JButton breedermanage = new JButton("饲养员管理");
        breedermanage.setBounds(300, 250, 110, 25);
        principaldask.add(breedermanage);



        JButton parkmanage = new JButton("园区管理");
        parkmanage.setBounds(300, 300, 110, 25);
        principaldask.add(parkmanage);



        JButton buttonout =new JButton("退出登录");
        buttonout.setBounds(300,350,110,25);
        principaldask.add(buttonout);


        principaldask.setBounds(400, 100, 800, 640);
        principaldask.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        principaldask.setVisible(true);

        //退出登录


        breedermanage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                principaldask.setVisible(false);
                BreederManage breederManage=new BreederManage();
            }
        });


        parkmanage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                principaldask.setVisible(false);
                ParkManage parkManage=new ParkManage();
            }
        });

        buttonout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                principaldask.setVisible(false);
                Login_Register login_register=new Login_Register();
            }
        });
    }

    public static void main(String[] args) {
        PrincipalDask principalDask=new PrincipalDask();
    }
}


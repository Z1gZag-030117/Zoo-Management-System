package view.breeder;
/*
院长动物饲养员管理
 */

import view.principal.BreederAdd;
import view.principal.BreederDelete;
import view.principal.PrincipalDask;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BreederManage extends JFrame {

    public BreederManage() {
        init();
    }

    public void init (){
        JFrame breedermanage=new JFrame("动物饲养员管理");
        breedermanage.setLayout(null);

        JButton BreederAdd = new JButton("添加动物管理员");
        BreederAdd.setBounds(270, 200, 150, 25);
        breedermanage.add(BreederAdd);

        JButton Breederdelete = new JButton("删除动物管理员");
        Breederdelete.setBounds(270, 250, 150, 25);
        breedermanage.add(Breederdelete);

        JButton buttonout=new JButton("返回");
        buttonout.setBounds(300,500,100,25);
        breedermanage.add(buttonout);

        breedermanage.setBounds(400, 100, 800, 640);
        breedermanage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        breedermanage.setVisible(true);

        BreederAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                breedermanage.setVisible(false);
                view.principal.BreederAdd breederAdd=new BreederAdd();
            }
        });

        Breederdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                breedermanage.setVisible(false);
                BreederDelete breederDelete=new BreederDelete();
            }
        });

        buttonout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                breedermanage.setVisible(false);
                PrincipalDask principalDask=new PrincipalDask();
            }
        });

    }

}

package view.principal;
/*
院长园区管理界面
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParkManage {

    public ParkManage() {
        init();
    }

    public void init() {
        JFrame parkmanage = new JFrame("动物园区管理");
        parkmanage.setLayout(null);

        JButton parkAdd = new JButton("添加园区");
        parkAdd.setBounds(300, 200, 150, 25);
        parkmanage.add(parkAdd);

        JButton parkdelete = new JButton("删除园区");
        parkdelete.setBounds(300, 250, 150, 25);
        parkmanage.add(parkdelete);

        JButton parkout=new JButton("返回");
        parkout.setBounds(300,300,150,25);
        parkmanage.add(parkout);

        parkmanage.setBounds(400, 100, 800, 640);
        parkmanage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parkmanage.setVisible(true);

        parkdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parkmanage.setVisible(false);
                ParkDelete parkDelete=new ParkDelete();
            }
        });

        parkAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parkmanage.setVisible(false);
                ParkAdd parkAdd=new ParkAdd();
            }
        });

        parkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parkmanage.setVisible(false);
                PrincipalDask principalDask=new PrincipalDask();
            }
        });




    }
}

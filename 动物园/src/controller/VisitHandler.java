package controller;

import view.user.UserDask;
import view.user.UserVisit;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisitHandler implements ActionListener {

    private UserVisit userVisit;
    public VisitHandler(UserVisit userVisit){this.userVisit=userVisit;}

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("上一页".equals(text)){
            userVisit.setPageNow(userVisit.getPageNow() - 1);
            userVisit.reloadTable();
        }else if ("下一页".equals(text)){
            userVisit.setPageNow(userVisit.getPageNow() + 1);
            userVisit.reloadTable();
        }else if("返回".equals(text)){
            userVisit.setVisible(false);
            UserDask userDask=new UserDask(userVisit.getAccount());
        }
    }
}

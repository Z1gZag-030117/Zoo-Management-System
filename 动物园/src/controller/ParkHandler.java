package controller;

import servlce.Comment;
import servlce.CommentImpl;
import view.user.CommentPark;
import view.user.UserDask;
import view.user.UserVisit;
import view.user.UserVisitPark;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParkHandler implements ActionListener {

    private UserVisitPark userVisitPark;
    public ParkHandler(UserVisitPark userVisitPark){this.userVisitPark=userVisitPark;}

    private CommentPark commentPark;
    public ParkHandler(CommentPark commentPark){this.commentPark=commentPark;}

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("返回".equals(text)){
            userVisitPark.setVisible(false);
            UserVisit userVisit=new UserVisit(userVisitPark.getAccount());
        }else if ("评论".equals(text)){
            CommentPark commentPark=new CommentPark(userVisitPark.getAccount());
        }
    }
}

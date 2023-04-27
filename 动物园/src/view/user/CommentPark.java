package view.user;

import controller.ParkHandler;
import dao.UserAddComment;
import view.principal.ParkAdd;
import view.principal.ParkManage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentPark extends JFrame {



    JButton post=new JButton("发布评论");
    JButton out=new JButton("返回");
    JPanel southPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
    JTextArea textArea=new JTextArea(20,50);
    String useraccount;
    //String park1;

    ParkHandler parkHandler;

    public CommentPark(String account)throws HeadlessException{
        useraccount=account;

        setTitle("评论");

        setBounds(600,300,400,350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container container=getContentPane();
        textArea.setText("写下你的评论...");
        JScrollPane jScrollPane=new JScrollPane(textArea);
        container.add(jScrollPane);
        latoutSouth(container);
        setVisible(true);

        parkHandler=new ParkHandler(this);

    }



    private void latoutSouth(Container contentPane) {

        southPanel.add(out);
        southPanel.add(post);


        out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.setVisible(false);
                UserVisit userVisit=new UserVisit(useraccount);
            }
        });

        post.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text=textArea.getText();
                String account=useraccount;
                //String park=park1;

                UserAddComment userAddComment=new UserAddComment();
                userAddComment.setText(text);
                userAddComment.setAccount(account);
                //userAddComment.setPark1(park);

                try {
                    if (userAddComment.JudgeAdd()) {
                        setVisible(false);
                       UserVisit userVisit=new UserVisit(account);


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

        contentPane.add(southPanel,BorderLayout.SOUTH);
    }

    public String getAccount(){
        return useraccount;
    }




}

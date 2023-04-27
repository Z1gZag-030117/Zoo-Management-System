package view.user;

import controller.ParkHandler;
import controller.TableDTOpark;
import controller.TableDTOvisit;
import controller.VisitHandler;
import po.VisitRequest;
import servlce.*;
import util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UserVisitPark extends JFrame {
    String park1;
    String useraccount;
    JPanel northPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel labelPark=new JLabel();
    JPanel southPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton outBtn=new JButton("返回");
    JLabel label;
    JButton comment=new JButton("评论");



    private int pageNow=1;
    private int pageSize=5;

    ParkHandler parkHandler;

    //UserVisitTable userVisitTable=new UserVisitTable();
    UserVisitParkTable userVisitParkTable=new UserVisitParkTable();

    public  UserVisitPark(String park,String account){
        park1=park;
        useraccount=account;

        label=new JLabel(park);
        label.setFont(new Font("华文行楷", Font.BOLD, 30));
        //JPanel northPanel=new JPanel();


        JFrame frame=new JFrame();
        frame.setLayout(null);

        JLabel parkname=new JLabel(park);
        parkname.setFont(new Font("华文行楷",Font.PLAIN,40));
        parkname.setBounds(280,70,500,50);
        frame.add(parkname);

        Container contentPane = getContentPane();
        Rectangle bounds = DimensionUtil.getBounds();
        pageSize = Math.floorDiv(bounds.height,35);
        parkHandler=new ParkHandler(this);


        layoutNorth(contentPane);

        latoutSouth(contentPane);

        layoutCenter(contentPane);

        setBounds(400, 100, 800, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public UserVisitPark() {
        String park;
        park=park1;
    }


    private void latoutSouth(Container contentPane) {

        southPanel.add(outBtn);
        southPanel.add(comment);
        outBtn.addActionListener(parkHandler);
        comment.addActionListener(parkHandler);
        contentPane.add(southPanel,BorderLayout.SOUTH);
    }


    private void layoutCenter(Container contentPane) {
        //getMYSQL getMYSQL=new getMYSQL();
        TableDTOpark dto = getTableDTO();
        UserVisitParkModel userVisitParkModel = UserVisitParkModel.assembleModel(dto.getData());
        // 把jtable和model关联
        userVisitParkTable.setModel(userVisitParkModel);
        //userVisitParkTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(userVisitParkTable);
        contentPane.add(jScrollPane,BorderLayout.CENTER);
    }

    /*
    设置上一页下一页是否可见
     */

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageNow() {
        return pageNow;
    }

    private TableDTOpark getTableDTO() {
//        VisitService visitService = new VisitServiceImpl();
        Comment comment=new CommentImpl();
        VisitRequest request = new VisitRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        TableDTOpark tableDTOpark = comment.retrieveAnimal(request);
        return tableDTOpark;
    }


    public void reloadTable() {
        TableDTOpark dto = getTableDTO();
        getMYSQL getMYSQL= new getMYSQL();
        UserVisitTableModel.updateModel(dto.getData());
        //userVisitParkTable.renderRule();
        //showPreNext(getMYSQL.getNUM());
    }

//    public static void main(String[] args) {
//        new UserVisit();
//    }

    private void layoutNorth(Container contentPane) {
        // 增加事件监听
        northPanel.add(label);
        contentPane.add(northPanel,BorderLayout.NORTH);
    }

    public String getAccount(){
        return useraccount;
    }




}

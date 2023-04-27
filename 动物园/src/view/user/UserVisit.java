package view.user;

import controller.TableDTOvisit;
import controller.VisitHandler;
import po.VisitRequest;
import servlce.VisitService;
import servlce.VisitServiceImpl;
import servlce.getMYSQL;
import util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UserVisit extends JFrame {
    String name;
    JPanel northPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel labelPark=new JLabel();
    JPanel southPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton preBtn=new JButton("上一页");
    JButton nextBtn=new JButton("下一页");
    JButton outBtn=new JButton("返回");
    JButton niceBtn=new JButton("点赞:");
    JButton enter=new JButton("进入园区:");
    JComboBox animalname=new JComboBox();
    JComboBox enterPark =new JComboBox();
    String useraccount;
    private int pageNow=1;
    private int pageSize=5;

    VisitHandler visitHandler;

    UserVisitTable userVisitTable=new UserVisitTable();

    public UserVisit(String account){

        super("用户参观界面");

        useraccount=account;

        Container contentPane = getContentPane();
        Rectangle bounds = DimensionUtil.getBounds();
        pageSize = Math.floorDiv(bounds.height,35);
        visitHandler=new VisitHandler(this);
        SelectFromAnimal(animalname);
        SelectFromPark(enterPark);


        niceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Shanghai","root","123456");
                    String sql = "update animal set nice=nice+1 where animalname=?";
                    PreparedStatement preparedStatement=connection.prepareStatement(sql);
                    String name=(String)animalname.getSelectedItem();
                    preparedStatement.setString(1,name);
                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"点赞成功","点赞成功",JOptionPane.OK_CANCEL_OPTION);
                    setVisible(false);
                    Container container=new UserVisit(account);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String parkname= (String) enterPark.getSelectedItem();
                setVisible(false);
                UserVisitPark userVisitPark=new UserVisitPark(parkname,useraccount);
            }
        });


        latoutSouth(contentPane);
        northPanel.add(labelPark);
        contentPane.add(northPanel,BorderLayout.NORTH);

        layoutCenter(contentPane);

        setBounds(400, 100, 800, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void latoutSouth(Container contentPane) {
        southPanel.add(nextBtn);
        southPanel.add(preBtn);
        southPanel.add(outBtn);
        southPanel.add(niceBtn);
        southPanel.add(animalname);
        southPanel.add(enter);
        southPanel.add(enterPark);
        preBtn.addActionListener(visitHandler);
        nextBtn.addActionListener(visitHandler);
        outBtn.addActionListener(visitHandler);
        niceBtn.addActionListener(visitHandler);
        contentPane.add(southPanel,BorderLayout.SOUTH);
    }


    private void layoutCenter(Container contentPane) {
        getMYSQL getMYSQL=new getMYSQL();
        TableDTOvisit dto = getTableDTO();
        UserVisitTableModel userVisitTableModel = UserVisitTableModel.assembleModel(dto.getData());
        // 把jtable和model关联
        userVisitTable.setModel(userVisitTableModel);
        userVisitTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(userVisitTable);
        contentPane.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(getMYSQL.getNUM());

    }

    /*
    设置上一页下一页是否可见
     */
    private void showPreNext(int totalCount) {
        if (pageNow == 1) {
            preBtn.setVisible(false);
        } else {
            preBtn.setVisible(true);
        }
        //getMYSQL getMYSQL=new getMYSQL();
        int pageCount =0;//总共有多少页
        if (totalCount % pageSize == 0) {
            pageCount = totalCount / pageSize;
        } else {
            pageCount = totalCount / pageSize + 1;
        }
        if (pageNow == pageCount) {
            nextBtn.setVisible(false);
        } else {
            nextBtn.setVisible(true);
        }
    }
    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public String getAccount(){return useraccount;}

    public int getPageNow() {
        return pageNow;
    }

    private TableDTOvisit getTableDTO() {
        VisitService visitService = new VisitServiceImpl();
        VisitRequest request = new VisitRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        TableDTOvisit tableDTOvisit = visitService.retrieveStudents(request);
        return tableDTOvisit;
    }


    public void reloadTable() {
        TableDTOvisit dto = getTableDTO();
        getMYSQL getMYSQL= new getMYSQL();
        UserVisitTableModel.updateModel(dto.getData());
        userVisitTable.renderRule();
        //showPreNext(getMYSQL.getNUM());
    }
//    public static void main(String[] args) {
//        new UserVisit();
//    }



    private void SelectFromAnimal(JComboBox<String> comboBoxPark) {
        try {
            name = (String) comboBoxPark.getSelectedItem();
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Shanghai","root","123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from animal ");
            while (resultSet.next()) {
                String pname = resultSet.getString("animalname");
                comboBoxPark.addItem(pname);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void SelectFromPark(JComboBox<String> comboBoxPark) {
        try {
            name = (String) comboBoxPark.getSelectedItem();
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Shanghai","root","123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from park ");
            while (resultSet.next()) {
                String pname = resultSet.getString("name");
                comboBoxPark.addItem(pname);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

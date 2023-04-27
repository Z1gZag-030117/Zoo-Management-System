package view.breeder;

import controller.QueryAnimalHeader;
import controller.TableDTO;
import po.AnimalRequest;
import servlce.AnimalSearch;
import servlce.AnimalSearchImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.Font;


/*
查询该饲养员管理的园区的动物信息
 */
public  class QueryAnimal extends JFrame {


    JPanel northPanel=new JPanel();
    JButton addBtn=new JButton("添加");
    JButton delBtn=new JButton("删除");
    JTextField searchText=new JTextField(15);
    JButton searchBtn=new JButton("查询");


    JPanel southPanel=new JPanel();
    JButton preBtn=new JButton("上一页");
    JButton nextBtn=new JButton("下一页");
    JButton outBtn=new JButton("返回");

    QueryAnimalTable queryAnimalTable=new QueryAnimalTable();



    private int pageNow=1;//当前是第几页
    private int pageSize=5;//一页显示几条纪录

    QueryAnimalHeader queryAnimalHeader;


    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageNow() {
        return pageNow;
    }

//    public void relodaTable(){
//        AnimalSearch animalSearch=new AnimalSearchImpl();
//        AnimalRequest request=new AnimalRequest();
//        request.getPageNow(pageNow);
//    }

    public void reloadTable(){
        AnimalSearch animalSearch=new AnimalSearchImpl();
        AnimalRequest request=new AnimalRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchText.getText().trim());
        TableDTO tableDTO = animalSearch.retrieveAnimal(request);
        Vector<Vector<Object>> data = tableDTO.getData();
        AnimalTableModel.updateModel(data);
        queryAnimalTable.renderRule();

    }

    public QueryAnimal() throws HeadlessException {

        super("动物查询界面");
        //Vector<Vector<Object>> data=new Vector<>();
        queryAnimalHeader=new QueryAnimalHeader(this);
        addBtn.addActionListener(queryAnimalHeader);
        delBtn.addActionListener(queryAnimalHeader);
        searchBtn.addActionListener(queryAnimalHeader);
        northPanel.add(addBtn);
        northPanel.add(delBtn);
        northPanel.add(searchText);
        northPanel.add(searchBtn);
        add(northPanel,BorderLayout.NORTH);



        preBtn.addActionListener(queryAnimalHeader);
        nextBtn.addActionListener(queryAnimalHeader);
        outBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                BreederDask breederDask=new BreederDask();
            }
        });
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        southPanel.add(outBtn);
        add(southPanel,BorderLayout.SOUTH);

        AnimalSearch animalSearch=new AnimalSearchImpl();
        AnimalRequest request=new AnimalRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchText.getText().trim());
        TableDTO tableDTO = animalSearch.retrieveAnimal(request);
        Vector<Vector<Object>> data = tableDTO.getData();


        //tablemodel和table关联后，只需要改变model就可以把变化反映到table中
        AnimalTableModel animalTableModel=AnimalTableModel.assembleMobel(data);


        //model和table关联
        JTable table=new JTable(animalTableModel);
        JTableHeader tableHeader=table.getTableHeader();
        tableHeader.setFont(new Font(null,Font.BOLD,17));
        tableHeader.setForeground(Color.red);
        table.setGridColor(Color.BLACK);
        table.setRowHeight(30);


        //设置表格列的渲染方式
        Vector<String> columns = AnimalTableModel.getColumns();
        AnimalCellRender render=new AnimalCellRender();
        for(int i=0;i<columns.size();i++){
            TableColumn column = table.getColumn(columns.get(i));
            column.setCellRenderer(render);
            if (i==0){
                column.setPreferredWidth(50);
            }
        }


        Container container=getContentPane();
        //table放到scrollPane上，默认没有表头
        JScrollPane scrollPane=new JScrollPane(table);
        container.add(scrollPane);

        setBounds(400, 100, 800, 640);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }



}




//自定义
class AnimalTableModel extends DefaultTableModel{

    static Vector<String> columns=new Vector<>();
    static {columns.addElement("ID");
        columns.addElement("园区");
        columns.addElement("动物名称");
        columns.addElement("动物种类");
        columns.addElement("点赞数");
    }

    private  AnimalTableModel(){
        super(null,columns);
    }

    private static AnimalTableModel animalTableModel=new AnimalTableModel();

    public static AnimalTableModel assembleMobel(Vector<Vector<Object>> data){
        animalTableModel.setDataVector(data,columns);
        return animalTableModel;
    }

    public static void updateModel(Vector<Vector<Object>> data){
        animalTableModel.setDataVector(data,columns);
    }


    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }


}


class AnimalCellRender extends DefaultTableCellRenderer{
    //在每一行的每一列显示之前都调用
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (row%2==0){
            setBackground(Color.lightGray);
        }else {
            setBackground(Color.white);
        }
        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }


}




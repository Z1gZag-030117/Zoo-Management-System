package view.user;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class UserVisitTableModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();
    static {
        columns.addElement("编号");
        columns.addElement("园区");
        columns.addElement("动物名");
        columns.addElement("种类");
        columns.addElement("点赞数");


    }

    private UserVisitTableModel() {
        super(null,columns);
    }

    private static UserVisitTableModel mainViewTableModel = new UserVisitTableModel();

    public static UserVisitTableModel assembleModel(Vector<Vector<Object>> data) {
        mainViewTableModel.setDataVector(data,columns);
        return mainViewTableModel;
    }

    public static void updateModel(Vector<Vector<Object>> data) {
        mainViewTableModel.setDataVector(data,columns);
    }

    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

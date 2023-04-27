package view.user;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class UserVisitParkModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();
    static {
        columns.addElement("用户名");
        columns.addElement("评论");

    }

    private UserVisitParkModel() {
        super(null,columns);
    }

    private static UserVisitParkModel mainViewTableModel = new UserVisitParkModel();

    public static UserVisitParkModel assembleModel(Vector<Vector<Object>> data) {
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

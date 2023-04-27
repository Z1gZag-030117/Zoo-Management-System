package servlce;

import controller.TableDTOpark;
import controller.TableDTOvisit;
import po.VisitRequest;
import util.DBUtil;
import view.user.UserVisitPark;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class CommentImpl implements Comment{

    @Override
    public TableDTOpark retrieveAnimal(VisitRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from comment ");
        if (request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())) {
            sql.append(" where comment like '%"+request.getSearchKey().trim()+"%'");
        }
        sql.append("order by ID desc limit ").append(request.getStart()).append(",")
                .append(request.getPageSize());
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableDTOpark returnDTO = new TableDTOpark();
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            // 查询记录
            returnDTO.setData(fillData(rs));

            sql.setLength(0);
            sql.append("select count(*) from comment ");
            if (request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())) {
                sql.append(" where userID like '%"+request.getSearchKey().trim()+"%'");
            }
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                returnDTO.setTotalCount(count);
            }
            return returnDTO;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return null;
    }



    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {

        Vector<Vector<Object>> data = new Vector<>();
        String name = null;
        while (rs.next()) {

                // 处理查出的每一条记录
                Vector<Object> oneRecord = new Vector<>();

                int userID = rs.getInt("userID");
                String contnet = rs.getString("content");
                try {
                    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Shanghai","root","123456");
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select * from user where account="+userID);
                    while(resultSet.next()){
                        name = resultSet.getString("username");
                    }
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                oneRecord.addElement(name);
                oneRecord.addElement(contnet);
                data.addElement(oneRecord);
            }



        return data;
    }
}

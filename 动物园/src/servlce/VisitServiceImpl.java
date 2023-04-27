package servlce;

import controller.TableDTOvisit;
import po.VisitRequest;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class VisitServiceImpl implements VisitService{
    @Override
    public TableDTOvisit retrieveStudents(VisitRequest request) {
            StringBuilder sql = new StringBuilder();
            sql.append("select * from animal ");
            if (request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())) {
                sql.append(" where animalname like '%"+request.getSearchKey().trim()+"%'");
            }
            sql.append("order by ID desc limit ").append(request.getStart()).append(",")
                    .append(request.getPageSize());
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            TableDTOvisit returnDTO = new TableDTOvisit();
            try {
                conn = DBUtil.getConn();
                ps = conn.prepareStatement(sql.toString());
                rs = ps.executeQuery();
                // 查询记录
                returnDTO.setData(fillData(rs));

                sql.setLength(0);
                sql.append("select count(*) from animal ");
                if (request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())) {
                    sql.append(" where animalname like '%"+request.getSearchKey().trim()+"%'");
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
        while (rs.next()) {
            // 处理查出的每一条记录
            Vector<Object> oneRecord = new Vector<>();
            int id = rs.getInt("ID");
            String park=rs.getString("park");
            String name = rs.getString("animalname");
            String varity = rs.getString("variety");
            int nice=rs.getInt("nice");

            oneRecord.addElement(id);
            oneRecord.addElement(park);
            oneRecord.addElement(name);
            oneRecord.addElement(varity);
            oneRecord.addElement(nice);
            data.addElement(oneRecord);
        }
        return data;
    }



}

package servlce;

import controller.TableDTO;
import po.AnimalRequest;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class AnimalSearchImpl implements AnimalSearch{
    @Override
    public TableDTO retrieveAnimal(AnimalRequest request) {

        StringBuilder sql=new StringBuilder();
        sql.append("select * from animal ");
        if (request.getSearchKey()!=null&&!"".equals(request.getSearchKey().trim())){
            sql.append("where animalname like '%"+request.getSearchKey().trim()+"%'");
        }
        sql.append("order by ID desc limit ").append(request.getStart()).append(",").append(request.getPageSize());

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        TableDTO returnDTO=new TableDTO();
        
        try {
            conn=DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            rs=ps.executeQuery();
            //查询纪录
            returnDTO.setData(fillData(rs));

            sql.setLength(0);
            sql.append("select count(*) from animal");
            if (request.getSearchKey()!=null&&!"".equals(request.getSearchKey().trim())){
                sql.append("animalname like '%"+request.getSearchKey().trim()+"%'");
            }
            ps=conn.prepareStatement(sql.toString());
            rs=ps.executeQuery();
            while(rs.next()){
                int count = Integer.parseInt(rs.getString(1));
                returnDTO.setTotalCount(count);
            }
            return returnDTO;

        }catch (Exception e){

        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closeConn(conn);
            DBUtil.closePs(ps);
        }

        DBUtil.getConn();
        return null;
    }

    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data=new Vector<>();
        while (rs.next()){
            Vector<Object> oneRecord=new Vector<>();
            String id= rs.getString("ID");
            String park= rs.getString("park");
            String name= rs.getString("animalname");
            String variety = rs.getString("variety");
            int nice= rs.getInt("nice");
            oneRecord.addElement(id);
            oneRecord.addElement(park);
            oneRecord.addElement(name);
            oneRecord.addElement(variety);
            oneRecord.addElement(nice);
            data.addElement(oneRecord);
        }
        return data;
    }
}

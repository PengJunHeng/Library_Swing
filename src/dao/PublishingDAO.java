package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import bean.PublishingForm;
import util.JDBCUtil;

public class PublishingDAO {
   // private ConnDB conn = new ConnDB();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    //查询数据
    public Collection query(String strif) {
        PublishingForm pubForm = null;
        Collection pubColl = new ArrayList();
        String sql = "";
        if (strif != "all" && strif != null && strif != "") {
            sql = "select * from tb_publishing where " + strif + "";
        } else {
            sql = "select * from tb_publishing";
        }
      //  ResultSet rs = conn.executeQuery(sql);
        try {
            preparedStatement= JDBCUtil.getStmt(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSet=preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                pubForm = new PublishingForm();
                pubForm.setIsbn(resultSet.getString(1));
                pubForm.setPubname(resultSet.getString(2));
                pubColl.add(pubForm);
            }
        } catch (SQLException ex) {
        }
        //conn.close();
        try {
            JDBCUtil.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pubColl;
    }

}

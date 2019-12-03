package dao;




import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.ParameterForm;
import util.JDBCUtil;

public class ParameterDAO {
   // ConnDB conn = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ParameterDAO() {
       // conn = new ConnDB();
    }

    public ParameterForm query() {
        ParameterForm libraryForm1 = null;
        String sql = "select * from tb_parameter where id=1";
       // ResultSet rs = conn.executeQuery(sql);
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
                libraryForm1 = new ParameterForm();
                libraryForm1.setId(Integer.valueOf(resultSet.getString(1)));
                libraryForm1.setCost(resultSet.getInt(2));
                libraryForm1.setValidity(resultSet.getInt(3));

            }
        } catch (Exception ex) {
        }
       // conn.close();
        try {
            JDBCUtil.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libraryForm1;
    }

    public int update(ParameterForm parameterForm) {
        String sql = "UPDATE tb_parameter SET cost=" + parameterForm.getCost() + ",validity=" + parameterForm.getValidity() + " where id=1";
       // int ret = conn.executeUpdate(sql);
        try {
            preparedStatement= JDBCUtil.getStmt(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int ret= 0;
        try {
            ret = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // conn.close();
        try {
            JDBCUtil.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

}

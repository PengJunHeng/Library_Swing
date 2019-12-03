package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import bean.ReaderTypeForm;
import util.JDBCUtil;

public class ReaderTypeDAO {
    //private ConnDB conn = new ConnDB();
private PreparedStatement preparedStatement;
private  ResultSet resultSet;
    //查询数据
    public Collection query(String strif) {
        ReaderTypeForm readerTypeForm = null;
        Collection readerTypeColl = new ArrayList();
        String sql = "";
        if (strif != "all" && strif != null && strif != "") {
            sql = "select * from tb_readerType where " + strif + "";
        } else {
            sql = "select * from tb_readerType";
        }
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
                readerTypeForm = new ReaderTypeForm();
                readerTypeForm.setId(Integer.valueOf(resultSet.getString(1)));
                readerTypeForm.setName(resultSet.getString(2));
                readerTypeForm.setNumber(resultSet.getInt(3));
                readerTypeColl.add(readerTypeForm);
            }
        } catch (SQLException ex) {
        }
        //conn.close();
        try {
            JDBCUtil.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readerTypeColl;
    }

    //用于修改的查询
    public ReaderTypeForm queryM(ReaderTypeForm readerTypeForm) {
        ReaderTypeForm readerTypeForm1 = null;
        String sql = "select * from tb_readerType where id=" + readerTypeForm.getId() + "";
        System.out.println("修改时的SQL：" + sql);
   //     ResultSet rs = conn.executeQuery(sql);
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
                readerTypeForm1 = new ReaderTypeForm();
                readerTypeForm1.setId(Integer.valueOf(resultSet.getString(1)));
                readerTypeForm1.setName(resultSet.getString(2));
                readerTypeForm1.setNumber(resultSet.getInt(3));
                System.out.println(resultSet.getInt(3));
            }
        } catch (SQLException ex) {
        }
       // conn.close();
        try {
            JDBCUtil.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readerTypeForm1;
    }

    //添加数据
    public int insert(ReaderTypeForm readerTypeForm) {
        String sql1 = "SELECT * FROM tb_readerType WHERE name='" + readerTypeForm.getName() + "'";
     //   ResultSet rs = conn.executeQuery(sql1);
        try {
            preparedStatement= JDBCUtil.getStmt(sql1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSet=preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "";
        int falg = 0;
        try {
            if (resultSet.next()) {
                falg = 2;
            } else {
                sql = "Insert into tb_readerType (name,number) values('" + readerTypeForm.getName() + "'," + readerTypeForm.getNumber() + ")";
              //  falg = conn.executeUpdate(sql);
                preparedStatement= JDBCUtil.getStmt(sql);
                falg=preparedStatement.executeUpdate();
                System.out.println("添加读者类型的SQL：" + sql);
              //  conn.close();
                JDBCUtil.closeAll();
            }
        } catch (SQLException ex) {
            falg = 0;
        }
        System.out.println("falg:" + falg);
        return falg;
    }

    //修改数据
    public int update(ReaderTypeForm readerTypeForm) {
        String sql = "Update tb_readerType set name='" + readerTypeForm.getName() + "',number=" + readerTypeForm.getNumber() + " where id=" + readerTypeForm.getId() + "";
       // int falg = conn.executeUpdate(sql);
        try {
            preparedStatement= JDBCUtil.getStmt(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int falg= 0;
        try {
            falg = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("修改数据时的SQL：" + sql);
      //  conn.close();
        try {
            JDBCUtil.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return falg;
    }

    //删除数据
    public int delete(ReaderTypeForm readerTypeForm) {
        String sql_1 = "SELECT * FROM tb_reader WHERE typeid=" + readerTypeForm.getId() + "";
      //  ResultSet rs = conn.executeQuery(sql_1);
        try {
            preparedStatement= JDBCUtil.getStmt(sql_1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSet=preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int flag = 0;
        try {
            if (!resultSet.next()) {
                String sql = "Delete from tb_readerType where id=" + readerTypeForm.getId() + "";
           //     flag = conn.executeUpdate(sql);
                preparedStatement= JDBCUtil.getStmt(sql);
                flag=preparedStatement.executeUpdate();
                System.out.println("删除时的SQL：" + sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}

package dao;




import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import bean.BookTypeForm;
import util.JDBCUtil;

public class BookTypeDAO {
    // private ConnDB conn=new ConnDB();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    //查询数据
    public Collection query(String strif) {
        BookTypeForm bookTypeForm = null;
        Collection bookTypeColl = new ArrayList();
        String sql = "";
        if (strif != "all" && strif != null && strif != "") {
            sql = "select * from tb_bookType where " + strif + "";
        } else {
            sql = "select * from tb_bookType";
        }
        try {
            preparedStatement = JDBCUtil.getStmt(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // ResultSet rs=conn.executeQuery(sql);
        try {
            while (resultSet.next()) {
                bookTypeForm = new BookTypeForm();
                bookTypeForm.setId(Integer.valueOf(resultSet.getString(1)));
                bookTypeForm.setTypeName(resultSet.getString(2));
                bookTypeForm.setDays(resultSet.getInt(3));
                bookTypeColl.add(bookTypeForm);
                System.out.print(bookTypeForm.getTypeName());
            }
        } catch (SQLException ex) {
        }
        //conn.close();
        try {
            JDBCUtil.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookTypeColl;
    }

    //用于修改的查询
    public BookTypeForm queryM(BookTypeForm bookTypeForm) {
        BookTypeForm bookTypeForm1 = null;
        String sql = "select * from tb_bookType where id=" + bookTypeForm.getId() + "";
        System.out.println("修改时的SQL：" + sql);
        try {
            preparedStatement = JDBCUtil.getStmt(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //ResultSet rs=conn.executeQuery(sql);
        try {
            while (resultSet.next()) {
                bookTypeForm1 = new BookTypeForm();
                bookTypeForm1.setId(Integer.valueOf(resultSet.getString(1)));
                bookTypeForm1.setTypeName(resultSet.getString(2));
                bookTypeForm1.setDays(resultSet.getInt(3));
            }
        } catch (SQLException ex) {
        }
        try {
            JDBCUtil.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // conn.close();
        return bookTypeForm1;
    }

    //添加数据
    public int insert(BookTypeForm bookTypeForm) {
        String sql1 = "SELECT * FROM tb_bookType WHERE typename='" + bookTypeForm.getTypeName() + "'";
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
        // ResultSet rs = conn.executeQuery(sql1);
        String sql = "";
        int falg = 0;
        try {
            if (resultSet.next()) {
                falg = 2;
            } else {
                sql = "Insert into tb_bookType (typename,days) values('" + bookTypeForm.getTypeName() + "'," + bookTypeForm.getDays() + ")";
                preparedStatement= JDBCUtil.getStmt(sql);
                falg=preparedStatement.executeUpdate();
             //   falg = conn.executeUpdate(sql);
                System.out.println("添加图书类型的SQL：" + sql);
                JDBCUtil.closeAll();
            //conn.close();
            }
        } catch (SQLException ex) {
            falg = 0;
        }
        System.out.println("falg:" + falg);
        return falg;
    }

    //修改数据
    public int update(BookTypeForm bookTypeForm) {
        String sql = "Update tb_bookType set typename='" + bookTypeForm.getTypeName() + "',days=" + bookTypeForm.getDays() + " where id=" + bookTypeForm.getId() + "";
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
        //    int falg = conn.executeUpdate(sql);
        System.out.println("修改数据时的SQL：" + sql);
        try {
            JDBCUtil.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //    conn.close();
        return falg;
    }

    //删除数据
    public int delete(BookTypeForm bookTypeForm) {
        String sql_1 = "SELECT * FROM tb_bookinfo WHERE typeid=" + bookTypeForm.getId() + "";
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
        // ResultSet rs = conn.executeQuery(sql_1);
        int falg = 0;
        try {
            if (!resultSet.next()) {
                String sql = "Delete from tb_bookType where id=" + bookTypeForm.getId() + "";
                preparedStatement= JDBCUtil.getStmt(sql);
                falg=preparedStatement.executeUpdate();
               // falg = conn.executeUpdate(sql);
                System.out.println("删除时的SQL：" + sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return falg;
    }
}

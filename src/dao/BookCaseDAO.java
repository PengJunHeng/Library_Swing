package dao;



import java.util.*;

import bean.BookCaseForm;
import util.JDBCUtil;

import java.sql.*;

public class BookCaseDAO {
   // private ConnDB conn=new ConnDB();
    //查询数据
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public Collection query(String strif){
        BookCaseForm bookCaseForm1=null;
        Collection bookcaseColl=new ArrayList();
        String sql="";
        if(strif!="all" && strif!=null && strif!=""){
            sql="select * from tb_bookcase where "+strif+"";
        }else{
            sql="select * from tb_bookcase";
        }
        try {
            preparedStatement=JDBCUtil.getStmt(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSet=preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // ResultSet rs= .executeQuery(sql);
        try {
            while (resultSet.next()) {
                bookCaseForm1=new BookCaseForm();
                bookCaseForm1.setId(Integer.valueOf(resultSet.getString(1)));
                bookCaseForm1.setName(resultSet.getString(2));
                bookcaseColl.add(bookCaseForm1);
            }
        } catch (SQLException ex) {
        }
        try {
            JDBCUtil.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookcaseColl;
    }
    //用于修改的查询
    public BookCaseForm queryM(BookCaseForm bookCaseForm){
        BookCaseForm bookCaseForm1=null;
        String sql="select * from tb_bookcase where id="+bookCaseForm.getId()+"";
        System.out.println("修改时的SQL："+sql);
        try {
            preparedStatement=JDBCUtil.getStmt(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSet=preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //  ResultSet rs=conn.executeQuery(sql);
        try {
            while (resultSet.next()) {
                bookCaseForm1=new BookCaseForm();
                bookCaseForm1.setId(Integer.valueOf(resultSet.getString(1)));
                bookCaseForm1.setName(resultSet.getString(2));
            }
        } catch (SQLException ex) {
        }
        try {
            JDBCUtil.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookCaseForm1;
    }
   //添加数据
    public int insert(BookCaseForm bookCaseForm){
    String sql1="SELECT * FROM tb_bookcase WHERE name='"+bookCaseForm.getName()+"'";
        try {
            preparedStatement=JDBCUtil.getStmt(sql1);
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
            sql ="Insert into tb_bookcase (name) values('"+bookCaseForm.getName()+"')";
            preparedStatement=JDBCUtil.getStmt(sql);
            falg=preparedStatement.executeUpdate();
           // falg = conn.executeUpdate(sql);
            System.out.println("添加书架信息的SQL：" + sql);
           JDBCUtil.closeAll();
        }
    } catch (SQLException ex) {
        falg = 0;
    }
    System.out.println("falg:"+falg);
    return falg;
}

    //修改数据
    public int update(BookCaseForm bookCaseForm){
    String sql="Update tb_bookcase set name='"+bookCaseForm.getName()+"' where id="+bookCaseForm.getId()+"";
        try {
            preparedStatement=JDBCUtil.getStmt(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int falg= 0;
        try {
            falg = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // int falg=conn.executeUpdate(sql);
    System.out.println("修改数据时的SQL："+sql);
        try {
            JDBCUtil.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return falg;
}
    //删除数据
    public int delete(BookCaseForm bookCaseForm){
    	String sql_1="SELECT * FROM tb_bookinfo WHERE bookcase="+bookCaseForm.getId()+"";
        try {
            preparedStatement=JDBCUtil.getStmt(sql_1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSet=preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //ResultSet rs=conn.executeQuery(sql_1);
    	int falg=0;
    	try {
    		if(!resultSet.next()){
    			String sql="Delete from tb_bookcase where id="+bookCaseForm.getId()+"";
                preparedStatement=JDBCUtil.getStmt(sql);
                falg=preparedStatement.executeUpdate();
    		//	falg=conn.executeUpdate(sql);
    			System.out.println("删除时的SQL："+sql);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}	
    	return falg;
    }

}

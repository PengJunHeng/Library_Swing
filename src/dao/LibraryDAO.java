package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.LibraryForm;
import util.JDBCUtil;

/**
 * <p>Title: LibraryManage</p>
 *
 * <p>Description: </p>
 *
 *
 * <p>Company:mr </p>
 *
 * @author:wgh
 */
public class LibraryDAO {
 //   ConnDB conn=null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public LibraryDAO() {
       // conn=new ConnDB();
    }
    public LibraryForm query(){
        LibraryForm libraryForm1=null;
        String sql = "select * from tb_library where id=1";
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
        //   ResultSet rs =conn.executeQuery(sql);
        try {
            while (resultSet.next()) {
                libraryForm1 = new LibraryForm();
                libraryForm1.setId(Integer.valueOf(resultSet.getString(1)));
                libraryForm1.setLibraryname(resultSet.getString(2));
                libraryForm1.setCurator(resultSet.getString(3));
                libraryForm1.setTel(resultSet.getString(4));
                libraryForm1.setAddress(resultSet.getString(5));
                libraryForm1.setEmail(resultSet.getString(6));
                libraryForm1.setUrl(resultSet.getString(7));
                libraryForm1.setCreateDate(resultSet.getString(8));
                libraryForm1.setIntroduce(resultSet.getString(9));
            }
        } catch (Exception ex) {
        }
        try {
            JDBCUtil.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //conn.close();
        return libraryForm1;
    }
    public int update(LibraryForm libraryForm){
        String sql="UPDATE tb_library SET libraryname='"+libraryForm.getLibraryname()+"',curator='"+libraryForm.getCurator()+"',tel='"+libraryForm.getTel()+"',address='"+libraryForm.getAddress()+"',email='"+libraryForm.getEmail()+"',url='"+libraryForm.getUrl()+"',createDate='"+libraryForm.getCreateDate()+"',introduce='"+libraryForm.getIntroduce()+"' where id="+libraryForm.getId()+"";
        int ret=0;
        try{
            preparedStatement= JDBCUtil.getStmt(sql);
            ret=preparedStatement.executeUpdate();
        //	ret=conn.executeUpdate(sql);
        }catch(Exception e){
        	System.out.println("修改图书馆信息："+e.getMessage());
        }
        System.out.println("修改图书馆信息时的SQL："+sql);
       // conn.close();
        try {
            JDBCUtil.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }


}

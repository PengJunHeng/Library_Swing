package dao;




import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import bean.BookForm;
import util.JDBCUtil;

public class BookDAO {
    //private ConnDB conn=new ConnDB();
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    //查询数据
    public Collection query(String strif) {
        BookForm bookForm = null;
        Collection bookColl = new ArrayList();
        String sql = "";
        if (strif != "all" && strif != null && strif != "") {
            sql = "select * from (select b.*,c.name as bookcaseName,p.pubname as publishing,t.typename from tb_bookinfo b left join tb_bookcase c on b.bookcase=c.id join tb_publishing p on b.ISBN=p.ISBN join tb_booktype t on b.typeid=t.id where b.del=0)  as book where book." + strif + "'";
        } else {
            sql = "select b.*,c.name as bookcaseName,p.pubname as publishing,t.typename from tb_bookinfo b left join tb_bookcase c on b.bookcase=c.id join tb_publishing p on b.ISBN=p.ISBN join tb_booktype t on b.typeid=t.id where b.del=0";
        }
        System.out.println("图书查询时的SQL：" + sql);
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
        //ResultSet rs = conn.executeQuery(sql);
        try {
            while (resultSet.next()) {
                bookForm = new BookForm();
                bookForm.setBarcode(resultSet.getString(1));
                bookForm.setBookName(resultSet.getString(2));
                bookForm.setTypeId(resultSet.getInt(3));
                bookForm.setAuthor(resultSet.getString(4));
                bookForm.setTranslator(resultSet.getString(5));
                bookForm.setIsbn(resultSet.getString(6));
                bookForm.setPrice(Float.valueOf(resultSet.getString(7)));  //此处必须进行类型转换
                bookForm.setPage(resultSet.getInt(8));
                bookForm.setBookcaseid(resultSet.getInt(9));
                bookForm.setInTime(resultSet.getString(10));
                bookForm.setOperator(resultSet.getString(11));
                bookForm.setDel(resultSet.getInt(12));
                bookForm.setId(Integer.valueOf(resultSet.getString(13)));
                bookForm.setBookcaseName(resultSet.getString(14));
                bookForm.setPublishing(resultSet.getString(15));
                bookForm.setTypeName(resultSet.getString(16));
                bookColl.add(bookForm);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            JDBCUtil.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookColl;
    }

    //用于修改的查询
    public BookForm queryM(BookForm bookForm1) {
        BookForm bookForm = null;
        String sql = "select b.*,c.name as bookcaseName,p.pubname as publishing,t.typename from tb_bookinfo b left join tb_bookcase c on b.bookcase=c.id join tb_publishing p on b.ISBN=p.ISBN join tb_booktype t on b.typeid=t.id where b.id=" + bookForm1.getId() + "";
        System.out.println("修改时的SQL：" + sql);
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
        //  ResultSet rs = conn.executeQuery(sql);
        try {
            while (resultSet.next()) {
                bookForm = new BookForm();
                bookForm.setBarcode(resultSet.getString(1));
                bookForm.setBookName(resultSet.getString(2));
                bookForm.setTypeId(resultSet.getInt(3));
                bookForm.setAuthor(resultSet.getString(4));
                bookForm.setTranslator(resultSet.getString(5));
                bookForm.setIsbn(resultSet.getString(6));
                bookForm.setPrice(Float.valueOf(resultSet.getString(7)));  //此处必须进行类型转换
                bookForm.setPage(resultSet.getInt(8));
                bookForm.setBookcaseid(resultSet.getInt(9));
                bookForm.setInTime(resultSet.getString(10));
                bookForm.setOperator(resultSet.getString(11));
                bookForm.setDel(resultSet.getInt(12));
                bookForm.setId(Integer.valueOf(resultSet.getString(13)));
                bookForm.setBookcaseName(resultSet.getString(14));
                bookForm.setPublishing(resultSet.getString(15));
                bookForm.setTypeName(resultSet.getString(16));
            }
        } catch (SQLException ex) {
        }
        try {
            JDBCUtil.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookForm;
    }

    //用于借阅的查询
    public BookForm queryB(String f, String key) {
        BookForm bookForm = null;
        String sql = "select b.*,c.name as bookcaseName,p.pubname as publishing,t.typename" +
                " from tb_bookinfo b left join tb_bookcase c on b.bookcase=c.id join" +
                " tb_publishing p on b.ISBN=p.ISBN join tb_booktype t on" +
                " b.typeid=t.id where b." + f + "='" + key + "'";    //查询图书信息的SQL语句
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
        // ResultSet rs = conn.executeQuery(sql);    //执行查询语句
        try {
            if (resultSet.next()) {
                bookForm = new BookForm();
                bookForm.setBarcode(resultSet.getString(1));    //获取图书条形码
                bookForm.setBookName(resultSet.getString(2));    //获取图书名称
                bookForm.setTypeId(resultSet.getInt(3));//获取图书类型ID
                bookForm.setAuthor(resultSet.getString(4));//获取作者
                bookForm.setTranslator(resultSet.getString(5));    //获取译者
                bookForm.setIsbn(resultSet.getString(6));    //获取图书的ISBN号
                bookForm.setPrice(Float.valueOf(resultSet.getString(7)));  //此处必须进行类型转换
                bookForm.setPage(resultSet.getInt(8));//获取页码
                bookForm.setBookcaseid(resultSet.getInt(9));    //获取书架ID
                bookForm.setInTime(resultSet.getString(10));    //获取入库时间
                bookForm.setOperator(resultSet.getString(11));//获取操作员
                bookForm.setDel(resultSet.getInt(12));//获取是否删除
                bookForm.setId(Integer.valueOf(resultSet.getString(13)));//获取图书ID号
                bookForm.setBookcaseName(resultSet.getString(14));//获取书架名称
                bookForm.setPublishing(resultSet.getString(15));//获取出版社
                bookForm.setTypeName(resultSet.getString(16));//获取类型名称
            }
        } catch (SQLException ex) {
        }
        try {
            JDBCUtil.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // conn.close();//关闭数据库连接
        return bookForm;
    }

    //添加数据
    public int insert(BookForm bookForm) {
        String sql1 = "SELECT * FROM tb_bookinfo WHERE barcode='" + bookForm.getBarcode() + "' or bookname='" + bookForm.getBookName() + "'";
     //   ResultSet rs = conn.executeQuery(sql1);
        try {
            preparedStatement=JDBCUtil.getStmt(sql1);
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
                sql = "Insert into tb_bookinfo (barcode,bookname,typeid,author,translator,isbn,price,page,bookcase,inTime,operator) values('" + bookForm.getBarcode() + "','" + bookForm.getBookName() + "'," + bookForm.getTypeId() + ",'" + bookForm.getAuthor() + "','" + bookForm.getTranslator() + "','" + bookForm.getIsbn() + "'," + bookForm.getPrice() + "," + bookForm.getPage() + "," + bookForm.getBookcaseid() + ",'" + bookForm.getInTime() + "','" + bookForm.getOperator() + "')";
                preparedStatement=JDBCUtil.getStmt(sql);
                falg=preparedStatement.executeUpdate();
                //falg = conn.executeUpdate(sql);
                System.out.println("添加图书的SQL：" + sql);
               JDBCUtil.closeAll();
               // conn.close();
            }
        } catch (SQLException ex) {
            falg = 0;
        }
        System.out.println("falg:" + falg);
        return falg;
    }

    //修改数据
    public int update(BookForm bookForm) {
        String sql = "Update tb_bookinfo set typeid=" + bookForm.getTypeId() + ",author='" + bookForm.getAuthor() + "',translator='" + bookForm.getTranslator() + "',isbn='" + bookForm.getIsbn() + "',price=" + bookForm.getPrice() + ",page=" + bookForm.getPage() + ",bookcase=" + bookForm.getBookcaseid() + " where id=" + bookForm.getId() + "";
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
        // int falg = conn.executeUpdate(sql);
        System.out.println("修改数据时的SQL：" + sql);
        try {
            JDBCUtil.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //conn.close();
        return falg;
    }

    //删除数据
    public int delete(BookForm bookForm) {
        String sql = "UPDATE tb_bookinfo SET del=1 where id=" + bookForm.getId() + "";
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
        //  int falg = conn.executeUpdate(sql);
        System.out.println("删除时的SQL：" + sql);
        return falg;
    }


}

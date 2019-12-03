package dao;


import java.util.*;

import bean.ManagerForm;
import util.ChStr;
import util.JDBCUtil;

import java.sql.*;
import java.*;

public class ManagerDAO {
	//private ConnDB conn = new ConnDB();
private PreparedStatement preparedStatement;
private ResultSet resultSet;
	// 查询数据
	public Collection query(String queryif) {
		ManagerForm managerForm = null;
		Collection managercoll = new ArrayList();
		String sql = "";
		if (queryif == null || queryif == "" || queryif == "all") { // 当参数queryif的值为null、all或空时查询全部数据
			sql = "select m.*,p.sysset,p.readerset,p.bookset,p.borrowback,p.sysquery from tb_manager m left join tb_purview p on m.id=p.id";
		} else {
			sql = "select m.*,p.sysset,p.readerset,p.bookset,p.borrowback,p.sysquery from tb_manager m left join tb_purview p on m.id=p.id where m.name='"
					+ queryif + "'"; // 此处需要应用左连接
		}
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
		//ResultSet rs = conn.executeQuery(sql); // 执行SQL语句
		try { // 捕捉异常信息
			while (resultSet.next()) {
				managerForm = new ManagerForm();
				managerForm.setId(Integer.valueOf(resultSet.getString(1)));
				managerForm.setName(resultSet.getString(2));
				managerForm.setPwd(resultSet.getString(3));
				managerForm.setSysset(resultSet.getInt(4));
				managerForm.setReaderset(resultSet.getInt(5));
				managerForm.setBookset(resultSet.getInt(6));
				managerForm.setBorrowback(resultSet.getInt(7));
				managerForm.setSysquery(resultSet.getInt(8));
				managercoll.add(managerForm); // 将查询结果保存到Collection集合中
			}
		} catch (SQLException e) {
		}
		return managercoll; // 返回查询结果
	}

	/*********************************************************/
	// 查询权限信息
	public ManagerForm query_p(String str) {
		ManagerForm managerForm1 = null;
		String sql = "select m.*,p.sysset,p.readerset,p.bookset,p.borrowback,p.sysquery from tb_manager m left join tb_purview p on m.id=p.id where m.name='"
				+ str + "'";
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
				managerForm1 = new ManagerForm();
				managerForm1.setId(Integer.valueOf(resultSet.getString(1)));
				managerForm1.setName(resultSet.getString(2));
				managerForm1.setPwd(resultSet.getString(3));
				managerForm1.setSysset(resultSet.getInt(4));
				managerForm1.setReaderset(resultSet.getInt(5));
				managerForm1.setBookset(resultSet.getInt(6));
				managerForm1.setBorrowback(resultSet.getInt(7));
				managerForm1.setSysquery(resultSet.getInt(8));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			//conn.close();
			try {
				JDBCUtil.closeAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return managerForm1;
	}

	// 管理员身份验证
	public int checkManager(ManagerForm managerForm) {
		int flag = 0; // 标记变量，值为0时表示不成功，值为1时表示成功
		String sql = "SELECT * FROM tb_manager where name='"
				+ ChStr.filterStr(managerForm.getName()) + "'"; // 连接SQL语句，并过滤管理员名称中的危险字符
		///ResultSet rs = conn.executeQuery(sql);
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
			if (resultSet.next()) {
				String pwd = ChStr.filterStr(managerForm.getPwd()); // 获取输入的密码并过滤输入字符串中的危险字符
				if (pwd.equals(resultSet.getString(3))) {
					flag = 1; // 表示验证成功
				} else {
					flag = 0; // 表示验证不成功
				}
			} else {
				flag = 0; // 表示验证不成功
			}
		} catch (SQLException ex) {
			flag = 0; // 表示验证不成功
		} finally {
			//conn.close(); // 关闭数据库连接
			try {
				JDBCUtil.closeAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	// 修改时应用的查询方法
	public ManagerForm query_update(ManagerForm managerForm) {
		ManagerForm managerForm1 = null;
		String sql = "select m.*,p.sysset,p.readerset,p.bookset,p.borrowback,p.sysquery from tb_manager m left join tb_purview p on m.id=p.id where m.id="
				+ managerForm.getId() + "";
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
		//ResultSet rs = conn.executeQuery(sql); // 执行查询语句
		try {
			while (resultSet.next()) {
				managerForm1 = new ManagerForm();
				managerForm1.setId(Integer.valueOf(resultSet.getString(1)));
				managerForm1.setName(resultSet.getString(2));
				managerForm1.setPwd(resultSet.getString(3));
				managerForm1.setSysset(resultSet.getInt(4));
				managerForm1.setReaderset(resultSet.getInt(5));
				managerForm1.setBookset(resultSet.getInt(6));
				managerForm1.setBorrowback(resultSet.getInt(7));
				managerForm1.setSysquery(resultSet.getInt(8));
			}
		} catch (SQLException ex) {
			ex.printStackTrace(); // 输出异常信息
		} finally {
			//conn.close(); // 关闭数据库连接
			try {
				JDBCUtil.closeAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return managerForm1;
	}

	// 更改口令时应用的查询方法
	public ManagerForm query_pwd(ManagerForm managerForm) {
		ManagerForm managerForm1 = null;
		String sql = "SELECT * FROM tb_manager WHERE name='"
				+ managerForm.getName() + "'";
		//		ResultSet rs = conn.executeQuery(sql);
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
				managerForm1 = new ManagerForm();
				managerForm1.setId(Integer.valueOf(resultSet.getString(1)));
				managerForm1.setName(resultSet.getString(2));
				managerForm1.setPwd(resultSet.getString(3));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			//conn.close();
			try {
				JDBCUtil.closeAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return managerForm1;
	}

	// 添加数据
	public int insert(ManagerForm managerForm) {
		String sql1 = "SELECT * FROM tb_manager WHERE name='"
				+ managerForm.getName() + "'";
		//ResultSet rs = conn.executeQuery(sql1);
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
				sql = "INSERT INTO tb_manager (name,pwd) values('"
						+ managerForm.getName() + "','" + managerForm.getPwd()
						+ "')";
				//falg = conn.executeUpdate(sql);
				preparedStatement= JDBCUtil.getStmt(sql);
				falg=preparedStatement.executeUpdate();
				System.out.println("添加管理员信息的SQL：" + sql);
			}
		} catch (SQLException ex) {
			falg = 0;
		} finally {
			//conn.close();
			try {
				JDBCUtil.closeAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return falg;
	}

	// 设置管理员权限
	public int update(ManagerForm managerForm) {
		String sql1 = "SELECT * FROM tb_purview WHERE id="
				+ managerForm.getId() + "";
		//ResultSet rs = conn.executeQuery(sql1); // 查询要设置权限的管理员的权限信息
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
		try { // 捕捉异常信息
			if (resultSet.next()) { // 当已经设置权限时，执行更新语句
				sql = "Update tb_purview set sysset=" + managerForm.getSysset()
						+ ",readerset=" + managerForm.getReaderset()
						+ ",bookset=" + managerForm.getBookset()
						+ ",borrowback=" + managerForm.getBorrowback()
						+ ",sysquery=" + managerForm.getSysquery()
						+ " where id=" + managerForm.getId() + "";
			} else { // 未设置权限时，执行插入语句
				sql = "INSERT INTO tb_purview values(" + managerForm.getId()
						+ "," + managerForm.getSysset() + ","
						+ managerForm.getReaderset() + ","
						+ managerForm.getBookset() + ","
						+ managerForm.getBorrowback() + ","
						+ managerForm.getSysquery() + ")";
			}
			//falg = conn.executeUpdate(sql);
			preparedStatement= JDBCUtil.getStmt(sql);
			falg=preparedStatement.executeUpdate();
			System.out.println("修改数据时的SQL：" + sql);
		} catch (SQLException ex) {
			falg = 0; // 表示设置管理员权限失败
		} finally {
		//	conn.close(); // 关闭数据库连接
			try {
				JDBCUtil.closeAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return falg;
	}

	// 修改管理员密码
	public int updatePwd(ManagerForm managerForm) {
		String sql = "UPDATE tb_manager SET pwd='" + managerForm.getPwd()
				+ "' where name='" + managerForm.getName() + "'";
		//int ret = conn.executeUpdate(sql);
		try {
			preparedStatement= JDBCUtil.getStmt(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int	ret= 0;
		try {
			ret = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("修改管理员密码时的SQL：" + sql);
		//conn.close();
		try {
			JDBCUtil.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	// 删除数据
	public int delete(ManagerForm managerForm) {
		int flag = 0;
		try { // 捕捉异常信息
			String sql = "DELETE FROM tb_manager where id="
					+ managerForm.getId() + "";
		//	flag = conn.executeUpdate(sql); // 执行删除管理员信息的语句
			preparedStatement= JDBCUtil.getStmt(sql);
			flag=preparedStatement.executeUpdate();
			if (flag != 0) {
				String sql1 = "DELETE FROM tb_purview where id="
						+ managerForm.getId() + "";
			//	conn.executeUpdate(sql1); // 执行删除权限信息的语句
				preparedStatement= JDBCUtil.getStmt(sql);
				preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("删除管理员信息时产生的错误：" + e.getMessage()); // 输出错误信息
		} finally {
			//conn.close(); // 关闭数据库连接
			try {
				JDBCUtil.closeAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
}

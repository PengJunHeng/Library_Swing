package panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bean.BorrowForm;
import bean.ManagerForm;
import dao.ManagerDAO;
import frame.InsertManagerFrame;
import frame.ModifyManagerPermission;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ManagerSettingPanel extends JPanel implements ActionListener {
	private JTable jTable;

	private JScrollPane scrollPane;
	private String[] title;
	private DefaultTableModel model;

	private JButton addButton;
	private JButton permissionSettingsButton;
	private ManagerDAO managerDAO = new ManagerDAO();
	String str = null;
	String flag = "mr";
	Collection coll = (Collection) managerDAO.query(str);
	List<ManagerForm> list = new ArrayList<ManagerForm>();
	ManagerForm managerForm;
	private JButton deleteButton;

	/**
	 * Create the panel.
	 */
	public ManagerSettingPanel() {
		setBorder(BorderFactory.createTitledBorder("管理员设置"));
		setBounds(0, 0, 1920, 970);
		setLayout(null);
		setVisible(true);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 20, 1770, 945);
		// setSize(1920,970);
		add(scrollPane);

		jTable = getJTable();
		title = new String[] { "管理员名称", "系统设置", "读者管理", "图书管理", "图书借还", "系统查询" };
		model = new DefaultTableModel(title, 0);
		setData();
		jTable.setModel(model);
		scrollPane.setViewportView(jTable);

		addButton = new JButton("添加");
		addButton.setBounds(1789, 214, 113, 27);
		addButton.addActionListener(this);
		add(addButton);

		deleteButton = new JButton("删除");
		deleteButton.setBounds(1789, 287, 113, 27);
		deleteButton.addActionListener(this);
		add(deleteButton);

		permissionSettingsButton = new JButton("权限设置");
		permissionSettingsButton.setBounds(1789, 359, 113, 27);
		permissionSettingsButton.addActionListener(this);
		add(permissionSettingsButton);

	}

	public void setData() {
		model.setRowCount(0);
		if (coll == null || coll.isEmpty()) {
		} else {
			// 通过迭代方式显示数据
			Iterator it = coll.iterator();

			int ID = 0;
			String name = "";
			int sysset = 0;
			int readerset = 0;
			int bookset = 0;
			int borrowback = 0;
			int sysquery = 0;
			while (it.hasNext()) {

				ManagerForm managerForm = (ManagerForm) it.next();
				list.add(managerForm);
				ID = managerForm.getId().intValue();

				name = managerForm.getName();
				sysset = managerForm.getSysset();
				readerset = managerForm.getReaderset();
				bookset = managerForm.getBookset();
				borrowback = managerForm.getBorrowback();
				sysquery = managerForm.getSysquery();
				model.addRow(new Object[] { name, sysset == 1 ? "已授权" : "未授权", readerset == 1 ? "已授权" : "未授权",
						bookset == 1 ? "已授权" : "未授权", borrowback == 1 ? "已授权" : "未授权", sysquery == 1 ? "已授权" : "未授权" });

			}
		}

	}

	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
			String[] title = new String[] {};
			model = new DefaultTableModel(title, 0) {
				public boolean isCellEdittable(int row, int column) {
					return false;
				}
			};
			jTable.setModel(model);
			jTable.getTableHeader().setReorderingAllowed(false);
			jTable.getTableHeader().setResizingAllowed(false);
		}
		return jTable;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == permissionSettingsButton) {
			int selectRow = jTable.getSelectedRow();
			System.out.println("selectRow==================" + selectRow);
			if (selectRow < 0) {
				JOptionPane.showMessageDialog(null, "请先点击你要修改权限的行数！");
			} else {

				managerForm = list.get(selectRow);

				if (!managerForm.getName().equals(flag)) {
					// managerForm.setId(Integer.valueOf(request.getParameter("id")));// 获取并设置管理ID号
					ModifyManagerPermission managerPermission = new ModifyManagerPermission(managerForm);

				}else {
					JOptionPane.showMessageDialog(null, "请勿修改主要管理员权限！");
				}

			}
		}
		if (e.getSource()==deleteButton) {
			
			int selectRow = jTable.getSelectedRow();
		//	System.out.println("selectRow==================" + selectRow);
			if (selectRow < 0) {
				JOptionPane.showMessageDialog(null, "请先点击你要删除的行数！");
			} else {

				managerForm = list.get(selectRow);
				
					try {

						int i = JOptionPane.showConfirmDialog(null, "确定要删除？");
						if (i == 0&&!managerForm.getName().equals(flag)) {
							int ret =managerDAO.delete(managerForm);
							if (ret == 0) {
								JOptionPane.showMessageDialog(null, "删除管理员信息失败！");// 保存错误提示信息到error参数中
							
							} else {
								
								JOptionPane.showMessageDialog(null, "删除成功");
								setData();
							}
						}
					} catch (HeadlessException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					
					}

			}
		}
		if(e.getSource()==addButton) {
			InsertManagerFrame insertManagerFrame=new InsertManagerFrame();
		}
	}

}

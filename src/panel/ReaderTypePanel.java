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

import bean.BookCaseForm;
import bean.BorrowForm;
import bean.ManagerForm;
import bean.ReaderTypeForm;
import dao.BookCaseDAO;
import dao.ManagerDAO;
import dao.ReaderTypeDAO;
import frame.AddBookCaseFrame;
import frame.AddReaderTypeFrame;
import frame.BookCaseModifyFrame;
import frame.InsertManagerFrame;
import frame.ModifyManagerPermission;
import frame.ReaderTypeModifyFrame;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ReaderTypePanel extends JPanel implements ActionListener {
	private JTable jTable;

	private JScrollPane scrollPane;
	private String[] title;
	private DefaultTableModel model;

	private JButton addButton;
	private JButton changeButton;

	private String str = null;
	private String flag = "mr";
	private List<ReaderTypeForm> list = new ArrayList<>();
	private JButton deleteButton;
	private BookCaseDAO bookCaseDAO = new BookCaseDAO();
	private BookCaseForm bookCaseForm;
	private ReaderTypeDAO readerTypeDAO=new ReaderTypeDAO();
	private ReaderTypeForm readerTypeForm;

	/**
	 * Create the panel.
	 */
	public ReaderTypePanel() {
		setBorder(BorderFactory.createTitledBorder("管理员设置"));
		setBounds(0, 0, 1920, 970);
		setLayout(null);
		setVisible(true);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 20, 1770, 945);
		// setSize(1920,970);
		add(scrollPane);

		jTable = getJTable();
		title = new String[] { "读者类型名称", "可借数量" };
		model = new DefaultTableModel(title, 0);
		setData();
		jTable.setModel(model);
		scrollPane.setViewportView(jTable);

		addButton = new JButton("添加读者类型信息");
		addButton.setBounds(1789, 214, 113, 27);
		addButton.addActionListener(this);
		add(addButton);

		deleteButton = new JButton("删除");
		deleteButton.setBounds(1789, 287, 113, 27);
		deleteButton.addActionListener(this);
		add(deleteButton);

		changeButton = new JButton("修改");
		changeButton.setBounds(1789, 359, 113, 27);
		changeButton.addActionListener(this);
		add(changeButton);

	}

	public void setData() {
		model.setRowCount(0);
		String str = null;

		Collection coll = readerTypeDAO.query(str);
		;

		if (coll == null || coll.isEmpty()) {
		} else {
			// 通过迭代方式显示数据
			Iterator it = coll.iterator();
			int ID = 0;
			String name = "";
			int number = 0;
			while (it.hasNext()) {
				ReaderTypeForm readerTypeForm = (ReaderTypeForm) it.next();
				list.add(readerTypeForm);
				ID = readerTypeForm.getId().intValue();
				name = readerTypeForm.getName();
				number = readerTypeForm.getNumber();

				model.addRow(new Object[] { name,number });

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
		if (e.getSource() == changeButton) {
			int selectRow = jTable.getSelectedRow();
			// System.out.println("==================================="+selectRow);
			if (selectRow < 0) {
				JOptionPane.showMessageDialog(null, "请先点击你要修改的行数！");
			} else {
				readerTypeForm = list.get(selectRow);
				ReaderTypeForm newReaderTypeForm=readerTypeDAO.queryM(readerTypeForm);
				ReaderTypeModifyFrame frame=new ReaderTypeModifyFrame(newReaderTypeForm);
			}

		}
		if (e.getSource() == deleteButton) {

			int selectRow = jTable.getSelectedRow();
			// System.out.println("selectRow==================" + selectRow);
			if (selectRow < 0) {
				JOptionPane.showMessageDialog(null, "请先点击你要删除的行数！");
			} else {

				readerTypeForm = list.get(selectRow);

				try {

					int i = JOptionPane.showConfirmDialog(null, "确定要删除？");
					if (i == 0) {
						int ret = readerTypeDAO.delete(readerTypeForm);
						if (ret == 0) {
							JOptionPane.showMessageDialog(null, "删除读者类型失败！");// 保存错误提示信息到error参数中

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
		if (e.getSource() == addButton) {
			AddReaderTypeFrame addReaderTypeFrame = new AddReaderTypeFrame();
		}
	}

}

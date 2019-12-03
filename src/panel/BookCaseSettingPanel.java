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
import dao.BookCaseDAO;
import dao.ManagerDAO;
import frame.AddBookCaseFrame;
import frame.BookCaseModifyFrame;
import frame.InsertManagerFrame;
import frame.ModifyManagerPermission;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class BookCaseSettingPanel extends JPanel implements ActionListener {
	private JTable jTable;

	private JScrollPane scrollPane;
	private String[] title;
	private DefaultTableModel model;

	private JButton addButton;
	private JButton changeButton;
	
	private String str = null;
	private String flag = "mr";
	private List<BookCaseForm> list=new ArrayList<BookCaseForm>();
	private JButton deleteButton;
	private BookCaseDAO bookCaseDAO = new BookCaseDAO();
	private BookCaseForm bookCaseForm;

	/**
	 * Create the panel.
	 */
	public BookCaseSettingPanel() {
		setBorder(BorderFactory.createTitledBorder("管理员设置"));
		setBounds(0, 0, 1920, 970);
		setLayout(null);
		setVisible(true);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 20, 1770, 945);
		// setSize(1920,970);
		add(scrollPane);

		jTable = getJTable();
		title = new String[] { "书架名称" };
		model = new DefaultTableModel(title, 0);
		setData();
		jTable.setModel(model);
		scrollPane.setViewportView(jTable);

		addButton = new JButton("添加书架信息");
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

		Collection coll = (Collection) bookCaseDAO.query(str);
		if (coll == null || coll.isEmpty()) {
		} else {
			// 通过迭代方式显示数据
			Iterator it = coll.iterator();
			int ID = 0;
			String name = "";
			while (it.hasNext()) {
				BookCaseForm bookCaseForm = (BookCaseForm) it.next();
				list.add(bookCaseForm);
				ID = bookCaseForm.getId().intValue();
				name = bookCaseForm.getName();
			
				model.addRow(new Object[] { name });

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
			System.out.println("==================================="+selectRow);
			if (selectRow < 0) {
				JOptionPane.showMessageDialog(null, "请先点击你要修改的行数！");
			} else {
				bookCaseForm = list.get(selectRow);
				BookCaseForm newBookCaseForm = bookCaseDAO.queryM(bookCaseForm);
				BookCaseModifyFrame bookCaseModifyFrame = new BookCaseModifyFrame(newBookCaseForm);
			}

		}
		if (e.getSource() == deleteButton) {

			int selectRow = jTable.getSelectedRow();
			// System.out.println("selectRow==================" + selectRow);
			if (selectRow < 0) {
				JOptionPane.showMessageDialog(null, "请先点击你要删除的行数！");
			} else {

				bookCaseForm = list.get(selectRow);

				try {

					int i = JOptionPane.showConfirmDialog(null, "确定要删除？");
					if (i == 0) {
						int ret = bookCaseDAO.delete(bookCaseForm);
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
		if (e.getSource() == addButton) {
			AddBookCaseFrame addBookCaseFrame = new AddBookCaseFrame();
		}
	}

}

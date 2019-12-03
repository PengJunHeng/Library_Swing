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

import bean.BookTypeForm;
import bean.BorrowForm;
import bean.ManagerForm;
import dao.BookTypeDAO;
import dao.ManagerDAO;
import frame.AddBookTypeFrame;
import frame.BookTypeModifyFrame;
import frame.InsertManagerFrame;
import frame.ModifyManagerPermission;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class BookTypePanel extends JPanel implements ActionListener {
	private JTable jTable;

	private JScrollPane scrollPane;
	private String[] title;
	private DefaultTableModel model;

	private JButton addButton;
	private JButton changeBookTypeButton;
	private ManagerDAO managerDAO = new ManagerDAO();
	String str = null;
	String flag = "mr";
	private BookTypeDAO bookTypeDAO = new BookTypeDAO();
	Collection coll = bookTypeDAO.query(str);
	List<BookTypeForm> list = new ArrayList<>();
	ManagerForm managerForm;
	private JButton deleteButton;
	private BookTypeForm bookTypeForm;

	/**
	 * Create the panel.
	 */
	public BookTypePanel() {
		setBorder(BorderFactory.createTitledBorder("管理员设置"));
		setBounds(0, 0, 1920, 970);
		setLayout(null);
		setVisible(true);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 20, 1770, 945);
		// setSize(1920,970);
		add(scrollPane);

		jTable = getJTable();
		title = new String[] { "图书类型名称", "可借天数" };
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

		changeBookTypeButton = new JButton("修改");
		changeBookTypeButton.setBounds(1789, 359, 113, 27);
		changeBookTypeButton.addActionListener(this);
		add(changeBookTypeButton);

	}

	public void setData() {
		model.setRowCount(0);
		if (coll == null || coll.isEmpty()) {
		} else {
			// 通过迭代方式显示数据
			Iterator it = coll.iterator();
			int ID = 0;
			String typename = "";
			int days = 0;
			while (it.hasNext()) {
				bookTypeForm = (BookTypeForm) it.next();
				list.add(bookTypeForm);
				ID = bookTypeForm.getId().intValue();
				typename = bookTypeForm.getTypeName();
				days = bookTypeForm.getDays();
				model.addRow(new Object[] { typename, days });
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
		if (e.getSource() == changeBookTypeButton) {
			int selectRow = jTable.getSelectedRow();
			if (selectRow < 0) {
				JOptionPane.showMessageDialog(null, "请先点击你要删除的行数！");
				return;
			}
			bookTypeForm= list.get(selectRow);
			
			BookTypeModifyFrame bookTypeModifyFrame=new BookTypeModifyFrame(bookTypeForm);
		}
		if (e.getSource() == deleteButton) {

			int selectRow = jTable.getSelectedRow();
			// System.out.println("selectRow==================" + selectRow);
			if (selectRow < 0) {
				JOptionPane.showMessageDialog(null, "请先点击你要删除的行数！");
			} else {

				// managerForm = list.get(selectRow);
				bookTypeForm=list.get(selectRow);
				try {

					int i = JOptionPane.showConfirmDialog(null, "确定要删除？");
					if (i == 0 && !managerForm.getName().equals(flag)) {
						int ret = bookTypeDAO.delete(bookTypeForm);
						if (ret == 0) {
							JOptionPane.showMessageDialog(null, "删除图书类型信息失败！");
							return;
						} else {
							JOptionPane.showMessageDialog(null, "操作成功！");

						}
					}
				} catch (HeadlessException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();

				}

			}
		}
		if (e.getSource() == addButton) {
			AddBookTypeFrame addBookTypeFrame=new AddBookTypeFrame();
		}
	}

}

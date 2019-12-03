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

import bean.BookForm;
import bean.BorrowForm;
import bean.ManagerForm;
import dao.BookDAO;
import dao.ManagerDAO;
import frame.BookDetailFrame;
import frame.InsertManagerFrame;
import frame.ModifyManagerPermission;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LibraryInfoQueryPanel extends JPanel implements ActionListener {
	private JTable jTable;

	private JScrollPane scrollPane;
	private String[] title;
	private DefaultTableModel model;
	private ManagerDAO managerDAO = new ManagerDAO();
	private BookDAO bookDAO = new BookDAO();
	private String str = null;
	private String flag = "mr";
	private String[] items = new String[] { "条形码", "书名", "类别", "作者", "书架", "书名" };

	private Collection coll = bookDAO.query(str);
	/// request.setAttribute("book", ); // 将查询结果保存到book中
	// request.getRequestDispatcher("book.jsp").forward(request, response);
	private ManagerForm managerForm;
	private JTextField queryField;
	private JButton queryButton;
	private JComboBox comboBox;
	private BookForm bookForm;
	private List<BookForm> list = new ArrayList<BookForm>();

	/**
	 * Create the panel.
	 */
	public LibraryInfoQueryPanel() {
		setBorder(BorderFactory.createTitledBorder("管理员设置"));
		setBounds(0, 0, 1920, 957);
		setLayout(null);
		setVisible(true);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 60, 1901, 884);
		// setSize(1920,970);
		add(scrollPane);

		jTable = getJTable();

		title = new String[] { "条形码", "图书名称", "图书类型", "出版社", "书架" };
		model = new DefaultTableModel(title, 0);
		setData();

		jTable.setModel(model);
		scrollPane.setViewportView(jTable);

		comboBox = new JComboBox(items);
		comboBox.setBounds(378, 23, 207, 24);
		add(comboBox);

		JLabel lblNewLabel = new JLabel("请选择查询依据：");
		lblNewLabel.setBounds(171, 26, 144, 18);
		add(lblNewLabel);

		queryButton = new JButton("查询");
		queryButton.addActionListener(this);
		queryButton.setBounds(1405, 22, 113, 27);
		add(queryButton);

		queryField = new JTextField();
		queryField.setBounds(599, 23, 792, 24);
		add(queryField);
		queryField.setColumns(10);

	}

	public void setData() {
		model.setRowCount(0);
		if (coll == null || coll.isEmpty()) {
		} else {
			// 通过迭代方式显示数据
			Iterator it = coll.iterator();

			int ID = 0;
			String bookname = "";
			String barcode = "";
			String typename = "";
			String publishing = "";
			String bookcase = "";
			int storage = 0;
			while (it.hasNext()) {

				bookForm = (BookForm) it.next();
				list.add(bookForm);
				ID = bookForm.getId().intValue();
				bookname = bookForm.getBookName();
				barcode = bookForm.getBarcode();
				if (barcode == null)
					barcode = "";
				typename = bookForm.getTypeName();
				publishing = bookForm.getPublishing();
				bookcase = bookForm.getBookcaseName();
				model.addRow(new Object[] { barcode, bookname, typename, publishing, bookcase });

			}
		}

	}

	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
			jTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int selectRow = jTable.getSelectedRow();// 改变的单元格所在的行索引，起始值为0
					bookForm = list.get(selectRow);
					BookDetailFrame bookDetailFrame = new BookDetailFrame(bookForm);
				}
			});
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
		if (e.getSource() == queryButton) {
			if (comboBox != null) {
				if (comboBox.getSelectedItem().equals("条形码")) {
					str = "barcode" + " like '%" + queryField.getText().trim() + "%";
				} else if (comboBox.getSelectedItem().equals("类别")) {
					str = "typename" + " like '%" + queryField.getText().trim() + "%";
				} else if (comboBox.getSelectedItem().equals("书名")) {
					str = "bookname" + " like '%" + queryField.getText().trim() + "%";
				} else if (comboBox.getSelectedItem().equals("作者")) {
					str = "author" + " like '%" + queryField.getText().trim() + "%";
				} else if (comboBox.getSelectedItem().equals("出版社")) {
					str = "publishing" + " like '%" + queryField.getText().trim() + "%";
				} else if (comboBox.getSelectedItem().equals("书架")) {
					str = "bookcasename" + " like '%" + queryField.getText().trim() + "%";
				}

				System.out.print("条件查询图书信息时的str:" + str);
			}

			coll = bookDAO.query(str);
			if (coll == null || coll.isEmpty()) {
				JOptionPane.showMessageDialog(null, "暂无图书信息！");
			} else {
				setData();
			}
		}
	}
	// System.out.print("条件查询图书信息时的str:" + str);
	/// request.getRequestDispatcher("bookQuery.jsp").forward(request, response);
}

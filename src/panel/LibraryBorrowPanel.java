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
import dao.BorrowDAO;
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
import javax.swing.JCheckBox;

public class LibraryBorrowPanel extends JPanel implements ActionListener {
	private JTable jTable;

	private JScrollPane scrollPane;
	private String[] title;
	private DefaultTableModel model;

	private String str = null;

	private String[] items = new String[] { "图书条形码", "图书名称", "读者条形码", "读者名称" };

	/// request.setAttribute("book", ); // 将查询结果保存到book中
	// request.getRequestDispatcher("book.jsp").forward(request, response);
	private ManagerForm managerForm;
	private JTextField queryField;
	private JButton queryButton;
	private JComboBox comboBox;
	private BookForm bookForm;
	private List<BookForm> list = new ArrayList<BookForm>();
	private JTextField fromTimeField;
	private JTextField toTimeField;
	private JCheckBox queryCheckBox;
	private JCheckBox dateCheckBox;
	private BorrowDAO borrowDAO = new BorrowDAO();
	private Collection coll = borrowDAO.borrowQuery(str);

	/**
	 * Create the panel.
	 */
	public LibraryBorrowPanel() {
		setBorder(BorderFactory.createTitledBorder("管理员设置"));
		setBounds(0, 0, 1920, 957);
		setLayout(null);
		setVisible(true);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 130, 1901, 814);
		// setSize(1920,970);
		add(scrollPane);
		jTable = getJTable();
		title = new String[] { "图书条形码", "图书名称", "读者条形码", "读者名称", "借阅时间", "应还时间", "是否归还" };
		model = new DefaultTableModel(title, 0);
		setData();

		jTable.setModel(model);
		scrollPane.setViewportView(jTable);

		comboBox = new JComboBox(items);
		comboBox.setBounds(378, 23, 207, 24);
		add(comboBox);

		queryButton = new JButton("查询");
		queryButton.addActionListener(this);
		queryButton.setBounds(1405, 22, 113, 27);
		add(queryButton);

		queryField = new JTextField();
		queryField.setBounds(599, 23, 792, 24);
		add(queryField);
		queryField.setColumns(10);

		queryCheckBox = new JCheckBox("请选择查询依据：");
		queryCheckBox.setBounds(212, 22, 156, 27);
		add(queryCheckBox);

		dateCheckBox = new JCheckBox("借阅时间：");
		dateCheckBox.setBounds(212, 62, 133, 27);
		add(dateCheckBox);

		JLabel lblNewLabel = new JLabel("从");
		lblNewLabel.setBounds(378, 66, 15, 18);
		add(lblNewLabel);

		fromTimeField = new JTextField();
		fromTimeField.setBounds(399, 63, 458, 24);
		add(fromTimeField);
		fromTimeField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("到");
		lblNewLabel_1.setBounds(871, 66, 72, 18);
		add(lblNewLabel_1);

		toTimeField = new JTextField();
		toTimeField.setBounds(902, 63, 416, 24);
		add(toTimeField);
		toTimeField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("(日期格式为：YYYY-MM-DD)");
		lblNewLabel_2.setBounds(1319, 66, 199, 18);
		add(lblNewLabel_2);

	}

	public void setData() {
		model.setRowCount(0);
		// String str = null;
		if (coll == null || coll.isEmpty()) {
			JOptionPane.showMessageDialog(null, "暂无图书借阅信息！");
		} else {
			Iterator it = coll.iterator();
			String bookname = "";
			String bookbarcode = "";
			String readerbar = "";
			String readername = "";
			String borrowTime = "";
			String backTime = "";
			int ifback = 0;
			String ifbackstr = "";
			while (it.hasNext()) {
				BorrowForm borrowForm = (BorrowForm) it.next();
				bookname = borrowForm.getBookName();
				bookbarcode = borrowForm.getBookBarcode();
				readerbar = borrowForm.getReaderBarcode();
				readername = borrowForm.getReaderName();
				borrowTime = borrowForm.getBorrowTime();
				backTime = borrowForm.getBackTime();
				ifback = borrowForm.getIfBack();
				if (ifback == 0) {
					ifbackstr = "未归还";
				} else {
					ifbackstr = "已归还";
				}
				model.addRow(
						new Object[] { bookbarcode, bookname, readerbar, readername, borrowTime, backTime, ifbackstr });

			}

		}

	}

	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();

			String[] title = new String[] { "图书条形码", "图书名称", "读者条形码", "读者名称", "借阅时间", "应还时间", "是否归还" };
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
			if (!queryCheckBox.isSelected() && !dateCheckBox.isSelected()) {
				JOptionPane.showMessageDialog(null, "请选择查询方式!");
			}
			if (dateCheckBox.isSelected()) {
				if (fromTimeField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入开始日期");
					return;
				}

				if (toTimeField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入结束日期");
					return;
				}
//				if(CheckDate(myform.edate.value)){
//					alert("您输入的结束日期不正确（如：2011-02-14）\n 请注意闰年!");myform.edate.focus();return false;
//				}
			}
			if (queryCheckBox.isSelected() || dateCheckBox.isSelected()) {

				if (queryCheckBox.isSelected()) {
					if (comboBox != null) {
						if (comboBox.getSelectedItem().equals("图书条形码")) {
							str = "barcode" + " like '%" + queryField.getText().trim() + "%'";
							// str = request.getParameter("f") + " like '%" + request.getParameter("key") +
							// "%'";
						} else if (comboBox.getSelectedItem().equals("图书名称")) {
							str = "bookname" + " like '%" + queryField.getText().trim() + "%'";
						} else if (comboBox.getSelectedItem().equals("读者条形码")) {
							str = "readerbarcode" + " like '%" + queryField.getText().trim() + "%'";
						} else if (comboBox.getSelectedItem().equals("读者名称")) {
							str = "readername" + " like '%" + queryField.getText().trim() + "%'";
						}
					}
				}
				if (dateCheckBox.isSelected()) {
					String sdate = fromTimeField.getText().trim();
					String edate = toTimeField.getText().trim();
					if (sdate != null && edate != null) {
						str = "borrowTime between '" + sdate + "' and '" + edate + "'";
					}
					// System.out.println("日期" + str);
				}
				// 同时选择日期和条件进行查询
				if (dateCheckBox.isSelected() && queryCheckBox.isSelected()) {
					if (comboBox != null) {
						if (comboBox.getSelectedItem().equals("图书条形码")) {
							str = "barcode" + " like '%" + queryField.getText().trim() + "%";
						} else if (comboBox.getSelectedItem().equals("图书名称")) {
							str = "bookname" + " like '%" + queryField.getText().trim() + "%";
						} else if (comboBox.getSelectedItem().equals("读者条形码")) {
							str = "readerbarcode" + " like '%" + queryField.getText().trim() + "%";
						} else if (comboBox.getSelectedItem().equals("读者名称")) {
							str = "readername" + " like '%" + queryField.getText().trim() + "%";
						}
					}
					System.out.println("日期和条件");
					String sdate = fromTimeField.getText().trim();
					String edate = toTimeField.getText().trim();
					String str1 = null;
					if (sdate != null && edate != null) {
						str1 = "borrowTime between '" + sdate + "' and '" + edate + "'";
					}
					str = str + " and borr." + str1;
					System.out.println("条件和日期：" + str);

				}
			}
			coll = borrowDAO.borrowQuery(str);

			setData();
		}
	}
}

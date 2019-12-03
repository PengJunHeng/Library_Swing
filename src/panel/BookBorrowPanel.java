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
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bean.BookCaseForm;
import bean.BookForm;
import bean.BorrowForm;
import bean.ManagerForm;
import bean.ReaderForm;
import bean.ReaderTypeForm;
import dao.BookCaseDAO;
import dao.BookDAO;
import dao.BorrowDAO;
import dao.ManagerDAO;
import dao.ReaderDAO;
import dao.ReaderTypeDAO;
import frame.AddBookCaseFrame;
import frame.AddReaderTypeFrame;
import frame.BookCaseModifyFrame;
import frame.InsertManagerFrame;
import frame.ModifyManagerPermission;
import frame.ReaderTypeModifyFrame;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class BookBorrowPanel extends JPanel implements ActionListener {
	private JTable jTable;

	private JScrollPane scrollPane;
	private String[] title;
	private DefaultTableModel model;

	private List<ReaderTypeForm> list = new ArrayList<>();

	private ReaderTypeDAO readerTypeDAO = new ReaderTypeDAO();

	private JTextField barCodeField;
	private JTextField nameField;
	private JTextField sexField;
	private JTextField readerTypeField;
	private JTextField cardTypeField;
	private JTextField cardNumField;
	private JTextField numberField;
	private JTextField field;
	private JRadioButton barCodeRa;
	private JRadioButton bookNameRa;
	private JButton okButton;
	private JButton saveButton;
	private JButton finishButton;
	private ButtonGroup buttonGroup;
	private ReaderDAO readerDAO = new ReaderDAO();
	private BorrowDAO borrowDAO = new BorrowDAO();
	private ReaderForm readerForm = new ReaderForm();
	private Collection coll;
	private int borrowNumber = 0;
	private String str = null;
	private BookForm bookForm;
	private String manager;
	private BookDAO bookDAO=new BookDAO();
	private ReaderForm newReaderForm;

	/**
	 * Create the panel.
	 */
	public BookBorrowPanel() {
		setBorder(BorderFactory.createTitledBorder("图书借阅"));
		setBounds(0, 0, 1920, 970);
		setLayout(null);
		setVisible(true);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 144, 1910, 813);
		// setSize(1920,970);
		add(scrollPane);

		jTable = getJTable();
		title = new String[] { "图书名称", "借阅时间", "应还时间", "出版社", "书架", "定价(元)" };
		model = new DefaultTableModel(title, 0);
		setData();
		jTable.setModel(model);
		scrollPane.setViewportView(jTable);

		JLabel barCodeLabel = new JLabel("读者条形码：");
		barCodeLabel.setBounds(14, 36, 108, 18);
		add(barCodeLabel);

		JLabel name = new JLabel("姓名：");
		name.setBounds(654, 36, 108, 18);
		add(name);

		JLabel sex = new JLabel("性别：");
		sex.setBounds(1294, 36, 108, 18);
		add(sex);

		JLabel label_1 = new JLabel("读者类型：");
		label_1.setBounds(14, 67, 108, 18);
		add(label_1);

		JLabel readerType = new JLabel("添加的依据：");
		readerType.setBounds(654, 98, 108, 18);
		add(readerType);

		JLabel cardNum = new JLabel("可借数量：");
		cardNum.setBounds(14, 98, 108, 18);
		add(cardNum);

		JLabel number = new JLabel("证件号码：");
		number.setBounds(1294, 67, 108, 18);
		add(number);

		JLabel addCondition = new JLabel("证件类型：");
		addCondition.setBounds(654, 67, 108, 18);
		add(addCondition);

		barCodeField = new JTextField();
		barCodeField.setBounds(125, 33, 379, 24);
		barCodeField.setColumns(10);
		add(barCodeField);

		nameField = new JTextField();
		nameField.setBounds(765, 33, 515, 24);
		add(nameField);
		nameField.setColumns(10);

		sexField = new JTextField();
		sexField.setColumns(10);
		sexField.setBounds(1405, 33, 501, 24);
		add(sexField);

		readerTypeField = new JTextField();
		readerTypeField.setColumns(10);
		readerTypeField.setBounds(125, 64, 501, 24);
		add(readerTypeField);

		cardTypeField = new JTextField();
		cardTypeField.setColumns(10);
		cardTypeField.setBounds(765, 64, 515, 24);
		add(cardTypeField);

		cardNumField = new JTextField();
		cardNumField.setColumns(10);
		cardNumField.setBounds(1405, 64, 501, 24);
		add(cardNumField);

		numberField = new JTextField();
		numberField.setColumns(10);
		numberField.setBounds(125, 95, 501, 24);
		add(numberField);

		field = new JTextField();
		field.setColumns(10);
		field.setBounds(1294, 95, 358, 24);
		add(field);

		okButton = new JButton("确定");
		okButton.addActionListener(this);
		okButton.setBounds(518, 32, 108, 27);
		add(okButton);

		saveButton = new JButton("确定");
		saveButton.setBounds(1666, 94, 113, 27);
		add(saveButton);

		finishButton = new JButton("完成借阅");
		finishButton.addActionListener(this);
		finishButton.setBounds(1793, 94, 113, 27);
		add(finishButton);

		barCodeRa = new JRadioButton("图书条形码");
		barCodeRa.setBounds(819, 94, 157, 27);
		add(barCodeRa);

		bookNameRa = new JRadioButton("图书名称");
		bookNameRa.setBounds(1014, 94, 157, 27);
		add(bookNameRa);

		buttonGroup = new ButtonGroup();
		buttonGroup.add(barCodeRa);
		buttonGroup.add(bookNameRa);

	}

	public BookBorrowPanel(String sessionName) {
		// TODO Auto-generated constructor stub
		this();
		manager = sessionName;
	}

	public void setData() {
		model.setRowCount(0);

		// coll = borrowDAO.borrowinfo(null);

		if (coll == null || coll.isEmpty()) {
		} else {
			// 通过迭代方式显示数据
			String bookname = "";
			String borrowTime = "";
			String backTime = "";
			Float price = new Float(0);
			String pubname = "";
			String bookcase = "";
			if (coll != null && !coll.isEmpty()) {
				borrowNumber = coll.size();
				Iterator it = coll.iterator();
				while (it.hasNext()) {
					BorrowForm borrowForm = (BorrowForm) it.next();
					bookname = borrowForm.getBookName();
					borrowTime = borrowForm.getBorrowTime();
					backTime = borrowForm.getBackTime();
					price = borrowForm.getPrice();
					pubname = borrowForm.getPubName();
					bookcase = borrowForm.getBookcaseName();

					model.addRow(new Object[] { bookname, borrowTime, backTime, pubname, bookcase, price });

				}
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
		if (e.getSource() == okButton) {
			readerForm.setBarcode(barCodeField.getText().trim());
			newReaderForm = readerDAO.queryM(readerForm);
			int ID = 0;
			String name = "";
			String sex = "";
			String barcode = "";
			String birthday = "";
			String paperType = "";
			String paperNO = "";
			int number = 0;
			String typename = "";
			if (readerForm != null) {
				ID = newReaderForm.getId().intValue();
				name = newReaderForm.getName();
				sex = newReaderForm.getSex();
				barcode = newReaderForm.getBarcode();
				birthday = newReaderForm.getBirthday();
				paperType = newReaderForm.getPaperType();
				paperNO = newReaderForm.getPaperNO();
				number = newReaderForm.getNumber();
				typename = newReaderForm.getTypename();
			}
			nameField.setText(name);
			sexField.setText(sex);
			readerTypeField.setText(typename);
			cardTypeField.setText(paperType);
			cardNumField.setText(paperNO);
			numberField.setText("" + number);
			coll = borrowDAO.borrowinfo(barCodeField.getText().trim());
			setData();
		}
		if (e.getSource() == saveButton) {

			if (field.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请输入查询关键字!");

				return;
			}

			if (Integer.parseInt(numberField.getText().trim()) - borrowNumber <= 0) {
				JOptionPane.showMessageDialog(null, "您不能再借阅其他图书了!");

				return;
			}

		}
		if (barCodeField.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "请输入读者条形码!");

			return;
		}

		String f = null; // 获取查询条件
		if (barCodeRa.isSelected()) {
			f = "barcode";
		} else if (bookNameRa.isSelected()) {
			f = "bookname";
		}
		String key = field.getText().trim(); // 获取输入的关键字
		if (key != null && !key.equals("")) { // 判断是否有符合条件的图书
			String operator = manager; //
			BookForm bookForm = bookDAO.queryB(f, key); // 根据查询条件获取图书信息
			if (bookForm != null) {
				int ret = borrowDAO.insertBorrow(newReaderForm, bookDAO.queryB(f, key), operator); // 保存图书借阅信息
				if (ret == 1) {
					// request.setAttribute("bar", request.getParameter("barcode"));
					// request.getRequestDispatcher("bookBorrow_ok.jsp").forward(request, response);

				} else {
					JOptionPane.showMessageDialog(null, "添加借阅信息失败!");

				}
			} else {
				JOptionPane.showMessageDialog(null, "没有该图书!");

			}
		} 

		if (e.getSource() == finishButton) {
			repaint();
		}
	}

}

package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import bean.BookCaseForm;
import bean.BookForm;
import bean.BookTypeForm;
import bean.LibraryForm;
import bean.PublishingForm;
import bean.ReaderForm;
import bean.ReaderTypeForm;
import dao.BookCaseDAO;
import dao.BookDAO;
import dao.BookTypeDAO;
import dao.LibraryDAO;
import dao.PublishingDAO;
import dao.ReaderDAO;
import dao.ReaderTypeDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class AddBookInfoFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel nameLabel;
	private JLabel sexLabel;
	private JLabel barCodeLabel;
	private JLabel readerTypeLabel;
	private JLabel birthLabel;
	private JLabel cardLabel;
	private JLabel numLabel;
	private JLabel dateLabel;
	private JTextField barCodeField;
	private JTextField authorField;
	private JTextField transerField;
	private JButton exitButton;
	private JButton saveButton;
	private LibraryForm libraryForm;
	private LibraryDAO libraryDAO = new LibraryDAO();
	private JTextField priceField;
	private JTextField pageField;
	private ReaderForm readerForm;
	private String manager;
	private ReaderDAO readerDAO = new ReaderDAO();
	private JComboBox bookTypeCombo;
	private JComboBox publicCombo;
	String str = null;
	ReaderTypeDAO readerTypeDAO = new ReaderTypeDAO();
	Collection coll = (Collection) readerTypeDAO.query(str);
	String[] value2 = {};
	private String[] value = {};
	private JTextField bookName;

	BookTypeDAO bookTypeDAO = new BookTypeDAO();
	Collection coll_type = (Collection) bookTypeDAO.query(str);
	private JComboBox bookCaseCombo;
	int ID ,TypeId;
	String isbn;
	int bookcaseID;
	BookDAO bookDAO=new BookDAO();
//	String[] value=new String[2];
	/**
	 * Create the frame.
	 */
	public AddBookInfoFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 425);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nameLabel = new JLabel("条形码：");
		nameLabel.setBounds(14, 13, 72, 18);
		contentPane.add(nameLabel);

		sexLabel = new JLabel("图书名称：");
		sexLabel.setBounds(14, 44, 72, 18);
		contentPane.add(sexLabel);

		barCodeLabel = new JLabel("图书类型：");
		barCodeLabel.setBounds(14, 75, 72, 18);
		contentPane.add(barCodeLabel);

		readerTypeLabel = new JLabel("作者：");
		readerTypeLabel.setBounds(14, 106, 72, 18);
		contentPane.add(readerTypeLabel);

		birthLabel = new JLabel("译者：");
		birthLabel.setBounds(14, 137, 72, 18);
		contentPane.add(birthLabel);

		cardLabel = new JLabel("出版社：");
		cardLabel.setBounds(14, 168, 72, 18);
		contentPane.add(cardLabel);

		numLabel = new JLabel("价格：");
		numLabel.setBounds(14, 199, 72, 18);
		contentPane.add(numLabel);

		dateLabel = new JLabel("页码：");
		dateLabel.setBounds(14, 230, 72, 18);
		contentPane.add(dateLabel);

		JLabel emailLabel = new JLabel("书架：");
		emailLabel.setBounds(14, 261, 72, 18);
		contentPane.add(emailLabel);

		barCodeField = new JTextField();
		barCodeField.setBounds(85, 10, 293, 24);
		contentPane.add(barCodeField);
		barCodeField.setColumns(10);

		authorField = new JTextField();
		authorField.setColumns(10);
		authorField.setBounds(85, 103, 293, 24);
		contentPane.add(authorField);

		transerField = new JTextField();
		transerField.setColumns(10);
		transerField.setBounds(85, 134, 293, 24);
		contentPane.add(transerField);

		priceField = new JTextField();
		priceField.setColumns(10);
		priceField.setBounds(85, 196, 293, 24);
		contentPane.add(priceField);

		pageField = new JTextField();
		pageField.setColumns(10);
		pageField.setBounds(85, 227, 293, 24);
		contentPane.add(pageField);

		saveButton = new JButton("提交");
		saveButton.setBounds(85, 305, 113, 27);
		saveButton.addActionListener(this);

		bookTypeCombo = new JComboBox(value);
		bookTypeCombo.setBounds(85, 72, 293, 24);
		contentPane.add(bookTypeCombo);

		publicCombo = new JComboBox(value2);
		publicCombo.setBounds(85, 165, 293, 24);
		contentPane.add(publicCombo);

		bookName = new JTextField();
		bookName.setColumns(10);
		bookName.setBounds(85, 41, 293, 24);
		contentPane.add(bookName);

		bookCaseCombo = new JComboBox();
		bookCaseCombo.setBounds(85, 258, 293, 24);
		contentPane.add(bookCaseCombo);
		contentPane.add(saveButton);

		exitButton = new JButton("退出");
		exitButton.setBounds(265, 305, 113, 27);
		exitButton.addActionListener(this);
		contentPane.add(exitButton);

		ButtonGroup buttonGroup = new ButtonGroup();

	}

	public AddBookInfoFrame(String manager) {
		// TODO Auto-generated constructor stub
		this();
		if (coll_type == null || coll_type.isEmpty()) {
			// out.println("<script>alert('请先录入图书类型信息!');history.back(-1);</script>");
			JOptionPane.showMessageDialog(null, "请先录入图书类型信息!");
			return;
		} else {
			Iterator it_type = coll_type.iterator();
			int typeID = 0;
			String typename = "";
			BookCaseDAO bookcaseDAO = new BookCaseDAO();
			String str1 = null;
			Collection coll_bookcase = (Collection) bookcaseDAO.query(str1);
			if (coll_bookcase == null || coll_bookcase.isEmpty()) {
				// out.println("<script>alert('请先录入书架信息!');history.back(-1);</script>");
				JOptionPane.showMessageDialog(null, "请先录入书架信息!");
				return;
			} else {
				Iterator it_bookcase = coll_bookcase.iterator();
				int bookcaseID = 0;
				String bookcasename = "";
				PublishingDAO pubDAO = new PublishingDAO();
				String str2 = null;
				Collection coll_pub = (Collection) pubDAO.query(str2);
				if (coll_pub == null || coll_pub.isEmpty()) {
					// out.println("<script>alert('请先录入出版社信息!');history.back(-1);</script>");
					JOptionPane.showMessageDialog(null, "请先录入出版社信息!");
					return;
				} else {
					Iterator it_pub = coll_pub.iterator();
					String isbn = "";
					String pubname = "";
					
					while (it_type.hasNext()) {
						BookTypeForm bookTypeForm = (BookTypeForm) it_type.next();
						typeID = bookTypeForm.getId().intValue();
						typename = bookTypeForm.getTypeName();
						bookTypeCombo.addItem(typename);
					}
					while (it_pub.hasNext()) {
						PublishingForm pubForm = (PublishingForm) it_pub.next();
						isbn = pubForm.getIsbn();
						pubname = pubForm.getPubname();
						publicCombo.addItem(pubname);
					}
					while (it_bookcase.hasNext()) {
						BookCaseForm bookCaseForm = (BookCaseForm) it_bookcase.next();
						bookcaseID = bookCaseForm.getId().intValue();
						bookcasename = bookCaseForm.getName();
						bookCaseCombo.addItem(bookcasename);
					}
				}
			}
		}
		this.manager = manager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == saveButton) {
			BookForm bookForm = new BookForm(); // 实例化BookForm类
			//bookForm.setId(ID);
			bookForm.setBarcode(barCodeField.getText()); // 获取并设置条形码属性
			bookForm.setBookName(bookName.getText());
			bookForm.setTypeId(TypeId);
			bookForm.setAuthor(authorField.getText());
			bookForm.setTranslator(transerField.getText());
			bookForm.setIsbn(isbn);
			bookForm.setPrice(Float.valueOf(priceField.getText()));
			bookForm.setPage(pageField.equals("")?0:Integer.parseInt(pageField.getText()));
			bookForm.setBookcaseid(bookcaseID);
			bookForm.setOperator(manager);
			//BookForm bookForm = new BookForm();
			//bookForm.setBarcode(request.getParameter("barcode"));
		//	bookForm.setBookName(request.getParameter("bookName"));
			//bookForm.setTypeId(Integer.parseInt(request.getParameter("typeId")));
			//bookForm.setAuthor(request.getParameter("author"));
			//bookForm.setTranslator(request.getParameter("translator"));
		//	bookForm.setIsbn(request.getParameter("isbn"));
		//	bookForm.setPrice(Float.valueOf(request.getParameter("price")));
		//	bookForm.setPage(request.getParameter("page")==""?0:Integer.parseInt(request.getParameter("page")));
			//bookForm.setBookcaseid(Integer.parseInt(request
					//.getParameter("bookcaseid")));
			// 获取系统日期
			Date date1 = new Date();
			java.sql.Date date = new java.sql.Date(date1.getTime());
			bookForm.setInTime(date.toString());
		//	bookForm.setOperator(request.getParameter("operator"));
			int a = bookDAO.insert(bookForm);
			if (a == 1) {
				JOptionPane.showMessageDialog(null, "图书信息添加成功");
			//	request.getRequestDispatcher("book_ok.jsp?para=1").forward(request, response);
			} else if (a == 2) {
				JOptionPane.showMessageDialog(null,"该图书信息已经添加！");
				
//				request.setAttribute("error", "该图书信息已经添加！");
//				request.getRequestDispatcher("error.jsp")
//						.forward(request, response);
			} else {
				JOptionPane.showMessageDialog(null, "图书信息添加失败！");
//				request.setAttribute("error", "图书信息添加失败！");
//				request.getRequestDispatcher("error.jsp")
//						.forward(request, response);
			}
		} else if (e.getSource() == exitButton) {
			dispose();
		}
	}
}

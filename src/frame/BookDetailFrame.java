package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.BookForm;
import bean.LibraryForm;
import dao.BookDAO;
import dao.LibraryDAO;
import panel.LibraryInfoQueryPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class BookDetailFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel barCodeLabel;
	private JLabel bookNameLabel;
	private JLabel bookTypeLabel;
	private JLabel authorLabel;
	private JLabel translaterLabel;
	private JLabel publicLabel;
	private JLabel countLabel;
	private JLabel pageNumLabel;
	private JButton exitButton;
	private LibraryForm libraryForm;
	private LibraryDAO libraryDAO = new LibraryDAO();
	private JLabel bookCaseLabel;
	private JLabel timeLabel;
	private JLabel operatorLabel;
	private BookForm bookForm;
	private BookDAO bookDAO=new BookDAO();

	/**
	 * Create the frame.
	 */
	public BookDetailFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 417, 482);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		barCodeLabel = new JLabel("");
		barCodeLabel.setBounds(14, 13, 371, 18);
		contentPane.add(barCodeLabel);

		bookNameLabel = new JLabel("");
		bookNameLabel.setBounds(14, 44, 371, 18);
		contentPane.add(bookNameLabel);

		bookTypeLabel = new JLabel("");
		bookTypeLabel.setBounds(14, 75, 371, 18);
		contentPane.add(bookTypeLabel);

		authorLabel = new JLabel("");
		authorLabel.setBounds(14, 106, 371, 18);
		contentPane.add(authorLabel);

		translaterLabel = new JLabel("");
		translaterLabel.setBounds(14, 137, 371, 18);
		contentPane.add(translaterLabel);

		publicLabel = new JLabel("");
		publicLabel.setBounds(14, 168, 371, 18);
		contentPane.add(publicLabel);

		countLabel = new JLabel("");
		countLabel.setBounds(14, 199, 371, 18);
		contentPane.add(countLabel);

		pageNumLabel = new JLabel("");
		pageNumLabel.setBounds(14, 230, 371, 18);
		contentPane.add(pageNumLabel);

		exitButton = new JButton("返回");
		exitButton.setBounds(132, 355, 113, 27);
		exitButton.addActionListener(this);
		contentPane.add(exitButton);

		bookCaseLabel = new JLabel("");
		bookCaseLabel.setBounds(14, 261, 371, 18);
		contentPane.add(bookCaseLabel);

		timeLabel = new JLabel("");
		timeLabel.setBounds(14, 292, 371, 18);
		contentPane.add(timeLabel);

		operatorLabel = new JLabel("");
		operatorLabel.setBounds(14, 323, 371, 18);
		contentPane.add(operatorLabel);
	}

	public BookDetailFrame(BookForm bookForm) {
		this();
		this.bookForm = bookDAO.queryM(bookForm);
		barCodeLabel.setText("条形码："+this.bookForm.getBarcode());
		bookNameLabel.setText("图书名称："+this.bookForm.getBookName());
		bookTypeLabel.setText("图书类型："+this.bookForm.getTypeName());;
		authorLabel.setText("作者："+this.bookForm.getAuthor());
		translaterLabel.setText("译者："+this.bookForm.getTranslator());
		publicLabel.setText("出版社："+this.bookForm.getPublishing());
		countLabel.setText("价格："+this.bookForm.getPrice());
		pageNumLabel.setText("页码："+this.bookForm.getPage());
		bookCaseLabel.setText("书架："+this.bookForm.getBookcaseName());
		timeLabel.setText("入库时间："+this.bookForm.getInTime());
		operatorLabel.setText("操作员："+this.bookForm.getOperator());
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==exitButton) {
			dispose();
			LibraryInfoQueryPanel infoQueryPanel = new LibraryInfoQueryPanel();
		}
		
	}

}

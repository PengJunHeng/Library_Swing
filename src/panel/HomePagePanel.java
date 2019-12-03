package panel;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bean.BorrowForm;
import dao.BorrowDAO;

public class HomePagePanel extends JPanel {
	private JTable jTable;
	private JTable table;
	private JScrollPane scrollPane;
	private String[] title;
	private DefaultTableModel model;
	BorrowDAO borrowDAO = new BorrowDAO();
	Collection coll_book = (Collection) borrowDAO.bookBorrowSort();

	/**
	 * Create the panel.
	 */
	public HomePagePanel() {

		setBorder(BorderFactory.createTitledBorder("首页"));
		setBounds(0, 0, 1920, 970);
		setVisible(true);
		setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = getJTable();
		title = new String[] { "排名", "图书条形码", "图书名称", "图书类型", "书架", "出版社", "作者", "定价(元)", "借阅次数" };
		model = new DefaultTableModel(title, 0);
		setData();
		table.setModel(model);
		scrollPane.setViewportView(table);

	}

	public void setData() {
		model.setRowCount(0);
		if (coll_book != null && !coll_book.isEmpty()) {
			Iterator it_book = coll_book.iterator();
			int i = 1;
			int degree = 0;
			String bookname = "";
			String typename = "";
			String barcode_book = "";
			String bookcase = "";
			String pub = "";
			String author = "";
			String translator = "";
			Float price = new Float(0);
			while (it_book.hasNext() && i < 6) {
				BorrowForm borrowForm = (BorrowForm) it_book.next();
				bookname = borrowForm.getBookName();
				barcode_book = borrowForm.getBookBarcode();
				typename = borrowForm.getBookType();
				degree = borrowForm.getDegree();
				bookcase = borrowForm.getBookcaseName();
				pub = borrowForm.getPubName();
				author = borrowForm.getAuthor();
				price = borrowForm.getPrice();

				model.addRow(
						new Object[] { i, barcode_book, bookname, typename, bookcase, pub, author, price, degree });
				i++;
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

	
}

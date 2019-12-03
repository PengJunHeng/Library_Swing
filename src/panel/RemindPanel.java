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

public class RemindPanel extends JPanel {
	private JTable jTable;
	private JTable table;
	private JScrollPane scrollPane;
	private String[] title;
	private DefaultTableModel model;
	BorrowDAO borrowDAO = new BorrowDAO();
	// Collection coll_book = (Collection) borrowDAO.bookBorrowSort();
	Collection coll = (Collection)borrowDAO.bremind();

	/**
	 * Create the panel.
	 */
	public RemindPanel() {

		setBorder(BorderFactory.createTitledBorder("首页"));
		setBounds(0, 0, 1920, 970);
		setVisible(true);
		setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = getJTable();
		title = new String[] { "图书条形码", "图书名称", "读者条形码", "读者名称", "借阅时间", "应还时间" };
		model = new DefaultTableModel(title, 0);
		setData();
		table.setModel(model);
		scrollPane.setViewportView(table);

	}

	public void setData() {
		model.setRowCount(0);

		if (coll != null || !coll.isEmpty()) {
			Iterator it = coll.iterator();
			String bookname = "";
			String bookbarcode = "";
			String readerbar = "";
			String readername = "";
			String borrowTime = "";
			String backTime = "";
			while (it.hasNext()) {
				BorrowForm borrowForm = (BorrowForm) it.next();
				bookname = borrowForm.getBookName();
				bookbarcode = borrowForm.getBookBarcode();
				readerbar = borrowForm.getReaderBarcode();
				readername = borrowForm.getReaderName();
				borrowTime = borrowForm.getBorrowTime();
				backTime = borrowForm.getBackTime();
				model.addRow(new Object[] { bookbarcode, bookname, readerbar, readername, borrowTime, backTime });

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

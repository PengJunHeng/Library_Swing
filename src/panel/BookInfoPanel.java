package panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import bean.BookForm;
import bean.BorrowForm;
import bean.ManagerForm;
import bean.ReaderForm;
import bean.ReaderTypeForm;
import dao.BookCaseDAO;
import dao.BookDAO;
import dao.ManagerDAO;
import dao.ReaderDAO;
import dao.ReaderTypeDAO;
import frame.AddBookCaseFrame;
import frame.AddBookInfoFrame;
import frame.AddReaderTypeFrame;
import frame.BookCaseModifyFrame;
import frame.BookDetailFrame;
import frame.InsertManagerFrame;
import frame.ModifyBookInfoFrame;
import frame.ModifyManagerPermission;
import frame.ModifyReaderFrame;
import frame.AddReaderFrame;
import frame.ReaderInfoFrame;
import frame.ReaderTypeModifyFrame;
import util.ChStr;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class BookInfoPanel extends JPanel implements ActionListener {
	private JTable jTable;

	private JScrollPane scrollPane;
	private String[] title;
	private DefaultTableModel model;

	private JButton addButton;
	private JButton changeButton;

	private String str = null;
	private String flag = "mr";
	private List<BookForm> list = new ArrayList<>();
	private JButton deleteButton;
	private BookCaseDAO bookCaseDAO = new BookCaseDAO();

	private ReaderTypeDAO readerTypeDAO = new ReaderTypeDAO();
	private ReaderTypeForm readerTypeForm;
	private ReaderDAO readerDAO = new ReaderDAO();
	private ReaderForm readerForm;
	private BookForm bookForm;
	int selectRow;
	private String manager;

	private BookDAO bookDAO = new BookDAO();

	Collection<BookForm> coll = bookDAO.query(str);
	ChStr chStr = new ChStr();

	/**
	 * Create the panel.
	 */
	public BookInfoPanel() {
		setBorder(BorderFactory.createTitledBorder("管理员设置"));
		setBounds(0, 0, 1920, 970);
		setLayout(null);
		setVisible(true);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 20, 1770, 945);
		// setSize(1920,970);
		add(scrollPane);

		jTable = getJTable();
		title = new String[] { "条形码", "图书名称", "图书类型", "出版社", "书架" };
		model = new DefaultTableModel(title, 0);
		setData();
		jTable.setModel(model);
		scrollPane.setViewportView(jTable);

		addButton = new JButton("添加读者信息");
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

	public BookInfoPanel(String sessionName) {
		// TODO Auto-generated constructor stub
		this();
		manager = sessionName;
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
				BookForm bookForm = (BookForm) it.next();
				list.add(bookForm);
				ID = bookForm.getId().intValue();
				bookname = bookForm.getBookName();
				barcode = chStr.nullToString(bookForm.getBarcode(), "&nbsp;");
				typename = bookForm.getTypeName();
				publishing = bookForm.getPublishing();
				bookcase = chStr.nullToString(bookForm.getBookcaseName(), "&nbsp;");

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
					selectRow = jTable.getSelectedRow();// 改变的单元格所在的行索引，起始值为0
					int selectColumn = jTable.getSelectedColumn();
					// bookForm = list.get(selectRow);
					if (selectColumn == 1) {
						System.out.println(selectRow);
						bookForm = list.get(selectRow);

						// System.out.println(readerForm.getId());
						if (bookForm != null) {

							BookForm newbookForm = new BookForm();
							newbookForm.setId(bookForm.getId());
							BookDetailFrame bookDetailFrame = new BookDetailFrame(newbookForm);

						} else {
							repaint();

						}
					}

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
		// TODO Auto-generated method stub
		if (e.getSource() == changeButton) {

			int selectRow = jTable.getSelectedRow();
			// System.out.println("==================================="+selectRow);
			if (selectRow < 0) {
				JOptionPane.showMessageDialog(null, "请先点击你要修改的行数！");
			} else {
				BookForm bookForm = new BookForm();
				// System.out.println("查询修改图书信息：" + request.getParameter("ID"));
				bookForm = list.get(selectRow);
				BookForm newBookForm=bookDAO.queryM(bookForm);
				ModifyBookInfoFrame bookInfoFrame=new ModifyBookInfoFrame( manager,newBookForm);
			//	request.setAttribute("bookQueryif", );
			//	request.getRequestDispatcher("book_Modify.jsp").forward(request, response);
				// readerForm = list.get(selectRow);
				// ReaderForm newReaderForm = readerDAO.queryM(readerForm);
				// ModifyReaderFrame frame = new ModifyReaderFrame(manager, newReaderForm);
			}

		}
		if (e.getSource() == deleteButton) {

			int selectRow = jTable.getSelectedRow();
			// System.out.println("selectRow==================" + selectRow);
			if (selectRow < 0) {
				JOptionPane.showMessageDialog(null, "请先点击你要删除的行数！");
			} else {

				// readerForm = list.get(selectRow);

				try {

					int i = JOptionPane.showConfirmDialog(null, "确定要删除？");
					if (i == 0) {
						
						bookForm=list.get(selectRow);
						int ret = bookDAO.delete(bookForm);
						if (ret == 0) {
							JOptionPane.showMessageDialog(null, "删除图书信息失败！");
						
						} else {
							JOptionPane.showMessageDialog(null, "删除图书信息成功！");
							
						}
						

					}
				} catch (HeadlessException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();

				}

			}
		}
		if (e.getSource() == addButton) {
			AddBookInfoFrame addBookInfoFrame = new AddBookInfoFrame(manager);
		}

	}
}

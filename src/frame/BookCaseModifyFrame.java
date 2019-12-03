package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.BookCaseForm;
import dao.BookCaseDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookCaseModifyFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField bookCaseNameField;
	private JButton saveButton;
	private JButton resetButton;
	private JButton exitButton;
	private BookCaseForm bookCaseForm;
	private BookCaseDAO bookCaseDAO=new BookCaseDAO();
	
	/**
	 * Create the frame.
	 */
	public BookCaseModifyFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 348, 190);
		setLocationRelativeTo(null);
		setTitle("书架修改");
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("书架名称");
		lblNewLabel.setBounds(14, 13, 72, 18);
		contentPane.add(lblNewLabel);

		bookCaseNameField = new JTextField();
		bookCaseNameField.setBounds(112, 10, 139, 24);
		contentPane.add(bookCaseNameField);
		bookCaseNameField.setColumns(10);

		saveButton = new JButton("保存");
		saveButton.setBounds(14, 59, 70, 27);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);

		resetButton = new JButton("重置");

		resetButton.setBounds(98, 59, 70, 27);
		resetButton.addActionListener(this);
		contentPane.add(resetButton);

		exitButton = new JButton("退出");
		exitButton.setBounds(182, 59, 70, 27);
		exitButton.addActionListener(this);
		contentPane.add(exitButton);
	}

	public BookCaseModifyFrame(BookCaseForm newBookCaseForm) {
		// TODO Auto-generated constructor stub
		this();
		this.bookCaseForm = newBookCaseForm;
		if (bookCaseForm != null) {

			bookCaseNameField.setText(this.bookCaseForm.getName());
			
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==saveButton) {
			BookCaseForm bookCaseForm=new BookCaseForm();
	    	 bookCaseForm.setId(this.bookCaseForm.getId());
	         bookCaseForm.setName(bookCaseNameField.getText());
	         int ret=bookCaseDAO.update(bookCaseForm);
	         if(ret==0){
	        	 JOptionPane.showMessageDialog(null, "修改书架信息失败！");
	            
	         }else{
	        	 JOptionPane.showMessageDialog(null, "修改书架信息成功！");
		            
	         }
		} else if (e.getSource()==resetButton) {
			bookCaseNameField.setText("");
		}else if (e.getSource()==exitButton) {
			dispose();
		}
	}

}

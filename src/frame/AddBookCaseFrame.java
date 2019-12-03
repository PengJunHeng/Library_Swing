package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.BookCaseForm;
import dao.BookCaseDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddBookCaseFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField nameField;
	private JLabel bookCaseNameLabel;
	private JButton saveButton;
	private JButton exitButton;
	private BookCaseDAO bookCaseDAO=new BookCaseDAO();
	

	/**
	 * Create the frame.
	 */
	public AddBookCaseFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 197);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		bookCaseNameLabel = new JLabel("书架名称：");
		bookCaseNameLabel.setBounds(14, 13, 77, 18);
		contentPane.add(bookCaseNameLabel);
		
		nameField = new JTextField();
		nameField.setBounds(93, 10, 163, 24);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		saveButton = new JButton("保存");
		saveButton.setBounds(14, 70, 113, 27);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);
		
		exitButton = new JButton("关闭");
		exitButton.setBounds(143, 70, 113, 27);
		exitButton.addActionListener(this);
		contentPane.add(exitButton);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==saveButton) {
			BookCaseForm bookCaseForm=new BookCaseForm();
	        bookCaseForm.setName(nameField.getText());
	        int a=bookCaseDAO.insert(bookCaseForm);
	        if(a==0){
	           JOptionPane.showMessageDialog(null, "书架信息添加失败！");
	      }else if(a==2){
	         
	          JOptionPane.showMessageDialog(null, "该书架信息已经添加！");
	        
	      }else{
	    	  JOptionPane.showMessageDialog(null, "书架信息添加成功!");
	    	 
	     }
		}else if (e.getSource()==exitButton) {
			dispose();
		}
	}
}

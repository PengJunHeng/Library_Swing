package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import bean.BookCaseForm;
import bean.ReaderTypeForm;
import dao.BookCaseDAO;
import dao.ReaderTypeDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddReaderTypeFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField nameField;
	private JLabel typeNameLabel;
	private JButton saveButton;
	private JButton exitButton;
	private BookCaseDAO bookCaseDAO=new BookCaseDAO();
	private JTextField numberField;
	private ReaderTypeDAO readerTypeDAO=new ReaderTypeDAO();

	/**
	 * Create the frame.
	 */
	public AddReaderTypeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 197);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		typeNameLabel = new JLabel("类型名称：");
		typeNameLabel.setBounds(14, 13, 77, 18);
		contentPane.add(typeNameLabel);
		
		nameField = new JTextField();
		nameField.setBounds(93, 10, 163, 24);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		saveButton = new JButton("保存");
		saveButton.setBounds(14, 78, 113, 27);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);
		
		exitButton = new JButton("关闭");
		exitButton.setBounds(143, 78, 113, 27);
		exitButton.addActionListener(this);
		contentPane.add(exitButton);
		
		JLabel numberLabel = new JLabel("可借数量：");
		numberLabel.setBounds(14, 44, 77, 18);
		contentPane.add(numberLabel);
		
		numberField = new JTextField();
		numberField.setColumns(10);
		numberField.setBounds(93, 41, 163, 24);
		contentPane.add(numberField);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==saveButton) {
			
			if("".equals(nameField.getText())) {
				JOptionPane.showMessageDialog(null, "请输入类型名称！");
				return;
			}
			if("".equals(numberField.getText()))
			{
				JOptionPane.showMessageDialog(null, "请输入可借数量！");
				return;
			}
			
			ReaderTypeForm readerTypeForm = new ReaderTypeForm();
			
			readerTypeForm.setName(nameField.getText());
			readerTypeForm.setNumber(Integer.parseInt(numberField.getText()));
			int a = readerTypeDAO.insert(readerTypeForm);
			if (a == 0) {
				JOptionPane.showMessageDialog(null, "读者类型信息添加失败！");
				
			} else if (a == 2) {
				JOptionPane.showMessageDialog(null, "该读者类型信息已经添加！");
				
			} else {
				JOptionPane.showMessageDialog(null, "读者类型信息添加成功！");
			}
		}else if (e.getSource()==exitButton) {
			dispose();
		}
	}
}

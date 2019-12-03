package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.LibraryForm;
import bean.ReaderForm;
import dao.LibraryDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class ReaderInfoFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel nameLabel;
	private JLabel sexLabel;
	private JLabel barCodeLabel;
	private JLabel readerTypeLabel;
	private JLabel birthLabel;
	private JLabel cardLabel;
	private JLabel numLabel;
	private JLabel dateLabel;
	private JTextField nameField;
	private JTextField sexField;
	private JTextField barCodeField;
	private JTextField readerTypeField;
	private JTextField jobField;
	private JTextField birthField;
	private JTextField cardField;
	private JButton exitButton;
	private JButton saveButton;
	private LibraryForm libraryForm;
	private LibraryDAO libraryDAO = new LibraryDAO();
	private JTextField numField;
	private JTextField dateField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextArea remarkTextArea;
	private ReaderForm readerForm;
	private JTextField textField;
	private JLabel remark;

	/**
	 * Create the frame.
	 */
	public ReaderInfoFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 595);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nameLabel = new JLabel("姓名：");
		nameLabel.setBounds(14, 13, 72, 18);
		contentPane.add(nameLabel);

		sexLabel = new JLabel("性别：");
		sexLabel.setBounds(14, 44, 72, 18);
		contentPane.add(sexLabel);

		barCodeLabel = new JLabel("条形码：");
		barCodeLabel.setBounds(14, 75, 72, 18);
		contentPane.add(barCodeLabel);

		readerTypeLabel = new JLabel("读者类型：");
		readerTypeLabel.setBounds(14, 106, 72, 18);
		contentPane.add(readerTypeLabel);

		birthLabel = new JLabel("职业：");
		birthLabel.setBounds(14, 137, 72, 18);
		contentPane.add(birthLabel);

		cardLabel = new JLabel("出生日期：");
		cardLabel.setBounds(14, 168, 72, 18);
		contentPane.add(cardLabel);

		numLabel = new JLabel("有效证件：");
		numLabel.setBounds(14, 199, 72, 18);
		contentPane.add(numLabel);

		dateLabel = new JLabel("证件号码：");
		dateLabel.setBounds(14, 230, 72, 18);
		contentPane.add(dateLabel);

		JLabel phoneLabel = new JLabel("登陆日期：");
		phoneLabel.setBounds(14, 261, 72, 18);
		contentPane.add(phoneLabel);

		JLabel emailLabel = new JLabel("电话：");
		emailLabel.setBounds(14, 292, 72, 18);
		contentPane.add(emailLabel);

		JLabel operatorLabel = new JLabel("Email地址：");
		operatorLabel.setBounds(14, 323, 72, 18);
		contentPane.add(operatorLabel);

		JLabel remarkLabel = new JLabel("操作员：");
		remarkLabel.setBounds(14, 354, 72, 18);
		contentPane.add(remarkLabel);

		nameField = new JTextField();
		nameField.setBounds(85, 10, 293, 24);
		contentPane.add(nameField);
		nameField.setColumns(10);

		sexField = new JTextField();
		sexField.setColumns(10);
		sexField.setBounds(85, 41, 293, 24);
		contentPane.add(sexField);

		barCodeField = new JTextField();
		barCodeField.setColumns(10);
		barCodeField.setBounds(85, 72, 293, 24);
		contentPane.add(barCodeField);

		readerTypeField = new JTextField();
		readerTypeField.setColumns(10);
		readerTypeField.setBounds(85, 103, 293, 24);
		contentPane.add(readerTypeField);

		jobField = new JTextField();
		jobField.setColumns(10);
		jobField.setBounds(85, 134, 293, 24);
		contentPane.add(jobField);

		birthField = new JTextField();
		birthField.setColumns(10);
		birthField.setBounds(85, 165, 293, 24);
		contentPane.add(birthField);

		cardField = new JTextField();
		cardField.setColumns(10);
		cardField.setBounds(85, 196, 293, 24);
		contentPane.add(cardField);

		numField = new JTextField();
		numField.setColumns(10);
		numField.setBounds(85, 227, 293, 24);
		contentPane.add(numField);

		dateField = new JTextField();
		dateField.setColumns(10);
		dateField.setBounds(85, 258, 293, 24);
		contentPane.add(dateField);

		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(85, 289, 293, 24);
		contentPane.add(phoneField);

		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(85, 320, 293, 24);
		contentPane.add(emailField);

		remarkTextArea = new JTextArea();
		remarkTextArea.setBounds(85, 385, 293, 96);
		contentPane.add(remarkTextArea);

		saveButton = new JButton("提交");
		saveButton.setBounds(85, 508, 113, 27);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);

		exitButton = new JButton("退出");
		exitButton.setBounds(265, 508, 113, 27);
		exitButton.addActionListener(this);
		contentPane.add(exitButton);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(85, 354, 293, 24);
		contentPane.add(textField);
		
		remark = new JLabel("备注：");
		remark.setBounds(14, 385, 72, 18);
		contentPane.add(remark);
	}

	public ReaderInfoFrame(String manager, ReaderForm newReaderForm) {

		this();
		nameField.setText(newReaderForm.getName());
		sexField.setText(newReaderForm.getSex());
		barCodeField.setText(newReaderForm.getBarcode());
		readerTypeField.setText(newReaderForm.getTypename());
		jobField.setText(newReaderForm.getVocation());
		birthField.setText(newReaderForm.getBirthday());
		cardField.setText(newReaderForm.getPaperType());
		numField.setText(newReaderForm.getPaperNO());
		dateField.setText(newReaderForm.getCreateDate());
		phoneField.setText(newReaderForm.getTel());
		emailField.setText(newReaderForm.getEmail());
		textField.setText(manager);
		remarkTextArea.setText(newReaderForm.getRemark());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == saveButton) {

//			if ("".equals(nameField) || "".equals(sexField) || "".equals(barCodeField) || "".equals(readerTypeField)
//					|| "".equals(jobField) || "".equals(birthField) || "".equals(cardField)
//					|| "".equals(introductionField)) {
//				JOptionPane.showMessageDialog(nameField, "请将信息添写完整!");
//				return;
//			}
//			libraryForm.setAddress(readerTypeField.getText());
//			libraryForm.setCreateDate(cardField.getText());
//			libraryForm.setCurator(sexField.getText());
//			libraryForm.setEmail(jobField.getText());
//			
//			libraryForm.setIntroduce(introductionField.getText());
//			libraryForm.setLibraryname(nameField.getText());
//			libraryForm.setTel(barCodeField.getText());
//			libraryForm.setUrl(birthField.getText());
//			
//			
//			int ret = libraryDAO.update(libraryForm);
//		        if (ret ==0) {
//		           JOptionPane.showMessageDialog(nameField,  "图书馆信息修改失败！");
//		            
//		        } else {
//		        	JOptionPane.showMessageDialog(nameField,  "图书馆信息修改成功！");;
//		        }
		} else if (e.getSource() == exitButton) {
			dispose();
		}
	}
}

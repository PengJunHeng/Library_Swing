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

import bean.LibraryForm;
import bean.ReaderForm;
import bean.ReaderTypeForm;
import dao.LibraryDAO;
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

public class ModifyReaderFrame extends JFrame implements ActionListener {

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
	private JTextField barCodeField;
	private JTextField jobField;
	private JTextField birthField;
	private JButton exitButton;
	private JButton saveButton;
	private LibraryForm libraryForm;
	private LibraryDAO libraryDAO = new LibraryDAO();
	private JTextField numField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextArea remarkTextArea;
	private ReaderForm readerForm;
	private JLabel remark;
	private String manager;
	private ReaderDAO readerDAO = new ReaderDAO();
	private JRadioButton boyRa;
	private JComboBox typeCombo;
	private JComboBox cardTypecombo;
	private JRadioButton girlRa;
	String str = null;
	ReaderTypeDAO readerTypeDAO = new ReaderTypeDAO();
	Collection coll = (Collection) readerTypeDAO.query(str);
	String[] value2 = { "身份证", "学生证", "军官证", "工作证" };
	private String[] value = { "身份证", "学生证", "军官证" };
	private JTextField dateField;
	private JTextField operatorField;

	ButtonGroup buttonGroup = new ButtonGroup();

	Iterator it = coll.iterator();

	// ReaderForm readerForm=(ReaderForm)request.getAttribute("readerQueryif");
//	String[] value=new String[2];
	/**
	 * Create the frame.
	 */
	public ModifyReaderFrame() {
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

		JLabel emailLabel = new JLabel("电话：");
		emailLabel.setBounds(14, 294, 72, 18);
		contentPane.add(emailLabel);

		JLabel emailLaber = new JLabel("Email地址：");
		emailLaber.setBounds(14, 325, 72, 18);
		contentPane.add(emailLaber);

		nameField = new JTextField();
		nameField.setBounds(85, 10, 293, 24);
		contentPane.add(nameField);
		nameField.setColumns(10);

		boyRa = new JRadioButton("男");
		boyRa.setBounds(85, 40, 66, 27);
		contentPane.add(boyRa);
		buttonGroup.add(boyRa);

		girlRa = new JRadioButton("女");
		girlRa.setBounds(157, 40, 78, 27);
		contentPane.add(girlRa);
		buttonGroup.add(girlRa);

		barCodeField = new JTextField();
		barCodeField.setColumns(10);
		barCodeField.setBounds(85, 72, 293, 24);
		contentPane.add(barCodeField);

		jobField = new JTextField();
		jobField.setColumns(10);
		jobField.setBounds(85, 134, 293, 24);
		contentPane.add(jobField);

		birthField = new JTextField();
		birthField.setColumns(10);
		birthField.setBounds(85, 165, 293, 24);
		contentPane.add(birthField);

		numField = new JTextField();
		numField.setColumns(10);
		numField.setBounds(85, 227, 293, 24);
		contentPane.add(numField);

		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(85, 291, 293, 24);
		contentPane.add(phoneField);

		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(85, 325, 293, 24);
		contentPane.add(emailField);

		dateField = new JTextField();
		dateField.setColumns(10);
		dateField.setBounds(85, 258, 293, 24);
		contentPane.add(dateField);

		typeCombo = new JComboBox(value);
		typeCombo.setBounds(85, 103, 293, 24);
		contentPane.add(typeCombo);

		cardTypecombo = new JComboBox(value2);
		cardTypecombo.setBounds(85, 196, 293, 24);
		contentPane.add(cardTypecombo);

		operatorField = new JTextField();
		operatorField.setColumns(10);
		operatorField.setBounds(85, 356, 293, 24);
		contentPane.add(operatorField);

		remarkTextArea = new JTextArea();
		remarkTextArea.setBounds(85, 388, 293, 96);
		contentPane.add(remarkTextArea);

		saveButton = new JButton("提交");
		saveButton.setBounds(85, 508, 113, 27);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);

		exitButton = new JButton("退出");
		exitButton.setBounds(265, 508, 113, 27);
		exitButton.addActionListener(this);
		contentPane.add(exitButton);

		remark = new JLabel("备注：");
		remark.setBounds(14, 390, 72, 18);
		contentPane.add(remark);

		if (coll == null || coll.isEmpty()) {
			// out.println("<script>alert('请先录入读者类型信息!');history.back(-1);</script>");
		} else {
			Iterator it = coll.iterator();
			int ID = 0;
			String name = "";
			int i = 0;

			while (it.hasNext()) {
				ReaderTypeForm readerTypeForm = (ReaderTypeForm) it.next();
				ID = readerTypeForm.getId().intValue();
				name = readerTypeForm.getName();
				value[i] = name;
				i++;
			}
		}

		JLabel label = new JLabel("登记日期：");
		label.setBounds(14, 261, 72, 18);
		contentPane.add(label);

		JLabel operatorLaber = new JLabel("操作员：");
		operatorLaber.setBounds(14, 359, 72, 18);
		contentPane.add(operatorLaber);
	}

	public ModifyReaderFrame(String manager, ReaderForm newReaderForm) {
		// TODO Auto-generated constructor stub
		this();
		this.manager = manager;
		int ID = readerForm.getId().intValue();
		String name = readerForm.getName();
		String sex = readerForm.getSex();
		String barcode = readerForm.getBarcode();
		String vocation = readerForm.getVocation();
		String birthday = readerForm.getBirthday();
		String paperType = readerForm.getPaperType();
		String paperNO = readerForm.getPaperNO();
		String tel = readerForm.getTel();
		String email = readerForm.getEmail();
		String createDate = readerForm.getCreateDate();
		String remark = readerForm.getRemark();
		String operator = readerForm.getOperator();
		int typeid = readerForm.getTypeid();
		String typename = readerForm.getTypename();
		String typeName = "";
		int typeID = 0;
		nameField.setText(name);
		if ("男".equals(sex)) {
			boyRa.isSelected();
		}
		if ("女".equals(sex)) {
			girlRa.isSelected();
		}
		barCodeField.setText(barcode);
		jobField.setText(vocation);
		birthField.setText(birthday);
		numField.setText(paperNO);
		phoneField.setText(tel);
		emailField.setText(email);
		dateField.setText(createDate);
		operatorField.setText(manager);
		while (it.hasNext()) {
			ReaderTypeForm readerTypeForm = (ReaderTypeForm) it.next();
			typeID = readerTypeForm.getId().intValue();
			typeName = readerTypeForm.getName();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == saveButton) {

			ReaderForm readerForm = new ReaderForm();
			readerForm.setName(nameField.getText().trim());
			if (boyRa.isSelected()) {
				readerForm.setSex("男");
			}
			if (girlRa.isSelected()) {
				readerForm.setSex("女");
			}

			readerForm.setBarcode(barCodeField.getText());
			readerForm.setVocation(jobField.getText());
			readerForm.setBirthday(birthField.getText());
			if (typeCombo.getSelectedItem().equals("学生")) {
				readerForm.setPaperType("学生");
			}
			if (typeCombo.getSelectedItem().equals("程序员")) {
				readerForm.setPaperType("程序员");
			}
			if (typeCombo.getSelectedItem().equals("教师")) {
				readerForm.setPaperType("教师");
			}

			readerForm.setPaperNO(numField.getText());
			readerForm.setTel(phoneField.getText());
			readerForm.setEmail(emailField.getText());
			// 获取系统日期
			Date date1 = new Date();
			java.sql.Date date = new java.sql.Date(date1.getTime());
			readerForm.setCreateDate(date.toString());
			readerForm.setOperator(manager);
			readerForm.setRemark(remarkTextArea.getText());
			if (cardTypecombo.getSelectedItem().equals("身份证")) {
				readerForm.setPaperType("身份证");
			}
			if (cardTypecombo.getSelectedItem().equals("学生证")) {
				readerForm.setPaperType("学生证");
			}
			if (cardTypecombo.getSelectedItem().equals("军官证")) {
				readerForm.setPaperType("军官证");
			}
			if (cardTypecombo.getSelectedItem().equals("工作证")) {
				readerForm.setPaperType("工作证");
			}
			// readerForm.setTypeid(Integer.parseInt(readerTypeField.getText()));
			int a = readerDAO.insert(readerForm);
			if (a == 0) {
				JOptionPane.showMessageDialog(null, "读者信息添加失败！");
				return;
			} else if (a == 2) {
				JOptionPane.showMessageDialog(null, "该读者信息已经添加！");
				return;

			} else {
				JOptionPane.showMessageDialog(null, "读者信息添加成功！");

			}
		} else if (e.getSource() == exitButton) {
			dispose();
		}
	}
}

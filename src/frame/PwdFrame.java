package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import bean.ManagerForm;
import dao.ManagerDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JButton;

public class PwdFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField nameField;
	private JPasswordField pwdField;
	private JPasswordField newField;
	private JPasswordField comfirmPwdField;
	private JButton saveButton;
	private JButton exitButton;
	private ManagerDAO managerDAO = new ManagerDAO();
	ManagerForm newManagerForm;
	/**
	 * Create the frame.
	 */
	public PwdFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 342, 258);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel nameLabel = new JLabel("管理员名称：");
		nameLabel.setBounds(14, 13, 90, 18);
		contentPane.add(nameLabel);

		JLabel pwdLabel = new JLabel("原密码：");
		pwdLabel.setBounds(14, 44, 72, 18);
		contentPane.add(pwdLabel);

		JLabel newPwdLabel = new JLabel("新密码：");
		newPwdLabel.setBounds(14, 75, 72, 18);
		contentPane.add(newPwdLabel);

		JLabel comfirmPwdLabel = new JLabel("确认新密码：");
		comfirmPwdLabel.setBounds(14, 106, 90, 18);
		contentPane.add(comfirmPwdLabel);

		nameField = new JTextField();
		nameField.setBounds(100, 10, 154, 24);
		contentPane.add(nameField);
		nameField.setColumns(10);

		pwdField = new JPasswordField();
		pwdField.setColumns(10);
		pwdField.setBounds(100, 41, 154, 24);
		contentPane.add(pwdField);

		newField = new JPasswordField();
		newField.setColumns(10);
		newField.setBounds(100, 72, 154, 24);
		contentPane.add(newField);

		comfirmPwdField = new JPasswordField();
		comfirmPwdField.setColumns(10);
		comfirmPwdField.setBounds(100, 103, 154, 24);
		contentPane.add(comfirmPwdField);

		saveButton = new JButton("保存");
		saveButton.setBounds(14, 140, 113, 27);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);

		exitButton = new JButton("退出");
		exitButton.setBounds(141, 140, 113, 27);
		exitButton.addActionListener(this);
		contentPane.add(exitButton);
	}

	public PwdFrame(String sessionName) {
		// TODO Auto-generated constructor stub
		this();
		ManagerForm managerForm = new ManagerForm();
		// HttpSession session = request.getSession();
		String manager = sessionName;
		managerForm.setName(manager);
		System.out.print("查询到的manager:" + manager);

		 newManagerForm = (ManagerForm) managerDAO.query_pwd(managerForm);
		nameField.setText(newManagerForm.getName());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == saveButton) {
			if (nameField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请输入管理员名称");
				return;
			}
			if (pwdField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请输入原密码！");
				return;
			}
			if (!pwdField.getText().equals(newManagerForm.getPwd())) {
				JOptionPane.showMessageDialog(null, "您输入的原密码不正确，请重新输入!");
				return;
			}
			if (newField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请输入的新密码!");
				return;
			}
			if (comfirmPwdField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请确认新密码!");
				return;
			}
			if (!newField.getText().equals(comfirmPwdField.getText())) {
				JOptionPane.showMessageDialog(null, "您两次输入的新密码不一致，请重新输入!");
				return;
			}
			
			ManagerForm managerForm = new ManagerForm();
			managerForm.setName(nameField.getText());
			managerForm.setPwd(newField.getText());
			int ret = managerDAO.updatePwd(managerForm);
			if (ret == 0) {
				JOptionPane.showMessageDialog(null, "更改口令失败！");
				
			} else {
				JOptionPane.showMessageDialog(null, "更改口令成功！");
				
			}
			
		}else if (e.getSource()==exitButton	) {
			dispose();
		}
		
	}
}

package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.ManagerForm;
import dao.ManagerDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class InsertManagerFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField passwordField;
	private JTextField comfirmPwdField;
	private JButton saveButton;
	private JButton exitButton;
	private ManagerDAO managerDAO = new ManagerDAO();

	/**
	 * Create the frame.
	 */
	public InsertManagerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 264);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel managerName = new JLabel("管理员名称：");
		managerName.setBounds(14, 13, 90, 18);
		contentPane.add(managerName);

		JLabel managerPassword = new JLabel("管理员密码：");
		managerPassword.setBounds(14, 44, 90, 18);
		contentPane.add(managerPassword);

		JLabel confirmPassword = new JLabel("确认密码：");
		confirmPassword.setBounds(14, 75, 90, 18);
		contentPane.add(confirmPassword);

		nameField = new JTextField();
		nameField.setBounds(110, 10, 150, 24);
		contentPane.add(nameField);
		nameField.setColumns(10);

		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(110, 41, 150, 24);
		contentPane.add(passwordField);

		comfirmPwdField = new JTextField();
		comfirmPwdField.setColumns(10);
		comfirmPwdField.setBounds(110, 72, 150, 24);
		contentPane.add(comfirmPwdField);

		saveButton = new JButton("保存");
		saveButton.setBounds(14, 122, 109, 27);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);

		exitButton = new JButton("关闭");
		exitButton.setBounds(151, 122, 109, 27);
		exitButton.addActionListener(this);
		contentPane.add(exitButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == saveButton) {
			if (nameField.getText() == "") {
				JOptionPane.showMessageDialog(null, "请输入管理员名称！");
				return;
			}
			if (passwordField.getText() == "") {
				JOptionPane.showMessageDialog(null, "请输入密码！");
				return;
			}
			if (comfirmPwdField.getText() == "") {
				JOptionPane.showMessageDialog(null, "请输入确认密码");
				return;
			}
			if (!passwordField.getText().equals(comfirmPwdField.getText())) {
				JOptionPane.showMessageDialog(null, "您两次输入的管理员密码不一致，请重新输入!");
				return;
			}
			ManagerForm managerForm = new ManagerForm();
			managerForm.setName(nameField.getText()); // 获取设置管理员名称
			managerForm.setPwd(passwordField.getText()); // 获取并设置密码
			int ret = managerDAO.insert(managerForm); // 调用添加管理员信息
			if (ret == 1) {
				JOptionPane.showMessageDialog(null, "添加成功！");

			} else if (ret == 2) {
				JOptionPane.showMessageDialog(null, "添加失败！该管理员已经添加！");
			} else {
				JOptionPane.showMessageDialog(null, "添加管理员信息失败！");
			}
		} else if (e.getSource() == exitButton) {
			dispose();
		}

	}

}

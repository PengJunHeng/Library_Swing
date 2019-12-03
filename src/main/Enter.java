package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import bean.ManagerForm;
import dao.ManagerDAO;
import panel.BackgroundPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;

public class Enter extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Image image;
	private JTextField nameField;
	private JPasswordField passwordField;
	private JButton enterButton;
	private MianFrame mianFrame;
	private ManagerDAO managerDAO = new ManagerDAO();
	private ManagerForm managerForm = new ManagerForm();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
				//	UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel");
					Enter frame = new Enter();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Enter() {
		Font font = new Font("Microsoft YaHei UI", Font.BOLD, 14);
		Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = (Object) keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, font);
			}

		}
		// setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 385, 688);
		setTitle("图书馆管理系统");
		setResizable(false);

		setLocationRelativeTo(null);
		// image = new ImageIcon(getClass().getResource("/icons/登录.png")).getImage();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nameField = new JTextField();
		// nameField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		nameField.setText("mr");
		nameField.setBounds(86, 343, 233, 42);
		// nameField.setOpaque(false);
		nameField.setBorder(null);
		// nameField.setForeground(Color.white);
		contentPane.add(nameField);
		nameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setText("mrsoft");
		passwordField.setBounds(86, 398, 233, 42);
		passwordField.setBorder(null);
		// passwordField.setForeground(Color.white);
		// passwordField.setOpaque(false);
		contentPane.add(passwordField);

		enterButton = new JButton("");
		enterButton.setIcon(null);
		enterButton.setBounds(111, 480, 150, 42);
		enterButton.setBorder(null);
		// enterButton.setContentAreaFilled(false);
		contentPane.add(enterButton);
		enterButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enterButton) {
			managerForm.setName(nameField.getText());// 获取管理员名称并设置name属性

			managerForm.setPwd(new String(passwordField.getText()));
			int ret = managerDAO.checkManager(managerForm);// 调用ManagerDAO类的checkManager()方法
			if (ret == 1) {

				MianFrame mianFrame = new MianFrame(nameField.getText());
				Enter.this.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "您输入的管理员名称或密码错误！");
				return;

			}
		}

	}

}

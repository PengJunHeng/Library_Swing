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
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class ModifyManagerPermission extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JCheckBox systemSettingCB;
	private JCheckBox readerManagerCB;
	private JCheckBox bookManagerCB;
	private JCheckBox bookLendAndBorrowCB;
	private JCheckBox systemQueryCB;
	private JButton saveButton;
	private JButton exitButton;
	private ManagerForm managerForm;
	private ManagerDAO managerDAO=new ManagerDAO();
	private JLabel nameLabel;

	/**
	 * Create the frame.
	 */
	public ModifyManagerPermission() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 358);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("权限设置");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel managerName = new JLabel("管理员名称");
		managerName.setBounds(14, 13, 92, 18);
		contentPane.add(managerName);

		JLabel ownedPermissions = new JLabel("拥有的权限");
		ownedPermissions.setBounds(14, 44, 215, 18);
		contentPane.add(ownedPermissions);

		systemSettingCB = new JCheckBox("系统设置");
		systemSettingCB.setBounds(14, 71, 215, 27);
		contentPane.add(systemSettingCB);

		readerManagerCB = new JCheckBox("读者管理");
		readerManagerCB.setBounds(14, 102, 215, 27);
		contentPane.add(readerManagerCB);

		bookManagerCB = new JCheckBox("图书管理");
		bookManagerCB.setBounds(14, 133, 215, 27);
		contentPane.add(bookManagerCB);

		bookLendAndBorrowCB = new JCheckBox("图书借还");
		bookLendAndBorrowCB.setBounds(14, 164, 215, 27);
		contentPane.add(bookLendAndBorrowCB);

		systemQueryCB = new JCheckBox("系统查询");
		systemQueryCB.setBounds(14, 195, 215, 27);
		contentPane.add(systemQueryCB);

		saveButton = new JButton("保存");
		saveButton.setBounds(14, 232, 92, 27);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);

		exitButton = new JButton("退出");
		exitButton.setBounds(139, 232, 92, 27);
		exitButton.addActionListener(this);
		contentPane.add(exitButton);

		nameLabel = new JLabel("");
		nameLabel.setBounds(120, 13, 111, 18);
		contentPane.add(nameLabel);
	}

	public ModifyManagerPermission(ManagerForm managerForm) {
		this();
		this.managerForm = managerForm;
		if (managerForm != null) {

			int ID = managerForm.getId().intValue();
			String name = managerForm.getName();
			int sysset = managerForm.getSysset();
			int readerset = managerForm.getReaderset();
			int bookset = managerForm.getBookset();
			int borrowback = managerForm.getBorrowback();
			int sysquery = managerForm.getSysquery();
			nameLabel.setText(name);
			systemSettingCB.setSelected(sysset == 1 ? true : false);
			readerManagerCB.setSelected(readerset == 1 ? true : false);
			bookManagerCB.setSelected(bookset == 1 ? true : false);
			bookLendAndBorrowCB.setSelected(borrowback == 1 ? true : false);
			systemQueryCB.setSelected(sysquery == 1 ? true : false);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == saveButton) {
			ManagerForm managerForm = new ManagerForm();
			managerForm.setId(this.managerForm.getId()); // 获取并设置管理员ID号
			managerForm.setName(nameLabel.getText()); // 获取并设置管理员名称
			managerForm.setPwd(this.managerForm.getPwd()); // 获取并设置管理员密码
			managerForm.setSysset(systemSettingCB.isSelected()? 1
					: 0); // 获取并设置系统设置权限
			managerForm.setReaderset(readerManagerCB.isSelected()? 1
					: 0); // 获取并设置读者管理权限
			managerForm.setBookset(bookManagerCB.isSelected()? 1
					: 0); // 获取并设置图书管理权限
			managerForm
					.setBorrowback(bookLendAndBorrowCB.isSelected()? 1
							: 0); // 获取并设置图书借还权限
			managerForm.setSysquery(systemQueryCB.isSelected()? 1
					: 0); // 获取并设置系统查询权限
			int ret = managerDAO.update(managerForm); // 调用设置管理员权限的方法
			if (ret == 0) {
				JOptionPane.showMessageDialog(null, "设置权限失败！");
				
						
			} else {
				JOptionPane.showMessageDialog(null, "设置权限成功！");
				
			}
		}else if(e.getSource()==exitButton) {
			dispose();
			
		}

	}
}

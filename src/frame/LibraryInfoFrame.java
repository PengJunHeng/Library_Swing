package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.LibraryForm;
import dao.LibraryDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class LibraryInfoFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel LibraryNameLabel;
	private JLabel CuratorLabel;
	private JLabel phoneNumLabel;
	private JLabel addressLabel;
	private JLabel emailLabel;
	private JLabel URLLabel;
	private JLabel buildTimeLabel;
	private JLabel IntroductionLabel;
	private JTextField nameField;
	private JTextField curatorField;
	private JTextField phoneField;
	private JTextField addressField;
	private JTextField emailField;
	private JTextField URLField;
	private JTextField buildTimeField;
	private JTextArea introductionField;
	private JButton exitButton;
	private JButton saveButton;
	private LibraryForm libraryForm;
	private LibraryDAO libraryDAO=new LibraryDAO();

	public LibraryInfoFrame(LibraryForm libraryForm) {
		this();
		this.libraryForm = libraryForm;
		if (libraryForm != null) {
			nameField.setText(libraryForm.getLibraryname());
			curatorField.setText(libraryForm.getCurator());
			phoneField.setText(libraryForm.getTel());
			addressField.setText(libraryForm.getAddress());
			emailField.setText(libraryForm.getEmail());
			URLField.setText(libraryForm.getUrl());
			buildTimeField.setText(libraryForm.getCreateDate());
			introductionField.setText(libraryForm.getIntroduce());
			
		}

	}

	/**
	 * Create the frame.
	 */
	public LibraryInfoFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 417, 442);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		LibraryNameLabel = new JLabel("图书馆名");
		LibraryNameLabel.setBounds(14, 13, 72, 18);
		contentPane.add(LibraryNameLabel);

		CuratorLabel = new JLabel("馆    长");
		CuratorLabel.setBounds(14, 44, 72, 18);
		contentPane.add(CuratorLabel);

		phoneNumLabel = new JLabel("联系电话");
		phoneNumLabel.setBounds(14, 75, 72, 18);
		contentPane.add(phoneNumLabel);

		addressLabel = new JLabel("联系地址");
		addressLabel.setBounds(14, 106, 72, 18);
		contentPane.add(addressLabel);

		emailLabel = new JLabel("联系邮箱");
		emailLabel.setBounds(14, 137, 72, 18);
		contentPane.add(emailLabel);

		URLLabel = new JLabel("网    址");
		URLLabel.setBounds(14, 168, 72, 18);
		contentPane.add(URLLabel);

		buildTimeLabel = new JLabel("建馆时间");
		buildTimeLabel.setBounds(14, 199, 72, 18);
		contentPane.add(buildTimeLabel);

		IntroductionLabel = new JLabel("简    介");
		IntroductionLabel.setBounds(14, 300, 72, 18);
		contentPane.add(IntroductionLabel);

		nameField = new JTextField();
		nameField.setBounds(85, 10, 250, 24);
		contentPane.add(nameField);
		nameField.setColumns(10);

		curatorField = new JTextField();
		curatorField.setColumns(10);
		curatorField.setBounds(85, 41, 250, 24);
		contentPane.add(curatorField);

		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(85, 72, 250, 24);
		contentPane.add(phoneField);

		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(85, 103, 250, 24);
		contentPane.add(addressField);

		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(85, 134, 250, 24);
		contentPane.add(emailField);

		URLField = new JTextField();
		URLField.setColumns(10);
		URLField.setBounds(85, 165, 250, 24);
		contentPane.add(URLField);

		buildTimeField = new JTextField();
		buildTimeField.setColumns(10);
		buildTimeField.setBounds(85, 196, 250, 24);
		contentPane.add(buildTimeField);

		introductionField = new JTextArea();
		introductionField.setBounds(85, 228, 250, 90);
		contentPane.add(introductionField);

		saveButton = new JButton("提交");
		saveButton.setBounds(85, 331, 113, 27);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);

		exitButton = new JButton("退出");
		exitButton.setBounds(222, 331, 113, 27);
		exitButton.addActionListener(this);
		contentPane.add(exitButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == saveButton) {
				
			if ("".equals(nameField) || "".equals(curatorField) || "".equals(phoneField) || "".equals(addressField)
					|| "".equals(emailField) || "".equals(URLField) || "".equals(buildTimeField)
					|| "".equals(introductionField)) {
				JOptionPane.showMessageDialog(nameField, "请将信息添写完整!");
				return;
			}
			libraryForm.setAddress(addressField.getText());
			libraryForm.setCreateDate(buildTimeField.getText());
			libraryForm.setCurator(curatorField.getText());
			libraryForm.setEmail(emailField.getText());
			
			libraryForm.setIntroduce(introductionField.getText());
			libraryForm.setLibraryname(nameField.getText());
			libraryForm.setTel(phoneField.getText());
			libraryForm.setUrl(URLField.getText());
			
			
			int ret = libraryDAO.update(libraryForm);
		        if (ret ==0) {
		           JOptionPane.showMessageDialog(nameField,  "图书馆信息修改失败！");
		            
		        } else {
		        	JOptionPane.showMessageDialog(nameField,  "图书馆信息修改成功！");;
		        }
		}else if (e.getSource()==exitButton) {
			dispose();
		}
	}

}

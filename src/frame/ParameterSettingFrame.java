package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Savepoint;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.ParameterForm;
import dao.ParameterDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ParameterSettingFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField certificateField;
	private JTextField periodField;
	private JButton saveButton;
	private JButton cancelButton;
	private ParameterDAO parameterDAO=new ParameterDAO();
	private ParameterForm parameterForm;

	/**
	 * Create the frame.
	 */
	public ParameterSettingFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 378, 218);
		setTitle("参数设置");
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel certificatefee = new JLabel("办证费：");
		certificatefee.setBounds(14, 13, 72, 18);
		contentPane.add(certificatefee);

		JLabel validityPeriod = new JLabel("有效期限：");
		validityPeriod.setBounds(14, 44, 84, 18);
		contentPane.add(validityPeriod);

		certificateField = new JTextField();
		certificateField.setBounds(100, 10, 200, 24);
		contentPane.add(certificateField);
		certificateField.setColumns(10);

		periodField = new JTextField();
		periodField.setBounds(100, 41, 200, 24);
		contentPane.add(periodField);
		periodField.setColumns(10);

		saveButton = new JButton("保存");
		saveButton.setBounds(14, 93, 113, 27);
		contentPane.add(saveButton);

		cancelButton = new JButton("退出");
		cancelButton.setBounds(187, 93, 113, 27);
		contentPane.add(cancelButton);

		parameterForm = parameterDAO.query();
		int cost = 0;
		int validity = 0;
		if (parameterForm != null) {
			cost = parameterForm.getCost();
			validity = parameterForm.getValidity();
			certificateField.setText(cost + "");
			periodField.setText(validity + "");
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == saveButton) {
			ParameterForm parameterForm = new ParameterForm();
			parameterForm.setId(1);
			parameterForm.setCost(Integer.parseInt(certificateField.getText()));
			parameterForm.setValidity(Integer.parseInt(periodField.getText()));
			int ret = parameterDAO.update(parameterForm);
			if (ret == 0) {
				JOptionPane.showMessageDialog(null,  "参数设置信息修改失败！");
			} else {
				JOptionPane.showMessageDialog(null, "参数设置信息修改成功!");
			}
		} else if (e.getSource() == cancelButton) {
			dispose();
		}

	}

}

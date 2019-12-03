package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bean.LibraryForm;
import dao.LibraryDAO;

import frame.LibraryInfoFrame;
import frame.ParameterSettingFrame;
import frame.PwdFrame;
import panel.BackgroundPanel;
import panel.BookBackPanel;
import panel.BookBorrowPanel;
import panel.BookCaseSettingPanel;
import panel.BookInfoPanel;
import panel.BookRenewPanel;
import panel.BookTypePanel;
import panel.HomePagePanel;
import panel.LibraryBorrowPanel;
import panel.LibraryInfoQueryPanel;
import panel.ManagerSettingPanel;
import panel.ReaderInfoPanel;
import panel.ReaderTypePanel;
import panel.RemindPanel;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.Timer;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MianFrame extends JFrame implements ActionListener {
	private Image image;

	private JLabel lblNewLabel;
	private JButton mainButton;
	private JMenu systemSetting;
	private JMenu readerManage;
	private JMenu bookManager;
	private JMenu lendAndReturn;
	private JMenu systemFind;
	private JButton changePassword;
	private JButton Logout;
	private JMenuBar navigationMenuBar;
	private JMenuItem libraryInfo;
	private JMenuItem managerInfo;
	private JMenuItem paraSetting;
	private JMenuItem shelvesSetting;
	private JMenuItem readerTypeManage;
	private JMenuItem readerInfoManage;
	private JMenuItem bookTypeManage;
	private JMenuItem bookInfoManage;
	private JMenuItem bookLending;
	private JMenuItem bookRenew;
	private JMenuItem bookReturn;
	private JMenuItem libraryInfoQuery;
	private JMenuItem libraryLendingQuery;
	private JMenuItem expiryRemind;
	private JTable table;
	private JScrollPane scrollPane;
	private String[] title;
	private DefaultTableModel model;
	private JPanel contentPane;
	private HomePagePanel homePagePanel;
	private ManagerSettingPanel managerSettingPanel;
	private JPanel mainPanel;
	private JLabel lblNewLabel_1;
	private LibraryDAO libraryDAO = new LibraryDAO();
	private LibraryForm libraryForm;
	private LibraryInfoFrame libraryInfoFrame;
	private String libraryname = "";
	private String curator = "";
	private String tel = "";
	private String address = "";
	private String email = "";
	private String url = "";
	private String createTime = "";
	private String introduce = "";

	private Timer time;
	private JLabel timeLabel;
	private ParameterSettingFrame parameterSettingFrame;
	private BookCaseSettingPanel bookCaseSettingPanel;
	private ReaderTypePanel readerTypePanel;
	private PwdFrame pwdFrame;
	private String sessionName;
	private RemindPanel remindPanel;
	private BookBorrowPanel bookBorrowPanel;
	private LibraryInfoQueryPanel infoQueryPanel;
	private LibraryBorrowPanel borrowPanel;
	private BookRenewPanel bookRenewPanel;
	private BookBackPanel backPanel;
	private BookTypePanel bookTypePanel;
	private ReaderInfoPanel readerInfoPanel;

	private BookInfoPanel bookInfoPanel;

	/**
	 * Create the frame.
	 */
//	public MianFrame(User u) {
//		this();
//		
//
//	}

	public MianFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1920, 1080);
		setVisible(true);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		image = new ImageIcon(getClass().getResource("/icons/Main.jpg")).getImage();
		contentPane = new BackgroundPanel(image);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 1920, 980);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		navigationMenuBar = new JMenuBar();
		setJMenuBar(navigationMenuBar);

		timeLabel = getTimelabel();
		navigationMenuBar.add(timeLabel);

		mainButton = new JButton("首页");
		mainButton.addActionListener(this);
		navigationMenuBar.add(mainButton);

		systemSetting = new JMenu("系统查询");

		navigationMenuBar.add(systemSetting);

		libraryInfo = new JMenuItem("图书馆档案");
		libraryInfo.addActionListener(this);
		systemSetting.add(libraryInfo);

		managerInfo = new JMenuItem("管理员设置");
		managerInfo.addActionListener(this);
		systemSetting.add(managerInfo);

		paraSetting = new JMenuItem("参数设置");
		paraSetting.addActionListener(this);
		systemSetting.add(paraSetting);

		shelvesSetting = new JMenuItem("书架设置");
		shelvesSetting.addActionListener(this);
		systemSetting.add(shelvesSetting);

		readerManage = new JMenu("读者管理");
		navigationMenuBar.add(readerManage);

		readerTypeManage = new JMenuItem("读者类型管理");
		readerTypeManage.addActionListener(this);
		readerManage.add(readerTypeManage);

		readerInfoManage = new JMenuItem("读者档案管理");
		readerInfoManage.addActionListener(this);
		readerManage.add(readerInfoManage);

		bookManager = new JMenu("图书管理");
		navigationMenuBar.add(bookManager);

		bookTypeManage = new JMenuItem("图书类型管理");
		bookTypeManage.addActionListener(this);
		bookManager.add(bookTypeManage);

		bookInfoManage = new JMenuItem("图书档案管理");
		bookInfoManage.addActionListener(this);
		bookManager.add(bookInfoManage);

		lendAndReturn = new JMenu("图书借还");
		navigationMenuBar.add(lendAndReturn);

		bookLending = new JMenuItem("图书借阅");
		bookLending.addActionListener(this);
		lendAndReturn.add(bookLending);

		bookRenew = new JMenuItem("图书续借");
		bookRenew.addActionListener(this);
		lendAndReturn.add(bookRenew);

		bookReturn = new JMenuItem("图书归还");
		bookReturn.addActionListener(this);
		lendAndReturn.add(bookReturn);

		systemFind = new JMenu("系统查询");
		navigationMenuBar.add(systemFind);

		libraryInfoQuery = new JMenuItem("图书馆档案查询");
		libraryInfoQuery.addActionListener(this);
		systemFind.add(libraryInfoQuery);

		libraryLendingQuery = new JMenuItem("图书馆借阅查询");
		libraryLendingQuery.addActionListener(this);
		systemFind.add(libraryLendingQuery);

		expiryRemind = new JMenuItem("到期提醒");
		expiryRemind.addActionListener(this);
		systemFind.add(expiryRemind);

		changePassword = new JButton("更改口令");
		changePassword.addActionListener(this);
		navigationMenuBar.add(changePassword);

		Logout = new JButton("注销");
		Logout.addActionListener(this);
		navigationMenuBar.add(Logout);

		// image = new ImageIcon(getClass().getResource("/icons/main.jpg")).getImage();

	}

	public MianFrame(String text) {
		// TODO Auto-generated constructor stub
		this();
		sessionName = text;
	}

	// 时间显示
	private JLabel getTimelabel() {
		if (timeLabel == null) {
			timeLabel = new JLabel(new SimpleDateFormat("yyyy年MM月dd日 EEEE hh:mm:ss").format(new Date()));
			timeLabel.setBounds(0, 0, 200, 20);
			timeLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
			timeLabel.setForeground(new Color(0, 0, 0));
			time = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					timeLabel.setText(new SimpleDateFormat("yyyy年MM月dd日 EEEE hh:mm:ss").format(new Date()));
				}
			});
			time.start();
		}
		return timeLabel;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mainButton) {
			homePagePanel = new HomePagePanel();
			mainPanel.removeAll();
			mainPanel.add(homePagePanel);
			mainPanel.repaint();
		}
		if (e.getSource() == libraryInfo) {
			LibraryForm libraryForm = libraryDAO.query();
			if (libraryForm != null)
				libraryInfoFrame = new LibraryInfoFrame(libraryForm);
		}
		if (e.getSource() == managerInfo) {
			managerSettingPanel = new ManagerSettingPanel();
			mainPanel.removeAll();
			mainPanel.add(managerSettingPanel);
			mainPanel.repaint();
		}
		if (e.getSource() == paraSetting) {
			parameterSettingFrame = new ParameterSettingFrame();

		}
		if (e.getSource() == shelvesSetting) {
			bookCaseSettingPanel = new BookCaseSettingPanel();
			mainPanel.removeAll();
			mainPanel.add(bookCaseSettingPanel);
			mainPanel.repaint();
		}
		if (e.getSource() == readerTypeManage) {
			readerTypePanel = new ReaderTypePanel();
			mainPanel.removeAll();
			mainPanel.add(readerTypePanel);
			mainPanel.repaint();
		}
		if (e.getSource() == changePassword) {
			pwdFrame = new PwdFrame(sessionName);

		}
		if (e.getSource() == Logout) {

			dispose();

		}
		if (e.getSource() == expiryRemind) {
			remindPanel = new RemindPanel();
			mainPanel.removeAll();
			mainPanel.add(remindPanel);
			mainPanel.repaint();
		}
		if (e.getSource() == bookLending) {

			bookBorrowPanel = new BookBorrowPanel();
			mainPanel.removeAll();
			mainPanel.add(bookBorrowPanel);
			mainPanel.repaint();
		}
		if (e.getSource() == libraryInfoQuery) {

			infoQueryPanel = new LibraryInfoQueryPanel();
			mainPanel.removeAll();
			mainPanel.add(infoQueryPanel);
			mainPanel.repaint();
		}
		if (e.getSource() == libraryLendingQuery) {

			borrowPanel = new LibraryBorrowPanel();
			mainPanel.removeAll();
			mainPanel.add(borrowPanel);
			mainPanel.repaint();
		}
		if (e.getSource() == bookRenew) {

			bookRenewPanel = new BookRenewPanel();
			mainPanel.removeAll();
			mainPanel.add(bookRenewPanel);
			mainPanel.repaint();
		}
		if (e.getSource() == bookReturn) {

			backPanel = new BookBackPanel();
			mainPanel.removeAll();
			mainPanel.add(backPanel);
			mainPanel.repaint();
		}
		if (e.getSource() == bookTypeManage) {

			bookTypePanel = new BookTypePanel();
			mainPanel.removeAll();
			mainPanel.add(bookTypePanel);
			mainPanel.repaint();
		}
		if (e.getSource() == readerInfoManage) {

			readerInfoPanel = new ReaderInfoPanel(sessionName);
			mainPanel.removeAll();
			mainPanel.add(readerInfoPanel);
			mainPanel.repaint();
		}
		if (e.getSource() == bookInfoManage) {

			bookInfoPanel = new BookInfoPanel(sessionName);
			mainPanel.removeAll();
			mainPanel.add(bookInfoPanel);
			mainPanel.repaint();
		}
	}
}

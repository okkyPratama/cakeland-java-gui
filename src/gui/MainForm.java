package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.User;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainForm extends JFrame implements ActionListener {
	private JPanel pnlForm, panelSpace1, panelTitle, panelIsi, panelWelcome, panelSpaceKiri, panelSpace2;
	private JLabel lblHelloHehehe, lblWelcome;
	private JMenuBar menuBar;
	private JMenu menuManageAcc, menuTrans;
	private JMenuItem itemProfile, itemLogout, itemViewAllMenu, itemManageCart, itemViewTransHis;

	public MainForm() {

		setBackground(Color.PINK);
		
		initVar();
		setUpMenuBar();
		setUpPanel();
		setUpStyle();
		
		itemProfile.addActionListener(this);
		itemLogout.addActionListener(this);
		itemViewAllMenu.addActionListener(this);
		itemManageCart.addActionListener(this);
		itemViewTransHis.addActionListener(this);

		setTitle("Main Form");
		setSize(700, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	private void setUpStyle() {
		
		panelSpace1.setBackground(Color.PINK);

		panelIsi.setBackground(Color.PINK);

		lblHelloHehehe.setForeground(new Color(0,0,128));
		lblHelloHehehe.setFont(new Font("Tahoma", Font.BOLD, 20));

		panelWelcome.setBackground(Color.PINK);

		lblWelcome.setBackground(Color.PINK);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblWelcome.setForeground(new Color(146,34,0));

		panelSpaceKiri.setBackground(Color.PINK);
		
		panelSpace2.setBackground(Color.PINK);

		menuManageAcc.setForeground(new Color(0,0,128));

		menuTrans.setForeground(new Color(0,0,128));
		
	}

	private void setUpMenuBar() {
		
		setJMenuBar(menuBar);
		menuBar.add(menuManageAcc);
		menuManageAcc.add(itemProfile);
		menuManageAcc.add(itemLogout);
		menuBar.add(menuTrans);
		menuTrans.add(itemViewAllMenu);
		menuTrans.add(itemManageCart);
		menuTrans.add(itemViewTransHis);
		
	}

	private void setUpPanel() {
		
		add(pnlForm, BorderLayout.CENTER);
		
		pnlForm.setLayout(new GridLayout(3,1));
		panelTitle.setLayout(new BorderLayout());
		panelIsi.setLayout(new BorderLayout());
		panelWelcome.setLayout(new BorderLayout());

		pnlForm.add(panelSpace1);
		pnlForm.add(panelTitle);
		pnlForm.add(panelSpace2);
		
		panelTitle.add(panelIsi);
		panelIsi.add(lblHelloHehehe, BorderLayout.NORTH);
		panelIsi.add(panelWelcome, BorderLayout.CENTER);
		panelTitle.add(panelSpaceKiri, BorderLayout.WEST);
		panelWelcome.add(lblWelcome, BorderLayout.CENTER);
		
	}

	private void initVar() {
		
		pnlForm = new JPanel();
		panelTitle = new JPanel();
		panelIsi = new JPanel();
		panelWelcome = new JPanel();
		panelSpaceKiri = new JPanel();
		
		lblHelloHehehe = new JLabel("Hello " + User.getUsername() + "!");
		lblWelcome = new JLabel("Welcome to CakeLAnd");

		panelSpace1 = new JPanel();
		panelSpace2 = new JPanel();

		menuBar = new JMenuBar();

		menuManageAcc = new JMenu("Manage Account");
		menuTrans = new JMenu("Transaction");

		itemProfile = new JMenuItem("Profile");
		itemLogout = new JMenuItem("Logout");
		
		itemViewAllMenu = new JMenuItem("View All Menu");
		itemManageCart = new JMenuItem("Manage Cart");
		itemViewTransHis = new JMenuItem("View Transaction History");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == itemLogout) {			
			setVisible(false);
			User.logout();
			new Login();
		}
		else if (e.getSource() == itemProfile) {
			setVisible(false);
			new UpdateProfile();
		}
		else if (e.getSource() == itemViewAllMenu) {
			setVisible(false);
			new ViewAllMenu();
		}
		else if (e.getSource() == itemManageCart) {
			setVisible(false);
			new ManageCart();
		}
		else if (e.getSource() == itemViewTransHis) {
			setVisible(false);
			new TransactionForm();
		}
		
	}
	
	
	
}



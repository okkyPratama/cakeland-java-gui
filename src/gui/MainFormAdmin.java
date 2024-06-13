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

public class MainFormAdmin extends JFrame implements ActionListener {
	private JPanel pnlForm, panelSpace1, panelTitle, panelIsi, panelIsiAtas, panelSpace2;
	private JLabel lblWelcome;
	private JMenuBar menuBar;
	private JMenu menuManageAcc, menuCake;
	private JMenuItem itemProfile, itemLogout, itemManageMenu;

	public MainFormAdmin() {

		setBackground(Color.PINK);

		initVar();
		setUpMenuBar();
		setUpPanel();
		setUpStyle();

		itemProfile.addActionListener(this);
		itemLogout.addActionListener(this);
		itemManageMenu.addActionListener(this);
		

		setTitle("Main Form");
		setSize(700, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	private void setUpStyle() {

		panelSpace1.setBackground(Color.PINK);

		panelIsi.setBackground(Color.PINK);

		lblWelcome.setBackground(Color.PINK);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblWelcome.setForeground(new Color(146, 34, 0));

		panelSpace2.setBackground(Color.PINK);

		menuManageAcc.setForeground(new Color(0, 0, 128));

		menuCake.setForeground(new Color(0, 0, 128));

	}

	private void setUpMenuBar() {

		setJMenuBar(menuBar);
		menuBar.add(menuManageAcc);
		menuManageAcc.add(itemProfile);
		menuManageAcc.add(itemLogout);
		menuBar.add(menuCake);
		menuCake.add(itemManageMenu);

	}

	private void setUpPanel() {

		add(pnlForm, BorderLayout.CENTER);

		pnlForm.setLayout(new GridLayout(3, 1));
		panelTitle.setLayout(new BorderLayout());
		panelIsi.setLayout(new BorderLayout());

		pnlForm.add(panelSpace1);
		pnlForm.add(panelTitle);
		pnlForm.add(panelSpace2);

		panelTitle.add(panelIsi);

		panelIsiAtas = new JPanel();
		panelIsiAtas.setBackground(Color.PINK);
		panelIsi.add(panelIsiAtas, BorderLayout.NORTH);

		lblWelcome = new JLabel("Welcome to CakeLAnd");
		panelIsiAtas.add(lblWelcome);

	}

	private void initVar() {

		pnlForm = new JPanel();
		panelTitle = new JPanel();
		panelIsi = new JPanel();

		panelSpace1 = new JPanel();
		panelSpace2 = new JPanel();

		menuBar = new JMenuBar();

		menuManageAcc = new JMenu("Manage Account");
		menuCake = new JMenu("Cake Menu");

		itemProfile = new JMenuItem("Profile");
		itemLogout = new JMenuItem("Logout");

		itemManageMenu = new JMenuItem("Manage Menu");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == itemLogout) {
			User.logout();
			setVisible(false);
			new Login();
		} else if (e.getSource() == itemProfile) {
			setVisible(false);
			new UpdateProfile();
		} else if (e.getSource() == itemManageMenu) {
			setVisible(false);
			new ManageMenu();
		}

	}

}

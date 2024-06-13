package gui;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.Connect;
import model.User;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.imageio.ImageIO;
import javax.sql.ConnectionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;

public class Login extends JFrame implements ActionListener {

	Connect connect = Connect.getConnection();

	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JPanel pnlSpace1, pnlSpace2, pnlSpace3, pnlSpace4, pnlSpace5, pnlTitle, pnlUsername1, pnlUsername2,
			pnlPassword1, pnlPassword2, pnlButton, pnlRegis, pnlSpaceRegis;
	private JLabel lblTitle, lblUsername, lblPassword;
	private JButton btnLogin, btnRegis;
	private JSeparator separator1, separator2;
	private User user;

	public Login() {

		setLayout(new GridLayout(12, 1));
		setBackground(Color.PINK);

		initVar();
		setUpPanel();
		setUpStyle();

		btnLogin.addActionListener(this);
		btnRegis.addActionListener(this);

		setTitle("Login");
		setSize(500, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void setUpPanel() {

		add(pnlSpace1);

		add(pnlTitle);
		pnlTitle.add(lblTitle);

		add(pnlSpace2);

		add(pnlUsername1);
		add(pnlUsername2);
		pnlUsername1.add(lblUsername);
		pnlUsername2.add(txtUsername);

		pnlSpace3.setLayout(new GridLayout(1, 1));
		pnlSpace3.add(separator1);
		add(pnlSpace3);

		add(pnlPassword1);
		pnlPassword1.add(lblPassword);

		add(pnlPassword2);
		pnlPassword2.add(txtPassword);

		pnlSpace4.setLayout(new GridLayout(1, 1));
		pnlSpace4.add(separator2);
		add(pnlSpace4);

		add(pnlButton);
		pnlButton.add(btnLogin);

		add(pnlSpace5);

		add(pnlRegis);
		pnlRegis.add(pnlSpaceRegis);
		pnlRegis.add(btnRegis);

	}

	private void setUpStyle() {

		separator1.setBackground(Color.BLUE);
		separator2.setBackground(Color.BLUE);

		pnlSpace1.setBackground(Color.PINK);

		pnlTitle.setBackground(Color.PINK);
		lblTitle.setForeground(new Color(165, 42, 42));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));

		pnlSpace2.setBackground(Color.PINK);

		pnlUsername1.setBackground(Color.PINK);

		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setForeground(new Color(0, 0, 139));

		pnlUsername2.setBackground(Color.PINK);

		// username box
		txtUsername.setBorder(BorderFactory.createLineBorder(Color.PINK));
		txtUsername.setForeground(Color.BLACK);
		txtUsername.setBackground(Color.PINK);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUsername.setColumns(25);

		pnlSpace3.setBackground(Color.PINK);

		pnlPassword1.setBackground(Color.PINK);

		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setForeground(new Color(0, 0, 139));

		pnlPassword2.setBackground(Color.PINK);

		// password box
		txtPassword.setBorder(BorderFactory.createLineBorder(Color.PINK));
		txtPassword.setBackground(Color.PINK);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPassword.setColumns(25);

		pnlSpace4.setBackground(Color.PINK);

		pnlButton.setBackground(Color.PINK);

		btnLogin.setForeground(new Color(0, 0, 139));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 25));

		pnlSpace5.setBackground(Color.PINK);

		pnlRegis.setBackground(Color.PINK);
		pnlRegis.setLayout(new GridLayout(1, 2));

		pnlSpaceRegis.setBackground(Color.PINK);

		btnRegis.setBorder(BorderFactory.createLineBorder(Color.PINK));
		btnRegis.setForeground(Color.BLUE);
		btnRegis.setBackground(Color.PINK);
		btnRegis.setFont(new Font("Tahoma", Font.ITALIC, 15));

	}

	private void initVar() {

		separator1 = new JSeparator();
		separator2 = new JSeparator();

		pnlTitle = new JPanel();
		pnlSpace1 = new JPanel();
		pnlSpace2 = new JPanel();
		pnlUsername1 = new JPanel();
		pnlUsername2 = new JPanel();
		pnlSpace3 = new JPanel();
		pnlPassword1 = new JPanel();
		pnlPassword2 = new JPanel();
		pnlSpace4 = new JPanel();
		pnlButton = new JPanel();
		pnlSpace5 = new JPanel();
		pnlSpaceRegis = new JPanel();
		pnlRegis = new JPanel();

		lblTitle = new JLabel("CakeLAnd");

		lblUsername = new JLabel("Username");
		txtUsername = new JTextField();

		lblPassword = new JLabel("Password");
		txtPassword = new JPasswordField();

		btnLogin = new JButton("Login");

		btnRegis = new JButton("Don't have account? Register");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnLogin) {

			if (txtUsername.getText().isEmpty() && txtPassword.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "Username and Password cannot be empty!", "Message",
						JOptionPane.ERROR_MESSAGE);

			} else if (txtUsername.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Username cannot be empty!", "Message", JOptionPane.ERROR_MESSAGE);

			} else if (txtPassword.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "Password cannot be empty!", "Message", JOptionPane.ERROR_MESSAGE);

			} else {
				String username = txtUsername.getText();
				String password = new String(txtPassword.getPassword());

				String query = String.format("SELECT * FROM user WHERE Username = '%s' AND UserPassword = '%s'",
						username, password);

				ResultSet result = connect.executeQuery(query);

				try {
					result.next();
					user = null;
					if (username.equals(result.getString("Username"))
							&& password.equals(result.getString("UserPassword"))) {
						if (result.getString("UserRole").equals("User")) {
							User.login(result.getString("UserID"), result.getString("Username"),
									result.getString("UserEmail"), result.getString("UserPassword"),
									result.getString("UserGender"), result.getString("UserDOB"),
									result.getString("UserPhoneNumber"), result.getString("UserAddress"),
									result.getString("UserRole"));
							new MainForm();
							setVisible(false);

						} else if (result.getString("UserRole").equals("Admin")) {
							User.login(result.getString("UserID"), result.getString("Username"),
									result.getString("UserEmail"), result.getString("UserPassword"),
									result.getString("UserGender"), result.getString("UserDOB"),
									result.getString("UserPhoneNumber"), result.getString("UserAddress"),
									result.getString("UserRole"));
							new MainFormAdmin();
							setVisible(false);
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Wrong Username/Password!", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					e2.printStackTrace();
				}
			}

		}
		if (e.getSource() == btnRegis) {
			setVisible(false);
			new Register();
		}

	}

}

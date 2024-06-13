package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;

import database.Connect;
import model.User;

public class UpdateProfile extends JFrame implements ActionListener {

	Connect connect = Connect.getConnection();
	
	private JPanel pnlTop, pnlMid, pnlBot, pnlTitle, pnlTitleForm, pnlForm, pnlUsername, pnlTxtUsername, pnlEmail,
			pnlEmailTxt, pnlGender, pnlRadioGender, pnlDOB, pnlTxtDOB, pnlAlignedDOB, pnlPhoneNumber, pnlTxtPhone,
			pnlAddress, pnlNewPass, pnlConfirmPass, pnlTxtNewPass, pnlTxtConfirm, pnlBtn, pnlLogin, pnlForLogin,
			pnlStrip1, pnlStrip2, pnlSpace1, pnlSpace2, pnlSpace3, pnlSpace4, pnlSpace5, pnlSpace6, pnlSpace7,
			pnlSpace8, pnlSpace9, pnlSpace10, pnlSpace11, pnlFormSpace1, pnlFormSpace2, pnlFormSpaceLeft,
			pnlFormSpaceRight, pnlOldPass, pnlTxtOldPass, pnlSpace12;
	private JLabel lblTitle, lblTitleForm, lblUsername, lblEmail, lblGender, lblDOB, lblPhoneNumber, lblAddress,
			lblNewPass, lblConfirmPass, lblStrip1, lblStrip2, lblOldPass;
	private JTextField txtUsername, txtEmail, txtDOBDay, txtDOBMonth, txtDOBYear, txtPhoneNumber;
	private JTextArea txtAddress;
	private ButtonGroup buttonGroup;
	private JRadioButton rdBtnMale, rdBtnFemale;
	private JPasswordField txtOldPass, txtNewPass, txtConfirmPass;
	private JButton btnSave;

	User user;
	
	String selectedGender = "";

	Date date = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	String userDOB = "";

	private boolean isOnlyOneAt, dobIsDigit, phoneIsDigit, isMatches, isAlphanumeric, dayIsCorrect, monthIsCorrect,
			yearIsCorrect, matchOldPass = false;

	public UpdateProfile() {

		setLayout(new BorderLayout());

		initVar();
		setUpPanel();
		setUpStyle();

		rdBtnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedGender = "Male";
			}
		});

		rdBtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedGender = "Female";
			}
		});

		btnSave.addActionListener(this);

		setTitle("CakeLAnd");
		setSize(675, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void setUpStyle() {
		pnlTop.setBackground(Color.PINK);

		pnlSpace1.setBackground(Color.PINK);

		pnlTitle.setBackground(Color.PINK);
		lblTitle.setForeground(new Color(149, 82, 81));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));

		pnlSpace2.setBackground(Color.PINK);

		pnlMid.setBackground(Color.PINK);

		pnlTitleForm.setBackground(Color.PINK);
		lblTitleForm.setForeground(new Color(0, 0, 139));
		lblTitleForm.setFont(new Font("Tahoma", Font.BOLD, 20));

		pnlUsername.setBackground(Color.PINK);
		lblUsername.setForeground(new Color(0, 0, 139));
		pnlTxtUsername.setBackground(Color.PINK);

		pnlSpace3.setBackground(Color.PINK);

		txtUsername.setForeground(new Color(0, 0, 139));
		txtUsername.setBackground(new Color(255, 228, 225));
		txtUsername.setBorder(new LineBorder(Color.PINK));
		txtUsername.setColumns(10);

		pnlEmail.setBackground(Color.PINK);

		lblEmail.setForeground(new Color(0, 0, 139));

		pnlEmailTxt.setBackground(Color.PINK);

		pnlSpace4.setBackground(Color.PINK);

		txtEmail.setForeground(new Color(0, 0, 139));
		txtEmail.setBackground(new Color(255, 228, 225));
		txtEmail.setBorder(new LineBorder(Color.PINK));
		txtEmail.setColumns(10);

		pnlGender.setBackground(Color.PINK);

		lblGender.setForeground(new Color(0, 0, 139));

		pnlRadioGender.setBackground(Color.PINK);

		rdBtnMale.setForeground(new Color(0, 0, 139));
		rdBtnMale.setBackground(Color.PINK);

		rdBtnFemale.setForeground(new Color(0, 0, 139));
		rdBtnFemale.setBackground(Color.PINK);

		pnlDOB.setBackground(Color.PINK);

		lblDOB.setForeground(new Color(0, 0, 139));

		pnlTxtDOB.setBackground(Color.PINK);

		pnlSpace5.setBackground(Color.PINK);

		pnlAlignedDOB.setBackground(Color.PINK);

		txtDOBDay.setForeground(new Color(0, 0, 139));
		txtDOBDay.setBackground(new Color(255, 228, 225));
		txtDOBDay.setBorder(new LineBorder(Color.PINK));
		txtDOBDay.setColumns(5);

		pnlStrip1.setBackground(Color.PINK);

		txtDOBMonth.setForeground(new Color(0, 0, 139));
		txtDOBMonth.setBackground(new Color(255, 228, 225));
		txtDOBMonth.setBorder(new LineBorder(Color.PINK));
		txtDOBMonth.setColumns(5);

		pnlStrip2.setBackground(Color.PINK);

		txtDOBYear.setForeground(new Color(0, 0, 139));
		txtDOBYear.setBackground(new Color(255, 228, 225));
		txtDOBYear.setBorder(new LineBorder(Color.PINK));
		txtDOBYear.setColumns(5);

		pnlPhoneNumber.setBackground(Color.PINK);

		lblPhoneNumber.setForeground(new Color(0, 0, 139));

		pnlTxtPhone.setBackground(Color.PINK);

		pnlSpace6.setBackground(Color.PINK);

		txtPhoneNumber.setForeground(new Color(0, 0, 139));
		txtPhoneNumber.setBackground(new Color(255, 228, 225));
		txtPhoneNumber.setBorder(new LineBorder(Color.PINK));
		txtPhoneNumber.setColumns(10);

		pnlAddress.setBackground(Color.PINK);

		lblAddress.setForeground(new Color(0, 0, 139));

		txtAddress.setLineWrap(true);
		txtAddress.setForeground(new Color(0, 0, 139));
		txtAddress.setBackground(new Color(255, 228, 225));
		txtAddress.setBorder(new LineBorder(Color.PINK));

		pnlForm.setBackground(Color.PINK);
		
		pnlOldPass.setBackground(Color.PINK);

		lblOldPass.setForeground(new Color(0, 0, 139));
		
		pnlTxtOldPass.setBackground(Color.PINK);
		
		pnlSpace12.setBackground(Color.PINK);
		
		txtOldPass.setBackground(new Color(255, 228, 225));
		txtOldPass.setBorder(new LineBorder(Color.PINK));
		
		pnlNewPass.setBackground(Color.PINK);

		lblNewPass.setForeground(new Color(0, 0, 139));

		pnlTxtNewPass.setBackground(Color.PINK);

		pnlSpace7.setBackground(Color.PINK);

		txtNewPass.setForeground(new Color(0, 0, 139));
		txtNewPass.setBackground(new Color(255, 228, 225));
		txtNewPass.setBorder(new LineBorder(Color.PINK));

		pnlConfirmPass.setBackground(Color.PINK);

		lblConfirmPass.setForeground(new Color(0, 0, 139));

		pnlTxtConfirm.setBackground(Color.PINK);

		pnlSpace8.setBackground(Color.PINK);

		txtConfirmPass.setForeground(new Color(0, 0, 139));
		txtConfirmPass.setBackground(new Color(255, 228, 225));
		txtConfirmPass.setBorder(new LineBorder(Color.PINK));

		pnlFormSpace1.setBackground(Color.PINK);

		pnlFormSpaceLeft.setBackground(Color.PINK);

		pnlFormSpace2.setBackground(Color.PINK);

		pnlFormSpaceRight.setBackground(Color.PINK);

		pnlSpace9.setBackground(Color.PINK);

		pnlBot.setBackground(Color.PINK);

		pnlBtn.setBackground(Color.PINK);

		btnSave.setBackground(new Color(255, 228, 225));
		btnSave.setForeground(new Color(0, 0, 139));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 24));

		pnlLogin.setBackground(Color.PINK);

		pnlSpace10.setBackground(Color.PINK);

		pnlSpace11.setBackground(Color.PINK);

	}

	private void setUpPanel() {

		// Panel Top
		add(pnlTop, BorderLayout.NORTH);
		pnlTop.setLayout(new GridLayout(3, 1));

		// Add Title for Top
		pnlTop.add(pnlSpace1);
		pnlTop.add(pnlTitle);
		pnlTitle.add(lblTitle);
		pnlTop.add(pnlSpace2);

		// Panel Mid
		add(pnlMid, BorderLayout.CENTER);
		pnlMid.setLayout(new BorderLayout());
		pnlTitleForm.add(lblTitleForm);
		pnlMid.add(pnlTitleForm, BorderLayout.NORTH);
		pnlForm.setLayout(new GridLayout(9, 2));

		// Panel for Username
		pnlForm.add(pnlUsername);
		pnlUsername.setLayout(new GridLayout(1, 2));
		pnlUsername.add(lblUsername);
		pnlUsername.add(pnlTxtUsername);
		pnlTxtUsername.setLayout(new GridLayout(3, 1));
		pnlTxtUsername.add(pnlSpace3);
		pnlTxtUsername.add(txtUsername);

		// Panel for Email
		pnlForm.add(pnlEmail);
		pnlEmail.setLayout(new GridLayout(1, 2));
		pnlEmail.add(lblEmail);
		pnlEmail.add(pnlEmailTxt);
		pnlEmailTxt.setLayout(new GridLayout(3, 1));
		pnlEmailTxt.add(pnlSpace4);
		pnlEmailTxt.add(txtEmail);

		// Panel for Gender
		pnlForm.add(pnlGender);
		pnlGender.setLayout(new GridLayout(1, 2));
		pnlGender.add(lblGender);

		// Radio Button for Gender
		buttonGroup.add(rdBtnMale);
		pnlRadioGender.add(rdBtnMale);
		buttonGroup.add(rdBtnFemale);
		pnlRadioGender.add(rdBtnFemale);

		// Panel add for Gender Group
		pnlGender.add(pnlRadioGender);
		pnlRadioGender.setLayout(new GridLayout(1, 2));

		// Panel for DOB
		pnlForm.add(pnlDOB);
		pnlDOB.setLayout(new GridLayout(1, 2));
		pnlDOB.add(lblDOB);
		pnlDOB.add(pnlTxtDOB);
		pnlTxtDOB.setLayout(new GridLayout(3, 1));
		pnlTxtDOB.add(pnlSpace5);

		// DOB Aligned
		pnlTxtDOB.add(pnlAlignedDOB);
		pnlAlignedDOB.setLayout(new GridLayout(1, 5));
		pnlAlignedDOB.add(txtDOBDay);
		pnlAlignedDOB.add(pnlStrip1);
		pnlStrip1.add(lblStrip1);
		pnlAlignedDOB.add(txtDOBMonth);
		pnlAlignedDOB.add(pnlStrip2);
		pnlStrip2.add(lblStrip2);
		pnlAlignedDOB.add(txtDOBYear);

		// Panel for Phone Number
		pnlForm.add(pnlPhoneNumber);
		pnlPhoneNumber.setLayout(new GridLayout(1, 2));
		pnlPhoneNumber.add(lblPhoneNumber);
		pnlPhoneNumber.add(pnlTxtPhone);
		pnlTxtPhone.setLayout(new GridLayout(3, 1));
		pnlTxtPhone.add(pnlSpace6);
		pnlTxtPhone.add(txtPhoneNumber);

		// Panel for Address
		pnlForm.add(pnlAddress);
		pnlAddress.setLayout(new GridLayout(1, 2));
		pnlAddress.add(lblAddress);
		pnlAddress.add(txtAddress);
		

		// Panel for Old Password
		pnlForm.add(pnlOldPass);
		pnlOldPass.add(lblOldPass);
		pnlOldPass.setLayout(new GridLayout(1, 2));
		pnlOldPass.add(pnlTxtOldPass);
		pnlTxtOldPass.setLayout(new GridLayout(3, 1));
		pnlTxtOldPass.add(pnlSpace12);
		pnlTxtOldPass.add(txtOldPass);
		
		// Panel for New Password
		pnlForm.add(pnlNewPass);
		pnlNewPass.setLayout(new GridLayout(1, 2));
		pnlNewPass.add(lblNewPass);
		pnlNewPass.add(pnlTxtNewPass);
		pnlTxtNewPass.setLayout(new GridLayout(3, 1));
		pnlTxtNewPass.add(pnlSpace7);
		pnlTxtNewPass.add(txtNewPass);

		// Panel for Re-Password
		pnlForm.add(pnlConfirmPass);
		pnlConfirmPass.setLayout(new GridLayout(1, 2));
		pnlConfirmPass.add(lblConfirmPass);
		pnlConfirmPass.add(pnlTxtConfirm);
		pnlTxtConfirm.setLayout(new GridLayout(3, 1));
		pnlTxtConfirm.add(pnlSpace8);
		pnlTxtConfirm.add(txtConfirmPass);

		// Add Form to Mid
		pnlMid.add(pnlForm);

		// Panel for Spacing Form
		pnlFormSpace1.add(pnlFormSpaceLeft);
		pnlFormSpace2.add(pnlFormSpaceRight);
		pnlMid.add(pnlFormSpace1, BorderLayout.WEST);
		pnlMid.add(pnlFormSpace2, BorderLayout.EAST);

		// Panel for Button
		pnlBtn.add(btnSave);

		// Panel Bot
		add(pnlBot, BorderLayout.SOUTH);
		pnlBot.setLayout(new BorderLayout());
		pnlBot.add(pnlSpace9, BorderLayout.NORTH);
		pnlBot.add(pnlBtn, BorderLayout.CENTER);
		pnlLogin.setLayout(new GridLayout(3, 1));
		pnlLogin.add(pnlSpace10);
		pnlForLogin.setLayout(new GridLayout(1, 2));
		pnlLogin.add(pnlForLogin);
		pnlForLogin.add(pnlSpace11);
		pnlBot.add(pnlLogin, BorderLayout.SOUTH);

	}

	private void initVar() {

		buttonGroup = new ButtonGroup();

		// Border Panels
		pnlTop = new JPanel();
		pnlMid = new JPanel();
		pnlBot = new JPanel();

		// Top
		pnlTitle = new JPanel();

		// Mid
		// Title Form
		pnlTitleForm = new JPanel();
		// Form
		pnlForm = new JPanel();
		pnlUsername = new JPanel();
		pnlTxtUsername = new JPanel();
		pnlEmail = new JPanel();
		pnlEmailTxt = new JPanel();
		pnlGender = new JPanel();
		pnlRadioGender = new JPanel();
		pnlDOB = new JPanel();
		pnlTxtDOB = new JPanel();
		pnlAlignedDOB = new JPanel();
		pnlPhoneNumber = new JPanel();
		pnlTxtPhone = new JPanel();
		pnlAddress = new JPanel();
		pnlNewPass = new JPanel();
		pnlOldPass = new JPanel();
		pnlTxtOldPass = new JPanel();
		
		pnlTxtNewPass = new JPanel();
		pnlConfirmPass = new JPanel();
		pnlTxtConfirm = new JPanel();

		// Bot
		pnlBtn = new JPanel();
		pnlLogin = new JPanel();
		pnlForLogin = new JPanel();

		// Space Panels
		pnlSpace1 = new JPanel();
		pnlSpace2 = new JPanel();
		pnlSpace3 = new JPanel();
		pnlSpace4 = new JPanel();
		pnlSpace5 = new JPanel();
		pnlSpace6 = new JPanel();
		pnlSpace7 = new JPanel();
		pnlSpace8 = new JPanel();
		pnlSpace9 = new JPanel();
		pnlSpace10 = new JPanel();
		pnlSpace11 = new JPanel();
		pnlSpace12 = new JPanel();
		pnlFormSpace1 = new JPanel();
		pnlFormSpace2 = new JPanel();
		pnlFormSpaceLeft = new JPanel();
		pnlFormSpaceRight = new JPanel();

		// Strip for DOB
		pnlStrip1 = new JPanel();
		pnlStrip2 = new JPanel();

		// Labels
		lblTitle = new JLabel("CakeLAnd");
		lblTitleForm = new JLabel("Change Profile");
		lblUsername = new JLabel("Username");
		lblEmail = new JLabel("Email");
		lblGender = new JLabel("Gender");
		lblDOB = new JLabel("Date of Birth");
		lblPhoneNumber = new JLabel("Phone Number");
		lblAddress = new JLabel("Address");
		lblOldPass = new JLabel("Old Password");
		lblNewPass = new JLabel("New Password");
		lblConfirmPass = new JLabel("Confirm Password");

		// Label for Strip
		lblStrip1 = new JLabel("-");
		lblStrip2 = new JLabel("-");

		// Radio Buttons
		rdBtnMale = new JRadioButton("Male");
		rdBtnFemale = new JRadioButton("Female");
		

		if (User.getUserGender().equals("Male")) {
			rdBtnMale.setEnabled(false);
			rdBtnMale.setSelected(true);
			
			rdBtnFemale.setEnabled(false);
			rdBtnFemale.setSelected(false);	
		} else if (User.getUserGender().equals("Female")) {
			rdBtnMale.setEnabled(false);
			rdBtnMale.setSelected(false);
			
			rdBtnFemale.setEnabled(false);
			rdBtnFemale.setSelected(true);		
		}
		// Button
		btnSave = new JButton("Save Change");
		
		// Text Field
		txtUsername = new JTextField();
		txtUsername.setText(User.getUsername());
		
		txtEmail = new JTextField();
		txtEmail.setText(User.getUserEmail());
		
		String[] date = User.getUserDOB().split("-");
		txtDOBDay = new JTextField();
		txtDOBDay.setText(date[2]);
		
		txtDOBMonth = new JTextField();
		txtDOBMonth.setText(date[1]);
		
		txtDOBYear = new JTextField();
		txtDOBYear.setText(date[0]);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setText(User.getUserPhoneNumber());

		// Text Arrea
		txtAddress = new JTextArea();
		txtAddress.setText(User.getUserAddress());

		// Passwords
		txtOldPass = new JPasswordField();
		txtNewPass = new JPasswordField();
		txtConfirmPass = new JPasswordField();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnSave) {

			checker();

			if (txtUsername.getText().isEmpty() && txtEmail.getText().isEmpty() && txtDOBDay.getText().isEmpty()
					&& txtDOBMonth.getText().isEmpty() && txtDOBYear.getText().isEmpty()
					&& txtPhoneNumber.getText().isEmpty() && txtAddress.getText().isEmpty()) {
				
				JOptionPane.showMessageDialog(null, "Every field must be filled!", "Message", JOptionPane.ERROR_MESSAGE);
				
			} else if (txtUsername.getText().length() < 5 || txtUsername.getText().length() > 15) {
				JOptionPane.showMessageDialog(null, "Username must be Between 5-15 characters", "Error",
						JOptionPane.WARNING_MESSAGE);
			}

			else if (txtEmail.getText().startsWith("@") || txtEmail.getText().startsWith(".")
					|| txtEmail.getText().endsWith("@") || txtEmail.getText().endsWith(".") || isOnlyOneAt == false
					|| txtEmail.getText().contains("@.")) {

				JOptionPane.showMessageDialog(null, "Email must be valid!", "Error", JOptionPane.WARNING_MESSAGE);
				
			}

			else if (!(txtEmail.getText().endsWith(".com") || txtEmail.getText().endsWith(".co.id"))) {
				JOptionPane.showMessageDialog(null, "Email must be valid!", "Error", JOptionPane.WARNING_MESSAGE);
			}
			
			else if (txtDOBDay.getText().toString().isEmpty() || txtDOBMonth.getText().toString().isEmpty() || txtDOBYear.getText().toString().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill DOB", "Error", JOptionPane.WARNING_MESSAGE);
			}
			
			else if (dobIsDigit == false) {
				JOptionPane.showMessageDialog(null, "DOB must be digit!", "Error", JOptionPane.WARNING_MESSAGE);
			}

			else if (dayIsCorrect == false) {
				JOptionPane.showMessageDialog(null, "Day must be 1 to 31", "Error", JOptionPane.WARNING_MESSAGE);
			}

			else if (monthIsCorrect == false) {
				JOptionPane.showMessageDialog(null, "Month must be 1 to 12", "Error", JOptionPane.WARNING_MESSAGE);
			}

			else if (yearIsCorrect == false) {
				JOptionPane.showMessageDialog(null, "Year must be 1900 to 2015", "Error", JOptionPane.WARNING_MESSAGE);
			}
			
			else if (phoneIsDigit == false) {
				JOptionPane.showMessageDialog(null, "Phone number must be digit!", "Error", JOptionPane.WARNING_MESSAGE);
			}

			else if ((txtPhoneNumber.getText().length() < 10 || txtPhoneNumber.getText().length() > 12)) {
				JOptionPane.showMessageDialog(null, "Phone number must be around 10 to 12 digits!", "Error", JOptionPane.WARNING_MESSAGE);
			}

			else if (!txtAddress.getText().endsWith(" Street")) {
				JOptionPane.showMessageDialog(null, "Street must be valid!", "Error", JOptionPane.WARNING_MESSAGE);
			}

			else if (matchOldPass == false) {
				JOptionPane.showMessageDialog(null, "Your old password is wrong!", "Error", JOptionPane.WARNING_MESSAGE);
			}

			else if (isAlphanumeric == false) {
				JOptionPane.showMessageDialog(null, "New password should be alphanumeric!", "Error", JOptionPane.WARNING_MESSAGE);
			}
			
			else if (!Arrays.equals(txtNewPass.getPassword(), txtConfirmPass.getPassword())) {
				
				JOptionPane.showMessageDialog(null, "Password confirmation is wrong!", "Error", JOptionPane.WARNING_MESSAGE);
				
			} else {
				
				updateQuery();
				
			}

		}

	}
	
	private void updateQuery() {
		
		String username = txtUsername.getText().toString();
		String userEmail = txtEmail.getText().toString();
		
		String userPassword = "";
		char[] password = txtNewPass.getPassword();
		for (int i = 0; i < txtNewPass.getPassword().length; i++) {
			userPassword += password[i];
		}
		
		String userPhoneNumber = txtPhoneNumber.getText().toString();
		String userAddress = txtAddress.getText().toString();
		
		PreparedStatement ps = Connect.getConnection()
				.prepareStatement("UPDATE user SET Username = ?, UserEmail = ?, UserPassword = ?, UserDOB = ?, UserPhoneNumber = ?, UserAddress = ? WHERE UserID = ?");
		
		try {
			ps.setString(1, username);
			ps.setString(2, userEmail);
			ps.setString(3, userPassword);
			ps.setDate(4, java.sql.Date.valueOf(txtDOBYear.getText().toString() + "-" + txtDOBMonth.getText().toString() + "-" + txtDOBDay.getText().toString()));
			ps.setString(5, userPhoneNumber);
			ps.setString(6, userAddress);
			ps.setString(7, User.getUserID());
			ps.execute();

			User.setUsername(username);
			User.setUserEmail(userEmail);
			User.setUserPassword(userPassword);
			User.setUserGender(selectedGender);
			User.setUserDOB(txtDOBYear.getText().toString() + "-" + txtDOBMonth.getText().toString() + "-" + txtDOBDay.getText().toString());
			User.setUserPhoneNumber(userPhoneNumber);
			User.setUserAddress(userAddress);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (User.getUserRole().equals("Admin")) {
			setVisible(false);
			JOptionPane.showMessageDialog(null, "Profile Updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
			new MainFormAdmin();
			
		} else if (User.getUserRole().equals("User")){
			setVisible(false);
			JOptionPane.showMessageDialog(null, "Profile Updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
			new MainForm();
			
		}
	}

	private void checker() {
		emailChecker();

		try {
			dobChecker();
		} catch (ParseException e2) {
			e2.printStackTrace();
		}

		phoneChecker();
		oldPasswordChecker();
		newPasswordChecker();
	}

	private void newPasswordChecker() {
		String tempPassword = "";
		boolean isAlphabet = false;
		boolean isNumeric = false;
		char[] password = txtNewPass.getPassword();
		for (int i = 0; i < txtNewPass.getPassword().length; i++) {
			tempPassword += password[i];
		}
		for (int i = 0; i < txtNewPass.getPassword().length; i++) {
			if ((tempPassword.charAt(i) >= 'a' && tempPassword.charAt(i) <= 'z') || (tempPassword.charAt(i) >= 'A' && tempPassword.charAt(i) <= 'Z')) {
				isAlphabet = true;
			} else if (tempPassword.charAt(i) > 47 && tempPassword.charAt(i) < 58) {
				isNumeric = true;
			} else {
				isAlphabet = false;
			}
		} isAlphanumeric = isAlphabet && isNumeric;
		
	}

	private void oldPasswordChecker() {
		String tempPassword = "";
		char[] password = txtOldPass.getPassword();
		for (int i = 0; i < txtOldPass.getPassword().length; i++) {
			tempPassword += password[i];
		}
		String sql = String.format("SELECT UserPassword FROM user WHERE UserID = '%s'",
				User.getUserID());

		ResultSet result = connect.executeQuery(sql);

		try {
			result.next();
			if (tempPassword.equals(result.getString("UserPassword"))) {
				matchOldPass = true;
			}
			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
	}

	private void dobChecker() throws ParseException {
		
		// buat digit
		String day = txtDOBDay.getText().toString();
		String month = txtDOBMonth.getText().toString();
		String year = txtDOBYear.getText().toString();

		boolean dayIsDigit = false;
		boolean monthIsDigit = false;
		boolean yearIsDigit = false;
		
		for (int i = 0; i < day.length(); i++) {
			if (Character.isDigit(day.charAt(i))) {
				dayIsDigit = true;
			} else {
				dayIsDigit = false;
			}
		}
		
		for (int i = 0; i < month.length(); i++) {
			if (Character.isDigit(month.charAt(i))) {
				monthIsDigit = true;
			} else {
				monthIsDigit = false;
			}
		}
		
		for (int i = 0; i < year.length(); i++) {
			if (Character.isDigit(year.charAt(i))) {
				yearIsDigit = true;
			} else {
				yearIsDigit = false;
			}
		}
		
		dobIsDigit = dayIsDigit && monthIsDigit && yearIsDigit;
		
		if (txtDOBDay.getText().length() > 0 && txtDOBMonth.getText().length() > 0 && txtDOBYear.getText().length() > 0) {

			int dayValidate = Integer.parseInt(day.trim());
			int monthValidate = Integer.parseInt(month.trim());
			int yearValidate = Integer.parseInt(year.trim());
			
			if (dayValidate > 0 && dayValidate < 32) {
				dayIsCorrect = true;
			}
			
			if (monthValidate > 0 && monthValidate < 13) {
				monthIsCorrect = true;
			}
			
			if (yearValidate > 1900 && yearValidate < 2019) {
				yearIsCorrect = true;
			}
			
			String tempDate = String.valueOf(dayValidate) + "/" + String.valueOf(monthValidate) + "/" + String.valueOf(yearValidate);
			date = dateFormat.parse(tempDate);
			
			userDOB = String.valueOf(yearValidate) + "/" + String.valueOf(monthValidate) + String.valueOf(dayValidate) + "/";
		}
	}

	private void phoneChecker() {
		
		String tempPhoneNum = txtPhoneNumber.getText();
		for (int i = 0; i < txtPhoneNumber.getText().length(); i++) {
			if (Character.isDigit(tempPhoneNum.charAt(i))) {
				phoneIsDigit = true;
			} else {
				phoneIsDigit = false;
			}
		}
		
	}

	private void emailChecker() {

		String containsAt = txtEmail.getText();
		int count = 0;
		for (int i = 0; i < txtEmail.getText().length(); i++) {
			if (containsAt.charAt(i) == '@') {
				count++;
			}
		}
		if (count == 1) {
			isOnlyOneAt = true;
		}
	}
}
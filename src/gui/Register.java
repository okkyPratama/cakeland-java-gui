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

import database.ClassModel;
import database.Connect;
import model.User;

public class Register extends JFrame implements ActionListener {
	
	Connect connect = Connect.getConnection();

	private JPanel pnlTop, pnlMid, pnlBot, pnlTitle, pnlTitleForm, pnlForm, pnlUsername, pnlTxtUsername, pnlEmail, pnlEmailTxt,
			pnlGender, pnlRadioGender, pnlDOB, pnlTxtDOB, pnlAlignedDOB, pnlPhoneNumber, pnlTxtPhone, pnlAddress,
			pnlPassword, pnlConfirmPass, pnlTxtPassword, pnlTxtConfirm, pnlChckbx, pnlBtn, pnlLogin, pnlForLogin,
			pnlbtnLogin, pnlStrip1, pnlStrip2, pnlSpace1, pnlSpace2, pnlSpace3, pnlSpace4, pnlSpace5, pnlSpace6,
			pnlSpace7, pnlSpace8, pnlSpace9, pnlSpace10, pnlSpace11, pnlFormSpace1, pnlFormSpace2, pnlFormSpaceLeft,
			pnlFormSpaceRight;
	private JLabel lblTitle, lblTitleForm, lblUsername, lblEmail, lblGender, lblDOB, lblPhoneNumber, lblAddress, lblPassword,
			lblConfirmPass, lblStrip1, lblStrip2;
	private JTextField txtUsername, txtEmail, txtDOBDay, txtDOBMonth, txtDOBYear, txtPhoneNumber;
	private JTextArea txtAddress;
	private ButtonGroup buttonGroup;
	private JRadioButton rdBtnMale, rdBtnFemale;
	private JPasswordField txtPassword, txtConfirmPass;
	private JButton btnRegister, btnLogin;
	private JCheckBox chckbxAgreement;
	
	String selectedGender = "";

	Date date = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	String userDOB = "";
	
	private boolean isOnlyOneAt, dobIsDigit, phoneIsDigit, isAlphanumeric, dayIsCorrect, monthIsCorrect, yearIsCorrect = false;

	public Register() {

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
		
		btnRegister.addActionListener(this);
		btnLogin.addActionListener(this);

		setTitle("Register");
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

		pnlPassword.setBackground(Color.PINK);

		lblPassword.setForeground(new Color(0, 0, 139));

		pnlTxtPassword.setBackground(Color.PINK);

		pnlSpace7.setBackground(Color.PINK);

		txtPassword.setForeground(new Color(0, 0, 139));
		txtPassword.setBackground(new Color(255, 228, 225));
		txtPassword.setBorder(new LineBorder(Color.PINK));

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

		pnlChckbx.setBackground(Color.PINK);

		chckbxAgreement.setForeground(new Color(0, 0, 139));
		chckbxAgreement.setBackground(Color.PINK);

		pnlSpace9.setBackground(Color.PINK);

		pnlBot.setBackground(Color.PINK);

		pnlBtn.setBackground(Color.PINK);

		btnRegister.setBackground(new Color(255, 228, 225));
		btnRegister.setForeground(new Color(0, 0, 139));
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 24));

		pnlLogin.setBackground(Color.PINK);

		pnlSpace10.setBackground(Color.PINK);

		pnlSpace11.setBackground(Color.PINK);

		pnlbtnLogin.setBackground(Color.PINK);

		btnLogin.setBorder(BorderFactory.createLineBorder(Color.PINK));
		btnLogin.setForeground(new Color(0, 0, 139));
		btnLogin.setBackground(Color.PINK);
		btnLogin.setFont(new Font("Tahoma", Font.ITALIC, 15));

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
		pnlForm.setLayout(new GridLayout(8, 2));

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

		// Panel for Password
		pnlForm.add(pnlPassword);
		pnlPassword.setLayout(new GridLayout(1, 2));
		pnlPassword.add(lblPassword);
		pnlPassword.add(pnlTxtPassword);
		pnlTxtPassword.setLayout(new GridLayout(3, 1));
		pnlTxtPassword.add(pnlSpace7);
		pnlTxtPassword.add(txtPassword);

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

		// Panel for Checkbox
		pnlChckbx.add(chckbxAgreement);
		pnlMid.add(pnlChckbx, BorderLayout.SOUTH);

		// Panel for Button
		pnlBtn.add(btnRegister);

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
		pnlForLogin.add(pnlbtnLogin);
		pnlbtnLogin.add(btnLogin);
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
		pnlForm.setBackground(Color.PINK);
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
		pnlPassword = new JPanel();
		pnlTxtPassword = new JPanel();
		pnlConfirmPass = new JPanel();
		pnlTxtConfirm = new JPanel();
		pnlChckbx = new JPanel();

		// Bot
		pnlBtn = new JPanel();
		pnlLogin = new JPanel();
		pnlForLogin = new JPanel();
		pnlbtnLogin = new JPanel();

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
		pnlFormSpace1 = new JPanel();
		pnlFormSpace2 = new JPanel();
		pnlFormSpaceLeft = new JPanel();
		pnlFormSpaceRight = new JPanel();

		// Strip for DOB
		pnlStrip1 = new JPanel();
		pnlStrip2 = new JPanel();

		// Labels
		lblTitle = new JLabel("CakeLAnd");
		lblTitleForm = new JLabel("Create your own account!");
		lblUsername = new JLabel("Username");
		lblEmail = new JLabel("Email");
		lblGender = new JLabel("Gender");
		lblDOB = new JLabel("Date of Birth");
		lblPhoneNumber = new JLabel("Phone Number");
		lblAddress = new JLabel("Address");
		lblPassword = new JLabel("Password");
		lblConfirmPass = new JLabel("Confirm Password");

		// Label for Strip
		lblStrip1 = new JLabel("-");
		lblStrip2 = new JLabel("-");

		// Radio Buttons
		rdBtnMale = new JRadioButton("Male");
		rdBtnFemale = new JRadioButton("Female");

		// Checkbox
		chckbxAgreement = new JCheckBox("I Agree with Terms & Conditions");

		// Button
		btnRegister = new JButton("Register");
		btnLogin = new JButton("Already have an account? Login");

		// Text Field
		txtUsername = new JTextField();
		txtEmail = new JTextField();
		txtDOBDay = new JTextField();
		txtDOBMonth = new JTextField();
		txtDOBYear = new JTextField();
		txtPhoneNumber = new JTextField();

		// Text Arrea
		txtAddress = new JTextArea();

		// Passwords
		txtPassword = new JPasswordField();
		txtConfirmPass = new JPasswordField();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnRegister) {

			emailChecker();
			
			try {
				dobChecker();
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
			
			phoneChecker();
			passwordChecker();

			if (txtUsername.getText().isEmpty() && txtEmail.getText().isEmpty() && rdBtnMale.isSelected() == false
					&& rdBtnFemale.isSelected() == false && txtDOBDay.getText().isEmpty()
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

			else if (rdBtnFemale.isSelected() == false && rdBtnMale.isSelected() == false) {
				JOptionPane.showMessageDialog(null, "Gender must be filled", "Error", JOptionPane.WARNING_MESSAGE);
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

			else if (isAlphanumeric == false) {
				JOptionPane.showMessageDialog(null, "Password should be alphanumeric!", "Error", JOptionPane.WARNING_MESSAGE);
			}
			
			else if (!Arrays.equals(txtPassword.getPassword(), txtConfirmPass.getPassword())) {
				
				JOptionPane.showMessageDialog(null, "Password confirmation is wrong!", "Error", JOptionPane.WARNING_MESSAGE);
				
			} else if (chckbxAgreement.isSelected() == false) {
				
				JOptionPane.showMessageDialog(null, "Please check the agreement!", "Error", JOptionPane.WARNING_MESSAGE);
				
			} else {
				Random rand = new Random();
				
				String userID = "U" + rand.nextInt(10) + + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
				String username = txtUsername.getText().toString();
				String userEmail = txtEmail.getText().toString();
				
				String userPassword = "";
				char[] password = txtPassword.getPassword();
				for (int i = 0; i < txtPassword.getPassword().length; i++) {
					userPassword += password[i];
				}
				
				
				String userPhoneNumber = txtPhoneNumber.getText().toString();
				String userAddress = txtAddress.getText().toString();
				String userRole = "User";
				
				PreparedStatement ps = Connect.getConnection()
						.prepareStatement("INSERT INTO user(UserID, Username, UserEmail, UserPassword, UserGender, UserDOB, UserPhoneNumber, UserAddress, UserRole) VALUES(?,?,?,?,?,?,?,?,?) ");
				try {
					ps.setString(1, userID);
					ps.setString(2, username);
					ps.setString(3, userEmail);
					ps.setString(4, userPassword);
					ps.setString(5, selectedGender);
					ps.setDate(6, java.sql.Date.valueOf(txtDOBYear.getText().toString() + "-" + txtDOBMonth.getText().toString() + "-" + txtDOBDay.getText().toString()));
					ps.setString(7, userPhoneNumber);
					ps.setString(8, userAddress);
					ps.setString(9, userRole);
					ps.execute();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				// buat pindah form
				JOptionPane.showMessageDialog(null, "Register success!", "Success", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				new Login();
			}
			
		} else if (e.getSource() == btnLogin) {
			setVisible(false);
			new Login();
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
		
		//validasi dob itu ber range
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

	private void passwordChecker() {
		
		String tempPassword = "";
		boolean isAlphabet = false;
		boolean isNumeric = false;
		char[] password = txtPassword.getPassword();
		for (int i = 0; i < txtPassword.getPassword().length; i++) {
			tempPassword += password[i];
		}
		for (int i = 0; i < txtPassword.getPassword().length; i++) {
			if ((tempPassword.charAt(i) >= 'a' && tempPassword.charAt(i) <= 'z') || (tempPassword.charAt(i) >= 'A' && tempPassword.charAt(i) <= 'Z')) {
				isAlphabet = true;
			} else if (tempPassword.charAt(i) > 47 && tempPassword.charAt(i) < 58) {
				isNumeric = true;
			} else {
				isAlphabet = false;
			}
		} isAlphanumeric = isAlphabet && isNumeric;
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

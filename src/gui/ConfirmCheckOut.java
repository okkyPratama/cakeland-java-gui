package gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import database.ClassModel;
import database.Connect;
import model.Cart;
import model.User;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ConfirmCheckOut extends JFrame implements ActionListener {

	Connect conn = Connect.getConnection();

	JTable cakeTable;
	DefaultTableModel cakeTableModel;

	JTextField txtTotal;
	JTextField txtPickUpDate;
	JPanel pnlAtas, pnlCheckout, pnlTable, pnlBawah, pnlSpaceAtas, pnlTitle, pnlYourOrder, pnlAngkaTotal, pnlBtn,
			pnlTotal, pnlSpaceTotal1, pnlPickUpDate, pnlContainerCheck, pnlBtnCheckout, pnlBtnMainMenu, pnlSpasiKiri;
	JLabel lblTitle, lblYourOrder, lblTotal, lblPickUp;
	JButton btnCheckout, btnMenu;
	JScrollPane scrollPane;

	Date date = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	Vector<Cart> cartList = new Vector<>();

	public ConfirmCheckOut() {
		setBackground(Color.PINK);

		initVar();
		setUpTable();
		setUpPanel();
		setUpStyle();

		btnMenu.addActionListener(this);
		btnCheckout.addActionListener(this);

		setTitle("CakeLAnd");
		setSize(1000, 575);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void setUpStyle() {

		pnlAtas.setBackground(Color.PINK);

		pnlSpaceAtas.setBackground(Color.PINK);

		pnlTitle.setBackground(Color.PINK);

		lblTitle.setForeground(new Color(205, 92, 92));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));

		cakeTable.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cakeTable.setBackground(new Color(255, 192, 203));
		cakeTable.getTableHeader().setBackground(Color.PINK);

		pnlYourOrder.setBackground(Color.PINK);

		lblYourOrder.setForeground(new Color(0, 0, 128));
		lblYourOrder.setFont(new Font("Tahoma", Font.BOLD, 25));

		pnlCheckout.setBackground(Color.PINK);

		pnlAngkaTotal.setBackground(Color.PINK);

		pnlTotal.setBackground(Color.PINK);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTotal.setForeground(new Color(0, 0, 128));

		txtTotal.setEditable(false);
		txtTotal.setColumns(10);

		pnlSpaceTotal1.setBackground(Color.PINK);

		pnlPickUpDate.setBackground(Color.PINK);

		lblPickUp.setForeground(new Color(0, 0, 128));
		lblPickUp.setFont(new Font("Tahoma", Font.BOLD, 17));

		txtPickUpDate.setEditable(false);
		txtPickUpDate.setColumns(10);

		pnlBtn.setBackground(Color.PINK);

		pnlContainerCheck.setBackground(Color.PINK);

		pnlBtnCheckout.setBackground(Color.PINK);

		btnCheckout.setForeground(new Color(0, 0, 128));
		btnCheckout.setFont(new Font("Tahoma", Font.BOLD, 20));

		pnlBtnMainMenu.setBackground(Color.PINK);

		btnMenu.setForeground(new Color(0, 0, 128));
		btnMenu.setFont(new Font("Tahoma", Font.BOLD, 20));

		pnlTable.setBackground(Color.PINK);

		pnlSpasiKiri.setBackground(Color.PINK);

		pnlBawah.setBackground(Color.PINK);

	}

	private void setUpPanel() {

		add(pnlAtas, BorderLayout.NORTH);
		add(pnlCheckout, BorderLayout.CENTER);
		add(pnlBawah, BorderLayout.SOUTH);
		add(pnlTable, BorderLayout.WEST);

		pnlAtas.add(pnlSpaceAtas);
		pnlAtas.add(pnlTitle);
		pnlTitle.add(lblTitle);
		pnlAtas.add(pnlYourOrder);

		pnlYourOrder.add(lblYourOrder);
		pnlAngkaTotal.add(pnlTotal);
		pnlTotal.add(lblTotal);

		pnlCheckout.setLayout(new BorderLayout());

		pnlAngkaTotal.add(txtTotal);
		pnlAngkaTotal.add(pnlSpaceTotal1);
		pnlAngkaTotal.add(pnlPickUpDate);

		pnlCheckout.add(pnlAngkaTotal, BorderLayout.NORTH);
		pnlCheckout.add(pnlBtn, BorderLayout.CENTER);
		pnlBtnCheckout.add(btnCheckout);
		pnlBtnMainMenu.add(btnMenu);
		pnlBtn.add(pnlContainerCheck);
		pnlContainerCheck.add(pnlBtnCheckout);
		pnlContainerCheck.add(pnlBtnMainMenu);

		pnlPickUpDate.add(lblPickUp);
		pnlAngkaTotal.add(txtPickUpDate);
		pnlTable.setLayout(new BorderLayout());
		pnlTable.add(pnlSpasiKiri, BorderLayout.WEST);

		pnlTable.add(scrollPane);

		pnlAtas.setLayout(new GridLayout(4, 1));
		pnlAngkaTotal.setLayout(new GridLayout(2, 3));
		pnlBtn.setLayout(new GridLayout(2, 1));
		pnlContainerCheck.setLayout(new GridLayout(2, 1));

	}

	private void setUpTable() {
		String[] header = { "Cake Name", "Cake Shape", "Cake Size", "Cake Price", "Quantity" };
		cakeTableModel = new DefaultTableModel(header, 5);
		cakeTable.setModel(cakeTableModel);
		fillTable();
		scrollPane = new JScrollPane(cakeTable);

	}

	private void fillTable() {

		cakeTableModel.setRowCount(0);
		cartList = ClassModel.getCartList();

		String query = String.format("SELECT ck.CakeName, ck.CakeShape, ck.CakeSize, ck.CakePrice, cr.Quantity "
				+ "FROM cart cr JOIN user us ON cr.UserID = us.UserID " + "JOIN cake ck ON cr.CakeID = ck.CakeID "
				+ "WHERE cr.UserID = '%s'", User.getUserID());

		ResultSet rs = conn.executeQuery(query);
		try {
			while (rs.next()) {
				Object ck[] = new Object[] { rs.getString("ck.CakeName"), rs.getString("ck.CakeShape"),
						rs.getString("ck.CakeSize"), rs.getInt("ck.CakePrice"), rs.getInt("cr.Quantity")

				};
				cakeTableModel.addRow(ck);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void initVar() {

		pnlAtas = new JPanel();
		pnlSpaceAtas = new JPanel();
		pnlTitle = new JPanel();
		pnlYourOrder = new JPanel();
		pnlCheckout = new JPanel();
		pnlAngkaTotal = new JPanel();
		pnlTotal = new JPanel();
		pnlBtn = new JPanel();
		pnlContainerCheck = new JPanel();
		pnlBtnCheckout = new JPanel();
		pnlSpaceTotal1 = new JPanel();
		pnlPickUpDate = new JPanel();
		pnlBtnMainMenu = new JPanel();
		pnlTable = new JPanel();
		pnlSpasiKiri = new JPanel();
		cakeTable = new JTable();
		pnlBawah = new JPanel();

		lblTitle = new JLabel("CakeLAnd");
		lblYourOrder = new JLabel("Your Order");
		lblTotal = new JLabel("Total");
		lblPickUp = new JLabel("Pick Up Date");

		txtTotal = new JTextField();
		setTextTotal();

		try {
			String stringDate = dateFormat.format(date);
			Calendar c = Calendar.getInstance();
			c.setTime(dateFormat.parse(stringDate));
			c.add(Calendar.DATE, 3);
			stringDate = dateFormat.format(c.getTime());
			txtPickUpDate = new JTextField(stringDate);
		} catch (Exception e) {
			// TODO: handle exception
		}

		btnCheckout = new JButton("Checkout");
		btnMenu = new JButton("Back to Main Menu");

	}

	private void setTextTotal() {

		// total calculator
		String sql = String.format(
				"SELECT SUM(cr.Quantity * ca.CakePrice) AS Total " + "FROM cart cr "
						+ "JOIN cake ca ON cr.CakeID = cr.CakeID  " + "WHERE cr.UserID = '%s'" + "GROUP BY cr.UserID",
				User.getUserID());

		ResultSet rs = conn.executeQuery(sql);

		try {
			while (rs.next()) {
				txtTotal.setText("Rp " + rs.getString("Total"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnMenu) {
			setVisible(false);
			new MainForm();
		}

		if (e.getSource() == btnCheckout) {
			
			Random rand = new Random();
			
			String transactionID = "T" + rand.nextInt(10) + + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
			
			String transactionDate = dateFormat.format(date);

			PreparedStatement ps1 = Connect.getConnection().prepareStatement("INSERT INTO transactionheader(TransactionID, UserID, TransactionDate, PickUpDate) VALUES (?,?,?,?)");
			try {
				ps1.setString(1, transactionID);
				ps1.setString(2, User.getUserID());
				ps1.setString(3, transactionDate);
				ps1.setString(4, txtPickUpDate.getText().toString());
				ps1.execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String query = String.format("SELECT cr.CakeID, cr.Quantity "
					+ "FROM cart cr JOIN user us ON cr.UserID = us.UserID "
					+ "JOIN cake ck ON cr.CakeID = ck.CakeID "
					+ "WHERE cr.UserID = '%s'", User.getUserID());

			ResultSet rs = conn.executeQuery(query);
			try {
				
				while (rs.next()) {
					
					PreparedStatement ps2 = Connect.getConnection().prepareStatement("INSERT INTO transactiondetail(TransactionID, CakeID, Quantity) VALUES (?,?,?)");

					try {
						ps2.setString(1, transactionID);
						ps2.setString(2, rs.getString("CakeID"));
						ps2.setString(3, rs.getString("Quantity"));
						ps2.execute();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			PreparedStatement ps3 = Connect.getConnection().prepareStatement("DELETE FROM cart WHERE UserID = ?");

			try {
				ps3.setString(1, User.getUserID());
				ps3.execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			JOptionPane.showMessageDialog(this, "Transaction successfull! Remember to pick up your order! :)",
					"Success", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			new MainForm();

		}

	}

}

package gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.jdbc.PreparedStatement;
import java.sql.*;

import database.ClassModel;
import database.Connect;
import model.Cake;
import model.Cart;
import model.User;

import java.awt.FlowLayout;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class ManageCart extends JFrame implements ActionListener, MouseListener {
	private JPanel pnlRight, pnlLeft, pnlBottom, pnlMid, pnlTop, pnlBtnContainer, pnlTableContainer, pnlAllBtn, pnlQty,
			SpaceBawah, spaceAtas2, spaceAtas1, pnlQuantityContainer, pnlSpaceAtas, pnlButton, pnlTitle, pnlBtn,
			pnlBtnSpaceMid, pnlBtnSpaceKiri, pnlYourCart;

	private JButton btnView, btnCheckout, btnUpdate, btnRemove, btnBacktomenu;

	private JSpinner spinner;
	private JLabel lblQty, lblYourCart, lblTitle;
	private JScrollPane scrollPane;
	private JTable cakeTable;
	private DefaultTableModel cakeTableModel;

	public static String chosenValue;

	Vector<Cart> cartList = new Vector<>();
	Connect connection = Connect.getConnection();

	public ManageCart() {
		setFont(new Font("Tahoma", Font.PLAIN, 16));
		setBackground(Color.PINK);

		initvar();
		setTable();
		setPanel();
		setStyle();

		getContentPane().add(pnlTop, BorderLayout.NORTH);
		getContentPane().add(pnlMid, BorderLayout.CENTER);
		getContentPane().add(pnlBottom, BorderLayout.SOUTH);
		getContentPane().add(pnlLeft, BorderLayout.WEST);
		getContentPane().add(pnlRight, BorderLayout.EAST);

		btnView.addActionListener(this);
		btnCheckout.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnRemove.addActionListener(this);
		btnBacktomenu.addActionListener(this);
		cakeTable.addMouseListener(this);

		setTitle("CakeLAnd");
		setSize(760, 560);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setVisible(true);

	}

	public void initvar() {
		pnlTop = new JPanel();
		pnlSpaceAtas = new JPanel();
		pnlButton = new JPanel();
		pnlBtnSpaceKiri = new JPanel();
		pnlBtnSpaceMid = new JPanel();
		pnlBtn = new JPanel();
		btnBacktomenu = new JButton("Back to Main Menu");
		pnlTitle = new JPanel();
		lblTitle = new JLabel("CakeLAnd");
		pnlYourCart = new JPanel();
		lblYourCart = new JLabel("Your Cart");

		pnlMid = new JPanel();
		pnlTableContainer = new JPanel();
		scrollPane = new JScrollPane();
		pnlBtnContainer = new JPanel();
		pnlQty = new JPanel();
		pnlQuantityContainer = new JPanel();
		spaceAtas1 = new JPanel();
		spaceAtas2 = new JPanel();
		lblQty = new JLabel("Quantity");
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		SpaceBawah = new JPanel();
		pnlAllBtn = new JPanel();
		btnRemove = new JButton("Remove from Cart");

		btnUpdate = new JButton("Update Cake Order");
		btnCheckout = new JButton("Check Out");
		btnView = new JButton("View All Menu");

		pnlBottom = new JPanel();
		pnlLeft = new JPanel();
		pnlRight = new JPanel();

	}

	public void setTable() {
		cakeTable = new JTable();
		String[] cakeHeader = { "Cake Name", "Cake Shape", "Cake Size", "Cake Price", "Quantity" };
		cakeTableModel = new DefaultTableModel(cakeHeader, 5);
		cakeTable.setModel(cakeTableModel);
		fillTable();
		scrollPane.setViewportView(cakeTable);

	}

	public void fillTable() {

		cakeTableModel.setRowCount(0);
		cartList = ClassModel.getCartList();

		String query = String.format("" + "SELECT ck.CakeName, ck.CakeShape, ck.CakeSize, ck.CakePrice, cr.Quantity "
				+ "FROM cart cr JOIN user us " + "ON cr.UserID = us.UserID JOIN cake ck "
				+ "ON cr.CakeID = ck.CakeID;");

		ResultSet rs = connection.executeQuery(query);
		try {
			while (rs.next()) {
				Object ck[] = new Object[] { rs.getString("CakeName"), rs.getString("CakeShape"),
						rs.getString("CakeSize"), rs.getInt("CakePrice"), rs.getInt("Quantity")

				};
				cakeTableModel.addRow(ck);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setPanel() {
		pnlTop.setLayout(new GridLayout(4, 1, 0, 0));
		pnlTop.add(pnlSpaceAtas);

		pnlTop.add(pnlButton);
		pnlButton.setLayout(new GridLayout(1, 3, 0, 0));

		pnlButton.add(pnlBtnSpaceKiri);

		pnlButton.add(pnlBtnSpaceMid);

		pnlButton.add(pnlBtn);

		pnlBtn.add(btnBacktomenu);
		pnlTop.add(pnlTitle);

		pnlTitle.add(lblTitle);

		pnlTop.add(pnlYourCart);

		pnlYourCart.add(lblYourCart);

		pnlMid.setLayout(new GridLayout(1, 2, 0, 0));

		pnlMid.add(pnlTableContainer);
		pnlTableContainer.setLayout(new GridLayout(0, 1, 0, 0));

		pnlTableContainer.add(scrollPane);

		pnlMid.add(pnlBtnContainer);
		pnlBtnContainer.setLayout(new GridLayout(3, 2));

		pnlBtnContainer.add(pnlQty);
		pnlQty.setLayout(new GridLayout(1, 2, 0, 0));

		pnlQty.add(pnlQuantityContainer);
		pnlQuantityContainer.setLayout(new GridLayout(4, 1, 0, 0));

		pnlQuantityContainer.add(spaceAtas1);

		pnlQuantityContainer.add(spaceAtas2);

		pnlQuantityContainer.add(lblQty);
		lblQty.setHorizontalAlignment(SwingConstants.CENTER);

		pnlQuantityContainer.add(spinner);

		pnlQuantityContainer.add(SpaceBawah);
		pnlBtnContainer.add(pnlAllBtn);
		pnlAllBtn.setLayout(new GridLayout(4, 1, 0, 0));

		pnlAllBtn.add(btnView);
		pnlAllBtn.add(btnRemove);

		pnlAllBtn.add(btnUpdate);

		pnlAllBtn.add(btnCheckout);

	}

	public void setStyle() {
		pnlTop.setBackground(Color.PINK);
		pnlSpaceAtas.setBackground(Color.PINK);
		pnlSpaceAtas.setForeground(Color.PINK);
		pnlBtnSpaceKiri.setBackground(Color.PINK);
		pnlBtnSpaceMid.setBackground(Color.PINK);
		pnlBtn.setBackground(Color.PINK);

		btnBacktomenu.setForeground(new Color(0, 0, 128));
		btnBacktomenu.setFont(new Font("Tahoma", Font.PLAIN, 17));

		pnlTitle.setBackground(Color.PINK);
		lblTitle.setForeground(new Color(233, 150, 122));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));

		pnlYourCart.setBackground(Color.PINK);
		lblYourCart.setForeground(new Color(0, 0, 128));
		lblYourCart.setFont(new Font("Tahoma", Font.BOLD, 30));

		pnlMid.setBackground(Color.PINK);
		pnlTableContainer.setBackground(Color.PINK);

		pnlBtnContainer.setBackground(Color.PINK);
		pnlQuantityContainer.setBackground(Color.PINK);
		spaceAtas1.setBackground(Color.PINK);
		spaceAtas2.setBackground(Color.PINK);
		lblQty.setBackground(Color.PINK);
		lblQty.setForeground(new Color(0, 0, 128));
		lblQty.setFont(new Font("Tahoma", Font.BOLD, 16));
		SpaceBawah.setBackground(Color.PINK);
		btnRemove.setForeground(new Color(0, 0, 128));
		btnUpdate.setForeground(new Color(0, 0, 128));
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCheckout.setForeground(new Color(0, 0, 128));
		btnCheckout.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnView.setForeground(new Color(0, 0, 128));
		cakeTable.setBackground(new Color(255, 192, 203));
		cakeTable.getTableHeader().setOpaque(false);
		cakeTable.getTableHeader().setBackground(Color.PINK);
		DefaultTableCellRenderer header = new DefaultTableCellRenderer();
		header.setFont(header.getFont().deriveFont(Font.BOLD));
		pnlBottom.setBackground(Color.PINK);
		pnlLeft.setBackground(Color.PINK);
		pnlRight.setBackground(Color.PINK);
		btnView.setFont(new Font("Tahoma", Font.PLAIN, 20));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBacktomenu) {
			setVisible(false);
			new MainForm();
		}

		if (e.getSource() == btnCheckout) {
			setVisible(false);
			new ConfirmCheckOut();
		}

		if (e.getSource() == btnView) {
			setVisible(false);
			new ViewAllMenu();
		}

		if (e.getSource() == btnRemove) {
			if (cakeTable.getSelectionModel().isSelectionEmpty()) {
				JOptionPane.showMessageDialog(this, "Select the cake you want to cancel", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else if (cakeTable.getSelectionModel() != null) {
				int selected = cakeTable.getSelectedRow();
				String cakeName = cakeTable.getValueAt(selected, 0).toString();

				String query = String.format(
						"DELETE FROM cart WHERE CakeID = (SELECT CakeID FROM cake WHERE CakeName = ?) AND UserID = ?");
				java.sql.PreparedStatement ps = Connect.getConnection().prepareStatement(query);

				try {
					ps.setString(1, cakeName);
					ps.setString(2, User.getUserID());
					ps.executeUpdate();
				} catch (SQLException e2) {
					System.out.println(e2);
				}

				fillTable();

				JOptionPane.showMessageDialog(this, "Cake successfully remove from your cart!", "Success",
						JOptionPane.INFORMATION_MESSAGE);

			}
		}

		if (e.getSource() == btnUpdate) {
			if (cakeTable.getSelectionModel().isSelectionEmpty()) {
				JOptionPane.showMessageDialog(this, "Select the cake you want to update", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {

				int selected = cakeTable.getSelectedRow();
				String cakeName = cakeTable.getValueAt(selected, 0).toString();

				int quantity = (int) spinner.getValue();

				String query = String.format("UPDATE cart SET Quantity= ? " + "WHERE CakeID = "
						+ "(SELECT CakeID FROM cake WHERE CakeName = ?)" + "AND UserID = ?");

				java.sql.PreparedStatement ps = Connect.getConnection().prepareStatement(query);

				try {

					ps.setInt(1, quantity);
					ps.setString(2, cakeName);
					ps.setString(3, User.getUserID());
					ps.execute();

				} catch (SQLException e2) {
					System.out.println(e2);
				}
				fillTable();

				JOptionPane.showMessageDialog(this, "Cake successfully updated!", "Success",
						JOptionPane.INFORMATION_MESSAGE);

			}

		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}

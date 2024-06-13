package gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import database.ClassModel;
import database.Connect;
import model.Cake;
import model.TransactionHistory;
import model.User;

import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class TransactionForm extends JFrame implements ActionListener, MouseListener, KeyListener {
	private JPanel pnlTengah, pnlTop, pnlSpasiAtas, pnlTitle, pnlTransHis, pnlMid, pnlTableTrans, pnlContainTrans,
			pnlContainSelect, pnlSelectId, pnlTableCake, pnlContainCake, pnlContainTotal, pnlTotal, pnlBot, pnlMenu,
			pnlSpasi1, pnlSpasi2, pnlSpasi3, pnlKiri, pnlSpasiKiri, pnlKanan, pnlSpasiKanan, pnlBawah, pnlSpasiBawah;

	private JLabel lblTitle, lblTransHis, lblSelectId, lblTotal;
	private JTable tableTrans;
	private DefaultTableModel transTableModel, cakeTableModel;
	private JTextField txtSelectId;
	private JTable tableCake;
	private JTextField txtTotal;
	private JButton btnMenu;
	private JScrollPane scrollPaneCake, scrollPaneTrans;

	Connect conn = Connect.getConnection();

	Vector<TransactionHistory> transList = new Vector<>();

	public TransactionForm() {
		setBackground(Color.PINK);
		
		initVar();
		setPanel();
		setTable();
		setStyle();

		tableTrans.addMouseListener(this);
		btnMenu.addActionListener(this);
		txtSelectId.addKeyListener(this);
		txtTotal.addKeyListener(this);

		setTitle("CakeLAnd");
		setSize(800, 750);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void setStyle() {
		pnlTengah.setBackground(Color.PINK);
		pnlTop.setBackground(Color.PINK);
		pnlSpasiAtas.setBackground(Color.PINK);
		pnlTitle.setForeground(new Color(0, 0, 128));
		pnlTitle.setBackground(Color.PINK);

		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblTitle.setForeground(new Color(128, 0, 0));
		pnlTransHis.setBackground(Color.PINK);
		lblTransHis.setForeground(new Color(0, 0, 128));
		lblTransHis.setFont(new Font("Tahoma", Font.BOLD, 26));
		pnlMid.setBackground(Color.PINK);
		pnlContainTrans.setBackground(Color.PINK);

		tableTrans.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tableTrans.setBackground(new Color(255, 192, 203));
		tableTrans.getTableHeader().setOpaque(false);
		tableTrans.getTableHeader().setBackground(Color.PINK);
		DefaultTableCellRenderer headerTrans = new DefaultTableCellRenderer();
		headerTrans.setFont(headerTrans.getFont().deriveFont(Font.BOLD));

		tableCake.setBackground(new Color(255, 192, 203));
		tableCake.getTableHeader().setOpaque(false);
		tableCake.getTableHeader().setBackground(Color.PINK);
		DefaultTableCellRenderer headerCake = new DefaultTableCellRenderer();
		headerCake.setFont(headerCake.getFont().deriveFont(Font.BOLD));

		pnlContainCake.setBackground(Color.PINK);
		pnlContainTotal.setBackground(Color.PINK);
		pnlTotal.setBackground(Color.PINK);
		lblTotal.setForeground(new Color(0, 0, 128));
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnlBot.setBackground(Color.PINK);
		pnlMenu.setBackground(Color.PINK);
		pnlSpasi1.setBackground(Color.PINK);
		pnlSpasi2.setBackground(Color.PINK);
		pnlSpasi3.setBackground(Color.PINK);

		pnlContainSelect.setBackground(Color.PINK);
		pnlSelectId.setBackground(Color.PINK);
		lblSelectId.setForeground(new Color(0, 0, 128));
		lblSelectId.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnMenu.setForeground(new Color(0, 0, 128));
		btnMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlKiri.setBackground(Color.PINK);
		pnlSpasiKiri.setBackground(Color.PINK);
		pnlKanan.setBackground(Color.PINK);
		pnlSpasiKanan.setBackground(Color.PINK);
		pnlBawah.setBackground(Color.PINK);
		pnlSpasiBawah.setBackground(Color.PINK);

	}

	private void initVar() {
		pnlTengah = new JPanel();
		pnlTop = new JPanel();
		pnlSpasiAtas = new JPanel();
		pnlTitle = new JPanel();
		lblTitle = new JLabel("CakeLAnd");
		pnlTransHis = new JPanel();
		lblTransHis = new JLabel("Transaction History");
		pnlMid = new JPanel();

		pnlTableTrans = new JPanel();
		pnlContainTrans = new JPanel();
		scrollPaneTrans = new JScrollPane();
		pnlContainSelect = new JPanel();

		pnlSelectId = new JPanel();
		lblSelectId = new JLabel("Selected ID");
		txtSelectId = new JTextField();
		pnlTableCake = new JPanel();
		pnlContainCake = new JPanel();
		scrollPaneCake = new JScrollPane();

		pnlContainTotal = new JPanel();
		pnlTotal = new JPanel();
		lblTotal = new JLabel("Total");
		txtTotal = new JTextField();
		pnlBot = new JPanel();
		pnlMenu = new JPanel();
		pnlSpasi1 = new JPanel();
		pnlSpasi2 = new JPanel();
		pnlSpasi3 = new JPanel();

		btnMenu = new JButton("Back to Main Menu");
		pnlKiri = new JPanel();
		pnlSpasiKiri = new JPanel();
		pnlKanan = new JPanel();
		pnlSpasiKanan = new JPanel();
		pnlBawah = new JPanel();
		pnlSpasiBawah = new JPanel();
	}

	private void setTable() {
		// Transaction History Table
		tableTrans = new JTable();
		String[] transHeader = { "Transaction ID", "Transaction Date", "Pick Up Date" };
		transTableModel = new DefaultTableModel(transHeader, 0);
		tableTrans.setModel(transTableModel);
		fillTable();
		scrollPaneTrans.setViewportView(tableTrans);

		// Cake Data Table
		tableCake = new JTable();
		String[] cakeHeader = { "Cake Name", "Cake Size", "Cake Shape", "Cake Price", "Quantity", "Subtotal" };
		cakeTableModel = new DefaultTableModel(cakeHeader, 0);
		tableCake.setModel(cakeTableModel);
		fillTable();
		scrollPaneCake.setViewportView(tableCake);
	}

	private void fillTable() {

		// Fill Transaction table
		transTableModel.setRowCount(0);
		transList = ClassModel.getTransHis();

		String query = String.format(
				"SELECT TransactionID, TransactionDate,PickUpDate FROM transactionheader WHERE UserID = '%s'",
				User.getUserID());

		ResultSet rs = conn.executeQuery(query);
		try {
			while (rs.next()) {
				Object ck[] = new Object[] { rs.getString("TransactionID"), rs.getString("TransactionDate"),
						rs.getString("PickUpDate") };
				transTableModel.addRow(ck);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	};

	private void setPanel() {

		setLayout(new BorderLayout());
		
		add(pnlKiri, BorderLayout.WEST);
		add(pnlTengah, BorderLayout.CENTER);
		add(pnlKanan, BorderLayout.EAST);
		add(pnlBawah, BorderLayout.SOUTH);
		
		pnlTengah.setLayout(new BorderLayout());
		pnlTengah.add(pnlTop, BorderLayout.NORTH);
		pnlTengah.add(pnlMid, BorderLayout.CENTER);
		pnlTengah.add(pnlBot, BorderLayout.SOUTH);

		pnlTop.setLayout(new GridLayout(0, 1));
		pnlMid.setLayout(new GridLayout(2, 1));
		pnlContainTrans.setLayout(new GridLayout(1, 1));
		pnlContainCake.setLayout(new GridLayout(1, 1));
		pnlBot.setLayout(new GridLayout(0, 1));
		pnlMenu.setLayout(new GridLayout(0, 4));

		pnlContainSelect.setLayout(new BorderLayout());
		pnlTableTrans.setLayout(new BorderLayout());
		pnlTableCake.setLayout(new BorderLayout());
		pnlContainTotal.setLayout(new BorderLayout());
		
		pnlTop.add(pnlSpasiAtas);
		pnlTop.add(pnlTitle);
		pnlTitle.add(lblTitle);
		pnlTop.add(pnlTransHis);

		pnlTransHis.add(lblTransHis);


		pnlMid.add(pnlTableTrans);

		pnlTableTrans.add(pnlContainTrans, BorderLayout.CENTER);

		pnlContainTrans.add(scrollPaneTrans);

		pnlTableTrans.add(pnlContainSelect, BorderLayout.SOUTH);

		pnlContainSelect.add(pnlSelectId, BorderLayout.WEST);

		pnlSelectId.add(lblSelectId);

		txtSelectId.setColumns(15);
		pnlSelectId.add(txtSelectId);

		pnlMid.add(pnlTableCake);

		pnlTableCake.add(pnlContainCake, BorderLayout.CENTER);

		pnlContainCake.add(scrollPaneCake);

		pnlTableCake.add(pnlContainTotal, BorderLayout.SOUTH);

		pnlContainTotal.add(pnlTotal, BorderLayout.WEST);

		pnlTotal.add(lblTotal);

		pnlTotal.add(txtTotal);
		txtTotal.setColumns(20);


		pnlBot.add(pnlMenu);

		pnlMenu.add(pnlSpasi1);

		pnlMenu.add(pnlSpasi2);

		pnlMenu.add(pnlSpasi3);

		pnlMenu.add(btnMenu);

		pnlKiri.add(pnlSpasiKiri);

		pnlKanan.add(pnlSpasiKanan);

		pnlBawah.add(pnlSpasiBawah);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMenu) {
			setVisible(false);
			new MainForm();

			if (e.getSource() == tableTrans) {
				int selected = tableTrans.getSelectedRow();
				txtSelectId.setText(tableTrans.getValueAt(selected, 0).toString());

			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tableTrans) {

			cakeTableModel.setRowCount(0);
			int selected = tableTrans.getSelectedRow();
			String  transactionId = tableTrans.getValueAt(selected, 0).toString();
			
			String query = String.format(
					"SELECT ca.CakeName, ca.CakeSize, ca.CakeShape, ca.CakePrice, td.Quantity, SUM(ca.CakePrice*td.Quantity) AS `Sub Total` "
					+ "FROM transactiondetail td JOIN cake ca ON td.CakeID = ca.CakeID "
					+ "JOIN transactionheader th ON td.TransactionID = th.TransactionID "
					+ "WHERE th.TransactionID = '%s' AND th.UserID = '%s' "
					+ "GROUP BY ca.CakeName, ca.CakeSize, ca.CakeShape, ca.CakePrice, td.Quantity",
					transactionId,User.getUserID());

			ResultSet rs = conn.executeQuery(query);
			try {
				while (rs.next()) {
					Object ck[] = new Object[] { rs.getString("ca.CakeName"), rs.getString("ca.CakeSize"),
							rs.getString("ca.CakeShape"), rs.getString("ca.CakePrice"), rs.getString("td.Quantity")
							, rs.getString("Sub Total") };
					cakeTableModel.addRow(ck);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			txtSelectId.setText(transactionId);
			
			// total calculator
			String sql = String.format(
					"SELECT SUM(td.Quantity * ca.CakePrice) AS Total "
					+ "FROM transactiondetail td JOIN cake ca ON td.CakeID = ca.CakeID "
					+ "JOIN transactionheader th ON td.TransactionID = th.TransactionID "
					+ "WHERE th.TransactionID = '%s'",
					transactionId);

			ResultSet rs1 = conn.executeQuery(sql);

			try {
				while (rs1.next()) {
					txtTotal.setText("Rp " + rs1.getString("Total"));
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}

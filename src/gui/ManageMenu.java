package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import database.ClassModel;
import database.Connect;
import model.Cake;
import model.User;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class ManageMenu extends JFrame implements ActionListener, MouseListener {
	private JPanel panelSpasiAtas, panelSpasiAdd, panelAddCake, panelSpasiRemove, panelRemoveCake, panelBtn,
			panelSpasiBawah, panelSpasiKanan, panelSpasiKiri, panelAtas, panelTengah, panelBtnMainMenu, panelSpaceMenu,
			panelMainMenu, panelTitle, panelCakeList, panelBawah, panelKiri, panelKanan, panelTable, panelInput,
			panelLabel, panelField;

	private JLabel lblTitle, lblCakeList, lblName, lblPrice, lblShape, lblOval, lblRectangle;

	private JTextField txtPrice, txtName;
	private JButton btnAddCake, btnRemove, btnMainMenu;
	private JTable cakeTable;
	private DefaultTableModel tableCakeModel;
	private JComboBox cmbShape, cmbOvalSize, cmbRectaSize;
	private JScrollPane scrollPane;

	Connect conn = Connect.getConnection();

	Vector<Cake> cakeList = new Vector<>();
	boolean invalid = false;

	public ManageMenu() {

		initVar();
		setTable();
		setPanel();
		setStyle();

		cakeTable.addMouseListener(this);
		btnAddCake.addActionListener(this);
		btnRemove.addActionListener(this);
		btnMainMenu.addActionListener(this);
		cmbShape.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (cmbShape.getSelectedItem().equals("Oval")) {
					cmbOvalSize.setEnabled(true);
					cmbRectaSize.setEnabled(false);
				} else if (cmbShape.getSelectedItem().equals("Rectangle")) {
					cmbOvalSize.setEnabled(false);
					cmbRectaSize.setEnabled(true);
				} else {
					cmbOvalSize.setEnabled(false);
					cmbRectaSize.setEnabled(false);
				}
			}
		});

		setTitle("CakeLand");
		setSize(770, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true); // TODO Auto-generated constructor stub
	}

	public void initVar() {
		panelAtas = new JPanel();
		panelSpasiAtas = new JPanel();
		panelBtnMainMenu = new JPanel();
		panelSpaceMenu = new JPanel();
		panelMainMenu = new JPanel();
		btnMainMenu = new JButton("Back to Main Menu");
		panelTitle = new JPanel();
		lblTitle = new JLabel("CakeLAnd");
		panelCakeList = new JPanel();

		lblCakeList = new JLabel("Cake List");
		panelBawah = new JPanel();
		panelSpasiBawah = new JPanel();
		panelKiri = new JPanel();
		panelSpasiKiri = new JPanel();
		panelKanan = new JPanel();
		panelSpasiKanan = new JPanel();
		panelTengah = new JPanel();

		panelTable = new JPanel();
		scrollPane = new JScrollPane();
		panelInput = new JPanel();
		panelLabel = new JPanel();

		lblName = new JLabel("Cake Name");
		lblPrice = new JLabel("Cake Price");
		lblShape = new JLabel("Shape");
		lblOval = new JLabel("Oval Size");
		lblRectangle = new JLabel("Rectangle Size");

		panelField = new JPanel();
		txtName = new JTextField();
		txtPrice = new JTextField();
		cmbShape = new JComboBox();
		cmbOvalSize = new JComboBox();
		cmbRectaSize = new JComboBox();

		cmbOvalSize.setEnabled(false);
		cmbRectaSize.setEnabled(false);

		cmbShape.setModel(new DefaultComboBoxModel(new String[] { "Select Shape", "Oval", "Rectangle" }));
		cmbOvalSize.setModel(new DefaultComboBoxModel(new String[] { "Select Size", "15 cm", "20 cm", "25 cm" }));
		cmbRectaSize.setModel(
				new DefaultComboBoxModel(new String[] { "Select Size", "10 x 10 cm", "20 x 20 cm", "30 x 30 cm" }));

		panelBtn = new JPanel();
		panelRemoveCake = new JPanel();
		panelSpasiRemove = new JPanel();

		btnRemove = new JButton("Remove Cake");
		panelAddCake = new JPanel();
		btnAddCake = new JButton("Add Cake");
		panelSpasiAdd = new JPanel();
	};

	public void setTable() {
		cakeTable = new JTable();
		String[] tableHeader = { "Cake ID", "Cake Name", "Cake Price", "Cake Shape", "Cake Size" };
		tableCakeModel = new DefaultTableModel(tableHeader, 0);
		cakeTable.setModel(tableCakeModel);
		cakeTable.setPreferredSize(new Dimension(400, 250));
		fillTable();
		scrollPane.setViewportView(cakeTable);

	};

	public void fillTable() {

		tableCakeModel.setRowCount(0);
		cakeList = ClassModel.getCakeList();

		String query = String.format("SELECT CakeID,CakeName,CakePrice,CakeShape,CakeSize FROM cake");
		ResultSet rs = conn.executeQuery(query);
		try {
			while (rs.next()) {
				Object ck[] = new Object[] { rs.getString("CakeID"), rs.getString("CakeName"), rs.getInt("CakePrice"),
						rs.getString("CakeShape"), rs.getString("CakeSize") };
				tableCakeModel.addRow(ck);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setPanel() {
		setLayout(new BorderLayout());
		add(panelAtas, BorderLayout.NORTH);
		add(panelBawah, BorderLayout.SOUTH);
		add(panelKiri, BorderLayout.WEST);
		add(panelKanan, BorderLayout.EAST);
		add(panelTengah, BorderLayout.CENTER);

		panelAtas.setLayout(new GridLayout(0, 1));
		panelAtas.add(panelSpasiAtas);
		panelAtas.add(panelBtnMainMenu);

		panelBtnMainMenu.setLayout(new GridLayout(1, 3));
		panelBtnMainMenu.add(panelSpaceMenu);
		panelBtnMainMenu.add(panelMainMenu);
		panelMainMenu.add(btnMainMenu);

		panelAtas.add(panelTitle);
		panelTitle.add(lblTitle);
		panelAtas.add(panelCakeList);
		panelCakeList.add(lblCakeList);
		panelBawah.add(panelSpasiBawah);
		panelKiri.add(panelSpasiKiri);
		panelKanan.add(panelSpasiKanan);

		panelTengah.setLayout(new GridLayout(2, 1));
		panelTengah.add(panelTable);
		panelTable.setLayout(new GridLayout(1, 1));
		panelTable.add(scrollPane);

		panelTengah.add(panelInput);
		panelInput.setLayout(new GridLayout(1, 3));

		panelInput.add(panelLabel);
		panelLabel.setLayout(new GridLayout(5, 0));

		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabel.add(lblName);

		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabel.add(lblPrice);

		lblShape.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabel.add(lblShape);

		lblOval.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabel.add(lblOval);

		lblRectangle.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabel.add(lblRectangle);

		panelInput.add(panelField);
		panelField.setLayout(new GridLayout(5, 0));

		txtName.setColumns(10);
		panelField.add(txtName);

		panelField.add(txtPrice);
		txtPrice.setColumns(10);

		panelField.add(cmbShape);

		panelField.add(cmbOvalSize);

		panelField.add(cmbRectaSize);

		panelInput.add(panelBtn);
		panelBtn.setLayout(new GridLayout(0, 1));

		panelBtn.add(panelRemoveCake);
		panelRemoveCake.setLayout(new GridLayout(2, 1));

		panelRemoveCake.add(panelSpasiRemove);

		panelRemoveCake.add(btnRemove);

		panelBtn.add(panelAddCake);
		panelAddCake.setLayout(new GridLayout(2, 1));

		panelAddCake.add(btnAddCake);

		panelAddCake.add(panelSpasiAdd);

	}

	public void setStyle() {
		panelSpasiAtas.setBackground(Color.PINK);
		panelSpaceMenu.setBackground(Color.PINK);
		panelMainMenu.setBackground(Color.PINK);
		btnMainMenu.setForeground(new Color(0, 0, 128));
		btnMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 20));

		panelTitle.setBackground(Color.PINK);
		lblTitle.setForeground(new Color(160, 82, 45));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		panelCakeList.setBackground(Color.PINK);
		lblCakeList.setForeground(new Color(0, 0, 128));
		lblCakeList.setFont(new Font("Tahoma", Font.BOLD, 30));
		panelBawah.setBackground(Color.PINK);
		panelSpasiBawah.setBackground(Color.PINK);

		cakeTable.setBackground(new Color(255, 192, 203));
		cakeTable.getTableHeader().setOpaque(false);
		cakeTable.getTableHeader().setBackground(Color.PINK);
		DefaultTableCellRenderer header = new DefaultTableCellRenderer();
		header.setFont(header.getFont().deriveFont(Font.BOLD));

		panelKiri.setBackground(Color.PINK);
		panelSpasiKiri.setBackground(Color.PINK);
		panelKanan.setBackground(Color.PINK);
		panelSpasiKanan.setBackground(Color.PINK);
		panelTable.setBackground(Color.PINK);
		panelInput.setBackground(Color.PINK);
		panelLabel.setBackground(Color.PINK);

		lblName.setForeground(new Color(0, 0, 128));
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBackground(Color.PINK);

		lblPrice.setForeground(new Color(0, 0, 128));
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblShape.setForeground(new Color(0, 0, 128));
		lblShape.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblOval.setForeground(new Color(0, 0, 128));
		lblOval.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblRectangle.setForeground(new Color(0, 0, 128));
		lblRectangle.setFont(new Font("Tahoma", Font.PLAIN, 18));

		panelField.setBackground(Color.PINK);
		panelBtn.setBackground(Color.PINK);
		panelRemoveCake.setBackground(Color.PINK);
		panelSpasiRemove.setBackground(Color.PINK);
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRemove.setForeground(new Color(0, 0, 128));
		panelAddCake.setBackground(Color.PINK);
		btnAddCake.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAddCake.setForeground(new Color(0, 0, 128));
		panelSpasiAdd.setBackground(Color.PINK);
	};

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMainMenu) {
			setVisible(false);
			new MainFormAdmin();
		}

		if (e.getSource() == btnRemove) {
			if (cakeTable.getSelectionModel().isSelectionEmpty()) {
				JOptionPane.showMessageDialog(this, "Select cake you want to remove", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else if (cakeTable.getSelectionModel() != null) {
				removeCake();

				JOptionPane.showMessageDialog(this, "Cake successfully Removed", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if (e.getSource() == btnAddCake) {
			validateInput();
			

		}
	}

	void removeCake() {
		int selected = cakeTable.getSelectedRow();
		String cakeName = cakeTable.getValueAt(selected, 0).toString();

		String query = String.format("DELETE FROM cake WHERE CakeID = ?");
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);

		try {
			ps.setString(1, cakeName);
			ps.executeUpdate();
		} catch (SQLException e2) {
			System.out.println(e2);
		}

		fillTable();
	}

	void insertCake() {
		String CakeID = generateCakeID();
		String CakeName = txtName.getText().toString();
		int CakePrice = Integer.parseInt(txtPrice.getText());

		String CakeShape = cmbShape.getSelectedItem().toString();
		String CakeSize = "";

		if (CakeShape.equals("Oval")) {
			CakeSize = cmbOvalSize.getSelectedItem().toString();

		} else {
			CakeSize = cmbRectaSize.getSelectedItem().toString();
		}

		String query = "INSERT INTO cake(CakeID,CakeName,CakePrice,CakeShape,CakeSize) VALUES(?,?,?,?,?)";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);

		try {
			ps.setString(1, CakeID);
			ps.setString(2, CakeName);
			ps.setInt(3, CakePrice);
			ps.setString(4, CakeShape);
			ps.setString(5, CakeSize);
			ps.execute();
		} catch (SQLException e2) {
			System.out.println(e2);
		}
		fillTable();
	}

	void validateInput() {

		String tempPrice = txtPrice.getText().toString();
		int cakePrice = Integer.parseInt(tempPrice);

		if (!txtName.getText().endsWith("Cake")) {
			JOptionPane.showMessageDialog(this, "Cake Name must ends with ‘Cake’", "Error", JOptionPane.ERROR_MESSAGE);
			invalid = true;
		}

		else if (cakePrice < 100000 || cakePrice > 500000) {
			JOptionPane.showMessageDialog(this, "Cake Price must between 100000 - 500000", "Error",
					JOptionPane.ERROR_MESSAGE);
			invalid = true;
		} else if (cmbShape.getSelectedItem().equals("Select Shape")) {
			JOptionPane.showMessageDialog(this, "Cake Shape must be chosen either Oval or Rectangle", "Error",
					JOptionPane.ERROR_MESSAGE);
			invalid = true;

		} else {
			invalid = false;
			generateCakeID();
			insertCake();
			JOptionPane.showMessageDialog(this, "Cake successfully inputted to the database", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			

		}

	};

	String generateCakeID() {
		String shape = cmbShape.getSelectedItem().toString();
		String ovalSize = cmbOvalSize.getSelectedItem().toString();
		String rectSize = cmbRectaSize.getSelectedItem().toString();
		Random rand = new Random();
		String shapeInit;
		String size;
		String CakeID = "";

		if (shape.equals("Oval")) {
			shapeInit = "O";

			if (ovalSize.equals("15 cm")) {
				size = "F";
			} else if (ovalSize.equals("20 cm")) {
				size = "N";
			} else {
				size = "V";
			}
			CakeID = "C" + shapeInit + size + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);

		} else if (shape.equals("Rectangle")) {
			shapeInit = "R";

			if (rectSize.equals("10 x 10 cm")) {
				size = "T";
			} else if (rectSize.equals("20 x 20 cm")) {
				size = "W";
			} else {
				size = "H";
			}
			CakeID = "C" + shapeInit + size + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);

		}
		System.out.println(CakeID);

		return CakeID;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == cakeTable) {

			int selected = cakeTable.getSelectedRow();
			String CakeID = cakeTable.getValueAt(selected, 0).toString();

			String query = String.format(
					"SELECT CakeName,CakePrice,CakeShape,CakeSize " + "FROM cake " + "WHERE CakeID = '%s'", CakeID);

			ResultSet rs = conn.executeQuery(query);
			try {
				while (rs.next()) {
					txtName.setText(rs.getString("CakeName"));
					txtPrice.setText(rs.getString("CakePrice"));

					if (rs.getString("CakeShape").equals("Oval")) {
						cmbShape.setSelectedItem(rs.getString("CakeShape"));
						cmbOvalSize.setSelectedItem(rs.getString("CakeSize"));
					} else if (rs.getString("CakeShape").equals("Rectangle")) {
						cmbShape.setSelectedItem(rs.getString("CakeShape"));
						cmbRectaSize.setSelectedItem(rs.getString("CakeSize"));
					}

				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

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
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}

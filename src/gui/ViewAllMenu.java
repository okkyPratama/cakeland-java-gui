package gui;

import java.awt.Frame;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import database.ClassModel;
import database.Connect;
import model.Cake;
import model.User;

import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ViewAllMenu extends JFrame implements ActionListener, MouseListener {
	private JPanel pnlAtas, pnlSpaceAtas, pnlSpasiAtas, pnlButton, pnlSpaceBtn1, PnlSpaceBtn2, pnlBtnContainer,
			pnlTitle, pnlCakeList, pnlTable, pnlCart, pnlQuantity, panelSpaceAtas, pnlContainerQty, pnlContainerBtn,
			pnlAdd, pnlView;

	private JButton btnMainMenu, btnAddCart, btnView;
	private JLabel lblTitle, lblCakeList, lblQty;
	private JScrollPane scrollPaneCake;
	private JSpinner spinnerQty;
	Connect connection = Connect.getConnection();

	private JTable tableCake;
	private DefaultTableModel tableCakeModel;
	private Vector<String> rowTitle;
	private Cake cake;
	private User user;
	Vector<Cake> cakeList = new Vector<>();

	public ViewAllMenu() {

		getContentPane().setLayout(new BorderLayout());

		initVar();
		setTable();
		setPanel();
		setStyle();

		btnMainMenu.addActionListener(this);
		btnAddCart.addActionListener(this);
		btnView.addActionListener(this);

		setTitle("CakeLAnd");
		setSize(760, 590);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	public void initVar() {

		// Panel
		pnlAtas = new JPanel();
		pnlSpaceAtas = new JPanel();
		pnlSpasiAtas = new JPanel();
		pnlButton = new JPanel();
		pnlSpaceBtn1 = new JPanel();
		PnlSpaceBtn2 = new JPanel();
		pnlBtnContainer = new JPanel();
		pnlTitle = new JPanel();
		pnlTable = new JPanel();
		pnlCart = new JPanel();
		pnlCakeList = new JPanel();
		pnlQuantity = new JPanel();
		pnlContainerQty = new JPanel();
		pnlContainerBtn = new JPanel();
		pnlAdd = new JPanel();
		pnlView = new JPanel();

		// Label
		lblTitle = new JLabel("CakeLAnd");
		lblCakeList = new JLabel("Cake List");
		lblQty = new JLabel("Quantity");

		// Button
		btnMainMenu = new JButton("Back to Main Menu");
		btnAddCart = new JButton("Add to Cart");
		btnView = new JButton("View Cart");

		// Scrollpane
		scrollPaneCake = new JScrollPane();

		tableCake = new JTable();
		spinnerQty = new JSpinner();
		spinnerQty.setModel(new SpinnerNumberModel(1, 1, 100, 1));

	};

	public void setTable() {
		// Panel for Table

		String[] cakeHeader = { "Cake Name", "Cake Price", "Cake Shape", "Cake Size" };
		tableCakeModel = new DefaultTableModel(cakeHeader, 0);
		tableCake.setModel(tableCakeModel);
		getContentPane().add(pnlTable, BorderLayout.WEST);

		fillTable();
		pnlTable.add(scrollPaneCake);
		scrollPaneCake.setPreferredSize(new Dimension(400, 200));
		scrollPaneCake.setViewportView(tableCake);
	}

	void fillTable() {
		tableCakeModel.setRowCount(0);
		cakeList = ClassModel.getCakeList();
		

		
		String query = String.format("SELECT CakeName,CakePrice,CakeShape,CakeSize FROM cake ");
		ResultSet rs = connection.executeQuery(query);
		try {
			while (rs.next()) {
				Object ck[] = new Object[] { 
						rs.getString("CakeName"),
						rs.getInt("CakePrice"),
						rs.getString("CakeShape"),
						rs.getString("CakeSize")
						
						};
				tableCakeModel.addRow(ck);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public void setPanel() {

		// Panel Top
		getContentPane().add(pnlAtas, BorderLayout.NORTH);
		pnlAtas.setLayout(new GridLayout(4, 1, 0, 0));
		pnlAtas.add(pnlSpaceAtas);
		pnlAtas.add(pnlButton);
		pnlButton.setLayout(new GridLayout(1, 3, 0, 0));

		// Panel for back to menu button
		pnlButton.add(pnlSpaceBtn1);
		pnlButton.add(PnlSpaceBtn2);
		pnlButton.add(pnlBtnContainer);
		pnlBtnContainer.add(btnMainMenu);

		// Panel for Title
		pnlAtas.add(pnlTitle);
		pnlTitle.add(lblTitle);
		pnlAtas.add(pnlCakeList);
		pnlCakeList.add(lblCakeList);

		// Panel for Cart Input
		getContentPane().add(pnlCart, BorderLayout.CENTER);
		pnlCart.setLayout(new GridLayout(4, 2, 0, 0));
		pnlCart.add(pnlQuantity);
		pnlQuantity.setLayout(new GridLayout(3, 2, 0, 0));
		pnlQuantity.add(pnlSpasiAtas);
		pnlQuantity.add(pnlContainerQty);
		pnlContainerQty.setLayout(new GridLayout(1, 2, 0, 0));
		lblQty.setHorizontalAlignment(SwingConstants.CENTER);
		pnlContainerQty.add(lblQty);
		pnlContainerQty.add(spinnerQty);
		pnlCart.add(pnlContainerBtn);
		pnlContainerBtn.setLayout(new GridLayout(0, 1, 0, 0));
		pnlContainerBtn.add(pnlAdd);
		pnlAdd.add(btnAddCart);
		btnAddCart.setHorizontalAlignment(SwingConstants.LEADING);
		pnlContainerBtn.add(pnlView);
		pnlView.add(btnView);
	};

	public void setStyle() {

		pnlSpaceAtas.setBackground(Color.PINK);
		pnlSpaceBtn1.setBackground(Color.PINK);

		PnlSpaceBtn2.setBackground(Color.PINK);
		pnlBtnContainer.setBackground(Color.PINK);
		btnMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMainMenu.setForeground(new Color(0, 0, 128));

		lblTitle.setForeground(new Color(233, 150, 122));
		pnlTitle.setBackground(Color.PINK);
		lblTitle.setBackground(Color.PINK);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		pnlCakeList.setBackground(Color.PINK);
		lblCakeList.setForeground(new Color(0, 0, 128));
		lblCakeList.setFont(new Font("Tahoma", Font.BOLD, 25));

		pnlTable.setBackground(Color.PINK);
		scrollPaneCake.setBackground(Color.PINK);
		tableCake.setBackground(new Color(255, 192, 203));
		tableCake.getTableHeader().setOpaque(false);
		tableCake.getTableHeader().setBackground(Color.PINK);
		DefaultTableCellRenderer header = new DefaultTableCellRenderer();
		header.setFont(header.getFont().deriveFont(Font.BOLD));

		pnlCart.setBackground(Color.PINK);
		pnlQuantity.setBackground(Color.PINK);
		pnlSpasiAtas.setBackground(Color.PINK);
		pnlContainerQty.setBackground(Color.PINK);
		lblQty.setForeground(new Color(0, 0, 128));
		lblQty.setBackground(Color.PINK);
		lblQty.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlContainerBtn.setBackground(Color.PINK);
		pnlAdd.setBackground(Color.PINK);
		btnAddCart.setForeground(new Color(0, 0, 128));
		btnAddCart.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlView.setBackground(Color.PINK);
		btnView.setForeground(new Color(0, 0, 128));
		btnView.setFont(new Font("Tahoma", Font.PLAIN, 20));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnView) {
			setVisible(false);
			new ManageCart();
		}

		if (e.getSource() == btnMainMenu) {
			setVisible(false);
			new MainForm();
		}

		if (e.getSource() == btnAddCart) {
			if (tableCake.getSelectionModel().isSelectionEmpty()) {
				JOptionPane.showMessageDialog(this, "Select Cake to add!", "Error", JOptionPane.WARNING_MESSAGE);

			} else if(tableCake.getSelectionModel() != null) {
				int selected = tableCake.getSelectedRow();
				String cakeName = tableCake.getValueAt(selected, 0).toString();
			
				int quantity = (int)spinnerQty.getValue();
				
				
				String query = String.format("SELECT * FROM cake WHERE CakeName = '%s'",
						cakeName);
				ResultSet rs = connection.executeQuery(query);
				
				try {
					while (rs.next()) {
						Cake.addCake(
								rs.getString("CakeID"),
								rs.getString("CakeName"),
								rs.getInt("CakePrice"),
								rs.getString("CakeShape"),
								rs.getString("CakeSize")
								
								);
					}
					
					
					
				} catch (SQLException e2) {
					System.out.println(e2);
				}
				
				PreparedStatement ps = Connect.getConnection().prepareStatement("INSERT INTO cart(UserID,CakeID,Quantity) VALUES(?,?,?)");
				
				
				try {
					ps.setString(1, User.getUserID());
					ps.setString(2, cake.getCakeID());
					ps.setInt(3, quantity);
					
					ps.execute();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				Cake.removeCake();
			
			
				
				JOptionPane.showMessageDialog(this, "Cake succesfully added to the cart", "Success",
						JOptionPane.INFORMATION_MESSAGE);

			}

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == tableCake) {

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

	};
}

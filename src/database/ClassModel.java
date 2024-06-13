package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import gui.ManageCart;
import model.Cake;
import model.Cart;
import model.TransactionHistory;
import model.User;

public class ClassModel {

	static Connect conn = Connect.getConnection();
	Vector<String> userId = new Vector<String>();

	public static Vector<Cake> getCakeList() {
		Vector<Cake> cakeList = new Vector<>();
		String query = "SELECT * FROM cake";
		ResultSet rs = conn.executeQuery(query);

		String CakeID, CakeName, CakeSize, CakeShape;
		int CakePrice;

		try {
			while (rs.next()) {
				CakeName = rs.getString("CakeName");
				CakePrice = rs.getInt("CakePrice");
				CakeSize = rs.getString("CakeSize");
				CakeShape = rs.getString("CakeShape");
				cakeList.add(new Cake(CakeName, CakePrice, CakeSize, CakeShape));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return cakeList;
	}

	public static Vector<Cart> getCartList() {
		Vector<Cart> cartList = new Vector<>();
		String query = ""
				+ "SELECT ck.CakeName, ck.CakeShape, ck.CakeSize, ck.CakePrice, cr.Quantity "
				+ "FROM cart cr JOIN user us "
				+ "ON cr.UserID = us.UserID JOIN cake ck "
				+ "ON cr.CakeID = ck.CakeID;";
		
		ResultSet rs = conn.executeQuery(query);

		String CakeName, CakeSize, CakeShape;

		int CakePrice, quantity;

		try {
			while (rs.next()) {
				CakeName = rs.getString("CakeName");
				CakePrice = rs.getInt("CakePrice");
				CakeSize = rs.getString("CakeSize");
				CakeShape = rs.getString("CakeShape");
				quantity = rs.getInt("Quantity");
				cartList.add(new Cart(CakeName, CakeShape, CakeSize, CakePrice, quantity));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return cartList;
	}
	


	public static boolean removeCart() {
		String query = "DELETE FROM cart WHERE CakeID = ? AND UserID = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		
		try {
			ps.setString(1, ManageCart.chosenValue);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	

	public static Vector<TransactionHistory> getTransHis() {
		Vector<TransactionHistory> transHis = new Vector<>();
		String query = "SELECT TransactionID,TransactionDate, PickUpDate FROM  transactionheader";
		ResultSet rs = conn.executeQuery(query);

		String TransactionID, TransactionDate, PickUpDate;

		try {
			while (rs.next()) {
				TransactionID = rs.getString("TransactionID");
				TransactionDate = rs.getString("TransactionDate");
				PickUpDate = rs.getString("PickUpDate");

				transHis.add(new TransactionHistory(TransactionID, TransactionDate, PickUpDate));
			}
		} catch (SQLException e) {
			System.out.println(e);

		}

		return transHis;
	}

	public static Vector<Cake> getAllCakeList() {
		Vector<Cake> cakeList = new Vector<>();
		String query = "SELECT * FROM cake";
		ResultSet rs = conn.executeQuery(query);

		String CakeID, CakeName, CakeSize, CakeShape;
		int CakePrice;

		try {
			while (rs.next()) {
				CakeID = rs.getString("CakeID");
				CakeName = rs.getString("CakeName");
				CakePrice = rs.getInt("CakePrice");
				CakeSize = rs.getString("CakeSize");
				CakeShape = rs.getString("CakeShape");
				cakeList.add(new Cake(CakeID, CakeName, CakePrice, CakeSize, CakeShape));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return cakeList;
	}

	public boolean insertCake(Cake cake) {
		PreparedStatement ps = Connect.getConnection().prepareStatement(
				"INSERT INTO cake(CakeID, CakeName, CakePrice, CakeSize, CakeShape) VALUES(?,?,?,?,?) ");
		try {
			ps.setString(1, cake.getCakeID());
			ps.setString(2, cake.getCakeName());
			ps.setInt(3, cake.getCakePrice());
			ps.setString(4, cake.getCakeSize());
			ps.setString(5, cake.getCakeShape());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	};

	public static Vector<User> getUser() {
		Vector<User> users = new Vector<>();
		String query = "SELECT username FROM user";
		ResultSet rs = conn.executeQuery(query);

		String UserID, Username, UserEmail, UserPassword, UserGender, UserDOB, UserPhoneNumber, UserAddress, UserRole;

		try {
			while (rs.next()) {
				UserID = rs.getString("UserID");
				Username = rs.getString("Username");
				UserEmail = rs.getString("UserEmail");
				UserPassword = rs.getString("UserPassword");
				UserGender = rs.getString("UserGender");
				UserDOB = rs.getString("UserDOB");
				UserPhoneNumber = rs.getString("UserPhoneNumber");
				UserAddress = rs.getString("UserAddress");
				UserRole = rs.getString("UserRole");
				users.add(new User(UserID, Username, UserEmail, UserPassword, UserGender, UserDOB, UserPhoneNumber,
						UserAddress, UserRole));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return users;

	}

	public boolean insertUser(User user) {
		PreparedStatement ps = Connect.getConnection().prepareStatement(
				"INSERT INTO user(UserID, Username, UserEmail, UserPassword, UserGender, UserDOB, UserPhoneNumber, UserAddress, UserRole) VALUES(?,?,?,?,?,?,?,?,?) ");
		try {
			ps.setString(1, user.getUserID());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getUserEmail());
			ps.setString(4, user.getUserPassword());
			ps.setString(5, user.getUserGender());
			ps.setString(6, user.getUserDOB());
			ps.setString(7, user.getUserPhoneNumber());
			ps.setString(8, user.getUserAddress());
			ps.setString(9, user.getUserRole());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	};

}

package model;

public class User {
	private static String UserID;
	private static String Username;
	private static String UserEmail;
	private static String UserPassword;
	private static String UserGender;
	private static String UserDOB;
	private static String UserPhoneNumber;
	private static String UserAddress;
	private static String UserRole;
	
	private static User user;
	
	public static void login(String userID, String username, String userEmail, String userPassword, String userGender,
			String userDOB, String userPhoneNumber, String userAddress, String userRole) {
		if (user == null) {
			user = new User(userID, username, userEmail, userPassword, userGender,
				userDOB, userPhoneNumber, userAddress, userRole);
		}
	}
	
	public static void logout() {
		user = null;
	}
	
	public User(String userID, String username, String userEmail, String userPassword, String userGender,
			String userDOB, String userPhoneNumber, String userAddress, String userRole) {
		super();
		UserID = userID;
		Username = username;
		UserEmail = userEmail;
		UserPassword = userPassword;
		UserGender = userGender;
		UserDOB = userDOB;
		UserPhoneNumber = userPhoneNumber;
		UserAddress = userAddress;
		UserRole = userRole;
	}

	public static String getUserID() {
		return UserID;
	}

	public static void setUserID(String userID) {
		UserID = userID;
	}

	public static String getUsername() {
		return Username;
	}

	public static void setUsername(String username) {
		Username = username;
	}

	public static String getUserEmail() {
		return UserEmail;
	}

	public static void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}

	public static String getUserPassword() {
		return UserPassword;
	}

	public static void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}

	public static String getUserGender() {
		return UserGender;
	}

	public static void setUserGender(String userGender) {
		UserGender = userGender;
	}

	public static String getUserDOB() {
		return UserDOB;
	}

	public static void setUserDOB(String userDOB) {
		UserDOB = userDOB;
	}

	public static String getUserPhoneNumber() {
		return UserPhoneNumber;
	}

	public static void setUserPhoneNumber(String userPhoneNumber) {
		UserPhoneNumber = userPhoneNumber;
	}

	public static String getUserAddress() {
		return UserAddress;
	}

	public static void setUserAddress(String userAddress) {
		UserAddress = userAddress;
	}

	public static String getUserRole() {
		return UserRole;
	}

	public static void setUserRole(String userRole) {
		UserRole = userRole;
	}
	
}
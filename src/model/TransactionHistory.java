package model;

public class TransactionHistory {
	public static String TransactionID;
	public static String TransactionDate;
	public static String PickUpDate;

	public TransactionHistory(String transactionID, String transactionDate, String pickUpDate) {
		super();
		TransactionID = transactionID;
		TransactionDate = transactionDate;
		PickUpDate = pickUpDate;
	}

	public static String getTransactionID() {
		return TransactionID;
	}

	public static void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}

	public static String getTransactionDate() {
		return TransactionDate;
	}

	public static void setTransactionDate(String transactionDate) {
		TransactionDate = transactionDate;
	}

	public static String getPickUpDate() {
		return PickUpDate;
	}

	public static void setPickUpDate(String pickUpDate) {
		PickUpDate = pickUpDate;
	}

}

package model;

public class Cart {
	private static String cakeName;
	private static int cakePrice;
	private static String cakeShape;
	private static String cakeSize;
	private static int quantity;
	static Cart cart;

	public Cart(String cakeName, String cakeShape, String cakeSize, int cakePrice, int quantity) {
		super();
		this.cakeName = cakeName;
		this.cakeShape = cakeShape;
		this.cakeSize = cakeSize;
		this.cakePrice = cakePrice;
		this.quantity = quantity;

	}
	public static void removeCart() {
		cart = null;
	}


	public static String getCakeName() {
		return cakeName;
	}

	public static void setCakeName(String cakeName) {
		Cart.cakeName = cakeName;
	}

	public static int getCakePrice() {
		return cakePrice;
	}

	public static void setCakePrice(int cakePrice) {
		Cart.cakePrice = cakePrice;
	}

	public static String getCakeShape() {
		return cakeShape;
	}

	public static void setCakeShape(String cakeShape) {
		Cart.cakeShape = cakeShape;
	}

	public static String getCakeSize() {
		return cakeSize;
	}

	public static void setCakeSize(String cakeSize) {
		Cart.cakeSize = cakeSize;
	}

	public static int getQuantity() {
		return quantity;
	}

	public static void setQuantity(int quantity) {
		Cart.quantity = quantity;
	}

}

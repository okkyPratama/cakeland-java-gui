package model;

import java.util.Vector;

public class Cake {
	private static String CakeID;
	private static String CakeName;
	private static int CakePrice;
	private static String CakeSize;
	private static String CakeShape;
	private static Cake cake;

	public Cake(String cakeID, String cakeName, int cakePrice, String cakeSize, String cakeShape) {
		super();
		CakeID = cakeID;
		CakeName = cakeName;
		CakePrice = cakePrice;
		CakeSize = cakeSize;
		CakeShape = cakeShape;

	}

	public Cake(String cakeName, int cakePrice, String cakeSize, String cakeShape) {
		super();
		CakeName = cakeName;
		CakePrice = cakePrice;
		CakeSize = cakeSize;
		CakeShape = cakeShape;

	}

	public static void addCake(String CakeID, String cakeName, int cakePrice, String cakeSize, String cakeShape) {
		if (cake == null) {
			cake = new Cake(CakeID, cakeName, cakePrice, cakeSize, cakeShape);
		}
	}

	public static void removeCake() {
		cake = null;
	}

	public static String getCakeID() {
		return CakeID;
	}

	public static void setCakeID(String cakeID) {
		CakeID = cakeID;
	}

	public static String getCakeName() {
		return CakeName;
	}

	public static void setCakeName(String cakeName) {
		CakeName = cakeName;
	}

	public static int getCakePrice() {
		return CakePrice;
	}

	public static void setCakePrice(int cakePrice) {
		CakePrice = cakePrice;
	}

	public static String getCakeSize() {
		return CakeSize;
	}

	public static void setCakeSize(String cakeSize) {
		CakeSize = cakeSize;
	}

	public static String getCakeShape() {
		return CakeShape;
	}

	public static void setCakeShape(String cakeShape) {
		CakeShape = cakeShape;
	}

}

import gui.*;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import database.*;

public class Main {

	public Main() {
		Connect.getConnection();
		new Login();

	}

	public static void main(String[] args) {
		new Main();

	}

}

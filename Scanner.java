package misc;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class Scanner {

	public static Color getMouseColor() {

		Robot robot = null;

		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		return robot.getPixelColor(MouseInfo.getPointerInfo().getLocation().x,
				MouseInfo.getPointerInfo().getLocation().y);

	}
	

	public static Point scanForColor(Color color) {

		int red = color.getRed();
		int blue = color.getBlue();
		int green = color.getGreen();

		Robot robot = null;

		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		Rectangle captureSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage bufferedImage = robot.createScreenCapture(captureSize);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		for (int i = 0; i < screen.getHeight(); i++) {
			for (int a = 0; a < screen.getWidth(); a++) {

				int colorx = bufferedImage.getRGB(a, i);
				int r = (colorx & 0x00ff0000) >> 16;
				int g = (colorx & 0x0000ff00) >> 8;
				int b = colorx & 0x000000ff;

				if (r == red && g == green && b == blue) {
					return new Point(a, i);
				}

			}
		}
		return null;

	}

	public static int[] scanForColor(int red, int green, int blue) {

		Robot robot = null;

		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		Rectangle captureSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage bufferedImage = robot.createScreenCapture(captureSize);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		for (int i = 0; i < screen.getHeight(); i++) {
			for (int a = 0; a < screen.getWidth(); a++) {

				int color = bufferedImage.getRGB(a, i);
				int r = (color & 0x00ff0000) >> 16;
				int g = (color & 0x0000ff00) >> 8;
				int b = color & 0x000000ff;

				if (r == red && g == green && b == blue) {
					return new int[] { a, i };
				}

			}
		}
		return null;

	}

}

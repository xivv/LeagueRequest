package misc;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import terminal.Terminal;

public class ColorPatterns {

	public static Color launch = new Color(228, 182, 138);
	public static Color login = new Color(245, 245, 241);
	public static Color play = new Color(197, 149, 122);
	public static Color map = new Color(36, 31, 51);
	public static Color start = new Color(46, 186, 218);
	public static Color create = new Color(60, 68, 70);
	public static Color exp = new Color(27, 29, 41);
	public static Color lockin = new Color(238, 210, 178);

	// Experimental

	public static Color waitLogin = new Color(239, 47, 34);
	public static Color champWait = new Color(203, 254, 0);
	public static Color champ = new Color(0, 109, 160);
	public static Color pvp = new Color(50, 113, 90);

	public static Point waitingForColor(Color color, int delay) {

		Point p = null;

		long start = System.currentTimeMillis();

		while (p == null) {

			long elapsedTimeMillis = System.currentTimeMillis() - start;

			p = Scanner.scanForColor(color);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (elapsedTimeMillis / (60 * 1000F) >= delay) {

				System.out.println("Checking if game is ending.");
			}
		}

		return p;
	}

	public static Point waitingForColor(Color color, boolean restart) {

		Point p = null;

		long start = System.currentTimeMillis();

		while (p == null) {

			p = Scanner.scanForColor(color);

			long elapsedTimeMillis = System.currentTimeMillis() - start;

			if (elapsedTimeMillis / (60 * 1000F) >= 5 && restart) {

				try {
					Runtime.getRuntime().exec("taskkill /F /IM LolClient.exe");
					restartApplication();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		return p;
	}

	public static void restartApplication() {
		final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
		File currentJar = null;
		try {
			currentJar = new File(Terminal.class.getProtectionDomain().getCodeSource().getLocation().toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* is it a jar file? */
		if (!currentJar.getName().endsWith(".jar"))
			return;

		/* Build command: java -jar application.jar */
		final ArrayList<String> command = new ArrayList<String>();
		command.add(javaBin);
		command.add("-jar");
		command.add(currentJar.getPath());

		final ProcessBuilder builder = new ProcessBuilder(command);
		try {
			builder.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}

}

package terminal;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import misc.ColorPatterns;
import misc.LeagueInstance;
import misc.Scanner;

public class Terminal {

	public static void main(String[] args) {

		Robot robot;

		try {

			robot = new Robot();

			startingGameExe(robot);

			robot = null;

			robot = new Robot();

			clickingCustom(robot);
			createGame(robot);
			chooseYourMap(robot);
			startLobby(robot);
			pickRandomChampion(robot);

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void clickingCustom(Robot robot) {
		System.out.println("Click on costum.");
		Point p = ColorPatterns.waitingForColor(ColorPatterns.pvp, true);

		robot.mouseMove(p.x, p.y + 125);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public static void startingGameExe(Robot robot) {

		System.out.println("Starting Game.");
		new LeagueInstance();
		System.out.println("Waiting for Game to load.");

		Point p = ColorPatterns.waitingForColor(ColorPatterns.launch, true);

		System.out.println("Click on Launch.");

		robot.mouseMove(p.x, p.y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);

		System.out.println("Waiting for login.");
		p = ColorPatterns.waitingForColor(ColorPatterns.play, true);

		System.out.println("Click on play.");

		robot.mouseMove(p.x, p.y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public static void printColor() {
		while (true)
			try {
				Thread.sleep(100);
				System.out.println(Scanner.getMouseColor());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void chooseYourMap(Robot robot) {

		ColorPatterns.waitingForColor(ColorPatterns.map, true);

		System.out.println("Choose map.");
		pressKey(robot, KeyEvent.VK_TAB, 5);
		space(robot);
	}

	public static void startLobby(Robot robot) {
		ColorPatterns.waitingForColor(ColorPatterns.start, true);

		System.out.println("Starting lobby.");
		pressKey(robot, KeyEvent.VK_TAB, 10);

		space(robot);

		System.out.println("Accepting exp.");

		ColorPatterns.waitingForColor(ColorPatterns.exp, true);
		pressKey(robot, KeyEvent.VK_TAB, 2);
		space(robot);

	}

	public static void createGame(Robot robot) {

		ColorPatterns.waitingForColor(ColorPatterns.create, true);
		System.out.println("Click on Create Game.");

		try {
			Thread.sleep(3000);
			pressKey(robot, KeyEvent.VK_TAB, 4);
			space(robot);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Maybe tab
	public static void pickRandomChampion(Robot robot) {

		System.out.println("Selecting Champion.");

		ColorPatterns.waitingForColor(ColorPatterns.champWait, true);

		pressKey(robot, KeyEvent.VK_TAB, 2);

		Point p = ColorPatterns.waitingForColor(ColorPatterns.champ, true);
		robot.mouseMove(p.x, p.y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);

		p = ColorPatterns.waitingForColor(ColorPatterns.lockin, true);
		robot.mouseMove(p.x, p.y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public static void space(Robot robot) {

		try {
			robot.keyPress(KeyEvent.VK_SPACE);
			Thread.sleep(50);
			robot.keyRelease(KeyEvent.VK_SPACE);
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void pressKey(Robot robot, int key, int iterations) {

		for (int i = 0; i < iterations; i++) {

			try {
				Thread.sleep(50);
				robot.keyPress(key);
				Thread.sleep(50);
				robot.keyRelease(key);
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

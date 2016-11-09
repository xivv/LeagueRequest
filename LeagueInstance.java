package misc;

import java.io.File;
import java.io.IOException;

public class LeagueInstance {

	public String leaguePath = "";

	// Starts the League Client via ProcessBuilder
	
	public LeagueInstance() {

		File file = FindFile.searchFile("lol.launcher.exe");

		try {
			@SuppressWarnings("unused")
			Process process = new ProcessBuilder(file.getAbsolutePath()).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		new LeagueInstance();
	}

}

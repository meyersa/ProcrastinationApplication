package main;

import gui.MainGUI;

public class Main {

	public static void main(String[] args) {

		// Check for offline saves 
		
		MainGUI MainGUI = new MainGUI();
		
		try {
			MainGUI.start();
		} catch (Exception e) {
			System.err.println("Fatal. Failed to start application.");
			e.printStackTrace();
			
		}
		// Starts GUI
		
	}
}
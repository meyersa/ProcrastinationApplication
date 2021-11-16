package main;

import gui.ErrorGUI;
import gui.MainGUI;

import java.util.HashMap;
import java.util.Map;

import events.*;

public class Main {
	private Map<Integer, Event> allEvents;

	public static void main(String[] args) {
		Main Application = new Main();

	}

	private Main() {
		if (LocalStorage.checkForCache()) {
			allEvents = LocalStorage.readCache();

		}

		allEvents = new HashMap<Integer, Event>();

		MainGUI MainGUI = new MainGUI();

		System.out.println("Success");

		try {
			MainGUI.start();

		} catch (Exception e) {
			System.err.println("Fatal. Failed to start application.");
			e.printStackTrace();

		}
	}

	private void exit() {
		LocalStorage.writeCache(allEvents);
		
	}
}
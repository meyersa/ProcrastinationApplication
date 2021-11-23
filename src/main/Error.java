// Package when an error happens, calls the ErrorGUI, takes string error (e.toString())

package main;

import gui.ErrorGUI;

public class Error {
	public static void createErrorGUI(Exception e) {		
		//ErrorGUI.display(e);
		e.printStackTrace();
	}
}

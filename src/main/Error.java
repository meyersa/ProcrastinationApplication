// Package when an error happens, calls the ErrorGUI, takes string error (e.toString())

package main;

import gui.ErrorGUI;
import gui.MainGUI;

public class Error {
	public static void createErrorGUI(Exception e) {		
		MainGUI.displayError(e.toString());
	
	}
}

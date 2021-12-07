# Procrastination Application
The program to help you remember to do your work. Perfect for programmers who get easily distracted...

## About
Created by August Meyers

GUI based program that lets you create "events" that you need to be reminded about and then proceeds to remind you about those events 

![Screenshot](https://raw.githubusercontent.com/meyersa/ProcrastinationApplication/master/assets/ProcrastinationApplicationGUI.png)

## Getting Started
### Requirements
- **JavaFX 17 in your ProgramFiles directory**
- **Java SDK 16**

### To run (easy way)
- Download the first release from the GitHub release [Page](https://github.com/meyersa/ProcrastinationApplication/releases/tag/Release-1). 
- Then simply run the INSTALL.bat and finally the PA.bat on the Desktop

Or, if you prefer to compile it from Source
- Download the source folder
- `cd` into the `src` directory
- Compile the packages `javac --module-path="%PROGRAMFILES%\Java\openjfx-17_windows-x64_bin-sdk\javafx-sdk-17\lib" --add-modules=javafx.controls,javafx.fxml -d ./build main/*.java events/*.java gui/*.java`
- Use Java to launch the Main class from the build directory `java  --module-path="%PROGRAMFILES%\Java\openjfx-17_windows-x64_bin-sdk\javafx-sdk-17\lib" --add-modules=javafx.controls,javafx.fxml -cp build main.Main`

## Abstract - Future Plan
- UI Showing manager for multiple UIs to be open 
	- Can the other UIs just quit the main One? 
	- if not, could animate between them
- Move some of the classes out of MainGUI
	- Or perhaps make a scene manager, and have MainGUI just draw that and call classes inside of MainGUI
	- CreateEvent Anchor stuff outside of MainGUI class though
- With new Event manager
	- Add back preferences MenuItem/Create Preferences Menu
		- Num of events to display? 
		- Default Size? 
		- Where to store default events? 
		- Etc
	- Re-enable Fatal Error Menu
- Time Input Calender instead of UNIX Time
	- Input Time/Date then convert to UNIX Time? 

## Continuity
- Colors
	- Grey: #424144
	- LighterGrey: #5b5a5e
	- Pink: #e945ff
	- LighterPink: #f082ff
	- White (Font): #ccc2cc
	- Brighter White: #ebe1eb
	- Red: #ff6961
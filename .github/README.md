# Procrastination Application
The program to help you remember to do your work. Perfect for programmers who get easily distracted...
#

## About
Created by August Meyers

GUI based program that lets you create "events" that you need to be reminded about and then proceeds to remind you about those events 

![Screenshot](https://github.com/meyersa/ProcrastinationApplication/blob/master/assets/ProcrastinationApplicationGUI.png)

## To-do
- **Setup backend Unix TIME alert system** - Take a map<Integer, Event> input and create timers
- **Notification service** - Take an Event input, display with that 
- Time Input Calender instead of UNIX Time
	- Input Time/Date then convert to UNIX Time? 
- Add a button to clear current fields, inline with submit perhaps
- Stuff on OneNote, quite a bit left

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

## Continuity
- Colors
	- Grey: #424144
	- LighterGrey: #5b5a5e
	- Pink: #e945ff
	- LighterPink: #f082ff
	- White (Font): #ccc2cc
	- Brighter White: #ebe1eb
	- Red: #ff6961
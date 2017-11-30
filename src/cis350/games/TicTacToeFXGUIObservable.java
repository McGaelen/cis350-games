package cis350.games;

import java.util.Observable;

public class TicTacToeFXGUIObservable extends Observable{
	
	private TicTacToeFXGUI gui;
	
	public TicTacToeFXGUIObservable() {
		gui = new TicTacToeFXGUI();
	}
}

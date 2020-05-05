package game;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import gui.Window;

import player.Player;

public class GameOfLife extends Window {
	private GoalPosition goalPosition;
	private Player player;
	private int level;

	public GameOfLife(int setLevel) {
		super(500,500);
		goalPosition = new GoalPosition();
		this.level = setLevel;
	}
	
	/**
	 * 
	 */
	public void play() {
		this.updatePanelContent();
	}
	
	/**
	 * The following void closes the window
	 */
	public void endGame() {
		this.dispose();
	}


	public static void main(String[] args) {
		GameOfLife game = new GameOfLife(1);
		game.play();

		game.endGame();
	}

}

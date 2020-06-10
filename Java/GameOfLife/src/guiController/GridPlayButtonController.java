package guiController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import gui.Window;
import player.PlayerKeyEventController;
import player.PlayerModel;

/**
 * 
 * @author ThomasDuvinage
 * @brief This Class is the play button controller
 *
 */
public class GridPlayButtonController implements ActionListener{
	/// Timer Class used to update grid content 
	private TimerMainWindowController timerController;
	
	/// actual view
	private Window window;
	
	/// pause button Controller 
	private GridPauseButtonController buttonPauseController;
	
	/// player model
	private PlayerModel player;

	/**
	 * @brief Constructor 
	 * @param timerController
	 * @param window
	 * @param buttonPauseController
	 */
	public GridPlayButtonController(TimerMainWindowController timerController,Window window, GridPauseButtonController buttonPauseController, PlayerModel player) {
		this.timerController = timerController;
		this.window = window;
		this.player = player;
	}

	@Override
	/**
	 * @brief We have to instantiate the pause button here otherwise the reference to the timer object is null then the pause button doesn't work 
	 */
	public void actionPerformed(ActionEvent e) {		
		this.timerController = new TimerMainWindowController(this.window.gridPanel);
		this.buttonPauseController = new GridPauseButtonController(this.timerController.timer);
		this.window.pauseButton.addActionListener(buttonPauseController);
							
	}
}

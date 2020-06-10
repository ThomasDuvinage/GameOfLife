package player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.StartGameFrame;

/**
 * 
 * @author ThomasDuvinage
 * 
 * @brief This Class is the player back button 
 *
 */
public class PlayerBackButtonController implements ActionListener {
	/// previsous frame 
	private StartGameFrame previousFrame;
	/// actual frame
	private PlayerCreationFrame actualFrame;

	/**
	 * @brief Constructor 
	 * @param actualFrame
	 * @param previousView
	 */
	public PlayerBackButtonController(PlayerCreationFrame actualFrame,StartGameFrame previousView) {
		this.actualFrame = actualFrame;
		this.previousFrame = previousView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.previousFrame.setVisible(true);
		this.actualFrame.setVisible(false);
	}

}

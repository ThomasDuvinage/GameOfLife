package guiController;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.StartGameFrame;
import player.PlayerCreationFrame;

/**
 * 
 * @author ThomasDuvinage
 * 
 * @brief This method is used to create an account
 *
 */
public class StartGamePlayButtonController implements ActionListener{
	/// actual view
	private StartGameFrame view;
	
	/**
	 * @brief Constructor 
	 * @param view
	 */
	public StartGamePlayButtonController(StartGameFrame view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new PlayerCreationFrame(this.view);
		this.view.setVisible(false);
	}
}

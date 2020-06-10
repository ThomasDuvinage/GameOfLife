package guiController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

/**
 * 
 * @author ThomasDuvinage
 * 
 * @brief This Class is the pause button controller 
 *
 */
public class GridPauseButtonController implements ActionListener {
	/// timer used to update the cell content 
	private Timer timerController;
	
	/**
	 * @brief Constructor 
	 * @param timerController
	 */
	public GridPauseButtonController(Timer timerController) {
		this.timerController = timerController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.timerController.cancel();
	}

}

package guiController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.LevelDoneFrame;
import gui.StartGameFrame;
import gui.Window;


/**
 * 
 * @author ThomasDuvinage
 * 
 * @brief This Class is used to back to the strating page 
 *
 */
public class BackMenuButtonController implements ActionListener {
	/// view 
	private LevelDoneFrame view;
	/// previous window used to delete it 
	private Window previousView;
	
	/**
	 * @brief Constructor
	 * 
	 * @param view
	 * @param previousView
	 */
	public BackMenuButtonController(LevelDoneFrame view,  Window previousView) {
		this.view = view;
		this.previousView = previousView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.view.dispose();
		this.previousView.dispose();
		new StartGameFrame(500, 500);
	}

}

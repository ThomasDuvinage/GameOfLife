package guiController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.LevelDoneFrame;
import gui.Window;
import player.PlayerModel;

/**
 * 
 * @author ThomasDuvinage
 * 
 * @brief This Class is used to move to the next level
 *
 */
public class NextLevelButtonController implements ActionListener{
	/// view of the Done level 
	private LevelDoneFrame view;
	/// model of the player
	private PlayerModel model;
	/// This is the previous window, it is used to delete this window
	private Window previousView;
	
	/**
	 * @brief Constructor
	 * 
	 * @param view
	 * @param model
	 * @param previousView
	 */
	public NextLevelButtonController(LevelDoneFrame view, PlayerModel model, Window previousView) {
		this.view = view;
		this.model = model;
		this.previousView = previousView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Window gridJFrameView = new Window(550, 550);
		gridJFrameView.playerName.setText(this.model.getName());
		gridJFrameView.playerLevel.setText(Integer.toString(this.model.getLevel()));
		gridJFrameView.playerBestScore.setText(Integer.toString(this.model.getBestScore()));
		gridJFrameView.setPlayerModel(this.model);
		
		//The following line permits to focus on the current JFrame to enable Key event
		gridJFrameView.setFocusable(true);
		this.view.dispose();
		this.previousView.dispose();
	}

}

package player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.Panel;
import gui.Window;
import guiController.MouseEventGridFilling;

public class PlayerController implements ActionListener {
	public PlayerModel model;
	private PlayerCreationFrame view;
	
	public MouseEventGridFilling mouseEventController;
	
	public PlayerController(Panel gridPanel) {
		mouseEventController = new MouseEventGridFilling(gridPanel); 
	}
	

	public PlayerController(PlayerCreationFrame view,PlayerModel model) {
		this.view = view;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 * If the textField is filled by something then we created the player and assigned him 
		 * the given name
		 */
		if(!this.view.nameTextField.getText().equals("") && ! this.view.firstNameTextField.getText().equals("")) {
			//TODO read if the name already exists in the name file
			// we can imagine doing that with a DB and SQL requests (for fun)
			this.model = new PlayerModel(this.view.nameTextField.getText(),this.view.firstNameTextField.getText());
			
			if(!this.model.checkUserPresence()) {
				this.model.insertPlayerToJSON();
				Window gridJFrameView = new Window(this.view.getSize().width, this.view.getSize().height);
				gridJFrameView.playerName.setText(this.model.getName());
				gridJFrameView.playerLevel.setText(Integer.toString(this.model.getLevel()));
				gridJFrameView.playerBestScore.setText(Integer.toString(this.model.getBestScore()));
				gridJFrameView.setPlayerModel(this.model);
				
				//The following line permits to focus on the current JFrame to enable Key event
				this.view.setVisible(false);
			}	
			else {
				this.view.errorConnectionText.setText("<html><font color='red'>This account already exists please use the Connect button</font></html>");
			}
		}
	}
}

package player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import gui.Window;

/**
 * 
 * @author ThomasDuvinage
 * 
 * @brief This Class is used to connect the player by reading the JSON file
 *
 */
public class PlayerConnectionController implements ActionListener {
	/// player creation frame
	private PlayerCreationFrame view;
	/// player model
	private PlayerModel model;

	/**
	 * @brief Constructor
	 * @param view
	 * @param model
	 */
	public PlayerConnectionController(PlayerCreationFrame view, PlayerModel model ) {
		this.view = view;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.checkUserPresence();
		
	}
	
	/**
	 * @brief This method is used to check if the player already exists in the JSON file 
	 * and update the label to let the player know that this account already exists
	 * @return
	 */
	private boolean checkUserPresence() {
		File jsonFile = new File("Files/players.json"); 
		
		if(jsonFile.exists()) {
			JSONParser parser = new JSONParser();
			JSONArray playerList = null;
			try {
				playerList = (JSONArray) parser.parse(new FileReader("Files/players.json"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i = 0; i < playerList.size();i++) {
				JSONObject obj = (JSONObject)playerList.get(i);
				String readName = (String) obj.get("lastName");
				String readFirstName = (String) obj.get("firstName");
				Long level = (Long) obj.get("level");
				Long bestScore = (Long) obj.get("bestScore");
				
				if(this.view.nameTextField.getText().equals(readName) && this.view.firstNameTextField.getText().equals(readFirstName)) {
					this.model = new PlayerModel(this.view.nameTextField.getText(),this.view.firstNameTextField.getText(),level.intValue(), bestScore.intValue());
					
					Window gridJFrameView = new Window(this.view.getSize().width, this.view.getSize().height);
					gridJFrameView.playerName.setText(this.model.getName());
					gridJFrameView.playerLevel.setText(Integer.toString(this.model.getLevel()));
					gridJFrameView.playerBestScore.setText(Integer.toString(this.model.getBestScore()));
					gridJFrameView.setPlayerModel(this.model);
				
					
					//The following line permits to focus on the current JFrame to enable Key event
					gridJFrameView.gridPanel.setFocusable(true);
					this.view.dispose();
					return true;
				}
			}
		}
		
		this.view.errorConnectionText.setText("<html><font color='red'>This account doesn't exist</font></html>");
		return false;
	}

}

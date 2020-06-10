package player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import game.Point2D;
import gui.LevelDoneFrame;
import gui.Window;

/**
 * 
 * @author ThomasDuvinage
 * 
 * @brief This Class represents the player behaviour.
 *
 */
public class PlayerModel {
	/// previous position of the player
	private Point2D previousPosition;
	/// actual position of the player
	private Point2D position;
	/// name of the player
	private String name;
	/// first name if the player
	private String firstName;
	/// actual score of the player
	private int score;
	/// best score of the player
	private int bestScore;
	/// actual level of the player
	public int level;
	/// main view 
	public Window view;
	
	/**
	 * @brief Constructor
	 * @param name
	 * @param firstName
	 */
	public PlayerModel(String name, String firstName) {
		this.name = name;
		this.firstName = firstName;
		
		this.position = new Point2D();
		this.previousPosition = position;
		
	}
	
	/**
	 * @brief Constructor 
	 * @param name
	 * @param firstName
	 * @param level
	 * @param bestScore
	 */
	public PlayerModel(String name, String firstName, int level, int bestScore) {
		this.name = name;
		this.firstName = firstName;
		this.level = level;
		this.bestScore = bestScore;
		
		this.position = new Point2D();
		this.previousPosition = position;
	}
	
	/**
	 * @brief set view 
	 * @param view
	 */
	public void setView(Window view) {
		this.view = view;
	}

	/**
	 * @brief set player name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get the name of the player
	 * @return player name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @brief get the player level
	 * @return player level
	 */
	public int getLevel() {
		return this.level;
	}

	/**
	 * @brief set the player level
	 * @param newLevel
	 */
	public void setLevel(int newLevel) {
		this.level = newLevel;
	}

	/**
	 * @brief This method is used to add score to the actual score 
	 * @param scoreToAdd
	 */
	public void addScore(int scoreToAdd) {
		this.score += scoreToAdd;
	}

	/**
	 * @brief get the player actual score 
	 * @return player score
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * @brief get the player best score
	 * @return best score
	 */
	public int getBestScore() {
		return this.bestScore;
	}

	/**
	 * @brief update the best score 
	 * @param newBestScore 
	 */
	public void updateBestScore(int newBestScore) {
		this.bestScore = newBestScore;
	}
	
	/**
	 * @brief get the player position
	 * @return player Pos Point2D
	 */
	public Point2D getPos() {
		return this.position;
	}
	
	/**
	 * @brief This method is used to set the player position by using Point2D
	 * @param newPos
	 */
	public void setPosition(Point2D newPos) {
		this.position = newPos;
	}
	
	/**
	 * @brief This method is used to set the player position by using coordinate
	 * @param xPos
	 * @param yPos
	 */
	public void setPositionByCoordinate(int xPos, int yPos) {
		this.position = new Point2D(xPos,yPos);
	}
	
	/**
	 * @brief This method is used to update the X player position
	 * @param positionIncrement
	 */
	public void updatePositionX(int positionIncrement) {
		Point2D positionBuffer = new Point2D(this.position.posX + positionIncrement, this.position.posY);
		
		if(this.view.gridPanel.grid.setPlayerCell(positionBuffer, this.previousPosition)) {
			if(this.view.gridPanel.grid.isPlanet(positionBuffer)) {
				this.score++;
				this.updateScoreLabel(score);
			}
			this.position.posX = positionBuffer.posX;
			this.previousPosition = this.position;
			this.view.updatePlayerPositionView();
		}
		
		if(this.checkScoreForCompleteLevel()) {
			this.level++;
			this.bestScore += this.score;
			this.score = 0;
			this.updateJSONFileContent();
			new LevelDoneFrame(this, this.view);
		}
	}
	
	/**
	 * @brief This method is used to update the Y player position
	 * @param positionIncrement
	 */
	public void updatePositionY(int positionIncrement) {
		Point2D positionBuffer = new Point2D(this.position.posX, this.position.posY + positionIncrement);

		if(this.view.gridPanel.grid.setPlayerCell(positionBuffer, this.previousPosition)) {
			if(this.view.gridPanel.grid.isPlanet(positionBuffer)) {
				this.score++;
				this.updateScoreLabel(score);
			}
			this.position.posY = positionBuffer.posY;
			this.previousPosition = this.position;
			this.view.updatePlayerPositionView();
		}
		
		if(this.checkScoreForCompleteLevel()) {
			this.level++;
			this.bestScore += this.score;
			this.score = 0;
			this.updateJSONFileContent();
			new LevelDoneFrame(this, this.view);
		}
	}
	
	/**
	 * @brief This method is used to check if the level is complete or not 
	 * @return
	 */
	public boolean checkScoreForCompleteLevel() {
		if(this.score == (this.level + 1)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @brief This method is used to update the score label in the view
	 * @param score
	 */
	private void updateScoreLabel(int score) {
		this.view.actualScore.setText(Integer.toString(score));
	}
	

	/**
	 * @brief Insert JSON file the player profile 
	 * This JSON file enables to store the players and easily travel trough to have informations on each of them 
	 * or to do statistics
	 */
	public void insertPlayerToJSON() {
		File jsonFile = new File("Files/players.json"); 
		
		JSONObject playerDetails = new JSONObject();

		playerDetails.put("firstName", this.firstName);
		playerDetails.put("lastName", this.name);
		playerDetails.put("level", this.level);
		playerDetails.put("bestScore", this.bestScore);
		
		JSONArray playerList = null;
		
		if(jsonFile.exists()) {
			JSONParser parser = new JSONParser();
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
		}
		else {
			playerList = new JSONArray();
		}
		
		playerList.add(playerDetails);

		try (FileWriter file = new FileWriter("Files/players.json")) {
			file.write(playerList.toString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @brief Update the player detail content in the JSON file 
	 */
	public void updateJSONFileContent() {
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
			
			JSONArray updatedPlayerList = new JSONArray();
			
			for(int i = 0; i < playerList.size();i++) {
				JSONObject obj = (JSONObject)playerList.get(i);
				String readName = (String) obj.get("lastName");
				String readFirstName = (String) obj.get("firstName");
				if(this.name.equals(readName) && this.firstName.equals(readFirstName)) {
					obj.put("level", this.level);
					obj.put("bestScore", this.bestScore);
					
				}
				
				updatedPlayerList.add(obj);
			}
			
			try (FileWriter file = new FileWriter("Files/players.json")) {
				file.write(updatedPlayerList.toString());
				file.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @brief This method is used to know if the player already exists in the JSON file
	 * @return
	 */
	public boolean checkUserPresence() {
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
				
				if(this.name.equals(readName) && this.firstName.equals(readFirstName)) {
					return true;
				}
			}
		}
		return false;
	}
}

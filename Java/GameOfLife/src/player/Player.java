package player;

public class Player extends SpaceShip{
	private String name;
	private int score;
	private int bestScore;
	public int level;
	
	//TODO add spaceShip
	
	public Player(String playerName) {
		this.name = playerName;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int newLevel) {
		this.level = newLevel;
	}
	
	public void addScore(int scoreToAdd) {
		this.score = scoreToAdd;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getBestScore() {
		return this.bestScore;
	}
	
	public void updateBestScore(int newBestScore) {
		this.bestScore = newBestScore;
	}
	
}

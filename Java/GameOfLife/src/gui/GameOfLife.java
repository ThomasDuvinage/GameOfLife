package gui;


/**
 * 
 * @author ThomasDuvinage
 * 
 * @brief This Class is the main class that you have to launch to start the games
 *
 */
public class GameOfLife {

	/**
	 * @brief Constructor
	 */
	public GameOfLife() {
		new StartGameFrame(550,550);
	}

	public static void main(String[] args) {
		new GameOfLife();
	}
}

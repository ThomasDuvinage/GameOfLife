package gui;

import javax.swing.JPanel;
import java.awt.*;


import game.Grid;
import player.PlayerController;


/**
 * 
 * @author ThomasDuvinage
 * 
 * @brief This Class represents the Grid Panel displayed on the screen 
 *
 */
public class Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2534922421944100743L;
	/// application is the game grid  
	public Grid grid; 
	/// color of the cells 
	private Color backgroundColor = Color.white; 
	
	private PlayerController playerController;
	
	/**
	 * @brief Panel Constructor 
	 * 
	 * Initialize application and mouseListener
	 * 
	 * @param frameSizeWidth
	 * @param frameSizeHeight
	 * @param panelWidth
	 * @param panelHeight
	 * @param cellSize
	 */
	public Panel(int frameSizeWidth, int frameSizeHeight, int panelWidth, int panelHeight, int cellSize) {
		grid = new Grid((frameSizeWidth - panelWidth)/2,cellSize,panelWidth, panelHeight, cellSize, true);
		
		playerController = new PlayerController(this);
		this.addMouseListener(playerController.mouseEventController);
		
	
	}
	

	
	/**
	 *  @brief Override paintComponement from JComponent
	 * 		Reset the background color
	 * 		Draw the updated grid
	 * 
	 * @param Graphics g
	 */
	public void paintComponent(Graphics g) {
		g.setColor(this.backgroundColor);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		grid.draw(g);
	}
	
}

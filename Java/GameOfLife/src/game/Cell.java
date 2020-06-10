package game;

import java.awt.*;

/**
 * 
 * @author ThomasDuvinage
 * 
 * @brief This class represents the cell composing the grid 
 *
 */
public class Cell extends Point2D {
	/// cells width
	private int cellSize;
	/// state of the cell (alive/dead)(true/false)
	private boolean state;
	/// state of the cell after applying rules on the grid
	private boolean newState;
	/// color of the Cell
	private Color color;
	
	/// state of the cell when it's a player
	private boolean playerState;
	/// state of the cell when it's a planet
	private boolean planetState;

	/// radius of cells corners
	private int cellRound = 15;

	/**
	 * @brief Constructor initialize the cell position and cell width
	 * @param indexX X index of the cell in the Grid
	 * @param indexY Y index of the cell in the Grid
	 * @param cellSize cell width 
	 */
	public Cell(int indexX, int indexY, int cellSize) {
		this.posX = indexX * cellSize;
		this.posY = indexY * cellSize;
		this.cellSize = cellSize;
		this.color = Color.red;
	}
	
	/**
	 * @brief Cell constructor with the color parameter 
	 * @param indexX
	 * @param indexY
	 * @param cellSize
	 * @param cellColor color used to fill the cell (java.awt.Color)
	 */
	public Cell(int indexX, int indexY, int cellSize, Color cellColor) {
		this.posX = indexX * cellSize;
		this.posY = indexY * cellSize;
		this.cellSize = cellSize;
		this.color = cellColor;
		this.playerState = true;
	}
	
	
	/**
	 * @brief Draw the rectangle border and fill the rectangle depending on the cell state
	 * 
	 * @param g 
	 * @param initPosX X distance set to center the Panel in the Window
	 * @param initPosY Y distance set to center the Panel in the Window
	 */
	public void draw(Graphics g, int initPosX, int initPosY) {
		if(this.state) {
			g.setColor(this.color);
			g.fillRoundRect(this.posX + initPosX, this.posY + initPosY, this.cellSize, this.cellSize, this.cellRound, this.cellRound);
		}
		if(this.playerState) {
			g.setColor(Color.blue);
			g.fillRoundRect(this.posX + initPosX, this.posY + initPosY, this.cellSize, this.cellSize,this.cellRound, this.cellRound);
		}
		if(this.planetState) {
			g.setColor(Color.green);
			g.fillRoundRect(this.posX + initPosX, this.posY + initPosY, this.cellSize, this.cellSize,this.cellRound, this.cellRound);
		}
		g.setColor(Color.black);
		g.drawRoundRect(this.posX + initPosX, this.posY + initPosY, this.cellSize, this.cellSize,this.cellRound, this.cellRound);
	}
	
	/**
	 * @brief set newState to originalNewState
	 * @param originalNewState
	 */
	public void setNewState(boolean originalNewState) {
		this.newState = originalNewState;
	}
	
	/**
	 * @brief set state to originState
	 * @param originState 
	 */
	public void setState(boolean originState) {
		this.state = originState;
	}
	
	/**
	 * @brief update state to newState
	 */
	public void updateState() {
		this.state = this.newState;
	}
	
	/**
	 * @return state of the cell
	 */
	public boolean getState() {
		return this.state;
	}
	
	/**
	 * @brief set cell state to player state 
	 */
	public void setPlayerCell() {
		this.playerState = true;
		this.state = false;
	}
	
	/**
	 * @brief Set cell state to default state 
	 */
	public void setDefaultCell() {
		this.playerState = false;
		this.planetState = false;
		this.state = false;
	}
	
	/**
	 * @brief set cell state to planet state 
	 */
	public void setPlanetState() {
		this.planetState = true;
		this.state = false;
	}
	
	/**
	 * @brief get the cell state 
	 * @return planet cell state 
	 */
	public boolean getPlanelState() {
		return this.planetState;
	}
	
}

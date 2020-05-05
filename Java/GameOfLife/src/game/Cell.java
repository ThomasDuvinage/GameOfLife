package game;

import java.awt.Color;
import java.awt.Graphics;

public class Cell extends Point2D {
	private int size;
	private boolean state;
	private boolean newState;
	//TODO add color

	public Cell(int indexX, int indexY, int cellSize) {
		this.posX = indexX * cellSize;
		this.posY = indexY * cellSize;
		this.size = cellSize;
	}
	
	
	public void draw(Graphics g, int initPosX, int initPosY) {
		if(this.state) {
			g.setColor(Color.red);
			g.fillRect(this.posX + initPosX, this.posY + initPosY, this.size, this.size);
		}
		g.setColor(Color.black);
		g.drawRect(this.posX + initPosX, this.posY + initPosY, this.size, this.size);
	}
	
	public void setNewState(boolean originalNewState) {
		this.newState = originalNewState;
	}
	
	public void setState(boolean originState) {
		System.out.println("Cell State update at x = " + posX + " y = " + posY);
		this.state = originState;
	}
	
	
	public void updateState() {
		this.state = this.newState;
	}
	
	public boolean getState() {
		return this.state;
	}
	

	
	private void applyRules() {
		
	}
}

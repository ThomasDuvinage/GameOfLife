package game;

public class Point2D {
	public int posX,posY;

	public Point2D(int newX, int newY) {
		this.posX = newX;
		this.posY = newY;
	}
	
	public Point2D() {
		this.posX = 0;
		this.posY = 0;
	}
	
	protected int getPosX() {
		return this.posX; 
	}
	
	protected int getPosY() {
		return this.posY; 
	}
	
	protected void setPosition(int newX, int newY) {
		this.posX = newX;
		this.posY = newY;
	}
	
	public boolean isNegatif() {
		if(this.posX < 0 || this.posY < 0)
			return true;
		
		return false;
	}
}

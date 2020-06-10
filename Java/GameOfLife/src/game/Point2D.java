package game;

/**
 * 
 * @author ThomasDuvinage
 * 
 * @brief This Class is used to represent the position of cell in the grid 
 *
 */
public class Point2D {
	/// X position
	public int posX;
	/// Y position
	public int posY;
	
	/**
	 * @brief Constructor 
	 * @param newX position X
	 * @param newY position Y
	 */
	public Point2D(int newX, int newY) {
		this.posX = newX;
		this.posY = newY;
	}
	
	/**
	 * @brief Empty Constructor
	 */
	public Point2D() {

	}
	
	/**
	 * @brief get X position
	 * @return X position
	 */
	protected int getPosX() {
		return this.posX; 
	}
	
	/**
	 * @brief get Y position 
	 * @return Y position
	 */
	protected int getPosY() {
		return this.posY; 
	}
	
	/**
	 * @brief Set position 
	 * @param newX position X
	 * @param newY position Y
	 */
	protected void setPosition(int newX, int newY) {
		this.posX = newX;
		this.posY = newY;
	}
	
	/**
	 * check if the position is out of the bounds
	 * @return
	 */
	public boolean isNegatif() {
		if(this.posX < 0 || this.posY < 0)
			return true;
		
		return false;
	}
	
	/**
	 * @brief This method is used to know if the point is outside a given bounding box
	 * @param sizeX
	 * @param sizeY
	 * @return
	 */
	public boolean isOutBound(double sizeX, double sizeY) {
		if(this.posX < 0 || this.posX > sizeX) {
			return true;
		}
		
		if(this.posY < 0 || this.posY > sizeY) {
			return true;
		}
		
		return false;
	}
}

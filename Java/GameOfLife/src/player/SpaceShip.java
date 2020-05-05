package player;
import game.Point2D;

public class SpaceShip extends Point2D {
	//TODO add Color
	
	private double speed;
	private double boostSpeed;
	private double header;
	
	public SpaceShip() {
		// TODO Auto-generated constructor stub
	}
	
	protected void updatePosition(int incrementationX, int incrementationY) {
		//TODO take in account the trajectory of the SpaceShip (header) (bressemham algorithm)
		this.posX += incrementationX;
		this.posY += incrementationY;
	}
	
	protected void applyBoost() {
		
	}
	
}

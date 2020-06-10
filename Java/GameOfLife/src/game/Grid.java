package game;

import java.awt.Graphics;
import java.util.Random;

import game.Cell;

/**
 * 
 * @author ThomasDuvinage
 * 
 * @brief This Class represents the grid which is displayed in the Panel
 *        afterward
 *
 */
public class Grid {
	/// width of the grid
	public int width;
	/// height of the grid
	public int height;
	/// Cells width
	public int cellWidth;
	/// Cells width number which is computed depending on the cellWidth
	public int cellNumberWidth;
	/// Cells height number which is computed depending on the cellWidth
	public int cellNumberHeight;
	/// X Position of the grid in the Panel
	public int gridPosX;
	/// Y Position of the grid in the Panel
	public int gridPosY;
	/// 2d array array of Cells representing the grid
	private Cell[][] grid;

	/**
	 * @brief Grid constructor
	 * @param gridPosX  X Position of the grid in the Panel
	 * @param gridPosY  Y Position of the grid in the Panel
	 * @param newWidth  width size of the grid
	 * @param newHeigth height size of the grid
	 * @param empty     empty or randomly filled
	 */
	public Grid(int gridPosX, int gridPosY, int newWidth, int newHeigth, boolean empty) {
		this.gridPosX = gridPosX;
		this.gridPosY = gridPosY;
		this.width = newWidth;
		this.height = newHeigth;
		this.cellWidth = 10;

		this.updateCellNumber();

		this.grid = new Cell[this.cellNumberHeight][this.cellNumberWidth];
		this.fillGridWithCell(empty);

	}

	/**
	 * @brief Grid constructor
	 * @param gridPosX     X Position of the grid in the Panel
	 * @param gridPosY     Y Position of the grid in the Panel
	 * @param newWidth     width size of the grid
	 * @param newHeigth    height size of the grid
	 * @param newCellWidth width size of cells
	 * @param empty        empty or randomly filled
	 */
	public Grid(int gridPosX, int gridPosY, int newWidth, int newHeigth, int newCellWidth, boolean empty) {
		this.gridPosX = gridPosX;
		this.gridPosY = gridPosY;
		this.width = newWidth;
		this.height = newHeigth;
		this.cellWidth = newCellWidth;

		this.updateCellNumber();

		this.grid = new Cell[this.cellNumberHeight][this.cellNumberWidth];
		this.fillGridWithCell(empty);
	}

	/**
	 * @param newWidth
	 * @param newHeigth
	 */
	protected void setGridSize(int newWidth, int newHeigth) {
		this.width = newWidth;
		this.height = newHeigth;

		this.updateCellNumber();
	}

	/**
	 * @brief Set the number of cells equals to size/cellWidth
	 */
	private void updateCellNumber() {
		this.cellNumberHeight = height / cellWidth;
		this.cellNumberWidth = width / cellWidth;
	}

	/**
	 * @param newCellWidth
	 */
	protected void setCellWidth(int newCellWidth) {
		this.cellWidth = newCellWidth;
	}

	/**
	 * @brief fill the grid with Cells
	 * @param emptyGrid true if you want the grid to be empty and false if you want
	 *                  to randomly fill the grid
	 */
	public void fillGridWithCell(boolean emptyGrid) {
		if (!emptyGrid) {
			for (int i = 0; i < this.cellNumberHeight; i++) {
				for (int k = 0; k < this.cellNumberWidth; k++) {
					Random randomState = new Random();
					this.grid[k][i] = new Cell(i, k, this.cellWidth);
					this.grid[k][i].setState(randomState.nextBoolean());

				}
			}
		} else {
			for (int i = 0; i < this.cellNumberHeight; i++) {
				for (int k = 0; k < this.cellNumberWidth; k++) {
					this.grid[k][i] = new Cell(i, k, this.cellWidth);
				}
			}
		}
	}
	
	/**
	 * @brief This method sets the pointed cell to default cell
	 * @param playerPos
	 */
	public void setDefaultCellAt(Point2D playerPos) {
		this.grid[playerPos.posY][playerPos.posX].setDefaultCell();
	}
	
	/**
	 * @brief 
	 * @param playerPos
	 * @param previousPlayerPos
	 * @return
	 */
	public boolean setPlayerCell(Point2D playerPos, Point2D previousPlayerPos) {
		if(playerPos.posX >= 0 && playerPos.posX < this.cellNumberWidth && playerPos.posY >= 0 && playerPos.posY < this.cellNumberHeight) {
			if(!this.grid[playerPos.posY][playerPos.posX].getState()) {
				this.grid[playerPos.posY][playerPos.posX].setPlayerCell();
				this.grid[previousPlayerPos.posY][previousPlayerPos.posX].setDefaultCell();
				return true;
			}
		}
		
		return false;
	}

	
	/**
	 * @brief This method is used to know if the the cell at player position is a planet or not 
	 * @param playerPos
	 * @return
	 */
	public boolean isPlanet(Point2D playerPos) {
		if(this.grid[playerPos.posY][playerPos.posX].getPlanelState()) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * @brief Count the number of alive neighbor around a given cell
	 * @param cellPosX X position of the cell
	 * @param cellPosY Y position of the cell
	 * @return number of living cells
	 */
	protected int getNeighborAt(int cellPosX, int cellPosY) {
		int sum = 0;

		if (cellPosX - 1 >= 0 && this.getCellStateAt(cellPosX - 1, cellPosY))
			sum++;

		if (cellPosX + 1 < this.cellNumberWidth && this.getCellStateAt(cellPosX + 1, cellPosY))
			sum++;

		if (cellPosY - 1 >= 0 && this.getCellStateAt(cellPosX, cellPosY - 1))
			sum++;

		if (cellPosY + 1 < this.cellNumberWidth && this.getCellStateAt(cellPosX, cellPosY + 1))
			sum++;

		if (cellPosY + 1 < this.cellNumberHeight && cellPosX - 1 >= 0) {
			if (this.getCellStateAt(cellPosX - 1, cellPosY + 1))
				sum++;
		}

		if (cellPosY + 1 < this.cellNumberHeight && cellPosX + 1 < this.cellNumberWidth) {
			if (this.getCellStateAt(cellPosX + 1, cellPosY + 1))
				sum++;
		}

		if (cellPosY - 1 >= 0 && cellPosX - 1 >= 0) {
			if (this.getCellStateAt(cellPosX - 1, cellPosY - 1))
				sum++;
		}

		if (cellPosY - 1 >= 0 && cellPosX + 1 < this.cellNumberWidth) {
			if (this.getCellStateAt(cellPosX + 1, cellPosY - 1))
				sum++;
		}

		return sum;
	}

	/**
	 * @param posX X position of the cell
	 * @param posY Y position of the cell
	 * @return state of the cell
	 */
	public boolean getCellStateAt(int posX, int posY) {
		if (posX < 0 || posX > this.width || posY < 0 || posY > this.height) {
			return false;
		} else {
			return this.grid[posY][posX].getState();
		}
	}

	/**
	 * @param posX X position of the cell
	 * @param posY Y position of the cell
	 * @return cell at the given position
	 */
	public Cell getCellAt(int posX, int posY) {
		return this.grid[posY][posX];
	}

	/**
	 * @brief Update the state at a given position, if the given state is equal to
	 *        the actual one then the opposite state is set
	 * @param mousePosInFrameX
	 * @param mousePosInFrameY
	 */
	public void updateCellStateAt(int mousePosInFrameX, int mousePosInFrameY) {
		if (this.grid[mousePosInFrameY][mousePosInFrameX].getState())
			this.grid[mousePosInFrameY][mousePosInFrameX].setState(false);
		else
			this.grid[mousePosInFrameY][mousePosInFrameX].setState(true);
	}

	/**
	 * @brief For every cells in the grid draw
	 * @param g
	 */
	public void draw(Graphics g) {
		for (int y = 0; y < this.cellNumberHeight; y++) {
			for (int x = 0; x < this.cellNumberWidth; x++) {
				this.getCellAt(x, y).draw(g, this.gridPosX, this.gridPosY);
			}
		}
	}

	/**
	 * @brief Count the neighbor around every cells
	 * 
	 *        Cell state is updated depending on the following conditions : -
	 *        numberOfNeighbor < 2 -> dead(false) - numberOfNeighbor > 3 ->
	 *        dead(false) - numberOfNeighbor = 3 -> born(true) - numberOfNeighbor =
	 *        2 -> live(actual state)
	 */
	public void update() {
		for (int y = 0; y < this.cellNumberHeight; y++) {
			for (int x = 0; x < this.cellNumberWidth; x++) {

				int numberOfNeighbor = this.getNeighborAt(x, y);
				// System.out.println("Neighbor : " + numberOfNeighbor);
				if (numberOfNeighbor < 2)
					grid[y][x].setNewState(false); // underpop
				else if (numberOfNeighbor > 3)
					grid[y][x].setNewState(false); // overcrowd
				else if (numberOfNeighbor == 3)
					grid[y][x].setNewState(true); // born
				else if (numberOfNeighbor == 2)
					grid[y][x].setNewState(grid[y][x].getState()); // stay same
				grid[y][x].updateState();
			}

		}
	}
	
	/**
	 * @brief This method is used to addPlanet to the grid depending on the level of the player 
	 */
	public void addPlanetToGridRandomPosition() {
		Random rand = new Random(); 
		int posX = rand.nextInt(this.cellNumberWidth);
		int posY = rand.nextInt(this.cellNumberHeight);
		//this.grid[posY][posX] = new Cell(posX, posY, this.cellWidth);
		this.grid[posY][posX].setPlanetState();
	}

	/**
	 * @brief used for debug, it helps to visualize the grid in the console
	 */
	public void displayGrid() {
		for (int y = 0; y < this.cellNumberHeight; y++) {
			for (int x = 0; x < this.cellNumberWidth; x++) {
				System.out.print("|" + this.getCellAt(x, y).getState());
			}
			System.out.println("");
		}
	}
}

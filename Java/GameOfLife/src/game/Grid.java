package game;

import java.awt.Graphics;
import java.util.Random;

import game.Cell;

//TODO find why the wikipedia patterns are not working 

public class Grid{
	public int width, height, cellWidth;
	private int cellNumberWidth, cellNumberHeight;
	public int gridPosX, gridPosY;
	private Cell[][] grid;

	public Grid(int gridPosX, int gridPosY, int newWidth, int newHeigth, boolean empty) {
		this.gridPosX = gridPosX;
		this.gridPosY = gridPosY;
		this.width = newWidth;
		this.height = newHeigth;
		this.cellWidth = 10;
		
		this.updateCellNumber();
		
		this.grid = new Cell[this.cellNumberHeight][this.cellNumberWidth];
		this.fillGridRandomValue(empty);
	}
	
	public Grid(int gridPosX, int gridPosY, int newWidth, int newHeigth,int newCellWidth, boolean empty) {
		this.gridPosX = gridPosX;
		this.gridPosY = gridPosY;
		this.width = newWidth;
		this.height = newHeigth;
		this.cellWidth = newCellWidth;
		
		this.updateCellNumber();
		
		this.grid = new Cell[this.cellNumberHeight][this.cellNumberWidth];
		this.fillGridRandomValue(empty);
	}
		
	protected void setGridSize(int newWidth, int newHeigth) {
		this.width = newWidth;
		this.height = newHeigth;
		
		this.updateCellNumber();
	}
	
	private void updateCellNumber() {
		this.cellNumberHeight = height / cellWidth;
		this.cellNumberWidth = width / cellWidth;
		
		System.out.println("cellNumberHeight = " + this.cellNumberHeight);
		System.out.println("cellNumberWidth = " + this.cellNumberWidth);
		
	}
	
	protected void setCellWidth(int newCellWidth) {
		this.cellWidth = newCellWidth;
	}
	
	
	//TODO change to private 
	public void fillGridRandomValue(boolean fillGrid) {
		if(!fillGrid) {
			for(int i = 0; i < this.cellNumberHeight; i++) {
				for(int k = 0; k < this.cellNumberWidth; k++) {
					Random randomState = new Random();
					this.grid[k][i] = new Cell(i,k,this.cellWidth);
					this.grid[k][i].setState(randomState.nextBoolean());
				}
			}
		}
		else {
			for(int i = 0; i < this.cellNumberHeight; i++) {
				for(int k = 0; k < this.cellNumberWidth; k++) {
					this.grid[k][i] = new Cell(i,k,this.cellWidth);
				}
			}
		}	
	}
	
	protected int getNeighborAt(int cellPosX, int cellPosY) {
        int sum=0;
        
        if(cellPosX - 1 >= 0 && this.getCellStateAt(cellPosX - 1, cellPosY))
        	sum++;
        
        if(cellPosX + 1 < this.cellNumberWidth && this.getCellStateAt(cellPosX + 1, cellPosY))
        	sum++;
        
        if(cellPosY - 1 >= 0 && this.getCellStateAt(cellPosX, cellPosY - 1))
        	sum++;
        
        if(cellPosY + 1 < this.cellNumberWidth && this.getCellStateAt(cellPosX, cellPosY + 1))
        	sum++;
        
        if(cellPosY + 1 < this.cellNumberHeight && cellPosX - 1 >= 0) {
        	if(this.getCellStateAt(cellPosX - 1, cellPosY + 1))
        		sum++;
        }
        
        if(cellPosY + 1 < this.cellNumberHeight && cellPosX + 1 < this.cellNumberWidth) {
        	if(this.getCellStateAt(cellPosX + 1, cellPosY + 1))
        		sum++;
        }
        
        if(cellPosY - 1 >= 0 && cellPosX - 1 >= 0) {
        	if(this.getCellStateAt(cellPosX - 1, cellPosY - 1))
        		sum++;
        }
        
        if(cellPosY - 1 >= 0 && cellPosX + 1 < this.cellNumberWidth) {
        	if(this.getCellStateAt(cellPosX + 1, cellPosY - 1))
        		sum++;
        }
       
        return sum;
	}
	
	private boolean getCellStateAt(int posX, int posY) {
		return this.grid[posY][posX].getState();
	}
	
	public Cell getCellAt(int posX, int posY) {
		return this.grid[posY][posX];
	}
	
	public void updateCellStateAt(int mousePosInFrameX, int mousePosInFrameY) {
		if(this.grid[mousePosInFrameY][mousePosInFrameX].getState())
			this.grid[mousePosInFrameY][mousePosInFrameX].setState(false);
		else
			this.grid[mousePosInFrameY][mousePosInFrameX].setState(true);
	}
	

	
	public void draw(Graphics g) {		
		for (int y = 0; y < this.cellNumberHeight; y++) {
			for (int x = 0; x < this.cellNumberWidth; x++) {
					
	                this.getCellAt(x, y).draw(g, this.gridPosX,this.gridPosY);
			}
	    }		
	}
	
	public void update() {
		
		for (int y = 0; y < this.cellNumberHeight; y++) {
			for (int x = 0; x < this.cellNumberWidth; x++) {
				int numberOfNeighbor = this.getNeighborAt(x, y);
				//System.out.println("Neighbor : " + numberOfNeighbor);
	            if (numberOfNeighbor < 2)
	            	grid[y][x].setNewState(false); //underpop
	            else if (numberOfNeighbor > 3)
	            	grid[y][x].setNewState(false); //overcrowd
	            else if (numberOfNeighbor == 3)
	            	grid[y][x].setNewState(true); //born
	            else if (numberOfNeighbor == 2)
	            	grid[y][x].setNewState(grid[y][x].getState()); // stay same
	            grid[y][x].updateState();
			}
	    }
	}
	
	public void displayGrid() {
		for (int y = 0; y < this.cellNumberHeight; y++) {
			for (int x = 0; x < this.cellNumberWidth; x++) {
	                System.out.print("|" + this.getCellAt(x, y).getState());
			}
			System.out.println("");
	    }	
	}
	
	protected void fillFromExternalFiles(String FileName) {
		
	}
}

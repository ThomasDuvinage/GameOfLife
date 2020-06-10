package guiController;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import game.Point2D;
import gui.Panel;

public class MouseEventGridFilling implements MouseListener {
	private Panel gridPanel;

	public MouseEventGridFilling(Panel gridPanel) {
		this.gridPanel = gridPanel;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Point2D pointInPanel = getMousePosInGrid(e.getX(), e.getY());
		
		/**
		 * @brief If the mouse is pressed the cell pointed by the mouse is updated to alive state 
		 */
		if(pointInPanel != null) {
			this.gridPanel.grid.updateCellStateAt(pointInPanel.posX/this.gridPanel.grid.cellWidth, pointInPanel.posY/this.gridPanel.grid.cellWidth);
			this.gridPanel.repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @brief Get the mouse position in the grid displayed on the screen 
	 * 
	 * @param mousePosInFrameX X position of the mouse in the Frame
	 * @param mousePosInFrameY Y position of the mouse in the Frame
	 * @return Point2D object representing the position of the mouse in the grid 
	 * @return null if the mouse is outside the grid displayed on the screen 
	 */
	private Point2D getMousePosInGrid(int mousePosInFrameX, int mousePosInFrameY) {
		Point2D point = new Point2D();
		point.posX = mousePosInFrameX - this.gridPanel.grid.gridPosX;
		point.posY = mousePosInFrameY - this.gridPanel.grid.gridPosY;
				
		if(point.isOutBound(this.gridPanel.grid.width, this.gridPanel.grid.height)) {
			return null;
		}

		return point;
	}

}

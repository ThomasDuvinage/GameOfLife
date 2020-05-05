package gui;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;


import game.Grid;
import game.Point2D;

public class Panel extends JPanel {
	protected Grid application; 
	private Color background = Color.white;
	

	public Panel(int frameSizeWidth, int frameSizeHeight, int panelWidth, int panelHeight, int cellSize) {
		application = new Grid((frameSizeWidth - panelWidth)/2,cellSize,panelWidth, panelHeight, cellSize, true);
		this.initMouseListener();
	}

	public void paintComponent(Graphics g) {
		g.setColor(this.background);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		application.draw(g);
	}

	public void updateComponent() {
		application.update();
	}

	private Point2D getMousePosInGrid(int mousePosInFrameX, int mousePosInFrameY) {
		Point2D point = new Point2D();
		point.posX = mousePosInFrameX - this.application.gridPosX;
		point.posY = mousePosInFrameY - this.application.gridPosY;
		
		if(point.isNegatif())
			return null;
		
		return point;
	}
	
	private void initMouseListener() {
		this.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {				
				Point2D pointInPanel = getMousePosInGrid(e.getX(), e.getY());
				
				if(pointInPanel != null) {
					application.updateCellStateAt(pointInPanel.posX/application.cellWidth, pointInPanel.posY/application.cellWidth);
					repaint();
					
					application.displayGrid();
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
		});
	}
	
	public void updateContent() {
		System.out.println("je suis la");
		for(;;) {
			this.updateComponent();
			this.repaint();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

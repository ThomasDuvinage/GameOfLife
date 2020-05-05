package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.Grid;

public class GridEvent extends MouseAdapter {
	private Grid grid;
	
	public GridEvent(Grid loadedGrid) {
		grid = loadedGrid;
	}
	
    @Override
    public void mousePressed(MouseEvent e) {
        grid.getCellAt(e.getX(), e.getY()).setNewState(true);
        System.out.println("Clicked");
    }

}

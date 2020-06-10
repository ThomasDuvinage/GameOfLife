package guiController;

import java.util.Timer;
import java.util.TimerTask;

import gui.Panel;


/**
 * 
 * @author ThomasDuvinage
 * @brief This Class represents the Timer which is used to update the grid content at a given rate
 *
 */
public class TimerMainWindowController{
	/// Panel corresponding to the Window gridPanel
	private Panel gridPanel;
	
	/// Timer corresponding to taskManager in Window
	public Timer timer;

	public TimerMainWindowController(Panel panel) {
		this.gridPanel = panel;
		
		this.timer = new Timer();
		// call MyTask Class each second 
		this.timer.schedule(new MyTask(), 0,1000);
	}
	
	
	/**
	 * 
	 * @author ThomasDuvinage
	 * @brief Class containing the action do to during the timer execution period
	 *
	 */
	public class MyTask extends TimerTask {
		@Override
		public void run() {
			gridPanel.grid.update();
			gridPanel.repaint();		
		}
	}
}

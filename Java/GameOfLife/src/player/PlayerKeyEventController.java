package player;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import gui.Panel;

/**
 * 
 * @author ThomasDuvinage
 * 
 * @brief This method is used to handle the player key events
 *
 */
public class PlayerKeyEventController{
	/// player model
	private Panel panel;
	private PlayerModel model;
	
	private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;


	/**
	 * @brief Constructor 
	 * @param model
	 */
	public PlayerKeyEventController(Panel panel, PlayerModel model) {
		this.panel = panel;
		this.model = model;
		
		Object cle = new Object();
		this.panel.getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"),cle);
		this.panel.getActionMap().put(cle, new ActionsClavier("LEFT"));
		
		Object cle1 = new Object();
		this.panel.getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"),cle1);
		this.panel.getActionMap().put(cle1, new ActionsClavier("RIGHT"));
		
		Object cle2 = new Object();
		this.panel.getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"),cle2);
		this.panel.getActionMap().put(cle2, new ActionsClavier("UP"));
		
		Object cle3 = new Object();
		this.panel.getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"),cle3);
		this.panel.getActionMap().put(cle3, new ActionsClavier("DOWN"));
		
	}
	
	
	class ActionsClavier extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = -7700396631594828316L;
		private String label;
		public ActionsClavier(String label) {
			this.label = label;
		}

		public void actionPerformed(ActionEvent e) {
		    switch(this.label) {
			case "LEFT": 
				model.updatePositionX(-1);
	            break;
			case "RIGHT": 
				model.updatePositionX(1);
	            break;
			case "UP": 
				model.updatePositionY(-1);
	            break;
			case "DOWN": 
				model.updatePositionY(1);
	            break;
		    }
		 }
	}
}

package guiController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import gui.StartGameFrame;

/**
 * 
 * @author ThomasDuvinage
 * 
 * @brief This method is used to exit the game 
 *
 */
public class StartGameExitButtonController implements ActionListener {
	/// actual view 
	private StartGameFrame view;

	/**
	 * @brief Constructor 
	 * @param view
	 */
	public StartGameExitButtonController(StartGameFrame view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object[] options = {"Yes","No"};
		int answer = JOptionPane.showOptionDialog(null, "To do want to quit ?",null, JOptionPane.NO_OPTION, 0, new ImageIcon("images/exit.png"), options, options[1]);				
		if(answer == 0) {
			this.view.dispose();
		}
	}
}

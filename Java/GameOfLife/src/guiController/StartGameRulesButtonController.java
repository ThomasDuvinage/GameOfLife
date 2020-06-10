package guiController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import gui.StartGameFrame;

public class StartGameRulesButtonController implements ActionListener {
	private StartGameFrame view;

	public StartGameRulesButtonController(StartGameFrame view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object[] options = {"OK"};
		int answer = JOptionPane.showOptionDialog(null, "The rules are simple, the player is represented as a blue cell in the grid, he can move in the grid by pressing the cross keys on his keyboard.\nThe red cells are representing meteoroids, the player can’t go over a meteoroid, he has to avoid the meteoroid to reach the planets.\nPlanets are the green cells in the grid.\nThe number of planets to reach depends on the level of the player, the number of planets equals the level of the player plus one.\nThen when the player reaches all the planet, he finishes the level and can then play a new level-up game or stop playing. \n" + 
				"During the game the player can interact with the grid to add or delete cells.\nThe goal of this add-on is to avoid blocking situation during the game.\nFurthermore, it can help the player to create patterns in the grid to complete the level.\n" ,null, JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE ,new ImageIcon("images/rules.png"), options, null);				

	}

}
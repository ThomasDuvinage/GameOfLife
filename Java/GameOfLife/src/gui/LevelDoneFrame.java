package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import guiController.BackMenuButtonController;
import guiController.NextLevelButtonController;
import player.PlayerModel;

import java.awt.*;

/**
 * 
 * @author ThomasDuvinage
 * 
 * @brief This method is a JFrame used when the player finish a level
 *
 */
public class LevelDoneFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7949387954665329461L;
	/// main panel containing all other panels
	private JPanel frameContainer = new JPanel();
	/// panel containing player infos 
	private JPanel playerInfoPannel = new JPanel();
	
	/// back to main menu button
	private JButton backMainMenuButton;
	/// go to next level button
	private JButton nextLevelButton;
	
	/// player model
	private PlayerModel model;
	/// previous view 
	private Window previousView;

	/**
	 * @brief Constructor
	 * 
	 * @param model
	 * @param previousView
	 */
	public LevelDoneFrame(PlayerModel model, Window previousView) {
		this.setTitle("Level Done !");
		this.setSize(300, 230);
		
		this.model = model;
		this.previousView = previousView;
		
		this.setPlayerCreationPanel();
		this.frameContainer.add(this.playerInfoPannel);
		this.setContentPane(frameContainer);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/**
	 * @brief This method is used to initialize buttons
	 */
	private void initButtons() {
		this.backMainMenuButton = new JButton("< Back Main Menu");
		this.backMainMenuButton.addActionListener(new BackMenuButtonController(this, this.previousView));
		
		this.nextLevelButton = new JButton("Next Level >");
		this.nextLevelButton.addActionListener(new NextLevelButtonController(this, this.model, this.previousView));
	}
	
	/**
	 * @brief This method permits to add all the player informations to the JFrame
	 */
	private void setPlayerCreationPanel() {
		this.playerInfoPannel.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.playerInfoPannel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;

		this.playerInfoPannel.add(new JLabel("<html><h1 color='red'><strong><i>Well Done !</i></strong></h1><hr></html>"), gbc);

		this.playerInfoPannel.add(new JLabel("<html><h4>Actual Level : "+ Integer.toString(this.model.getLevel()) +"</h4><hr></html>"), gbc);
	
		this.playerInfoPannel.add(new JLabel("<html><h3>Play again ?</h3></html>"), gbc);

		
		// TODO find a way to place buttons to the south of the JFrame
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		this.initButtons();
		JPanel buttons = new JPanel();
		buttons.add(backMainMenuButton, gbc);
		buttons.add(nextLevelButton, gbc);

		gbc.weighty = 1;
		this.playerInfoPannel.add(buttons, gbc);
	}
	
}

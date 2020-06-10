package player;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


import gui.StartGameFrame;

public class PlayerCreationFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1435151723351217009L;
	/// main panel container
	private JPanel frameContainer = new JPanel();
	/// panel player account container 
	private JPanel playerAccountCreationPanel = new JPanel();
	/// previous frame
	protected StartGameFrame previousFrame;
	
	///player model
	protected PlayerModel model;
	
	/// start game button
	private JButton startGame;
	/// player connection button
	private JButton connectionButton;
	/// back main menu button
	private JButton backButton;
	/// player name label
	public JTextField nameTextField;
	/// player first name label
	public JTextField firstNameTextField;
	/// error connection label
	public JLabel errorConnectionText;
	
	/**
	 * @brief Constructor 
	 * @param previousView Represents the last JFrame which is the StartGameFrame
	 */
	public PlayerCreationFrame(StartGameFrame previousView) {
		this.setTitle("Account");
		this.setSize(previousView.getSize().width, previousView.getSize().height);
		
		this.previousFrame = previousView;
		
		this.setPlayerCreationPanel();

		frameContainer.add(this.playerAccountCreationPanel);
		this.setContentPane(frameContainer);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/**
	 * @brief This method is used to initialize all buttons 
	 */
	private void initButtons() {
		this.startGame = new JButton("Start Game");
		this.startGame.addActionListener(new PlayerController(this,this.model));

		// TODO implement the connection task
		this.connectionButton = new JButton("Connect to my account");
		this.connectionButton.addActionListener(new PlayerConnectionController(this, this.model));
		
		
		this.backButton = new JButton("Back");
		this.backButton.addActionListener(new PlayerBackButtonController(this, this.previousFrame));
	}

	/**
	 * @brief This methods is used to set the content of the panel. This content is used to let the player sign up
	 */
	private void  setPlayerCreationPanel() {
		this.playerAccountCreationPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.playerAccountCreationPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;

		this.playerAccountCreationPanel.add(new JLabel("<html><h1><strong><i>Account</i></strong></h1><hr></html>"), gbc);

		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setColumns(10);

		this.playerAccountCreationPanel.add(new JLabel("Name :"), gbc);
		this.playerAccountCreationPanel.add(nameTextField, gbc);
		this.playerAccountCreationPanel.add(new JLabel("First Name :"), gbc);
		this.playerAccountCreationPanel.add(firstNameTextField, gbc);
		
		this.errorConnectionText = new JLabel();
		this.playerAccountCreationPanel.add(errorConnectionText,gbc);

		// TODO find a way to place buttons to the south of the JFrame
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		this.initButtons();
		JPanel buttons = new JPanel(new GridBagLayout());
		buttons.add(startGame, gbc);
		buttons.add(connectionButton, gbc);
		buttons.add(backButton,gbc);

		gbc.weighty = 1;
		this.playerAccountCreationPanel.add(buttons, gbc);
	}
		
}

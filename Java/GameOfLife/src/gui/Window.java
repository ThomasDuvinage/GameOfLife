package gui;

import java.awt.*;
import javax.swing.*;


import guiController.*;
import player.PlayerKeyEventController;
import player.PlayerModel;

/**
 * 
 * @author ThomasDuvinage
 * 
 * @brief This Class represents the Window which pop up when you run the project  
 *
 */
public class Window extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/// JPanel containing all gridPanel and JButton 
	private JPanel FrameContainer = new JPanel();
	
	/// JPanel containing all the informations concerning the player (name, level, score)
	private JPanel playerPanel = new JPanel();
	
	public JLabel playerName = new JLabel();
	public JLabel playerLevel = new JLabel();
	public JLabel playerBestScore = new JLabel();
	public JLabel actualScore = new JLabel("0");
	
	private PlayerModel playerModel;
	
	/// Panel inserted in the JFrame 
	public Panel gridPanel;
		
	/// Button used to start the game
	private JButton playButton;
	
	/// Button used to pause the game
	public JButton pauseButton;
	
	/// Timer used to schedule the grid update task
	public TimerMainWindowController taskManager;
	
		
	/**
	 * @brief Window constructor 
	 * 
	 * @param windowWidth  width of the window
	 * @param windowHeight height of the window
	 */
	public Window(int windowWidth, int windowHeight) {
		this.setTitle("GameOfLife");
		this.setSize(windowWidth, windowHeight);
		
		this.gridPanel = new Panel(windowWidth, windowHeight,400,400,20);
		
		this.getContentPane().setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		FrameContainer.setLayout(new BorderLayout());
		
		FrameContainer.add(this.gridPanel);
		
		this.buttonsInit();
		this.playerPanelInit();
			
			
		this.setContentPane(FrameContainer);
		this.setVisible(true);
		
		gridPanel.grid.fillGridWithCell(false);	
	}
	
	/**
	 * @brief This loop initialize all buttons and actions linked to them
	 */
	private void buttonsInit() {
		GridPauseButtonController buttonPauseController = null;
		GridPlayButtonController buttonPlayController = new GridPlayButtonController(this.taskManager, this, buttonPauseController, playerModel);
		this.playButton=new JButton("Play");
		this.playButton.addActionListener(buttonPlayController);
		
		this.pauseButton=new JButton("Pause"); 
	
		JPanel buttonsPanel = new JPanel();
		
		buttonsPanel.add(playButton);
		buttonsPanel.add(pauseButton);
		
		// the following statement is an exception this is the cleanest solution I found
		//TODO find a solution to display score at the east of the JPanel
		buttonsPanel.add(new JLabel("Score :"));
		buttonsPanel.add(actualScore);
		
		FrameContainer.add(buttonsPanel,BorderLayout.SOUTH);
	}
	
	/**
	 * @brief This method is used to display player informations
	 */
	private void playerPanelInit() {
		this.playerPanel.add(new JLabel("Player : "));
		this.playerPanel.add(playerName);
		
		this.playerPanel.add(new JLabel("| Level : "));
		this.playerPanel.add(playerLevel);
		
		this.playerPanel.add(new JLabel("| Best score : "));
		this.playerPanel.add(playerBestScore);
		
		FrameContainer.add(this.playerPanel, BorderLayout.NORTH);
	}
	
	/**
	 * @brief This method is used to instantiate the playerModel
	 * @param model
	 */
	public void setPlayerModel(PlayerModel model) {
		this.playerModel = model;
		this.playerModel.setView(this);
		this.addPlanetToGrid(); // This line is called here because it needs the player level
		
		new PlayerKeyEventController(this.gridPanel, this.playerModel);

	}
	
	/**
	 * @brief This method is used to update the player position in the view when the player move
	 */
	public void updatePlayerPositionView() {
		this.gridPanel.repaint();
	}
	
	/**
	 * @brief This method is used to randomly add Planet(s) to the grid 
	 */
	private void addPlanetToGrid() {
		for(int i = 0; i < this.playerModel.getLevel() + 1;i++) {
			this.gridPanel.grid.addPlanetToGridRandomPosition();
		}
	}

}
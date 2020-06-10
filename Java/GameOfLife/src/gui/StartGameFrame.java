package gui;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import guiController.StartGameExitButtonController;
import guiController.StartGamePlayButtonController;
import guiController.StartGameRulesButtonController;

/**
 * 
 * @author ThomasDuvinage
 * 
 * @brief This Class is used to let the player login or create an account and play
 *
 */
public class StartGameFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel StartFrameContainer = new JPanel();
	private JButton rulesButton;
	private JButton playButton;
	private JButton exitButton;

	/**
	 * @brief Constructor
	 * @param windowWidth
	 * @param windowHeight
	 */
	public StartGameFrame(int windowWidth, int windowHeight) {
		this.setTitle("GameOfLife");
		this.setSize(windowWidth, windowHeight);
		StartFrameContainer.setLayout(new BorderLayout());
		this.initButton();
		setButtonAction();
		
		StartFrameContainer.add(new MenuPanel());
		
        this.setContentPane(StartFrameContainer);
		this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible (true);
		
	}
	
	/**
	 * @brief This method is used to initialize the buttons
	 */
	private void initButton() {
		Dimension buttonDimension = new Dimension(150,60);
		
		playButton = new JButton("Play");
		rulesButton = new JButton("Rules");
		exitButton = new JButton("Exit");
		playButton.setBackground(Color.red);
		
		playButton.setFont(new Font("Maker Felt", Font.PLAIN, 36));
		rulesButton.setFont(new Font("Maker Felt", Font.PLAIN, 36));
		exitButton.setFont(new Font("Maker Felt", Font.PLAIN, 36));
		
		playButton.setPreferredSize(buttonDimension);
		rulesButton.setPreferredSize(buttonDimension);
		exitButton.setPreferredSize(buttonDimension);
	}
	
	/**
	 * @brief This method is used to set ActionListener to the buttons
	 */
	private void setButtonAction() {
		this.playButton.addActionListener(new StartGamePlayButtonController(this));
		
		this.exitButton.addActionListener(new StartGameExitButtonController(this));
		
		this.rulesButton.addActionListener(new StartGameRulesButtonController(this));
	}
	
	/**
	 * 
	 * @author ThomasDuvinage
	 * 
	 * @brief This private class represents the Panel containing all the buttons and main label
	 *
	 */
    private class MenuPanel extends JPanel {
       

		/**
		 * 
		 */
		private static final long serialVersionUID = -2324573697320330132L;

		public MenuPanel() {
            setBorder(new EmptyBorder(10, 10, 10, 10));
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;

            add(new JLabel("<html><h1><strong><i>Game Of Life Space version</i></strong></h1><hr></html>"), gbc);

            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JPanel buttons = new JPanel(new GridBagLayout());
            buttons.add(playButton, gbc);
            buttons.add(rulesButton, gbc);
            buttons.add(exitButton, gbc);

            gbc.weighty = 1;
            add(buttons, gbc);
        }
    }
}

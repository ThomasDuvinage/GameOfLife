package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

public class StartGame extends JFrame{
	private JPanel StartFrameContainer = new JPanel();

	public StartGame(int windowWidth, int windowHeight) {
		this.setTitle("Starting page GameOfLife");
		this.setSize(windowWidth, windowHeight);
		StartFrameContainer.setLayout(new BorderLayout());
		
        this.setTextTitle();
        this.addButton();
		
        this.setContentPane(StartFrameContainer);
		this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		this.setVisible (true);
		
	}
	
	private void setTextTitle() {
	       setPreferredSize(new Dimension(300, 300));

	        JLabel label1 = new JLabel("Welcome");
	        JLabel label2 = new JLabel("To");
	        JLabel label3 = new JLabel("Game Of Life Space version");

	        label1.setAlignmentX(CENTER_ALIGNMENT);
	        label2.setAlignmentX(CENTER_ALIGNMENT);
	        label3.setAlignmentX(CENTER_ALIGNMENT);

	        StartFrameContainer.setLayout(new BoxLayout(StartFrameContainer, BoxLayout.PAGE_AXIS));

	        StartFrameContainer.add(Box.createVerticalGlue());
	        StartFrameContainer.add(label1);
	        StartFrameContainer.add(Box.createRigidArea(new Dimension(0, 10)));
	        StartFrameContainer.add(label2);
	        StartFrameContainer.add(Box.createRigidArea(new Dimension(0, 10)));
	        StartFrameContainer.add(label3);
	        StartFrameContainer.add(Box.createVerticalGlue());
	}
	
	private void addButton() {
		JButton rulesButton = new JButton("Rules");
		JButton playButton = new JButton("Play");
		
		StartFrameContainer.add(rulesButton, BorderLayout.SOUTH);
		//StartFrameContainer.add(playButton, BorderLayout.SOUTH);
		
		
	}
	
	public static void main(String args[]) {
		StartGame application = new StartGame(550, 500);
	}

}

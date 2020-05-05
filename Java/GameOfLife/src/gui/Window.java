package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;

import javax.swing.*;


public class Window extends JFrame  {
	private JPanel Framecontainer = new JPanel();
	private Panel gridPanel;
		
	//Define Buttons
	private JButton playButton;
	
	public Window(int windowWidth, int windowHeight) {
		this.setTitle("GameOfLife");
		this.setSize(windowWidth, windowHeight);
		
		this.gridPanel = new Panel(windowWidth, windowHeight,400,400,10);
		this.gridPanel.getPreferredSize();
		
		this.getContentPane().setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		Framecontainer.setLayout(new BorderLayout());
		
		Framecontainer.add(this.gridPanel);
		
		this.buttonsInit();
			
		this.setContentPane(Framecontainer);
		this.setVisible(true);
		
	}
	
	/**
	 * This loop initalize all buttons and actions linked to them
	 */
	private void buttonsInit() {
		this.playButton=new JButton("Play"); 
		
		this.playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked");
				
				//if you want you can comment the following line to draw you own grid
				gridPanel.application.fillGridRandomValue(false);
				updatePanelContent();
			}
		});
		
		Framecontainer.add(this.playButton, BorderLayout.SOUTH);
		
	}
	
	//TODO find a way to stop the loop
	protected void updatePanelContent() {
		System.out.println("je suis la");
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				gridPanel.repaint();
				gridPanel.updateComponent();
			}
		};
		Timer timer = new Timer();
		long intevalPeriod = 1 * 1000;
		System.out.println("StartingTask");
		timer.scheduleAtFixedRate(task, 0,intevalPeriod);
		System.out.println("Finish");
	
	}

	public static void main(String args[]) {
		Window application = new Window(500, 500);
		//application.updatePanelContent();
	}
}

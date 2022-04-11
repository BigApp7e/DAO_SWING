package com;


import java.awt.Color;
import java.awt.Component;

import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class GUI implements ActionListener {
	
	public JFrame getFrame() {
		return frame;
	}
		
	Game game;
	JFrame frame;

	//top panel components
	JPanel topPanel;
	ImageIcon firstCardImage;
	ImageIcon secondCardImage;
	ImageIcon fullDeckImage;
		
	JLabel firstImageHolder ;
	JLabel secondImageHolder;
	JLabel fullDeckDisplayHolder;
	
	//middle panel components
	JPanel midPanel;
	ButtonGroup radioBtnsGroup;
	
	//button panel components
	JPanel btnPanel;
	JButton playBtn;
	
	//bottom layer 
	JPanel bottomPanel;

	//Other
	ArrayList<String> logStringList;
	ArrayList<Card> fullDeckList;
	Card firstRandomCard;


	private static GUI instance;

	public static GUI getInstance(Game game){
	       if(instance == null){
	            instance = new GUI(game);
	        }
	        return instance;
	}   
	
	private GUI(Game game) {
				
		this.game = game;
		frame = new JFrame();		
		frame.setTitle("DAO Task");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1000, 800));
	    frame.setLayout(new FlowLayout());
	    frame.setLocationRelativeTo(null);
	       
		setTopPanel();
		setMiddlePanel();
		setButtonsPanel();
		setBottomPanel();
		
		frame.pack();
		frame.setVisible(true);
	    		
	}

	public void initializeValues(Card firstRandomCard,ArrayList<String> logStringList,ArrayList<Card> fullDeckList) {
		this.firstRandomCard = firstRandomCard;		
		this.logStringList = logStringList;
		this.fullDeckList = fullDeckList;
				
		firstImageHolder.setText("<html><body style='width:200;text-align:center'>"+ firstRandomCard.getName()+"<br/>"+ firstRandomCard.getValue()+"<br/>"+firstRandomCard.getSuit()+"</body></html>");
		 secondImageHolder.setText("");
		bottomPanel.removeAll();
		for (String line : this.logStringList) {
		    JLabel label = new JLabel(line);
		    bottomPanel.add(label);
		}

		bottomPanel.revalidate();
	}
	
	private void setTopPanel()
	{
		topPanel  = new JPanel();
	
		firstImageHolder = new JLabel();		
		firstImageHolder.setPreferredSize(new Dimension(200, 300));
		firstImageHolder.setBorder(BorderFactory.createLineBorder(Color.RED,2));
	
		secondImageHolder = new JLabel();
		secondImageHolder.setPreferredSize(new Dimension(200, 300));
		secondImageHolder.setBorder(BorderFactory.createLineBorder(Color.RED,2));

		fullDeckImage = new ImageIcon(new ImageIcon(Constants.fullDeckCardsPath).getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT));		
		fullDeckDisplayHolder = new JLabel(fullDeckImage);
				
		topPanel.add(firstImageHolder);
		topPanel.add(secondImageHolder);
		topPanel.add(fullDeckDisplayHolder);
		frame.add(topPanel);
	}
	private void setMiddlePanel() {
		
		midPanel = new JPanel();
		
		JRadioButton button1 = new JRadioButton("Higher Value");
		button1.setSelected(true);
		button1.setActionCommand(Constants.HIGHER);
		JRadioButton button2 = new JRadioButton("Lower Value");
		button2.setActionCommand(Constants.LOWER);
		JRadioButton button3 = new JRadioButton("Same Value");
		button3.setActionCommand(Constants.SAME);
		JRadioButton button4 = new JRadioButton("Same Suit");
		button4.setActionCommand(Constants.SUIT);
		
		button1.setHorizontalAlignment(SwingConstants.CENTER);  
		button2.setHorizontalAlignment(SwingConstants.CENTER);
		button3.setHorizontalAlignment(SwingConstants.CENTER);
		button4.setHorizontalAlignment(SwingConstants.CENTER);
		radioBtnsGroup = new ButtonGroup();
		radioBtnsGroup.add(button1); 
		radioBtnsGroup.add(button2);
		radioBtnsGroup.add(button3);
		radioBtnsGroup.add(button4);
		
		JPanel radiobuttonpanel = new JPanel();
		radiobuttonpanel.setPreferredSize(new Dimension(900, 200));
		BoxLayout radiobuttonpanellayout = new BoxLayout(radiobuttonpanel, BoxLayout.X_AXIS);
		radiobuttonpanel.setLayout(radiobuttonpanellayout);
		radiobuttonpanel.add(button1);
		radiobuttonpanel.add(button2);
		radiobuttonpanel.add(button3);
		radiobuttonpanel.add(button4);
		midPanel.add(radiobuttonpanel);
		
		frame.add(midPanel);
	}
	private void setButtonsPanel() {
		
		btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		 
		JPanel innerHolder = new JPanel();
		
		playBtn = new JButton("Play");
	    playBtn.addActionListener(this);

		playBtn.setPreferredSize(new Dimension(160, 60));
	    
	    playBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

	    innerHolder.add(playBtn);

	    btnPanel.add(innerHolder);
		frame.add(btnPanel);
	}
	private void setBottomPanel() {
		
		bottomPanel = new JPanel();		
		bottomPanel.setPreferredSize(new Dimension(1000, 200));
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		frame.add(bottomPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getActionCommand().equals("Play")){			 
			Card secondCard = fullDeckList.get(GameUtil.getRandomNumber(0, fullDeckList.size()-1));
			secondImageHolder.setText("<html><body style='width:200;text-align:center'>"+ secondCard.getName()+"<br/>"+ secondCard.getValue()+"<br/>"+secondCard.getSuit()+"</body></html>");
			this.game.Play(radioBtnsGroup.getSelection().getActionCommand(),firstRandomCard,secondCard);
			
		}
	}
}

package com;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Game {

	Card 			 firstRandomCard;
	ArrayList<Card>  previousGameResults;
	GUI 			 gui;
	
	public void StartNewGame(){	
		
		gui = GUI.getInstance(this);
		gui.initializeValues(generateRandomCard(),getLastResults(),setNewfullDeckList());
	}
	private Card generateRandomCard(){
		
		List<String> keysAsArray = new ArrayList<String>(Constants.fullDeckKeyValues.keySet());
		String cardName = keysAsArray.get(GameUtil.getRandomNumber(0,keysAsArray.size()-1));
		int cardValue = Constants.fullDeckKeyValues.get(cardName);
		String cardSuit = Constants.suits[GameUtil.getRandomNumber(0, 3)];
		return new Card(cardName,cardValue,cardSuit);
	}
	private ArrayList<String> getLastResults(){
		
		ArrayList<String> result = new ArrayList<>();
	       
		try {
			BufferedReader br = new BufferedReader(new FileReader(Constants.gameLogPath));			
			String previousGameResult;		
			while((previousGameResult = br.readLine())!= null) {	
				result.add(previousGameResult);
			}
			br.close();			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	private ArrayList<Card> setNewfullDeckList() {
		
		ArrayList<Card> tmpList = new ArrayList<Card>();
				
		for (Map.Entry<String, Integer> entry : Constants.fullDeckKeyValues.entrySet()) {
		    String key = entry.getKey();
		    Integer value = entry.getValue();
		    
		    for(int i = 0;i < 4; i++) {
		    	tmpList.add(new Card(key, value, Constants.suits[i]));		    	
		    }
		}
		Collections.shuffle(tmpList);
		return tmpList;		
	}

	public void Play(String actionCommand,Card firstCard, Card secondCard) {
		
		RoundLog rl = getRoundResult(actionCommand,firstCard,secondCard);		
		if(rl == null) return;
		
		 int result = JOptionPane.showConfirmDialog(null, 
	 				(rl.getIsWin())?"You WIN. Try again!":"You Lose, Try Again!", "You Are Rock", 
	                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Constants.crownIconPath));
	 	  
		 if (result == JOptionPane.OK_OPTION){			 		 
	 		 writeGameResult(rl.toString());
	 	   StartNewGame();
	 	 }
		 else if(result == JOptionPane.CANCEL_OPTION) {
		 		 gui.getFrame().dispatchEvent(new WindowEvent(gui.getFrame(), WindowEvent.WINDOW_CLOSING));
	 	 }

	}
	private RoundLog getRoundResult(String actionCommand,Card firstCard, Card secondCard) {
		RoundLog rl = null;
		switch(actionCommand) {
		 case Constants.HIGHER:
			 	 rl = new RoundLog(firstCard,secondCard, actionCommand,(firstCard.getValue()<secondCard.getValue()));
		        break;
		 case Constants.LOWER:
		 	 	 rl = new RoundLog(firstCard,secondCard, actionCommand,(firstCard.getValue()>secondCard.getValue()));
	        break;
		 case Constants.SAME:
	 	 	 	 rl = new RoundLog(firstCard,secondCard, actionCommand,(firstCard.getValue() == secondCard.getValue()));
	 	 	 break;
		 case Constants.SUIT:
	 	 	 	 rl = new RoundLog(firstCard,secondCard, actionCommand,(firstCard.getSuit().equals(secondCard.getSuit())));
	 	 	 break;
		}
		return rl;
		
	}
	private void writeGameResult(String text) {
				
		ArrayList<String> previousResult = getLastResults();		
		previousResult.add(0,text);
		
		if(previousResult.size()>4) {
			previousResult.subList(4, previousResult.size()).clear();
		}
		
		try {			
			BufferedWriter bw = new BufferedWriter(new FileWriter(Constants.gameLogPath));
			while(previousResult.size() > 0 ) {
				bw.write(previousResult.remove(0));
				bw.newLine();	
			}
			bw.close();		
	
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
